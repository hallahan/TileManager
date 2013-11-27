package edu.oregonstate.carto.tilemanager;

import java.net.URL;

/**
 * Java is not letting me have abstract static methods, so I am faking it. The
 * subclass will override these static methods.
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public abstract class TileSchema {

    TileSet tileSet;
    
    public TileSchema(TileSet tileSet) {
        this.tileSet = tileSet;
    }
    
    public abstract URL getTopLeftTile(Tile tile);
    public abstract URL getTopTile(Tile tile);
    public abstract URL getTopRightTile(Tile tile);
    public abstract URL getLeftTile(Tile tile);
    public abstract URL getRightTile(Tile tile);
    public abstract URL getBottomLeftTile(Tile tile);
    public abstract URL getBottomTile(Tile tile);
    public abstract URL getBottomRightTile(Tile tile);
    
}
