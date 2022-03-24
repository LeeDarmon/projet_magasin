import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * 
 */

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
    
    public void Deserialize() {
//        String inputString = "[{\"id\":1,\"name\":\"name1\"},{\"id\":2,\"name\":\"name2\"}]";
//        List<Commande> inputList = Arrays.asList(new Commande(1, "name1"), new Commande(2, "name2"));
//
//        Type listOfMyClassObject = new TypeToken<ArrayList<MyClass>>() {}.getType();
//
//        Gson gson = new Gson();
//        List<Commande> outputList = gson.fromJson(inputString, listOfMyClassObject);
//
//        assertEquals(inputList, outputList);
    }
}