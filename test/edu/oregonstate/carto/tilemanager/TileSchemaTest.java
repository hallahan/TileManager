package edu.oregonstate.carto.tilemanager;

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
public class TileSchemaTest {
    
    public TileSchemaTest() {
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
     * Test of getTileForLatLngZoom method, of class TileSchema.
     */
    @Test
    public void testGetTileForLatLngZoom() {
        System.out.println("getTileForLatLngZoom");
        double lat = 56.053635;
        double lng = -112.167868;
        int zoom = 9;
        TileSchema instance = new TileSchemaImpl();
        
        int expZ = 9;
        int expX = 96;
        int expY = 352;
        
        TileCoord result = instance.getTileForLatLngZoom(lat, lng, zoom);
        
        assertEquals(expZ, result.Z);
        assertEquals(expX, result.X);
        assertEquals(expY, result.Y);

    }

    public class TileSchemaImpl extends TileSchema {

        public TileCoord getTopLeftTile(Tile tile) {
            return null;
        }

        public TileCoord getTopTile(Tile tile) {
            return null;
        }

        public TileCoord getTopRightTile(Tile tile) {
            return null;
        }

        public TileCoord getLeftTile(Tile tile) {
            return null;
        }

        public TileCoord getRightTile(Tile tile) {
            return null;
        }

        public TileCoord getBottomLeftTile(Tile tile) {
            return null;
        }

        public TileCoord getBottomTile(Tile tile) {
            return null;
        }

        public TileCoord getBottomRightTile(Tile tile) {
            return null;
        }
    }
}