package com.bengreenier.smashgrab.util;

import com.bengreenier.slick.util.Vector2i;

/**
 * takes a Vector2i position, a Vector2i point, and a time in ms
 * and then calling .getNextPoint will smooth return the position for
 * a move for the GameObject to the point over the time.
 * @author B3N
 * @version 0.1
 */
public class Tweener {

	private Vector2i pos,point;
	int time;
	
	public Tweener(Vector2i pos, Vector2i point, int time)
	{
		this.pos = pos;
		this.point = point;
		this.time = time;
	}
	
	/**
	 * get next point
	 * @param delta update time
	 * @return position
	 */
	public Vector2i getNextPoint(int delta)
	{
		return new Vector2i();
	}
}
