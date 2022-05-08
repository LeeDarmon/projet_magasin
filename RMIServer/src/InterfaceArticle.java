import java.rmi.Remote;
import java.util.List;

public interface InterfaceArticle extends Remote {  
    public List<Article> getArticle() throws Exception;
    public List<Article> getArticleFamille(String famille) throws Exception;
    public Article getArticle(String reference) throws Exception;
    public Article getArticleStock(Article article) throws Exception;
    public List<Article> getArticleInformations(Article article) throws Exception;
    public void UpdateArticle(String reference, int price) throws Exception;
}
