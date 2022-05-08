import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
 
public class MagasinServer {
    private Registry registry = null;
    private ImplClassServer implcs = null;
    private ImplClassMagasin implcm = null;
    public MagasinServer() throws RemoteException {
        System.setProperty("java.rmi.server.hostname","127.0.1.1");
        registry = LocateRegistry.createRegistry(1975);
    }
    
    public ImplClassServer getImplcs() {
        return implcs;
    }

    public void setImplcs(ImplClassServer implcs) {
        this.implcs = implcs;
    }

    public ImplClassMagasin getImplcm() {
        return implcm;
    }

    public void setImplcm(ImplClassMagasin implcm) {
        this.implcm = implcm;
    }

    public void Update() throws Exception {
        this.getImplcm().updateAllArticles();
    }
    
    public void Fetch() throws RemoteException {
        
    }
    
    public void Stop() throws NoSuchObjectException {
        System.out.println("stopping rmi server.");
        UnicastRemoteObject.unexportObject(registry, true);
        System.exit(0);
    }
    
    
    public void initialize() throws AccessException, RemoteException, AlreadyBoundException {
        // crée l'objet distant
        ImplClassServer obj = new ImplClassServer(); 
        ImplClassMagasin ob = new ImplClassMagasin();
        implcs = obj;
        implcm = ob;
        
        // ici, nous exportons l'objet distant vers le stub
        registry.rebind("RemoteInterMagasin", obj);  
        System.out.println("Le Serveur magasin est prêt..."); 
    }

public static void main(String[] args) throws Exception {  
    
    //Serveur siege
    Registry reg = LocateRegistry.getRegistry(1974);
    
    // Recherche dans le registre de l'objet distant
    InterfaceArticle stubArticle = (InterfaceArticle) reg.lookup("RemoteInter"); 
    InterfaceCommande stubCommande = (InterfaceCommande) reg.lookup("RemoteInter"); 
    
    //Mise en marche serveur magasin
        MagasinServer ms = new MagasinServer();
        //ms.initialize();
        //ms.Update();
    }

}
