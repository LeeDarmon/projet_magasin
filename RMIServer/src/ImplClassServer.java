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
import java.util.List;

public class ImplClassServer extends UnicastRemoteObject implements InterfaceArticle, InterfaceCommande, Serializable {

    protected ImplClassServer() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }


    @Override
    public Boolean BuyArticle(Commande c) throws Exception {
        String ref;
        Connection conn = null;
        Statement stmtSelect = null;
        
        //Enregistrer le pilote JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");   
        
        //Ouvrez une connexion
        System.out.println("Connexion à la base de données sélectionnée..."); 
        conn = DriverManager.getConnection("jdbc:mysql://localhost/magasin", "root", ""); 
        System.out.println("Base de données connectée avec succès...");  
        
        //Exécuter la requête
        System.out.println("Créer l'objet Statement...");   
        stmtSelect = conn.createStatement();  
        for(int i = 0; i < c.getListArticle().size(); i++) {
            List<Article> a = c.getListArticle();
            ref = a.get(i).getReference();


            String sql = "SELECT * FROM article WHERE reference = " + ref; 
            ResultSet res = stmtSelect.executeQuery(sql);
            int stock = res.getInt("nb_exemplaire"); 
            
            if(stock <= 0) {
                System.out.println("Plus de stock pour " + ref);
                break;
            }
            
            String sq = "UPDATE article set nb_exemplaire=? where reference=?"; 
            
            try (
                PreparedStatement stmt = conn.prepareStatement(sq);) {
              stmt.setString(2, ref);
              stmt.setInt(1, stock - 1);
              stmt.executeUpdate();
              
              System.out.println("Article updated successfully ");
            } catch (SQLException e) {
              e.printStackTrace();
              return false;
            }
            
        }
        
        //On serialise l'objet et stocke le gson correspondant 
        c.Serialize();
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("insert into commande (ticket_de_caisse)" +
                               "values("+ c.getTicket() +")");
          } catch (SQLException e) {
            e.printStackTrace();
          }
        
        return true;
        
    }


    @Override
    public List<Article> getArticle() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Article> getArticle(int famille) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Article getArticleStock(Article article) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Article> getArticleInformations(Article article) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Article> getArticleFamille(Article article) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addArticleStock(Article articleCible, int nbExemplaire) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Article> getArticleByID(int id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Article getArticle(String reference) throws Exception {
        Article article = new Article();
        
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
        String sql = "SELECT * FROM article WHERE reference = '" + reference + "'"; 
        ResultSet res = stmt.executeQuery(sql);  
        
        //Extraire des données de ResultSet
        while(res.next()) {
           // Récupérer par nom de colonne
           int id = res.getInt("id"); 
           int price = res.getInt("prix_unitaire"); 
           
           // Définir les valeurs
           Article p = new Article(); 
           p.setId(id);
           p.setPrix_unitaire(price);  
        }
        res.close(); 
        return article;     
    }

    @Override
    public void UpdateArticle(String reference, int price) throws Exception {
        // TODO Auto-generated method stub
        
    }

    //Method from interfaceCommande
    @Override
    public Article addArticle(Article art) throws Exception {
        return art;
    }

    @Override
    public Commande getFacture(Commande c) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
