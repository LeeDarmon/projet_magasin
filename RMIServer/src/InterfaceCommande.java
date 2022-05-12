import java.rmi.Remote;

public interface InterfaceCommande extends Remote {      
    public Article addArticle(Article art) throws Exception;
    public Boolean BuyArticle(Commande c) throws Exception;
    public Commande getFacture(String numticket) throws Exception;
}
