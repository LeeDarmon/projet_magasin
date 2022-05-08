import java.sql.*; 
import java.util.*;  

// Implémenter l'interface de l'objet distante
public class ImplClasseSiege implements InterfaceArticle, InterfaceCommande {  
   
    //Partie Commande 

    //Recupere les factures du serveur magasin et les insert dans le serveur central 
    @Override
    public List<Commande> getFacture() throws Exception {
        List<Commande> factures = new ArrayList<Commande>();
        Connection connMagasin = null; 
        Connection connCentral = null; 
        Statement stmt = null;  
        Statement stmtCentral = null;  

        
        //Enregistrer le pilote JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");   
        
        //Ouvrez une connexion
        System.out.println("Connexion à la base de données sélectionnée..."); 
        connMagasin = DriverManager.getConnection("jdbc:mysql://localhost/magasin", "root", ""); 
        connCentral = DriverManager.getConnection("jdbc:mysql://localhost/central", "root", ""); 
        System.out.println("Base de données connectée avec succès...");  
        
        //Exécuter la requête
        System.out.println("Créer l'objet Statement..."); 
        
        stmt = connMagasin.createStatement();  
        String sql = "SELECT * FROM commande"; 
        ResultSet res = stmt.executeQuery(sql);  

        
        stmtCentral = connCentral.createStatement();  
        String sq = "SELECT * FROM commande"; 
        stmtCentral.executeQuery(sq);  
        
        //Extraire des données de ResultSet
        while(res.next()) {
           String tic = res.getString("ticket_de_caisse");
           Commande c = new Commande();
           c = c.Deserialize(tic);
           c.setTicket(tic);
           try (Statement stmtc = connCentral.createStatement()) {
               stmtc.executeUpdate("insert into commande (date_emission, methode_paiement, numticket, prix_total, ticket_de_caisse)" +
                                  "values("
                                  + "STR_TO_DATE(" + "'"+ c.Date_emissionToString() +"',\"%d-%m-%Y\")"+ 
                                  ", '" + c.getMethode_paiement() +
                                  "', '" + c.getNumticket() + 
                                  "', " + c.getPrixtotal() +
                                  ", '"+  c.getTicket() +"')");
        }
        }
        res.close();
        return factures; 
    }

    @Override
    public Commande BuyArticle() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    
   // Implémenter la méthode de l'interface
   public List<Article> getArticle() throws Exception 
   {  
      List<Article> liste = new ArrayList<Article>();   
        
      Connection conn = null; 
      Statement stmt = null;  
      
      //Enregistrer le pilote JDBC
      Class.forName("com.mysql.cj.jdbc.Driver");   
      
      //Ouvrez une connexion
      System.out.println("Connexion à la base de données sélectionnée..."); 
      conn = DriverManager.getConnection("jdbc:mysql://localhost/central", "root", ""); 
      System.out.println("Base de données connectée avec succès...");  
      
      //Exécuter la requête
      System.out.println("Créer l'objet Statement..."); 
      
      stmt = conn.createStatement();  
      String sql = "SELECT * FROM article"; 
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
         liste.add(p); 
      }
      res.close(); 
      return liste;     
   }

@Override
public List<Article> getArticle(int reference) throws Exception {
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
public void AddArticle(Article article) throws Exception {
    // TODO Auto-generated method stub
    
}

@Override
public void addArticleStock(Article articleCible, int nbExemplaire) throws Exception {
    // TODO Auto-generated method stub
    
}


@Override
public void UpdateArticle(String reference, int price) throws Exception {
    Connection connCentral = null; 

    
    //Enregistrer le pilote JDBC
    Class.forName("com.mysql.cj.jdbc.Driver");   
    
    //Ouvrez une connexion
    System.out.println("Connexion à la base de données sélectionnée..."); 
    connCentral = DriverManager.getConnection("jdbc:mysql://localhost/central", "root", ""); 
    System.out.println("Base de données connectée avec succès...");  
    
    //Exécuter la requête
    System.out.println("Créer l'objet Statement..."); 
    String sq = "UPDATE article set price=? where reference=?"; 

    try (
        PreparedStatement stmt = connCentral.prepareStatement(sq);) {
      stmt.setString(2, reference);
      stmt.setInt(1, price);
      stmt.executeUpdate();
      
      System.out.println("Article updated successfully ");
    } catch (SQLException e) {
      e.printStackTrace();
    }
}

@Override
public List<Article> getArticleByID(int id) throws Exception {
    // TODO Auto-generated method stub
    return null;
}

@Override
public Article getArticle(String reference) throws Exception {
    // TODO Auto-generated method stub
    return null;
}  

public void UpdateAllArticles(Map<String, Integer> newPrices) throws Exception {
    List<Article> liste = new ArrayList<Article>();   
    
    Connection conn = null; 
    Statement stmt = null;  
    
    //Enregistrer le pilote JDBC
    Class.forName("com.mysql.cj.jdbc.Driver");   
    
    //Ouvrez une connexion
    System.out.println("Connexion à la base de données sélectionnée..."); 
    conn = DriverManager.getConnection("jdbc:mysql://localhost/central", "root", ""); 
    System.out.println("Base de données connectée avec succès...");  
    
    //Exécuter la requête
    System.out.println("Créer l'objet Statement..."); 
    
    stmt = conn.createStatement();  
    String sq = "UPDATE article set price=? where reference=?"; 
    
    for (Map.Entry<String, Integer> entry : newPrices.entrySet()) {
        String reference = entry.getKey();
        int price = entry.getValue();
        
        try (
            PreparedStatement stmtUpdate = conn.prepareStatement(sq);) {
            stmtUpdate.setString(2, reference);
            stmtUpdate.setInt(1, price);
            stmtUpdate.executeUpdate();
          
          System.out.println("Article updated successfully ");
        } catch (SQLException e) {
          e.printStackTrace();
        }  
    }  
}

public void UpdateAllArticles() throws Exception {
    Map<String, Integer> newPrices = new HashMap<String, Integer>();
    List<Article> liste = new ArrayList<Article>();   
    
    Connection conn = null; 
    Statement stmt = null;  
    
    //Enregistrer le pilote JDBC
    Class.forName("com.mysql.cj.jdbc.Driver");   
    
    //Ouvrez une connexion
    System.out.println("Connexion à la base de données sélectionnée..."); 
    conn = DriverManager.getConnection("jdbc:mysql://localhost/central", "root", ""); 
    System.out.println("Base de données connectée avec succès...");  
    
    //Exécuter la requête
    System.out.println("Créer l'objet Statement..."); 
    
    stmt = conn.createStatement();  
    String sqselect = "select * from article";
    ResultSet rs = stmt.executeQuery(sqselect);
    
    while(rs.next()) {
        int prix = rs.getInt("prix_unitaire");
        String ref = rs.getString("reference");
        prix = prix * 2;
        newPrices.put(ref, prix);
    }
    
    stmt = conn.createStatement();  
    String sq = "UPDATE article set prix_unitaire=? where reference=?"; 
    
    for (Map.Entry<String, Integer> entry : newPrices.entrySet()) {
        String reference = entry.getKey();
        int price = entry.getValue();
        
        try (
            PreparedStatement stmtUpdate = conn.prepareStatement(sq);) {
            stmtUpdate.setString(2, reference);
            stmtUpdate.setInt(1, price);
            stmtUpdate.executeUpdate();
          
          System.out.println("Article updated successfully ");
        } catch (SQLException e) {
          e.printStackTrace();
        }  
    }  
}
}