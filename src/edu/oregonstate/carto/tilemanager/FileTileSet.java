package edu.oregonstate.carto.tilemanager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public class FileTileSet extends TileSet {

    private final String rootDirectory;

    public FileTileSet(String rootDirectory) {
        File dir = new File(rootDirectory);
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(rootDirectory + " is not a directory");
        }
        int len = rootDirectory.length();
        char lastChar = rootDirectory.charAt(len - 1);
        if (lastChar == '/' || lastChar == '\\') {
            rootDirectory = rootDirectory.substring(0, len - 1);
        }
        this.rootDirectory = rootDirectory;
    }

    @Override
    public URL urlForZXY(int z, int x, int y) {
        try {
            String urlStr = rootDirectory;

            return new URL(urlStr);
        } catch (MalformedURLException ex) {
            Logger.getLogger(HTTPTileSet.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String getRootDirectory() {
        return rootDirectory;
    }
}
