package edu.oregonstate.carto.tilemanager;

import java.net.URL;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public abstract class TileSet {

    /**
     * Tiles in the tile set can only be one type of tile. When constructing
     * tiles, we need to know which type of tile to construct.
     */
    public enum TileType {
        IMAGE, GRID
    }
    /**
     * The constructor sets the type of tiles we will have in the set. This
     * helps us decide what type of tile to construct.
     */
    private final TileType tileType;
    /**
     * The constructor also sets the schema. once this is set, the TileSchema
     * methods will compute the correct tile relative to a given input tile.
     */
    private TileSchema tileSchema;
    /**
     * The cache is a content addressable object that will return a given tile
     * if it already has been created.
     */
    private Cache cache = Cache.getInstance();

    /**
     * Almost all tile sets on the internet use Google Tile schema, including
     * OpenStreetMap, MapQuest, MapBox, and Esri. We also have the tile type be
     * an image by default.
     */
    public TileSet() {
        tileSchema = new GoogleTileSchema();
        tileType = TileType.IMAGE;
    }

    /**
     * You can specify a TMS tile schema by passing in a TMSTileSchema object.
     * For example:
     *
     * new TileSet(new TMSTileSchema());
     *
     * The tile type is image by default.
     *
     * @param schema
     */
    public TileSet(TileSchema schema) {
        tileSchema = schema;
        tileType = TileType.IMAGE;
    }

    /**
     * You can specify the type of tile: IMAGE or GRID. This constructor sets
     * the schema to GoogleTile by default.
     *
     * @param type
     */
    public TileSet(TileType type) {
        tileSchema = new GoogleTileSchema();
        tileType = type;
    }

    /**
     * This constructor explicitly sets both the type and schema.
     *
     * @param schema
     * @param type
     */
    public TileSet(TileSchema schema, TileType type) {
        tileSchema = schema;
        tileType = type;
    }

    /**
     * Constructs the URL corresponding to a given tile.
     *
     * @param tile
     * @return URL of a tile.
     */
    public URL urlForTile(Tile tile) {
        int z = tile.getZ();
        int x = tile.getX();
        int y = tile.getY();

        return urlForZXY(z, x, y);
    }

    /**
     * Returns a properly formatted URL for tile coordinates (z, x, y).
     *
     * @param z
     * @param x
     * @param y
     * @return URL
     */
    public abstract URL urlForZXY(int z, int x, int y);

    public TileSchema getTileSchema() {
        return tileSchema;
    }

    /**
     * This creates a new tile and puts it in the cache.
     *
     * @param z coordinate
     * @param x coordinate
     * @param y coordinate
     * @return the new tile
     */
    private Tile createTile(int z, int x, int y) {
        if (tileType == TileType.GRID) {
            return new GridTile(this, z, x, y);
        }
        return new ImageTile(this, z, x, y);
    }

    /**
     * Gets the tile with the corresponding coordinates from the cache. If not,
     * a new tile is created.
     *
     * @param coord
     * @return the tile we are looking for
     */
    private Tile retrieveTile(TileCoord coord) {
        URL url = urlForZXY(coord.Z, coord.X, coord.Y);
        Tile t = cache.get(url);
        if (t == null) {
            t = createTile(coord.Z, coord.X, coord.Y);
            cache.put(url, t);
        }
        return t;
    }

    public Tile getTopLeftTile(Tile tile) {
        TileCoord coord = tileSchema.getTopLeftTile(tile);
        return retrieveTile(coord);
    }

    public Tile getTopTile(Tile tile) {
        TileCoord coord = tileSchema.getTopTile(tile);
        return retrieveTile(coord);
    }

    public Tile getTopRightTile(Tile tile) {
        TileCoord coord = tileSchema.getTopRightTile(tile);
        return retrieveTile(coord);
    }

    public Tile getLeftTile(Tile tile) {
        TileCoord coord = tileSchema.getLeftTile(tile);
        return retrieveTile(coord);
    }

    public Tile getRightTile(Tile tile) {
        TileCoord coord = tileSchema.getRightTile(tile);
        return retrieveTile(coord);
    }

    public Tile getBottomLeftTile(Tile tile) {
        TileCoord coord = tileSchema.getBottomLeftTile(tile);
        return retrieveTile(coord);
    }

    public Tile getBottomTile(Tile tile) {
        TileCoord coord = tileSchema.getBottomTile(tile);
        return retrieveTile(coord);
    }

    public Tile getBottomRightTile(Tile tile) {
        TileCoord coord = tileSchema.getBottomRightTile(tile);
        return retrieveTile(coord);
    }
}
