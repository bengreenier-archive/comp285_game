package com.bengreenier.smashgrab.util;

import com.bengreenier.slick.util.GameObject;

/**
 * small storage class, defining what we store within a TileSystem's 
 * Tile.userData.
 * 
 * @author B3N
 *
 */
public class TileUserData {
	public GameObject object;
	
	public TileUserData()
	{
		this.object = null;
	}
	
	public TileUserData(GameObject object)
	{
		this.object = object;
	}
}
