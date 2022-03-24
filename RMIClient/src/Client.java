import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
 
public class Client {
    
    private Client(){}

public static void main(String[] args) throws Exception {  
   try { 
       
      // Récupérer le registre
       Registry reg = LocateRegistry.getRegistry(1974);
 
      // Recherche dans le registre de l'objet distant
      InterfaceArticle stub = (InterfaceArticle) reg.lookup("RemoteInter"); 
 
      // Appel de la méthode distante à l'aide de l'objet obtenu
      List<Article> liste = stub.getArticle(); 
      
      for (Article p : liste) {
         System.out.println("ID: " + p.getId());
         System.out.println("Price: " + p.getPrice()); 
      }
   } catch (Exception e) {
      System.err.println(e.toString()); 
   } 
} 
}