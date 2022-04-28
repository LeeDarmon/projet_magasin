import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
 
public class Client  extends UnicastRemoteObject {
    public InterfaceArticle stubArticle;
    public InterfaceCommande stubCommande;
    public Commande commandeActuel;
    
    public Client() throws Exception{
    }
    
    public void initialize() throws Exception {
        // Récupérer le registre
           Registry reg = LocateRegistry.getRegistry(1975);
           System.out.print(reg);
          // Recherche dans le registre de l'objet distant
          stubArticle = (InterfaceArticle) reg.lookup("RemoteInterMagasin"); 
          stubCommande = (InterfaceCommande) reg.lookup("RemoteInterMagasin"); 
          commandeActuel = new Commande();
    }

    public void addArticle(String reference) throws Exception {
        commandeActuel.addArticle(stubCommande.addArticle(stubArticle.getArticle(reference)));
        System.out.println("Article" + reference + "added.");
    }
    
public static void main(String[] args) throws Exception {  
   try { 
       
      // Récupérer le registre
       Registry reg = LocateRegistry.getRegistry(1975);
 
      // Recherche dans le registre de l'objet distant
      InterfaceArticle stubArticle = (InterfaceArticle) reg.lookup("RemoteInter"); 
      InterfaceCommande stubCommande = (InterfaceCommande) reg.lookup("RemoteInter"); 
      Commande c = new Commande();
      
      // Appel de la méthode distante à l'aide de l'objet obtenu
      c.addArticle(stubCommande.addArticle(stubArticle.getArticle("Ballon")));
      c.addArticle(stubCommande.addArticle(stubArticle.getArticle("Terreau")));
      c.removeArticle(stubArticle.getArticle("Terreau"));
   
      stubCommande.BuyArticle(c);
      stubCommande.getFacture(c);
   } catch (Exception e) {
     System.err.println(e.toString()); 
   } 
} 
}