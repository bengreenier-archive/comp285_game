package com.bengreenier.smashgrab.util;


/**
 * small storage class, defining what we store within an
 * AbstractEnemy.userData.
 * 
 * @author B3N
 *
 */
public class EnemyUserData {

	public PathWrapper pw;
	public int delta_count;
	public Tweener tweener;
	
	public EnemyUserData() {
		pw = null;
		delta_count = 0;
	}
	
	public EnemyUserData(PathWrapper pw) {
		this.pw = pw;
		this.delta_count = 0;
	}
	
	public EnemyUserData(Tweener tweener) {
		this.tweener = tweener;
		this.delta_count = 0;
	}
	
	public EnemyUserData(PathWrapper pw,Tweener tweener) {
		this.pw = pw;
		this.tweener = tweener;
		this.delta_count = 0;
	}

}
