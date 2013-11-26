package edu.oregonstate.carto.tilemanager;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public class GoogleTileSchema extends TileSchema {

    @Override
    public Tile getTopLeftTileCoord(int x, int y, int z) {
        int x = x - 1;
        int y = y - 1;
        int z = z;
        
        return null;
    }

    @Override
    public Tile getTopTile(Tile tile) {
        int x = tile.getX();
        int y = tile.getY() - 1;
        int z = tile.getZ();
        
        return null;
    }

    @Override
    public Tile getTopRightTile(Tile tile) {
        int x = tile.getX() + 1;
        int y = tile.getY() - 1;
        int z = tile.getZ();
        
        return null;
    }

    @Override
    public Tile getLeftTile(Tile tile) {
        int x = tile.getX() - 1;
        int y = tile.getY();
        int z = tile.getZ();
        
        return null;
    }

    @Override
    public Tile getRightTile(Tile tile) {
        int x = tile.getX() + 1;
        int y = tile.getY();
        int z = tile.getZ();
        
        return null;
    }

    @Override
    public Tile getBottomLeftTile(Tile tile) {
        int x = tile.getX() - 1;
        int y = tile.getY() + 1;
        int z = tile.getZ();
        
        return null;
    }

    @Override
    public Tile getBottomTile(Tile tile) {
        int x = tile.getX();
        int y = tile.getY() + 1;
        int z = tile.getZ();
        
        return null;
    }

    @Override
    public Tile getBottomRightTile(Tile tile) {
        int x = tile.getX() + 1;
        int y = tile.getY() + 1;
        int z = tile.getZ();
        
        return null;
    }
}
