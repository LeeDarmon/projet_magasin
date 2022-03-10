import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
 
public class AdditionClient {
    public static void main (String[] args) {
        AdditionInterface hello;
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);
            
            hello = (AdditionInterface)registry.lookup("a");
            int result=hello.add(9,10);
            
            System.out.println("Result is :"+result);
 
            }catch (Exception e) {
                System.out.println("HelloClient exception: " + e);
                }
        }
}