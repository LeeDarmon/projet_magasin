import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
 
public class MagasinServer extends UnicastRemoteObject {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Registry registry = null;
    private ImplClassServer implcs = null;
    private ImplClassMagasin implcm = null;
    private InterfaceArticle implMagasinSiege = null;
    
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
        this.getImplcm().updateAllArticles(this.implMagasinSiege.getAllArticle());
    }
    
    public void Fetch() throws RemoteException {
        
    }
    
    public void Stop() throws NoSuchObjectException {
        System.out.println("stopping rmi server.");
        UnicastRemoteObject.unexportObject(registry, true);
        System.exit(0);
    }
    
    
    public void initialize() throws Exception, AccessException, RemoteException, AlreadyBoundException {
        // crée l'objet distant
        ImplClassServer obj = new ImplClassServer(); 
        ImplClassMagasin ob = new ImplClassMagasin("jdbc:mysql://localhost/magasin");
        implcs = obj;
        implcm = ob;
        
        // ici, nous exportons l'objet distant vers le stub
        registry.rebind("RemoteInterMagasin", obj);  

        registry.rebind("RemoteInterMagasinSiege", (InterfaceMagasin) ob); 
        
        //On recupere le lien avec le siege 
        Registry reg = LocateRegistry.getRegistry(1974);
        System.out.print(reg);
       // Recherche dans le registre de l'objet distant
        this.implMagasinSiege = (InterfaceArticle) reg.lookup("RemoteInter"); 
        
        System.out.println("Le Serveur magasin est prêt..."); 
    }

public static void main(String[] args) throws Exception {  
    
    }

}
