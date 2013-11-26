package edu.oregonstate.carto.tilemanager;

import java.net.URL;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public abstract class TileSet {

    private TileSchema tileSchema;
    
    /**
     * Almost all tile sets on the internet use Google Tile schema,
     * including OpenStreetMap, MapQuest, MapBox, and Esri.
     */
    public TileSet() {
        this(new GoogleTileSchema());
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
        this.tileSchema = schema;
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
    
}
