package edu.oregonstate.carto.tilemanager;

import java.io.IOException;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public abstract class Tile<TileData> {

    /**
     * Internal coordinates addressing the tile.
     */
    private final int z, x, y;
    
    
    /**
     * Tiles are always 256px x 256px.
     */
    protected final int TILE_SIZE = 256;
    
    protected int megaTileSize = TILE_SIZE * 3;
    
    /**
     * A tile needs to be in a set so it can find its neighbors.
     */
    protected final TileSet tileSet;

    
    public Tile(TileSet tileSet, int z, int x, int y) {
        this.tileSet = tileSet;
        this.z = z;
        this.x = x;
        this.y = y;
    }
    
    public Tile(TileSet tileSet, TileCoord coord) {
        this(tileSet, coord.Z, coord.X, coord.Y);
    }

    /**
     * Fetches the tile's data from memory, http, or file.
     * If the tile is an image, the BufferedImage is returned.
     * If the tile is a grid, Grid is returned;
     * @return BufferedImage or Grid
     */
    public abstract TileData fetch() throws IOException;
    
    /**
     * 
     * @return a BufferedImage or Grid that is 3x3 tiles
     */
    public abstract TileData createMegaTile() throws IOException;
    
    public Tile getTopLeftTile() {
        return tileSet.getTopLeftTile(this);
    }

    public Tile getTopTile() {
        return tileSet.getTopTile(this);
    }

    public Tile getTopRightTile() {
        return tileSet.getTopRightTile(this);
    }

    public Tile getLeftTile() {
        return tileSet.getLeftTile(this);
    }

    public Tile getRightTile() {
        return tileSet.getRightTile(this);
    }

    public Tile getBottomLeftTile() {
        return tileSet.getBottomLeftTile(this);
    }

    public Tile getBottomTile() {
        return tileSet.getBottomTile(this);
    }

    public Tile getBottomRightTile() {
        return tileSet.getBottomRightTile(this);
    }


    /**
     * @return the z
     */
    public int getZ() {
        return z;
    }


    /**
     * @return the x
     */
    public int getX() {
        return x;
    }


    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @return the megaTileSize
     */
    public int getMegaTileSize() {
        return megaTileSize;
    }

    /**
     * @param megaTileSize the megaTileSize to set
     */
    public void setMegaTileSize(int pixels) {
        this.megaTileSize = pixels;
    }

}
