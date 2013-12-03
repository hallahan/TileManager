package edu.oregonstate.carto.tilemanager;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public abstract class TileSchema {

    protected static final int TILE_SIZE = 256;
    protected static final double INITIAL_RESOLUTION = 2 * Math.PI * 6378137 / TILE_SIZE;
    protected static final double ORIGIN_SHIFT = 2 * Math.PI * 6378137 / 2.0;

    /**
     * Derived from globalmaptiles.py:
     * http://www.maptiler.org/google-maps-coordinates-tile-bounds-projection/
     *
     * This method is overridden by GoogleTileSchema.java that takes this and
     * converts the y tile to the correct number (coordinate origin is moved
     * from bottom-left to top-left corner of the extent).
     *
     * @param lat
     * @param lng
     * @param zoom
     * @return
     */
    public TileCoord getTileForLatLngZoom(double lat, double lng, int zoom) {
        // convert lat lng to meters
        double xMeters = lng * ORIGIN_SHIFT / 180.0;
        double yMeters = Math.log(Math.tan((90 + lat) * Math.PI / 360.0))
                / (Math.PI / 180.0);
        yMeters = yMeters * ORIGIN_SHIFT / 180.0;

        // resolution of meters/pixel for given zoom level
        double resolution = INITIAL_RESOLUTION / Math.pow(2, zoom);

        // meters to pixels
        double xPixels = (xMeters + ORIGIN_SHIFT) / resolution;
        double yPixels = (yMeters + ORIGIN_SHIFT) / resolution;

        // pixels to tile
        int xTile = (int) (Math.ceil(xPixels / (double) TILE_SIZE) - 1);
        int yTile = (int) (Math.ceil(yPixels / (double) TILE_SIZE) - 1);

        return new TileCoord(zoom, xTile, yTile);
    }

    public TileCoord[] getTilesForBBoxZoom(double minLat, double minLng,
            double maxLat, double maxLng, int zoom) {
        
        TileCoord minCoord = getTileForLatLngZoom(minLat, minLng, zoom);
        TileCoord maxCoord = getTileForLatLngZoom(maxLat, maxLng, zoom);
        
        int minX = minCoord.X;
        int minY = minCoord.Y;
        int maxX = maxCoord.X;
        int maxY = maxCoord.Y;
        
        int difX = maxX - minX;
        int difY = maxY - minY;
        
        TileCoord[] tileCoords = new TileCoord[(difX + 1) * (Math.abs(difY) + 1)];
        
        // TMSTileSchema
        if (difY >= 0) {
           
            int i = 0;
            for(int xIdx=0; xIdx <= difX; ++xIdx) {
                for (int yIdx=0; yIdx <= difY; ++yIdx) {
                    tileCoords[i++] = new TileCoord(zoom, minX+xIdx, minY+yIdx);
                }
            }
        
        // GoogleTileSchema
        } else {
            
            int i = 0;
            for(int xIdx=0; xIdx <= difX; ++xIdx) {
                for (int yIdx=0; yIdx >= difY; --yIdx) {
                    tileCoords[i++] = new TileCoord(zoom, minX+xIdx, minY+yIdx);
                }
            }
        
        }
        
        return tileCoords;
    }

    public abstract TileCoord getTopLeftTile(Tile tile);

    public abstract TileCoord getTopTile(Tile tile);

    public abstract TileCoord getTopRightTile(Tile tile);

    public abstract TileCoord getLeftTile(Tile tile);

    public abstract TileCoord getRightTile(Tile tile);

    public abstract TileCoord getBottomLeftTile(Tile tile);

    public abstract TileCoord getBottomTile(Tile tile);

    public abstract TileCoord getBottomRightTile(Tile tile);
}
