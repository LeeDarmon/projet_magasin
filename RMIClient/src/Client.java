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
      InterfaceArticle stubArticle = (InterfaceArticle) reg.lookup("RemoteInter"); 
      InterfaceCommande stubCommande = (InterfaceCommande) reg.lookup("RemoteCommande"); 
      Commande c = new Commande();
      
      // Appel de la méthode distante à l'aide de l'objet obtenu
      stubCommande.addArticle(stubArticle.getArticle("NomArticle"));
      
      
   } catch (Exception e) {
     System.err.println(e.toString()); 
   } 
} 
}