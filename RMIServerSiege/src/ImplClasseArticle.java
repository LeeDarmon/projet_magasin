import java.sql.*; 
import java.util.*;  

// Implémenter l'interface de l'objet distante
public class ImplClasseArticle implements InterfaceArticle {  
   
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
      conn = DriverManager.getConnection("jdbc:mysql://localhost/my_db", "root", ""); 
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
         int price = res.getInt("price"); 
         
         // Définir les valeurs
         Article p = new Article(); 
         p.setId(id);
         p.setPrice(price); 
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
}