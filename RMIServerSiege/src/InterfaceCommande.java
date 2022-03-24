import java.rmi.Remote;
import java.util.List;

public interface InterfaceCommande extends Remote {  
    public List<Article> getArticle() throws Exception;
    public List<Article> getArticle(int reference) throws Exception;
    public Article getArticleStock(Article article) throws Exception;
    public List<Article> getArticleInformations(Article article) throws Exception;
    public List<Article> getArticleFamille(Article article) throws Exception;
    public void AddArticle(Article article) throws Exception;
    public void addArticleStock(Article articleCible, int nbExemplaire) throws Exception;
    public Commande BuyArticle() throws Exception;
    public Commande getFacture() throws Exception;
}
