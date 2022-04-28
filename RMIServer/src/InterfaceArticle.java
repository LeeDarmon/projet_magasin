import java.rmi.Remote;
import java.util.List;

public interface InterfaceArticle extends Remote {  
    public List<Article> getArticle() throws Exception;
    public List<Article> getArticleByID(int id) throws Exception;
    public List<Article> getArticle(int famille) throws Exception;
    public Article getArticle(String reference) throws Exception;
    public Article getArticleStock(Article article) throws Exception;
    public List<Article> getArticleInformations(Article article) throws Exception;
    public List<Article> getArticleFamille(Article article) throws Exception;
    public void UpdateArticle(String reference, int price) throws Exception;
    public void addArticleStock(Article articleCible, int nbExemplaire) throws Exception;
    
}
