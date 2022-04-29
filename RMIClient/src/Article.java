import java.io.Serializable;

/**
 * 
 */

/**
 * @author Gabriel
 *
 */
public class Article implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private int nb_exemplaire;
    private String reference;
    private String famille;
    private int prix_unitaire;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getNb_exemplaire() {
        return nb_exemplaire;
    }
    public void setNb_exemplaire(int nb_exemplaire) {
        this.nb_exemplaire = nb_exemplaire;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public String getFamille() {
        return famille;
    }
    public void setFamille(String famille) {
        this.famille = famille;
    }
    public int getPrix_unitaire() {
        return prix_unitaire;
    }
    public void setPrix_unitaire(int prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }


    
}
