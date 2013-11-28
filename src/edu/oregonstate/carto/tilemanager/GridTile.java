package edu.oregonstate.carto.tilemanager;

import edu.oregonstate.carto.importer.BinaryGridReader;
import edu.oregonstate.carto.tilemanager.util.Grid;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public class GridTile extends Tile {

    /**
     * This is the DEM grid data of this tile. This in-memory
     * field is populated by fetch.
     */
    private Grid grid;
    
    
    public GridTile(TileSet tileSet, int z, int x, int y) {
        super(tileSet, z, x, y);
    }

    
    @Override
    public Grid fetch() {
        if (grid == null) {
            URL url = tileSet.urlForTile(this);
            // FIXME TODO resolve this exception properly
            // FIXME TODO We must modify BinaryGridReader so that it reads from
            // a URL rather than a file path.
//            try {
//                grid = BinaryGridReader.read(url);
//            } catch (IOException ex) {
//                Logger.getLogger(ImageTile.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        return grid;
    }

    @Override
    public Object createMegaTile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
