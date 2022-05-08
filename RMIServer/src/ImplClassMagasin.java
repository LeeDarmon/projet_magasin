import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.*; 
import java.util.*;  

public class ImplClassMagasin extends RMIServer implements InterfaceMagasin {
    
    @Override
    public int getChiffreAffaire(String date) throws Exception {
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
        String sq = "SELECT * from commande where date_emission="+ "STR_TO_DATE(" + "'"+ date +"',\"%Y-%m-%d\")";

        ResultSet res = stmt.executeQuery(sq);
        int result = 0;
        while(res.next()) {
            result = result + res.getInt("prix_total");
        }        
        return result; 
    }

    @Override
    public void addStock(String reference, int stock) throws Exception { 
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
        String sq = "UPDATE article set stock = stock + ? where reference=?"; 
            try (
                PreparedStatement stmtUpdate = conn.prepareStatement(sq);) {
                stmtUpdate.setInt(1, stock);
                stmtUpdate.setString(2, reference);
                stmtUpdate.executeUpdate();
              
              System.out.println("Article updated successfully ");
            } catch (SQLException e) {
              e.printStackTrace();
            }  
        
    }

    @Override
    public void updateAllArticles() throws Exception{
        List<Article> liste = new ArrayList<Article>();   
        
        Map<String, Integer> newPrices = new HashMap();
        Connection connCentral = null, conn = null; 
        Statement stmt = null;  
        
        //Enregistrer le pilote JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");   
        
        //Ouvrez une connexion
        System.out.println("Connexion à la base de données sélectionnée..."); 
        conn = DriverManager.getConnection("jdbc:mysql://localhost/magasin", "root", ""); 
        System.out.println("Base de données connectée avec succès...");  
        
        //Exécuter la requête
        System.out.println("Créer l'objet Statement..."); 
        //Ouvrez une connexion
        System.out.println("Connexion à la base de données sélectionnée..."); 
        connCentral = DriverManager.getConnection("jdbc:mysql://localhost/central", "root", ""); 
        System.out.println("Base de données connectée avec succès...");  
        
        //Exécuter la requête
        System.out.println("Créer l'objet Statement..."); 
        
        stmt = connCentral.createStatement();  
        String sqselect = "select * from article";
        ResultSet rs = stmt.executeQuery(sqselect);
        
        while(rs.next()) {
            int prix = rs.getInt("prix_unitaire");
            String ref = rs.getString("reference");
            prix = prix;
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

    @Override
    public int getChiffreAffaire(String date, List<Commande> c) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

}
