import java.io.Serializable;
import java.lang.reflect.Type;
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
    private List<Article> listArticle;
    private Date date_emission;
    private int prixtotal;
    private String ticket;
    

}
