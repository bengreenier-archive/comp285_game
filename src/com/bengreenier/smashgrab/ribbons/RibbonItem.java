package com.bengreenier.smashgrab.ribbons;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;

/**
 * Item for the Ribbon.
 * @author B3N
 *
 **/
public class RibbonItem implements Renderable {
	
	private ArrayList<Renderable> list;
	private ArrayList<RibbonListener> listeners;
	
	private int width=0;
	private int height=0;
	private int location_x=-1;
	private int location_y=-1;
	
	public RibbonItem()
	{
		list = new ArrayList<Renderable>();
		listeners = new ArrayList<RibbonListener>();
	}

	@Override
	public void draw(float x, float y) {
		for (Renderable r : list)
			r.draw(x, y);
		
	}
	
	public void addRibbonListener(RibbonListener e)
	{
		listeners.add(e);
	}
	
	public void notifyListeners(GameContainer gc)
	{
		for(RibbonListener e : listeners)
			e.onNotify(gc,this);
	}
	
	public void addRenderable(Renderable r)
	{
		list.add(r);
	}
	
	public void addImageAndResize(Image i)
	{
		width = i.getWidth();
		height = i.getHeight();
		list.add(i);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void updateBounds(float boundx,float boundy)
	{
		location_x = (int)boundx;
		location_y = (int)boundy;
	}
	
	public boolean inBounds(int x,int y)
	{
		boolean a=false,b=false;
		if (location_x < x && location_x+width > x)
			a=true;
		if (location_y < y && location_y+height > y)
			b=true;
		
		if (a&&b)
			return true;
		else
			return false;
	}
	
}
