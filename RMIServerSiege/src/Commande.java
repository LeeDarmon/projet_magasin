import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
/**
 * 
 */
import com.google.gson.reflect.TypeToken;

/**
 * @author Gabriel
 *
 */
public class Commande implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<Article> listArticle;
    private Date date_emission;
    private int prixtotal;
    @Expose (serialize = false, deserialize = false)
    private String ticket;
    
    /**
     * @return articles dans la facture
     */
    public List<Article> getListArticle() {
        return listArticle;
    }

    /**
     * @param prixtotal : the listArticle to set
     */
    public void setListArticle(List<Article> article) {
        this.listArticle = article;
    }
    
    public void addArticle(Article article) {
        this.listArticle.add(article);
    }
    
    /**
     * @return the prixtotal
     */
    public int getPrixtotal() {
        return prixtotal;
    }

    /**
     * @param prixtotal : the prixtotal to set
     */
    private void setPrixtotal(int price) {
        this.prixtotal = price;
    }
    
    /**
     * @return the date_emission
     */
    public Date getDate_emission() {
        return date_emission;
    }

    /**
     * @param date_emission the date_emission to set
     */
    public void setDate_emission(Date date_emission) {
        this.date_emission = date_emission;
    }    
    public void resolvePrice() {
        int somme = 0;
        for(int i = 0; i < listArticle.size(); i++) {
            somme += listArticle.get(i).getPrice();
        }
        this.setPrixtotal(somme);
    }
    
    public void Serialize() {
        Gson g = new Gson();
        String jsonString = g.toJson(this);
        this.ticket = jsonString;
    }
    
    public Commande Deserialize(String tic) {
        Commande targetObject = new Gson().fromJson(tic, Commande.class);
        return targetObject;
    }


}
