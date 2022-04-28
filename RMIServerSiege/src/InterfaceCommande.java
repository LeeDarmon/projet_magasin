import java.rmi.Remote;
import java.util.List;

public interface InterfaceCommande extends Remote {  
    public Commande BuyArticle() throws Exception;
    public List<Commande> getFacture() throws Exception;
}
