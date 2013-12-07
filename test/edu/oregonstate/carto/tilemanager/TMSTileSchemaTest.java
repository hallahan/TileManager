/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class TMSTileSchemaTest {
    
    public TMSTileSchemaTest() {
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

    @Test
    public void testGetTileCoordsForLatLngZoom() {
        System.out.println("getTileCoordsForLatLngZoom");
        double lat = 56.053635;
        double lng = -112.167868;
        int zoom = 9;
        TileSchema instance = new TMSTileSchema();
        
        int expZ = 9;
        int expX = 96;
        int expY = 352;
        
        TileCoord result = instance.getTileCoordsForLatLngZoom(lat, lng, zoom);
        
        assertEquals(expZ, result.Z);
        assertEquals(expX, result.X);
        assertEquals(expY, result.Y);

    }
        
    @Test
    public void testGetTileCoordsForBBoxZoom() {
        System.out.println("getTileCoordsForBBoxZoom");
        
//        -120.952351,45.076126,-120.884865,45.098067
        double minLat = 45.076126;
        double minLng = -120.952351;
        double maxLat = 45.098067;
        double maxLng = -120.884865;
        int zoom = 13;
        
        TileSchema tmsSchema = new TMSTileSchema();
        
        TileCoord[] tmsTiles = tmsSchema.getTileCoordsForBBoxZoom(minLat, minLng, maxLat, maxLng, zoom);
        
        assertEquals(tmsTiles.length, 6);
        
        assertEquals(tmsTiles[0].Z, 13);
        assertEquals(tmsTiles[0].X, 1343);
        assertEquals(tmsTiles[0].Y, 5247);
        
        assertEquals(tmsTiles[1].Z, 13);
        assertEquals(tmsTiles[1].X, 1343);
        assertEquals(tmsTiles[1].Y, 5248);
        
        assertEquals(tmsTiles[2].Z, 13);
        assertEquals(tmsTiles[2].X, 1344);
        assertEquals(tmsTiles[2].Y, 5247);
        
        assertEquals(tmsTiles[3].Z, 13);
        assertEquals(tmsTiles[3].X, 1344);
        assertEquals(tmsTiles[3].Y, 5248);
        
        assertEquals(tmsTiles[4].Z, 13);
        assertEquals(tmsTiles[4].X, 1345);
        assertEquals(tmsTiles[4].Y, 5247);
        
        assertEquals(tmsTiles[5].Z, 13);
        assertEquals(tmsTiles[5].X, 1345);
        assertEquals(tmsTiles[5].Y, 5248);

    }
        

}