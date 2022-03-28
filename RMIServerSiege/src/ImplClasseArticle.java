import java.sql.*; 
import java.util.*;  

// Impl�menter l'interface de l'objet distante
public class ImplClasseArticle implements InterfaceArticle {  
   
   // Impl�menter la m�thode de l'interface
   public List<Article> getArticle() throws Exception 
   {  
      List<Article> liste = new ArrayList<Article>();   
        
      Connection conn = null; 
      Statement stmt = null;  
      
      //Enregistrer le pilote JDBC
      Class.forName("com.mysql.cj.jdbc.Driver");   
      
      //Ouvrez une connexion
      System.out.println("Connexion � la base de donn�es s�lectionn�e..."); 
      conn = DriverManager.getConnection("jdbc:mysql://localhost/my_db", "root", ""); 
      System.out.println("Base de donn�es connect�e avec succ�s...");  
      
      //Ex�cuter la requ�te
      System.out.println("Cr�er l'objet Statement..."); 
      
      stmt = conn.createStatement();  
      String sql = "SELECT * FROM article"; 
      ResultSet res = stmt.executeQuery(sql);  
      
      //Extraire des donn�es de ResultSet
      while(res.next()) {
         // R�cup�rer par nom de colonne
         int id = res.getInt("id"); 
         int price = res.getInt("price"); 
         
         // D�finir les valeurs
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