import java.rmi.Remote;
import java.util.List;

public interface RemoteInter extends Remote {  
    public List<Article> getArticle() throws Exception;
}
