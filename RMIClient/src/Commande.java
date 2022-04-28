import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/**
 * @author Gabriel
 *
 */
public class Commande implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Override
    public String toString() {
        return "Commande [listArticle=" + listArticle + ", date_emission=" + date_emission + ", prixtotal=" + prixtotal
                + ", ticket=" + ticket + "]";
    }

    private List<Article> listArticle;
    private Date date_emission;
    private int prixtotal;
    private String ticket;
    
    
    public Commande(List<Article> listArticle, Date date_emission, int prixtotal, String ticket) {
        super();
        this.listArticle = listArticle;
        this.date_emission = date_emission;
        this.prixtotal = prixtotal;
        this.ticket = ticket;
    }
    
    public Commande() {
        super();
        this.listArticle = new ArrayList<Article>();
        this.date_emission = null;
        this.prixtotal = 0;
        this.ticket = null;
    }
    
    public Date getDate_emission() {
        return date_emission;
    }

    public void setDate_emission(Date date_emission) {
        this.date_emission = date_emission;
    }

    public int getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(int prixtotal) {
        this.prixtotal = prixtotal;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public void addArticle(Article article) {
        this.listArticle.add(article);
    }
    
    public void removeArticle(Article article) {
        this.listArticle.remove(article);
    }

}
