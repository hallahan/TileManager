package edu.oregonstate.carto.tilemanager;

import java.util.concurrent.ConcurrentHashMap;

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
    
    private ConcurrentHashMap<Long, Tile> map = new ConcurrentHashMap<>();
    
    /**
     * Call this method to get the cache, not the constructor. We only want a
     * single cache to exist for the application.
     * 
     * @return the singleton instance
     */
    public static Cache singleton() {
        if (singleton == null) singleton = new Cache();
        return singleton;
    }
    
    public void put(Tile tile) {
        Long key = tile.getKey();
        map.put(key, tile);
    }
    
    /**
     * Returns a tile if it is in the cache,
     * returns null otherwise.
     * 
     * @param tileKey
     * @return a tile or null
     */
    public Tile get(Long tileKey) {
        return map.get(tileKey);
    }
}
