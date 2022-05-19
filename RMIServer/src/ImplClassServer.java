import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImplClassServer extends UnicastRemoteObject implements InterfaceArticle, InterfaceCommande, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected ImplClassServer() throws RemoteException {
        super();
    }


    @Override
    public Boolean BuyArticle(Commande c) throws Exception {
        String ref = null;
        int stock = 0;
        Connection conn = null;
        
        //Enregistrer le pilote JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");   
        
        //Ouvrez une connexion
        System.out.println("Connexion à la base de données sélectionnée..."); 
        conn = DriverManager.getConnection("jdbc:mysql://localhost/magasin", "root", ""); 
        System.out.println("Base de données connectée avec succès...");  
        
        //Exécuter la requête
        System.out.println("Créer l'objet Statement...");   
        if(c.getListArticle().size() > 0) {
            
        for(int i = 0; i < c.getListArticle().size(); i++) {
            List<Article> a = c.getListArticle();
            ref = a.get(i).getReference();
            String sql = "SELECT * FROM article WHERE reference=?";         
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ref);
            ResultSet res = stmt.executeQuery();
            if(res.next()) {
                stock = res.getInt("nb_exemplaire");    
            }
            
            if(stock <= 0) {
                System.out.println("Plus de stock pour " + ref);
                break;
            }
            
            String sq = "UPDATE article set nb_exemplaire=? where reference=?"; 
            
            try (
                PreparedStatement stmts = conn.prepareStatement(sq);) {
              stmts.setString(2, ref);
              stmts.setInt(1, stock - 1);
              stmts.executeUpdate();
              
              System.out.println("Article updated successfully ");
            } catch (SQLException e) {
              e.printStackTrace();
              return false;
            }
            
        }
        //On prepare la facture 
        c.setDate_emission();
        c.setNumticket();
        c.resolvePrice();
        //On serialise l'objet et stocke le gson correspondant 
        c.Serialize();
        System.out.println("insert into commande (date_emission, methode_paiement, numticket, prix_total, ticket_de_caisse)" +
                "values("
                + c.Date_emissionToString() + 
                ", '" + c.getMethode_paiement() +
                "', '" + c.getNumticket() + 
                "', " + c.getPrixtotal() +
                ", '"+  c.getTicket() +"')");
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("insert into commande (date_emission, methode_paiement, numticket, prix_total, ticket_de_caisse)" +
                               "values("
                               + "STR_TO_DATE(" + "'"+ c.Date_emissionToString() +"',\"%d-%m-%Y\")"+ 
                               ", '" + c.getMethode_paiement() +
                               "', '" + c.getNumticket() + 
                               "', " + c.getPrixtotal() +
                               ", '"+  c.getTicket() +"')");
          } catch (SQLException e) {
            e.printStackTrace();
          }
        
        return true;

        } else {
            return false;
        }
    }

    @Override
    public List<Article> getArticleFamille(String famille) throws Exception {
        famille = famille.replaceAll("[^a-zA-Z0-9]", " ");
        List<Article> listeArticle = new ArrayList<Article>();
        Connection conn = null;
        Statement stmtSelect = null;
        
        //Enregistrer le pilote JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");   
        
        //Ouvrez une connexion
        System.out.println("Connexion à la base de données sélectionnée..."); 
        conn = DriverManager.getConnection("jdbc:mysql://localhost/magasin", "root", ""); 
        System.out.println("Base de données connectée avec succès...");  

        stmtSelect = conn.createStatement();  
        String sql = "SELECT * FROM article WHERE famille = '" + famille + "' AND nb_exemplaire > 0"; 
        ResultSet res = stmtSelect.executeQuery(sql);
        
        while(res.next()) {
            Article a = new Article();
            a.setFamille(famille);
            a.setNb_exemplaire(res.getInt("nb_exemplaire"));
            a.setReference(res.getString("reference"));
            a.setPrix_unitaire(res.getInt("prix_unitaire"));
            listeArticle.add(a);
        }
        
        return listeArticle;
    }
    
    @Override
    public Article getArticle(String reference) throws Exception {
        reference = reference.replaceAll("[^a-zA-Z0-9]", " ");
        Article article = new Article();

        String sql = "SELECT * FROM article WHERE reference=? AND nb_exemplaire > 0"; 
        Connection conn = null; 
        
        //Enregistrer le pilote JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");   
        
        //Ouvrez une connexion
        System.out.println("Connexion à la base de données sélectionnée..."); 
        conn = DriverManager.getConnection("jdbc:mysql://localhost/magasin", "root", ""); 
        System.out.println("Base de données connectée avec succès...");  

        PreparedStatement stmt = conn.prepareStatement(sql);  
        //Exécuter la requête
        System.out.println("Créer l'objet Statement..."); 
        

        stmt.setString(1, reference);
        ResultSet res = stmt.executeQuery();
        
        //Extraire des données de ResultSet
        while(res.next()) {
           // Récupérer par nom de colonne
           int id = res.getInt("id"); 
           int price = res.getInt("prix_unitaire"); 
           // Définir les valeurs
           article.setId(id);
           article.setReference(res.getString("reference"));
           article.setNb_exemplaire(res.getInt("prix_unitaire"));
           article.setFamille(res.getString("famille"));
           article.setPrix_unitaire(price);  
        }
        res.close(); 
        
        return article;     
    }
    
    
    //Method from interfaceCommande
    @Override
    public Article addArticle(Article art) throws Exception {
        return art;
    }

    @Override
    public Commande getFacture(String numticket) throws Exception {
        Commande c = new Commande();
        Connection conn = null; 
        Statement stmt = null;  
        
      //Enregistrer le pilote JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");   
        
        //Ouvrez une connexion
        System.out.println("Connexion à la base de données sélectionnée..."); 
        conn = DriverManager.getConnection("jdbc:mysql://localhost/magasin", "root", ""); 
        System.out.println("Base de données connectée avec succès...");  
        
        //Exécuter la requête
        System.out.println("Créer l'objet Statement..."); 
        
        stmt = conn.createStatement();  
        String sql = "SELECT * FROM commande WHERE numticket = '" + numticket + "'"; 
        ResultSet res = stmt.executeQuery(sql);  
        
        if(res != null) {
            res.next();
           /* c.setDate_emission(res.getDate("date_emission"));
            c.setNumticket(res.getString("numticket"));
            c.setPrixtotal(res.getInt("prix_total"));   
            c.setTicket(res.getString("ticket_de_caisse"));
            */
            c.setTicket(res.getString("ticket_de_caisse"));
            c = c.Deserialize();
            return c; 
        } else {
            return null;
        }
    }


    @Override
    public List<Article> getAllArticle() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


}
