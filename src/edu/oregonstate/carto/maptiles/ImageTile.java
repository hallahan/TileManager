package edu.oregonstate.carto.maptiles;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public class ImageTile extends Tile {
    
    /**
     * The BufferedImage that is the raster data of this tile.
     * This in memory field is populated by fetch()
     */
    private BufferedImage img;
    
    public ImageTile(TileSet tileSet, int z, int x, int y) {
        super(tileSet, z,x,y);
    }

    @Override
    public BufferedImage fetch() {
        if (img == null) {
            URL url = tileSet.urlForTile(this);
            try {
                img = ImageIO.read(url);
            } catch (IOException ex) {
                Logger.getLogger(ImageTile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return img;
    }

    @Override
    public ImageTile getTopLeftTile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageTile getTopTile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageTile getTopRightTile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageTile getLeftTile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageTile getRightTile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageTile getBottomLeftTile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageTile getBottomTile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ImageTile getBottomRightTile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Creates a Buffered image of 9 tiles with this tile being the center.
     * This is needed when a rendering engine needs data of the surrounding
     * neighborhood tiles.
     * 
     * @return A BufferedImage of the 9 tiles.
     */
    @Override
    public BufferedImage createMegaTile() {
        BufferedImage megaTile = new BufferedImage(tileSize * 3, tileSize * 3, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g2d = megaTile.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
        BufferedImage topLeftTile = getTopLeftTile().fetch();
        g2d.drawImage(topLeftTile, 0, 0, null);
        
        BufferedImage topTile = getTopTile().fetch();
        g2d.drawImage(topTile, tileSize, 0, null);
        
        BufferedImage topRightTile = getTopRightTile().fetch();
        g2d.drawImage(topRightTile, tileSize * 2, 0, null);
        
        BufferedImage leftTile = getLeftTile().fetch();
        g2d.drawImage(leftTile, 0, tileSize, null);
        
        // The tile in the center is this tile.
        g2d.drawImage(fetch(), tileSize, tileSize, null);
        
        BufferedImage rightTile = getRightTile().fetch();
        g2d.drawImage(rightTile, tileSize * 2, tileSize, null);
        
        BufferedImage bottomLeftTile = getBottomLeftTile().fetch();
        g2d.drawImage(bottomLeftTile, 0, tileSize * 2, null);
        
        BufferedImage bottomTile = getBottomTile().fetch();
        g2d.drawImage(bottomTile, tileSize, tileSize * 2, null);
        
        BufferedImage bottomRightTile = getBottomTile().fetch();
        g2d.drawImage(bottomRightTile, tileSize * 2, tileSize * 2, null);
        
        return megaTile;
    }
    
}
