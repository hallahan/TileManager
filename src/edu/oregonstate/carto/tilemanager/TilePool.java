package edu.oregonstate.carto.tilemanager;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Nicholas Hallahan nick@theoutpost.io
 */
public class TilePool {

    private ConcurrentHashMap<long, Tile> map = new ConcurrentHashMap<long, Tile>();
    
    
}
