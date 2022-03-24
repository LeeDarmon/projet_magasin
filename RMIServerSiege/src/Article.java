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
    private int price;

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price : the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
