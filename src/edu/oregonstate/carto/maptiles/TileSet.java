package edu.oregonstate.carto.maptiles;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public abstract class TileSet<TileData> {

    public abstract URL urlForTile(Tile tile);
    
}
