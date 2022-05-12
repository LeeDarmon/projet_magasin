import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SiegeServer {
    public final static int RMI_PORT = 1974;
    private Registry registry = null;
    private ImplClasseSiege implcs;
    private List<InterfaceMagasin> implmag;
    
    public SiegeServer() throws RemoteException {
        System.setProperty("java.rmi.server.hostname","127.0.1.1");
        registry = LocateRegistry.createRegistry(RMI_PORT);
    }
    
    
    public List<InterfaceMagasin> getImplmag() {
        return implmag;
    }


    public void setImplmag(List<InterfaceMagasin> implmag) {
        this.implmag = implmag;
    }


    public ImplClasseSiege getImplcs() {
        return implcs;
    }


    public void setImplcs(ImplClasseSiege implcs) {
        this.implcs = implcs;
    }

    private void startServerRun() {
        final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC+2"));
        ZonedDateTime nextUpdateRun = now.withHour(18).withMinute(0).withSecond(0);
        ZonedDateTime nextFetchRun = now.withHour(6).withMinute(0).withSecond(0);

        // If midnight is in the past, add one day
        if (now.compareTo(nextUpdateRun) > 0) {
            nextUpdateRun = nextUpdateRun.plusDays(1);
        }
        
        if (now.compareTo(nextFetchRun) > 0) {
            nextFetchRun = nextFetchRun.plusDays(1);
        }
        
        // Get duration between now and next run
        final Duration initialUpdateDelay = Duration.between(now, nextUpdateRun);
        final Duration initialFetchDelay = Duration.between(now, nextFetchRun);
        
        // Schedule a task to run at midnight and then every day
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
                try {
                    this.update();
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        },
                initialUpdateDelay.toMillis(),
                Duration.ofDays(1).toMillis(),
                TimeUnit.MILLISECONDS);
        
        scheduler.scheduleAtFixedRate(() -> {
                try {
                    this.fetch();
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        },
                initialFetchDelay.toMillis(),
                Duration.ofDays(1).toMillis(),
                TimeUnit.MILLISECONDS);
    }
    
    public boolean fetch() throws RemoteException {
        try {
            for(int i = 0; i < implmag.size(); i++) {
                this.getImplcs().getFacture(implmag.get(i).getUrl());   
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update() throws RemoteException {
        try {
            this.getImplcs().UpdateAllArticles();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;  
        }      
    }
    
    
    public void stop() throws NoSuchObjectException {
        System.out.println("stopping rmi server.");
        UnicastRemoteObject.unexportObject(registry, true);
        System.exit(0);
    }
    
    public void initialize() throws AccessException, RemoteException, AlreadyBoundException {
        // crée l'objet distant
        ImplClasseSiege obj = new ImplClasseSiege(); 
        implcs = obj;
        implmag = new ArrayList<InterfaceMagasin>();
        // ici, nous exportons l'objet distant vers le stub
        InterfaceArticle stub = (InterfaceArticle) UnicastRemoteObject.exportObject(obj, 0); 
        registry.rebind("RemoteInter", stub);  
        
        //Commence les tâches automatiques du serveur 
        this.startServerRun();
        System.out.println("Le Serveur est prêt..."); 
    }

    public boolean connect() throws AccessException, RemoteException, NotBoundException, AlreadyBoundException {

        // Récupérer le registre
         Registry reg = LocateRegistry.getRegistry(1975);
   
        // Recherche dans le registre de l'objet distant
         //Pour prendre en compte plusieurs magasins possibles 
        InterfaceMagasin obj = (InterfaceMagasin) reg.lookup("RemoteInterMagasinSiege"); 
        return this.implmag.add(obj);
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
