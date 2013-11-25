package edu.oregonstate.carto.maptiles;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public abstract class Tile<TileData> {

    /**
     * Internal coordinates addressing the tile.
     */
    private int z, x, y;
    
    /**
     * Tiles are almost always 256px x 256px.
     */
    protected int tileSize = 256;
    
    /**
     * A tile needs to be in a set so it can find its neighbors.
     */
    private TileSet tileSet;

    
    public Tile(TileSet tileSet, int z, int x, int y) {
        this.tileSet = tileSet;
        this.z = z;
        this.x = x;
        this.y = y;
    }

    /**
     * Fetches the tile's data from memory, http, or file.
     * If the tile is an image, the BufferedImage is returned.
     * If the tile is a grid, Grid is returned;
     * @return BufferedImage or Grid
     */
    public abstract TileData fetch();
    
    public abstract Tile getTopLeftTile();

    public abstract Tile getTopTile();

    public abstract Tile getTopRightTile();

    public abstract Tile getLeftTile();

    public abstract Tile getRightTile();

    public abstract Tile getBottomLeftTile();

    public abstract Tile getBottomTile();

    public abstract Tile getBottomRightTile();

    public abstract TileData createMegaTile();

    /**
     * @return the z
     */
    public int getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
}
