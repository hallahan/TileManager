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
        Long key = getKey(tile.getZ(), tile.getX(), tile.getY());
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
    
    public Tile get(int z, int x, int y) {
        Long key = getKey(z, x, y);
        return map.get(key);
    }
    
    
    /**
     * When referencing a tile from a Cache, the tile must be content
     * addressable based on the z, x, y coordinates. This is achieved by
     * packing these values into a Long. This method is guaranteed
     * to return a unique value for all z, x, y values within the following
     * range:
     * 
     *      z: 1 - 127         (1 byte or 8 bits) Zoom is at least 1.
     *      x: 0 - 134217727   (7 nibbles or 28 bits)
     *      Y: 0 - 134217727   (7 nibbles or 28 bits)
     * 
     * All of this results in 64 bits, the size of a long integer.
     * 
     * @return hash key as a packed long integer
     */
    public Long getKey(int z, int x, int y) {
        long zL = (long) z;
        long xL = (long) x;
        long yL = (long) y;
        
        Long key = new Long( yL | (xL << 28) | (zL << 56) );
        
        return key;
    }
    
    
    
    
    /**
     * This extracts the z, x, y values from a hash key. 
     * This is a long integer that has the z, x, y values bit
     * packed into it.
     * 
     * @param hashKey
     * @return [z, x, y] array
     */
    public static int[] getZXYFromKey(Long key) {
        long k = key.longValue();
        int z = (int) ((k >> 56) & 0xFFFFFFF); // 7 Fs means we have 7 nibbles
        int x = (int) ((k >> 28) & 0xFFFFFFF);
        int y = (int) (k & 0xFFFFFFF);
        
        int[] zxy = {z, x, y};
        return zxy;
    }
}
