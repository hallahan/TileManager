package edu.oregonstate.carto.tilemanager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public class Cache {

    /* This is the one instance that exists throughout the entire life-cycle
     * of the program.  The can be retrieved at any time using the static
     * singleton method.  This is to be used to prevent many instances of
     * the same cache hanging around.
     */
    private static Cache singleton = null;
    
    private ConcurrentHashMap<String, Tile> map = new ConcurrentHashMap<>();
    
    private Cache() {}
    
    /**
     * Call this method to get the cache, not the constructor. We only want a
     * single cache to exist for the application.
     * 
     * @return the singleton instance
     */
    public static Cache getInstance() {
        if (singleton == null) singleton = new Cache();
        return singleton;
    }
    
    public void put(URL url, Tile tile) {
        String urlStr = url.toString();
        map.put(urlStr, tile);
    }
    
    /**
     * Returns a tile if it is in the cache,
     * returns null otherwise.
     * 
     * @param url (the key)
     * @return a tile or null
     */
    public Tile get(URL url) {
        Tile tile = map.get(url.toString());
        return tile;
    }
    
    public Tile get(String urlStr) {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Cache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return get(url);
    }
    
}
