/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.oregonstate.carto.tilemanager;

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
public class FileTileSetTest {
    
    public FileTileSetTest() {
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
     * Test of urlForZXY method, of class FileTileSet.
     */
    @Test
    public void testUrlForZXY() {
        System.out.println("urlForZXY");
        int z = 0;
        int x = 0;
        int y = 0;
        FileTileSet instance = new FileTileSet("file:///C:/Users/nick/Dropbox/notes");
        URL expResult = null;
        URL result = instance.urlForZXY(z, x, y);
        assertEquals(expResult, result);
//        System.out
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getRootDirectory method, of class FileTileSet.
     */
    @Test
    public void testGetRootDirectory() {
//        System.out.println("getRootDirectory");
//        FileTileSet instance = null;
//        String expResult = "";
//        String result = instance.getRootDirectory();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}