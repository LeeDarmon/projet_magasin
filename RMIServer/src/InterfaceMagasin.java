import java.rmi.Remote;
import java.util.List;
import java.util.Map;

public interface InterfaceMagasin extends Remote {  
    public int getChiffreAffaire(List<Commande> c) throws Exception;
    public int addStock(String reference) throws Exception;
    public void updateAllArticles(Map<String, Integer> newPrices) throws Exception;
}
