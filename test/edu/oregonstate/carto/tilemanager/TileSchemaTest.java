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
    public void testGetTileCoordForLatLngZoom() {
        System.out.println("getTileForLatLngZoom");
        double lat = 56.053635;
        double lng = -112.167868;
        int zoom = 9;
        TileSchema instance = new TileSchemaImpl();
        
        int expZ = 9;
        int expX = 96;
        int expY = 352;
        
        TileCoord result = instance.getTileCoordsForLatLngZoom(lat, lng, zoom);
        
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

    /**
     * Test of getTileCoordsForBBoxZoomRange method, of class TileSchema.
     */
    @Test
    public void testGetTileCoordsForBBoxZoomRange() {
        System.out.println("getTilesForBBoxZoomRange");
        
//        -120.952351,45.076126,-120.884865,45.098067
        double minLat = 45.076126;
        double minLng = -120.952351;
        double maxLat = 45.098067;
        double maxLng = -120.884865;
        int minZoom = 9;
        int maxZoom = 13;
        
        TileSchema instance = new GoogleTileSchema();
        
        TileCoord[] result = instance.getTileCoordsForBBoxZoomRange(minLat, minLng, maxLat, maxLng, minZoom, maxZoom);

        assertEquals(result.length, 22);
        assertEquals(result[4].Z, 10);
        assertEquals(result[21].Z, 13);
        assertEquals(result[15].Z, 12);
        assertEquals(result[15].X, 672);
        assertEquals(result[15].Y, 1471);
    }

}