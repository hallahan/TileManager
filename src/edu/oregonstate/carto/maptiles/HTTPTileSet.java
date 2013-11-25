package edu.oregonstate.carto.maptiles;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public class HTTPTileSet extends TileSet {

    /**
     * Patterns used for replacing z, x, y formatting tokens to create a valid
     * URL for a given tile.
     */
    private final Pattern Z_TOKEN = Pattern.compile("\\{z\\}"); // \\ is special esc char for regex
    private final Pattern X_TOKEN = Pattern.compile("\\{x\\}");
    private final Pattern Y_TOKEN = Pattern.compile("\\{y\\}");
    
    /**
     * Format strings for fetching tiles should follow the following format:
     * http://tile.openstreetmap.org/{z}/{x}/{y}.png
     */
    private String httpFormatString;


    public HTTPTileSet(String httpFormatString) {
        setHttpFormatString(httpFormatString);
    }

    /**
     * Constructs the URL corresponding to a given tile.
     *
     * @param tile
     * @return String URL of a tile.
     */
    public String urlForTile(Tile tile) {
        int z = tile.getZ();
        int x = tile.getX();
        int y = tile.getY();
        
        Matcher zMatch = Z_TOKEN.matcher(httpFormatString);
        String url = zMatch.replaceAll(String.valueOf(z));
        
        Matcher xMatch = X_TOKEN.matcher(url);
        url = xMatch.replaceAll(String.valueOf(x));
        
        Matcher yMatch = Y_TOKEN.matcher(url);
        url = yMatch.replaceAll(String.valueOf(y));

        return url;
    }

    /**
     * @return the httpFormatString
     */
    public String getHttpFormatString() {
        return httpFormatString;
    }

    /**
     * This sets the format string used to process the
     * URL for a given tile. This method is private, because changing the HTTP
     * format string in media res would put the tile set in an inconsistent
     * state. We would think older tiles came from the newer place when that
     * would not be true.
     *
     * @param httpFormatString the httpFormatString to set
     */
    private void setHttpFormatString(String httpFormatString) {
        this.httpFormatString = httpFormatString;
    }
}
