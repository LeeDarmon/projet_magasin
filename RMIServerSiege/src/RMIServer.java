/**
 * 
 */

/*
 * Classe abstraite utilise pour recuperer l'url d'un magasin distant.
 */
public abstract class RMIServer {
    public RMIServer(String url) {
        this.urlServer = url;
    }

    String urlServer;
    
}
