package edu.oregonstate.carto.maptiles;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public class HTTPTileSetTest {
    
    public HTTPTileSetTest() {
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
     * Test of urlForTile method, of class HTTPTileSet.
     */
    @Test
    public void testUrlForTile() {
        System.out.println("urlForTile");
        
        HTTPTileSet tileSet = new HTTPTileSet("http://tile.openstreetmap.org/{z}/{x}/{y}.png");
        Tile tile = new ImageTile(tileSet, 8, 42, 95);
        String expResult = "http://tile.openstreetmap.org/8/42/95.png";
        String result = tileSet.urlForTile(tile);
        assertEquals(expResult, result);
    }

    /**
     * Test of getHttpFormatString method, of class HTTPTileSet.
     */
    @Test
    public void testGetHttpFormatString() {
        System.out.println("getHttpFormatString");
        HTTPTileSet instance = new HTTPTileSet("http://tile.openstreetmap.org/{z}/{x}/{y}.png");
        String expResult = "http://tile.openstreetmap.org/{z}/{x}/{y}.png";
        String result = instance.getHttpFormatString();
        assertEquals(expResult, result);
    }
}