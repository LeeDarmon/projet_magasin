import java.rmi.Remote;
import java.util.List;
import java.util.Map;

public interface InterfaceMagasin extends Remote {  
    public int getChiffreAffaire(String date) throws Exception;
    public String getUrl() throws Exception;
    public void addStock(String reference, int stock) throws Exception;
    public void updateAllArticles(List<Article> article) throws Exception;
}
