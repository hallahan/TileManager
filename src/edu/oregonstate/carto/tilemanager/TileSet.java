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
        tileSchema = new GoogleTileSchema(cache);
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
     * @return
     */
    public abstract URL urlForTile(Tile tile);
    
    
    public TileSchema getTileSchema() {
        return tileSchema;
    }
    
    
    public Tile getTopLeftTile(Tile tile) {
        return tileSchema.getTopLeftTile(tile);
    }

    public Tile getTopTile(Tile tile) {
        return tileSchema.getTopTile(tile);
    }

    public Tile getTopRightTile(Tile tile) {
        return tileSchema.getTopRightTile(tile);
    }

    public Tile getLeftTile(Tile tile) {
        return tileSchema.getLeftTile(tile);
    }

    public Tile getRightTile(Tile tile) {
        return tileSchema.getRightTile(tile);
    }

    public Tile getBottomLeftTile(Tile tile) {
        return tileSchema.getBottomLeftTile(tile);
    }

    public Tile getBottomTile(Tile tile) {
        return tileSchema.getBottomTile(tile);
    }

    public Tile getBottomRightTile(Tile tile) {
        return tileSchema.getBottomRightTile(tile);
    }
    
}
