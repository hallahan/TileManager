package edu.oregonstate.carto.tilemanager;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public abstract class Tile<TileData> {

    /**
     * Internal coordinates addressing the tile.
     */
    private final int z, x, y;
    
    /**
     * z, x, y packed into a Long. This functions as a content
     * addressable, unique value used to identify a tile based on the
     * z, x, y address space.
     */
    private Long key;
    
    /**
     * Tiles are always 256px x 256px.
     */
    protected final int tileSize = 256;
    
    /**
     * A tile needs to be in a set so it can find its neighbors.
     */
    protected final TileSet tileSet;

    
    public Tile(TileSet tileSet, int z, int x, int y) {
        this.tileSet = tileSet;
        this.z = z;
        this.x = x;
        this.y = y;
    }

    /**
     * Fetches the tile's data from memory, http, or file.
     * If the tile is an image, the BufferedImage is returned.
     * If the tile is a grid, Grid is returned;
     * @return BufferedImage or Grid
     */
    public abstract TileData fetch();
    
    /**
     * 
     * @return a BufferedImage or Grid that is 3x3 tiles
     */
    public abstract TileData createMegaTile();
    
    public Tile getTopLeftTile() {
//        return tileSet.getTopLeftTile(this);
        return null;
    }

    public Tile getTopTile() {
        return null;
    }

    public Tile getTopRightTile() {
        return null;
    }

    public Tile getLeftTile() {
        return null;
    }

    public Tile getRightTile() {
        return null;
    }

    public Tile getBottomLeftTile() {
        return null;
    }

    public Tile getBottomTile() {
        return null;
    }

    public Tile getBottomRightTile() {
        return null;
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
    public Long getKey() {
        if (key != null) return key;
        
        long zL = (long) this.z;
        long xL = (long) this.x;
        long yL = (long) this.y;
        
        key = new Long( yL | (xL << 28) | (zL << 56) );
        
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
    
    
    /**
     * @return the z
     */
    public int getZ() {
        return z;
    }


    /**
     * @return the x
     */
    public int getX() {
        return x;
    }


    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

}
