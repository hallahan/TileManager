/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.oregonstate.carto.tilemanager;

import java.awt.image.BufferedImage;
import java.net.URL;
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
        System.out.println("getTile");
        ImageTile t = (ImageTile) set.getTile(8, 43, 92);
        BufferedImage img = t.fetch();
        int height = img.getHeight();
        String str = img.toString();
        System.out.println(height);
        System.out.println(str);
    }
    
    @Test
    public void testGetTileBogus() {
        System.out.println("getTileBogus");
        ImageTile t = (ImageTile) set.getTile(8, 43, 92);
        BufferedImage img = t.fetch();
        int height = img.getHeight();
        String str = img.toString();
        System.out.println(height);
        System.out.println(str);
    }

//    /**
//     * Test of getTiles method, of class TileSet.
//     */
//    @Test
//    public void testGetTiles() {
//        System.out.println("getTiles");
//        int minLat = 0;
//        int minLng = 0;
//        int maxLat = 0;
//        int maxLng = 0;
//        int minZoom = 0;
//        int maxZoom = 0;
//        TileSet instance = new TileSetImpl();
//        Tile[] expResult = null;
//        Tile[] result = instance.getTiles(minLat, minLng, maxLat, maxLng, minZoom, maxZoom);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//
//    public class TileSetImpl extends TileSet {
//
//        public URL urlForZXY(int z, int x, int y) {
//            return null;
//        }
//    }

 
}