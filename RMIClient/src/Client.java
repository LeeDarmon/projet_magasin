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
        commandeActuel.resolvePrix();
        System.out.println(stubArticle.getArticle(reference).getReference());

        System.out.println("Article " + reference + " added.");
    }
    
    public void buyArticle() throws Exception {
        stubCommande.BuyArticle(commandeActuel);
    }
    
public static void main(String[] args) throws Exception {  
    
}


}