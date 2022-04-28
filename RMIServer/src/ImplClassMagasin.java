import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.*; 
import java.util.*;  

public class ImplClassMagasin implements InterfaceMagasin {

    @Override
    public int getChiffreAffaire(List<Commande> c) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int addStock(String reference) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void updateAllArticles(Map<String, Integer> newPrices) throws Exception{
        List<Article> liste = new ArrayList<Article>();   
        
        Connection conn = null; 
        Statement stmt = null;  
        
        //Enregistrer le pilote JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");   
        
        //Ouvrez une connexion
        System.out.println("Connexion � la base de donn�es s�lectionn�e..."); 
        conn = DriverManager.getConnection("jdbc:mysql://localhost/magasin", "root", ""); 
        System.out.println("Base de donn�es connect�e avec succ�s...");  
        
        //Ex�cuter la requ�te
        System.out.println("Cr�er l'objet Statement..."); 
        
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

}
