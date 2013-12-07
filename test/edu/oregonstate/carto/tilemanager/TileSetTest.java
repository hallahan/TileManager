/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.oregonstate.carto.tilemanager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nick
 */
public class TileSetTest {
   
    TileSet set, bogusSet;
    
    public TileSetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        String httpFormatString = "http://c.tile.openstreetmap.org/{z}/{x}/{y}.png";
        set = new HTTPTileSet(httpFormatString);
        
        String bogusFormatString = "http://c.tile.openstreetmapppppp.org/{z}/{x}/{y}.png";
        bogusSet = new HTTPTileSet(bogusFormatString);
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of getTile method, of class TileSet.
     */
    @Test
    public void testGetTile() {
        System.out.println("===getTile===");
        ImageTile t = (ImageTile) set.getTile(8, 43, 92);
        BufferedImage img = t.fetch();
        int height = img.getHeight();
        String str = img.toString();
        System.out.println(height);
        System.out.println(str);
        
        File f = new File("test/output/testGetTile-8-43-92.png");
        try {
            ImageIO.write(img, "png", f);
            System.out.println("Wrote: test/output/testGetTile-8-43-92.png");
        } catch (IOException ex) {
            Logger.getLogger(TileSetTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetTileBogus() {
        System.out.println("===getTileBogus===");
        ImageTile t = (ImageTile) bogusSet.getTile(8, 43, 92);
        BufferedImage img = t.fetch();
        
        assertEquals(img, null);
    }

    /**
     * Test of getTiles method, of class TileSet.
     */
//    @Test
//    public void testGetTilesChicago() {
//        System.out.println("===getTiles===");
//        double minLat = 41.886866;
//        double minLng = -87.68336;
//        double maxLat = 41.941194;
//        double maxLng = -87.610362;
//        int minZoom = 13;
//        int maxZoom = 15;
//
//        Tile[] result = set.getTiles(minLat, minLng, maxLat, maxLng, minZoom, maxZoom);
//        for (Tile t : result) {
//            BufferedImage img = (BufferedImage) t.fetch();
//            int z = t.getZ();
//            int x = t.getX();
//            int y = t.getY();
//            File f = new File("test/output/testGetTilesChicago-" + z + "-" + x + 
//                    "-" + y + ".png");
//            
//            try {
//                ImageIO.write(img, "png", f);
//                System.out.println("test/output/testGetTilesChicago-" + z + "-" + x + 
//                    "-" + y + ".png");
//            } catch (IOException ex) {
//                Logger.getLogger(TileSetTest.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    
    @Test
    public void testGetMegaTilesChicago() {
        System.out.println("===getTiles===");
        double minLat = 41.886866;
        double minLng = -87.68336;
        double maxLat = 41.941194;
        double maxLng = -87.610362;
        int minZoom = 13;
        int maxZoom = 15;

        Tile[] result = set.getTiles(minLat, minLng, maxLat, maxLng, minZoom, maxZoom);
        for (Tile t : result) {
            ImageTile imgTile = (ImageTile) t;
            BufferedImage img = (BufferedImage)t.createMegaTile();
            int z = t.getZ();
            int x = t.getX();
            int y = t.getY();
            File f = new File("test/output/testGetMegaTilesChicago-" + z + "-" + x + 
                    "-" + y + ".png");
            
            try {
                ImageIO.write(img, "png", f);
                System.out.println("test/output/testGetMegaTilesChicago-" + z + "-" + x + 
                    "-" + y + ".png");
            } catch (IOException ex) {
                Logger.getLogger(TileSetTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    public class TileSetImpl extends TileSet {

        public URL urlForZXY(int z, int x, int y) {
            return null;
        }
    }

 
}