package com.bengreenier.smashgrab.util;

import com.bengreenier.smashgrab.towers.AbstractTower;

/**
 * small storage class, defining what we store within a TileSystem's 
 * Tile.userData.
 * 
 * @author B3N
 *
 */
public class TileUserData {
	public AbstractTower object;
	
	public TileUserData()
	{
		this.object = null;
	}
	
	public TileUserData(AbstractTower object)
	{
		this.object = object;
	}
}
