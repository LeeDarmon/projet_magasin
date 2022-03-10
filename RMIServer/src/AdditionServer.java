import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;   
 
public class AdditionServer {
       public static void main (String[] argv) {
           try {
               
               LocateRegistry.createRegistry(9100);
               System.setProperty("java.rmi.server.hostname", "127.0.0.1");
               Addition a1 = new Addition();                  
               Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);
               registry.rebind("a", a1);
               System.out.println("Addition Server is ready.");
               
               }catch (Exception e) {
                   System.out.println("Addition Server failed: " + e);
                }
           }
}