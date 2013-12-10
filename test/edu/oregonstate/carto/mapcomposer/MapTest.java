/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.oregonstate.carto.mapcomposer;

import edu.oregonstate.carto.tilemanager.HTTPTileSet;
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
public class MapTest {
    
    public MapTest() {
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
     * Test of generateTile method, of class Map.
     */
    @Test
    public void testGenerateTileOSM() {
        System.out.println("testGenerateTileOSM");
        URL url = null;
        Map map = new Map();
        Layer layer1 = new Layer();
        
        HTTPTileSet imageTileSet = new HTTPTileSet("http://tile.openstreetmap.org/{z}/{x}/{y}.png");
        layer1.setImageTileSet(imageTileSet);
        map.addLayer(layer1);
        
        
        BufferedImage result = map.generateTile();
        try {
            ImageIO.write(result, "png", new File("test/output/testGenerateTileOSM.png"));
        } catch (IOException ex) {
            Logger.getLogger(MapTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Test
    public void testGenerateTileEsri() {
        System.out.println("testGenerateTileEsri");
        URL url = null;
        Map map = new Map();
        Layer layer1 = new Layer();
        
        HTTPTileSet imageTileSet = new HTTPTileSet("http://services.arcgisonline.com/ArcGIS/rest/services/Ocean_Basemap/MapServer/tile/{z}/{y}/{x}");
        layer1.setImageTileSet(imageTileSet);
        map.addLayer(layer1);
        
        
        BufferedImage result = map.generateTile();
        try {
            ImageIO.write(result, "png", new File("test/output/testGenerateTileEsri.png"));
        } catch (IOException ex) {
            Logger.getLogger(MapTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Test
    public void testGenerateTileTwoLayers() {
        System.out.println("testGenerateTileTwoLayers");
        URL url = null;
        Map map = new Map();
        Layer layer1 = new Layer();
        Layer layer2 = new Layer();
        
        HTTPTileSet imageTileSet1 = new HTTPTileSet("http://tile.openstreetmap.org/{z}/{x}/{y}.png");
        HTTPTileSet imageTileSet2 = new HTTPTileSet("http://services.arcgisonline.com/ArcGIS/rest/services/Ocean_Basemap/MapServer/tile/{z}/{y}/{x}");
        layer1.setImageTileSet(imageTileSet1);
        layer2.setImageTileSet(imageTileSet2);
        layer2.setOpacity(0.9f);
        map.addLayer(layer1);
        map.addLayer(layer2);
        
        BufferedImage result = map.generateTile();
        try {
            ImageIO.write(result, "png", new File("test/output/testGenerateTileTwoLayers.png"));
        } catch (IOException ex) {
            Logger.getLogger(MapTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}