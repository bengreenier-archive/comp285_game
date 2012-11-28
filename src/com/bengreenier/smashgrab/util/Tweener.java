package com.bengreenier.smashgrab.util;

import com.bengreenier.slick.util.Vector2i;

/**
 * takes a Vector2i position, a Vector2i point, and a total time
 * and then calling .getNextPoint will smooth return the position for
 * a move for the GameObject to the point over the time.
 * @author B3N
 * @version 0.1
 */
public class Tweener {
	
	private Vector2i pos,point,res;
	private boolean wasJustDone;
	
	public Tweener(Vector2i pos, Vector2i point)
	{
		this.pos = pos;
		this.point = point;
		this.res = pos;
		this.wasJustDone = false;
	}
	
	/**
	 * get next point
	 * @param delta update time
	 * @return position
	 */
	public Vector2i getNextPoint()
	{
		if (pos.getX() < point.getX() ) res.add(new Vector2i(1,0));
		if (pos.getX() > point.getX() ) res.add(new Vector2i(-1,0));
		if (pos.getY() < point.getY() ) res.add(new Vector2i(0,1));
		if (pos.getY() > point.getY() ) res.add(new Vector2i(0,-1));
		return res;
	}
	
	public boolean isFinished()
	{	
		if (point.getX() == res.getX() && point.getY() == res.getY())
			if (!wasJustDone)
				{
					wasJustDone=true;
					return false;
				}
			else
			return true;
		else
			return false;
	}
	
}
