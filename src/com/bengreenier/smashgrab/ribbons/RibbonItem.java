package com.bengreenier.smashgrab.ribbons;

import java.util.ArrayList;

import org.newdawn.slick.Renderable;

/**
 * Item for the Ribbon.
 * @author B3N
 *
 **/
public class RibbonItem implements Renderable {
	
	private ArrayList<Renderable> list;
	
	public RibbonItem()
	{
		list = new ArrayList<Renderable>();
	}

	@Override
	public void draw(float x, float y) {
		for (Renderable r : list)
			r.draw(x, y);
		
	}
	
	public void addRenderable(Renderable r)
	{
		list.add(r);
	}
}
