import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SiegeServer {
    public final static int RMI_PORT = 1974;
    private Registry registry = null;

    public SiegeServer() throws RemoteException {
        System.setProperty("java.rmi.server.hostname","10.25.143.99");
        registry = LocateRegistry.createRegistry(RMI_PORT);
    }
    
    public void stop() throws NoSuchObjectException {
        System.out.println("stopping rmi server.");
        UnicastRemoteObject.unexportObject(registry, true);
        System.exit(0);
    }
    
    public void initialize() throws AccessException, RemoteException, AlreadyBoundException {
        // crée l'objet distant
        ImplClasse obj = new ImplClasse(); 

        // ici, nous exportons l'objet distant vers le stub
        RemoteInter stub = (RemoteInter) UnicastRemoteObject.exportObject(obj, 0); 
        registry.rebind("RemoteInter", stub);  
        System.out.println("Le Serveur est prêt..."); 
        
    }
    public static void main(String args[]) {
    try { 
        SiegeServer ss = new SiegeServer();
        ss.initialize();
     } catch (Exception e) { 
        System.err.println(e.toString()); 
        e.printStackTrace(); 
     }     
    }
}
