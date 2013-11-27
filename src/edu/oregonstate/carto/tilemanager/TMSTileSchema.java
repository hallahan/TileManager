package edu.oregonstate.carto.tilemanager;

import java.net.URL;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public class TMSTileSchema extends TileSchema {

    public TMSTileSchema(TileSet tileSet) {
        super(tileSet);
    }
    
    @Override
    public URL getTopLeftTile(Tile tile) {
        int x = tile.getX() - 1;
        int y = tile.getY() + 1;
        int z = tile.getZ();
        
        return tileSet.urlForZXY(z, x, y);
    }

    @Override
    public URL getTopTile(Tile tile) {
        int x = tile.getX();
        int y = tile.getY() + 1;
        int z = tile.getZ();
        
        return tileSet.urlForZXY(z, x, y);
    }

    @Override
    public URL getTopRightTile(Tile tile) {
        int x = tile.getX() + 1;
        int y = tile.getY() + 1;
        int z = tile.getZ();
        
        return tileSet.urlForZXY(z, x, y);
    }

    @Override
    public URL getLeftTile(Tile tile) {
        int x = tile.getX() - 1;
        int y = tile.getY();
        int z = tile.getZ();
        
        return tileSet.urlForZXY(z, x, y);
    }

    @Override
    public URL getRightTile(Tile tile) {
        int x = tile.getX() + 1;
        int y = tile.getY();
        int z = tile.getZ();
        
        return tileSet.urlForZXY(z, x, y);
    }

    @Override
    public URL getBottomLeftTile(Tile tile) {
        int x = tile.getX() - 1;
        int y = tile.getY() - 1;
        int z = tile.getZ();
        
        return tileSet.urlForZXY(z, x, y);
    }

    @Override
    public URL getBottomTile(Tile tile) {
        int x = tile.getX();
        int y = tile.getY() - 1;
        int z = tile.getZ();
        
        return tileSet.urlForZXY(z, x, y);
    }

    @Override
    public URL getBottomRightTile(Tile tile) {
        int x = tile.getX() + 1;
        int y = tile.getY() - 1;
        int z = tile.getZ();
        
        return tileSet.urlForZXY(z, x, y);
    }
}
