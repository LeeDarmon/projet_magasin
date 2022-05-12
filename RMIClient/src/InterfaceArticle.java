import java.rmi.Remote;
import java.util.List;

public interface InterfaceArticle extends Remote {  

    public List<Article> getArticleFamille(String famille) throws Exception;
    public Article getArticle(String reference) throws Exception;
}
