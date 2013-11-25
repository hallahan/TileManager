/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
     * Patterns used for replacing z, x, y formatting tokens
     * to create a valid URL for a given tile.
     */
    private final Pattern Z_TOKEN = Pattern.compile("\\{z\\}"); // \\ is special esc char for regex
    private final Pattern X_TOKEN = Pattern.compile("\\{x\\}");
    private final Pattern Y_TOKEN = Pattern.compile("\\{y\\}");
    
    /**
     * Format strings for fetching tiles should follow the following format:
     * http://tile.openstreetmap.org/{z}/{x}/{y}.png
     */
    private String httpFormatString;
    
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
        Matcher xMatch = X_TOKEN.matcher(httpFormatString);
        Matcher yMatch = Y_TOKEN.matcher(httpFormatString);
        
        String url = zMatch.replaceAll(String.valueOf(z));
        url = xMatch.replaceAll(String.valueOf(x));
        url = yMatch.replaceAll(String.valueOf(y));
        
        return url;
    }
}
