package edu.oregonstate.carto.tilemanager;

import java.net.URL;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public abstract class TileSet {

    private TileSchema tileSchema;
    
    private Cache cache = Cache.singleton();
    
    /**
     * Almost all tile sets on the internet use Google Tile schema,
     * including OpenStreetMap, MapQuest, MapBox, and Esri.
     */
    public TileSet() {
        tileSchema = new GoogleTileSchema(this);
    }
    
    /**
     * You can specify a TMS tile schema by passing in a TMSTileSchema
     * object. For example:
     * 
     *      new TileSet(new TMSTileSchema());
     * 
     * @param schema 
     */
    public TileSet(TileSchema schema) {
        tileSchema = schema;
    }
    
    /**
     * Returns a properly formatted URL object for a tile. This works for both
     * the file system as well as from HTTP.
     * 
     * @param tile
     * @return URL
     */
    public abstract URL urlForTile(Tile tile);
    
    /**
     * Returns a properly formatted URL for tile coordinates (z, x, y).
     * 
     * @param z
     * @param x
     * @param y
     * @return URL
     */
    public abstract URL urlForZXY(int z, int x, int y);
    
    public TileSchema getTileSchema() {
        return tileSchema;
    }
    
    
    public Tile getTopLeftTile(Tile tile) {
        URL url = tileSchema.getTopLeftTile(tile);
        return cache.get(url);
    }

    public Tile getTopTile(Tile tile) {
        URL url = tileSchema.getTopTile(tile);
        return cache.get(url);
    }

    public Tile getTopRightTile(Tile tile) {
        URL url = tileSchema.getTopRightTile(tile);
        return cache.get(url);
    }

    public Tile getLeftTile(Tile tile) {
        URL url = tileSchema.getLeftTile(tile);
        return cache.get(url);
    }

    public Tile getRightTile(Tile tile) {
        URL url = tileSchema.getRightTile(tile);
        return cache.get(url);
    }

    public Tile getBottomLeftTile(Tile tile) {
        URL url = tileSchema.getBottomLeftTile(tile);
        return cache.get(url);
    }

    public Tile getBottomTile(Tile tile) {
        URL url = tileSchema.getBottomTile(tile);
        return cache.get(url);
    }

    public Tile getBottomRightTile(Tile tile) {
        URL url = tileSchema.getBottomRightTile(tile);
        return cache.get(url);
    }
    
}
