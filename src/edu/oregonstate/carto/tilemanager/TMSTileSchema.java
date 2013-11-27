package edu.oregonstate.carto.tilemanager;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public class TMSTileSchema extends TileSchema {

    public TMSTileSchema(Cache cache) {
        super(cache);
    }
    
    @Override
    public Tile getTopLeftTile(Tile tile) {
        int x = tile.getX() - 1;
        int y = tile.getY() + 1;
        int z = tile.getZ();
        
        return cache.get(z, x, y);
    }

    @Override
    public Tile getTopTile(Tile tile) {
        int x = tile.getX();
        int y = tile.getY() + 1;
        int z = tile.getZ();
        
        return cache.get(z, x, y);
    }

    @Override
    public Tile getTopRightTile(Tile tile) {
        int x = tile.getX() + 1;
        int y = tile.getY() + 1;
        int z = tile.getZ();
        
        return cache.get(z, x, y);
    }

    @Override
    public Tile getLeftTile(Tile tile) {
        int x = tile.getX() - 1;
        int y = tile.getY();
        int z = tile.getZ();
        
        return cache.get(z, x, y);
    }

    @Override
    public Tile getRightTile(Tile tile) {
        int x = tile.getX() + 1;
        int y = tile.getY();
        int z = tile.getZ();
        
        return cache.get(z, x, y);
    }

    @Override
    public Tile getBottomLeftTile(Tile tile) {
        int x = tile.getX() - 1;
        int y = tile.getY() - 1;
        int z = tile.getZ();
        
        return cache.get(z, x, y);
    }

    @Override
    public Tile getBottomTile(Tile tile) {
        int x = tile.getX();
        int y = tile.getY() - 1;
        int z = tile.getZ();
        
        return cache.get(z, x, y);
    }

    @Override
    public Tile getBottomRightTile(Tile tile) {
        int x = tile.getX() + 1;
        int y = tile.getY() - 1;
        int z = tile.getZ();
        
        return cache.get(z, x, y);
    }
}
