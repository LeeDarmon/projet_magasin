import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
/**
 * 
 */
import com.google.gson.reflect.TypeToken;

/**
 * Classe correspondant a l'objet Commande dans la bdd.
 * Modelise la facture d'un achat
 */
public class Commande implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private List<Article> listArticle;
    private Date date_emission;
    private int prixtotal;
    private String methode_paiement; 
    private String numticket;
    /*
     * Correspond au tableau json de cette objet.
     * Garde le string pour permettre la serialisation/deserialisation de la commande.
     */
    private String ticket;
    

    
    @Override
    public String toString() {

        String articles = "";
        for(int i = 0; i < this.listArticle.size(); i++) {
           articles = "\n" + articles + this.listArticle.get(i).getReference();
           System.out.println(this.listArticle.get(i).getReference());
        }
        return "Facture\n\nArticles:" + articles 
                + "\n\ndate d'emission : " + date_emission 
                + "\nprix total=" + prixtotal;
    }
    
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
    
    public List<Article> getListArticle() {
        return listArticle;
    }

    public void setListArticle(List<Article> listArticle) {
        this.listArticle = listArticle;
    }

    public String getNumticket() {
        return numticket;
    }

    public void setNumticket(String numticket) {
        this.numticket = numticket;
    }

    public String getMethode_paiement() {
        return methode_paiement;
    }

    public void setMethode_paiement(String methode_paiement) {
        this.methode_paiement = methode_paiement;
    }

    public Date getDate_emission() {
        return date_emission;
    }
    
    public String Date_emissionToString() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
        Date date = new Date();  
        return formatter.format(date);  

    }

    public void setDate_emission(Date date_emission) {
        this.date_emission = date_emission;
    }
    
    public void setDate_emission() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
        Date date = new Date();  
        System.out.println(formatter.format(date));  


        this.date_emission = date;
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

    public void resolvePrice() {
        int somme = 0;
        for(int i = 0; i < listArticle.size(); i++) {
            somme += listArticle.get(i).getPrix_unitaire();
        }
        this.setPrixtotal(somme);
    }
    
    public void Serialize() {
        Gson g = new Gson();
        String jsonString = g.toJson(this);
        this.ticket = jsonString;
    }
    
    public Commande Deserialize() {
        Commande targetObject = new Gson().fromJson(this.ticket, Commande.class);
        return targetObject;
    }

    public void setNumticket() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
        this.numticket = generatedString;
        
    }


}
