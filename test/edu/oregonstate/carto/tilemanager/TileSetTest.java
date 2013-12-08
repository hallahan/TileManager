/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.oregonstate.carto.tilemanager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
    FileTileSet fileTileSet;

    public TileSetTest() {
        String httpFormatString = "http://c.tile.openstreetmap.org/{z}/{x}/{y}.png";
        set = new HTTPTileSet(httpFormatString);

        String bogusFormatString = "http://c.tile.openstreetmapppppp.org/{z}/{x}/{y}.png";
        bogusSet = new HTTPTileSet(bogusFormatString);

        String fileFormatString = "C:\\Users\\nick\\Documents\\TMS_tiles_MountHood\\buildingMask";
        try {
            fileTileSet = new FileTileSet(fileFormatString, new TMSTileSchema());
        } catch (MalformedURLException ex) {
            Logger.getLogger(TileSetTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTile method, of class TileSet.
     */
    @Test
    public void testGetTile() {
        System.out.println("===testGetTile===");
        ImageTile t = (ImageTile) set.getTile(8, 43, 92);
        BufferedImage img;
        try {
            img = t.fetch();
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
        } catch (IOException ex) {
            System.out.println("Could not fetch tile " + set.urlForTile(t).toString());
            Logger.getLogger(TileSetTest.class.getName()).log(Level.INFO, null, ex);
        }

    }

    @Test
    public void testGetFileTile() {
        System.out.println("===testGetFileTile===");
        ImageTile t = (ImageTile) fileTileSet.getTile(13, 1327, 5255);
        BufferedImage img;
        try {
            img = t.fetch();
            int height = img.getHeight();
            String str = img.toString();
            System.out.println(height);
            System.out.println(str);

            File f = new File("test/output/testGetFileTile-13-1327-5255.png");
            try {
                ImageIO.write(img, "png", f);
                System.out.println("Wrote: test/output/testGetFileTile-13-1327-5255.png");
            } catch (IOException ex) {
                Logger.getLogger(TileSetTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            System.out.println("Could not fetch tile " + fileTileSet.urlForTile(t).toString());
            Logger.getLogger(TileSetTest.class.getName()).log(Level.INFO, null, ex);
        }
        
    }
    
    @Test
    public void testGetFileTileHood() {
        System.out.println("===testGetFileTileHood===");
        double minLat = 45.277017;
        double minLng = -121.832719;
        double maxLat = 45.467799;
        double maxLng = -121.561141;
        int minZoom = 9;
        int maxZoom = 14;

        Tile[] result = fileTileSet.getTiles(minLat, minLng, maxLat, maxLng, minZoom, maxZoom);
        for (Tile t : result) {
            BufferedImage img;
            try {
                img = (BufferedImage) t.fetch();
                int z = t.getZ();
                int x = t.getX();
                int y = t.getY();
                File f = new File("test/output/testGetFileTileHood-" + z + "-" + x + 
                        "-" + y + ".png");

                try {
                    ImageIO.write(img, "png", f);
                    System.out.println("test/output/testGetFileTileHood-" + z + "-" + x + 
                        "-" + y + ".png");
                } catch (IOException ex) {
                    Logger.getLogger(TileSetTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                System.out.println("Could not fetch tile " + fileTileSet.urlForTile(t).toString());
            }
            
        }
    }

    @Test(expected=IOException.class)
    public void testGetFileTileNonexistent() throws IOException {
        System.out.println("===testGetFileTileNonexistent===");
        ImageTile t = (ImageTile) fileTileSet.getTile(13, 1327, 4000);
        BufferedImage img;
        
        img = t.fetch(); // should be thow io exception here
        int height = img.getHeight();
        String str = img.toString();
        System.out.println(height);
        System.out.println(str);

        File f = new File("test/output/testGetFileTileNonexistent-13-1327-4000.png");
        try {
            ImageIO.write(img, "png", f);
            System.out.println("Wrote: test/output/testGetFileTileNonexistent-13-1327-4000.png");
        } catch (IOException ex) {
            Logger.getLogger(TileSetTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    @Test(expected=IOException.class)
    public void testGetTileBogus() throws IOException {
        System.out.println("===getTileBogus===");
        ImageTile t = (ImageTile) bogusSet.getTile(8, 43, 92);
        BufferedImage img = t.fetch();

        assertEquals(img, null);
    }

    /**
     * Test of getTiles method, of class TileSet.
     */
    @Test
    public void testGetTilesChicago() {
        System.out.println("===testGetTilesChicago===");
        double minLat = 41.886866;
        double minLng = -87.68336;
        double maxLat = 41.941194;
        double maxLng = -87.610362;
        int minZoom = 13;
        int maxZoom = 15;

        Tile[] result = set.getTiles(minLat, minLng, maxLat, maxLng, minZoom, maxZoom);
        for (Tile t : result) {
            BufferedImage img;
            try {
                img = (BufferedImage) t.fetch();
                int z = t.getZ();
                int x = t.getX();
                int y = t.getY();
                File f = new File("test/output/testGetTilesChicago-" + z + "-" + x + 
                        "-" + y + ".png");

                try {
                    ImageIO.write(img, "png", f);
                    System.out.println("test/output/testGetTilesChicago-" + z + "-" + x + 
                        "-" + y + ".png");
                } catch (IOException ex) {
                    Logger.getLogger(TileSetTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                System.out.println("Could not fetch tile " + set.urlForTile(t).toString());
                Logger.getLogger(TileSetTest.class.getName()).log(Level.INFO, null, ex);
            }
            
        }
    }
    
    public class ReadWriteChicagoTiles implements Runnable {
        private Tile[] tiles;
        private int idx, endIdx;
        
        public ReadWriteChicagoTiles(Tile[] tiles, int startIdx, int endIdx) {
            this.tiles = tiles;
            this.idx = startIdx;
            this.endIdx = endIdx;
        }
        
        @Override
        public void run() {
            while (idx <= endIdx) {
                Tile t = tiles[idx];
                int z = t.getZ();
                int x = t.getX();
                int y = t.getY();
               
                try {
                    BufferedImage img = (BufferedImage) t.fetch();
                    File f = new File("test/output/testGetTilesChicagoThread-" + z + "-" + x + 
                            "-" + y + ".png");
                    ImageIO.write(img, "png", f);
                    System.out.println("test/output/testGetTilesChicagoThread-" + z + "-" + x + 
                            "-" + y + ".png");
                } catch (IOException ex) {
                    System.out.println("Could not write test/output/testGetTilesChicagoThread-" 
                            + z + "-" + x + "-" + y + ".png");
                }
                ++idx;
            }
        }
    }
    
    @Test
    public void testGetTilesChicagoThread() {
        final int NUM_THREADS = 4;
        
        System.out.println("===testGetTilesChicagoThread===");
        double minLat = 41.886866;
        double minLng = -87.68336;
        double maxLat = 41.941194;
        double maxLng = -87.610362;
        int minZoom = 13;
        int maxZoom = 15;

        Tile[] result = set.getTiles(minLat, minLng, maxLat, maxLng, minZoom, maxZoom);
        int len = result.length;
        int partitionSize = len / NUM_THREADS;
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        Runnable worker;
        
        int i = 0;
        for(; i < NUM_THREADS-1; ++i) {
            worker = new ReadWriteChicagoTiles( result, partitionSize*i, partitionSize*(i+1)-1 );
            pool.execute(worker);
        }
        worker = new ReadWriteChicagoTiles( result, partitionSize*i, len-1 ); // last one may be a bit longer in the array
        pool.execute(worker);
        
        pool.shutdown();
        while (!pool.isTerminated()) {}
        System.out.println("Finished processing testGetTilesChicagoThread");
    }
    
    @Test
    public void testGetMegaTilesChicago() {
        System.out.println("===testGetMegaTilesChicago===");
        double minLat = 41.886866;
        double minLng = -87.68336;
        double maxLat = 41.941194;
        double maxLng = -87.610362;
        int minZoom = 13;
        int maxZoom = 15;

        Tile[] result = set.getTiles(minLat, minLng, maxLat, maxLng, minZoom, maxZoom);
        for (Tile t : result) {
            ImageTile imgTile = (ImageTile) t;
            BufferedImage img;
            try {
                img = (BufferedImage)t.createMegaTile();
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
            } catch (IOException ex) {
                System.out.println("Could not fetch tile " + set.urlForTile(t).toString());
                Logger.getLogger(TileSetTest.class.getName()).log(Level.INFO, null, ex);
            }
        }
    }
    
    public class TileSetImpl extends TileSet {

        public URL urlForZXY(int z, int x, int y) {
            return null;
        }
    }
}