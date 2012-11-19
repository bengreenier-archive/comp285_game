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
	
	private Vector2i pos,point;
	private int time;
	private int rtot=0;
	
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
		rtot+=delta;
		//pos = rtot/time of Vector2i.subtract(point,pos); 
		Vector2i sub = Vector2i.subtract(point, pos);
		pos = new Vector2i(sub.getX()*(time/rtot),sub.getY()*(time/rtot));
		
		return pos;
	}
	
	public static void main(String args[])
	{
		Tweener t = new Tweener(new Vector2i(0,0),new Vector2i(50,0),2000);
		Vector2i pt = t.getNextPoint(5);
		while(pt.getX() != 50)
		{
			System.out.println(pt);
			pt = t.getNextPoint(5);
		}
	}
}
