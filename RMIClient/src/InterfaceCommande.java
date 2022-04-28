import java.rmi.Remote;
import java.util.List;

public interface InterfaceCommande extends Remote {      
    public Article addArticle(Article art) throws Exception;
    public Boolean BuyArticle(Commande c) throws Exception;
    public Commande getFacture(Commande c) throws Exception;
}
