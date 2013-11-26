package edu.oregonstate.carto.tilemanager;

/**
 * Java is not letting me have abstract static methods, so I am faking it. The
 * subclass will override these static methods.
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public abstract class TileSchema {

    public abstract TileCoord getTopLeftTile(int z, int x, int y);
    public abstract TileCoord getTopTile(int z, int x, int y);
    public abstract TileCoord getTopRightTile(int z, int x, int y);
    public abstract TileCoord getLeftTile(int z, int x, int y);
    public abstract TileCoord getRightTile(int z, int x, int y);
    public abstract TileCoord getBottomLeftTile(int z, int x, int y);
    public abstract TileCoord getBottomTile(int z, int x, int y);
    public abstract TileCoord getBottomRightTile(int z, int x, int y);
    
    class TileCoord {
        public int z;
        public int x;
        public int y;
    }
}
