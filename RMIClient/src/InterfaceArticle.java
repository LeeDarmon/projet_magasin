import java.rmi.Remote;
import java.util.List;

public interface InterfaceArticle extends Remote {  
    public List<Article> getArticle() throws Exception;
}
