import java.util.List;
import java.util.Map;
import java.io.Serializable;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*; 
import java.util.*;  


public class ImplClassMagasin extends UnicastRemoteObject implements InterfaceMagasin, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String url;
    
    public ImplClassMagasin(String url) throws Exception {
        this.url = url;
    }
    @Override
    public int getChiffreAffaire(String date) throws Exception {
        Connection conn = null; 
        Statement stmt = null;  
        int result = 0;
        
        //Enregistrer le pilote JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");   
        
        //Ouvrez une connexion
        System.out.println("Connexion à la base de données sélectionnée..."); 
        conn = DriverManager.getConnection(url, "root", ""); 
        System.out.println("Base de données connectée avec succès...");  
        
        //Exécuter la requête
        System.out.println("Créer l'objet Statement..."); 
        
        stmt = conn.createStatement();  
        String sq = "SELECT * from commande where date_emission="+ "STR_TO_DATE(" + "'"+ date +"',\"%Y-%m-%d\")";

        ResultSet res = stmt.executeQuery(sq);
        while(res.next()) {
            result = result + res.getInt("prix_total");
        }        
        return result; 
    }

    @Override
    public void addStock(String reference, int stock) throws Exception { 
        Connection conn = null; 
        
        //Enregistrer le pilote JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");   
        
        //Ouvrez une connexion
        System.out.println("Connexion à la base de données sélectionnée..."); 
        conn = DriverManager.getConnection(url, "root", ""); 
        System.out.println("Base de données connectée avec succès...");  
        
        //Exécuter la requête
        System.out.println("Créer l'objet Statement..."); 
        
        String sq = "UPDATE article set nb_exemplaire = nb_exemplaire + ? where reference=?"; 
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
    public void updateAllArticles(List<Article> articles) throws Exception{
        Map<String, Integer> newPrices = new HashMap<String, Integer>();
        
        //Enregistrer le pilote JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");   
        
        //Ouvrez une connexion
        System.out.println("Connexion à la base de données sélectionnée..."); 
        Connection conn = DriverManager.getConnection(url, "root", ""); 
        System.out.println("Base de données connectée avec succès...");  
        
        //Exécuter la requête
        System.out.println("Créer l'objet Statement..."); 
        //Ouvrez une connexion
        System.out.println("Connexion au siège"); 
        
        //Exécuter la requête
        System.out.println("Créer l'objet Statement..."); 
        
        for(int i = 0; i < articles.size(); i++) {
            int prix = articles.get(i).getPrix_unitaire();
            String ref = articles.get(i).getReference();
            newPrices.put(ref, prix);
        }
        
        
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
    public String getUrl() throws Exception {
        return this.url;
    }

}
