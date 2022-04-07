import java.rmi.Remote;
import java.util.List;

public interface InterfaceCommande extends Remote {      
    public void addArticle(Article art) throws Exception;
    public Commande BuyArticle() throws Exception;
    public Commande getFacture() throws Exception;
}
