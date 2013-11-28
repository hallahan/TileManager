package edu.oregonstate.carto.tilemanager;

/**
 * Java is not letting me have abstract static methods, so I am faking it. The
 * subclass will override these static methods.
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public abstract class TileSchema {
    
    public abstract TileCoord getTopLeftTile(Tile tile);
    public abstract TileCoord getTopTile(Tile tile);
    public abstract TileCoord getTopRightTile(Tile tile);
    public abstract TileCoord getLeftTile(Tile tile);
    public abstract TileCoord getRightTile(Tile tile);
    public abstract TileCoord getBottomLeftTile(Tile tile);
    public abstract TileCoord getBottomTile(Tile tile);
    public abstract TileCoord getBottomRightTile(Tile tile);
    
}
