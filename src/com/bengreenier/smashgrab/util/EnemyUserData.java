package com.bengreenier.smashgrab.util;

import org.newdawn.slick.util.pathfinding.Path.Step;

/**
 * small storage class, defining what we store within an
 * AbstractEnemy.userData.
 * 
 * @author B3N
 *
 */
public class EnemyUserData {

	public Step current;
	public Step next;
	public int cid,nid;
	
	public EnemyUserData() {
		current = null;
		next = null;
	}
	
	public EnemyUserData(Step current,Step next) {
		this.current = current;
		this.next = next;
	}

}
