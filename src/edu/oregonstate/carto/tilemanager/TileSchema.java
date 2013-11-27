package edu.oregonstate.carto.tilemanager;

/**
 * Java is not letting me have abstract static methods, so I am faking it. The
 * subclass will override these static methods.
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public abstract class TileSchema {

    Cache cache;
    
    public TileSchema(Cache cache) {
        this.cache = cache;
    }
    
    public abstract Tile getTopLeftTile(Tile tile);
    public abstract Tile getTopTile(Tile tile);
    public abstract Tile getTopRightTile(Tile tile);
    public abstract Tile getLeftTile(Tile tile);
    public abstract Tile getRightTile(Tile tile);
    public abstract Tile getBottomLeftTile(Tile tile);
    public abstract Tile getBottomTile(Tile tile);
    public abstract Tile getBottomRightTile(Tile tile);
    
}
