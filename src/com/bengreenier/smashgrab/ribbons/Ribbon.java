package com.bengreenier.smashgrab.ribbons;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import com.bengreenier.slick.util.HexColor;

public class Ribbon{

	private Rectangle s;
	private String color;
	private ArrayList<RibbonItem> list;
	public Ribbon(String color, boolean isHorizontal,int width,int height)
	{
		this.color = color;
		if (isHorizontal)
			s = new Rectangle(0,0,width,height);
		else
			s = new Rectangle(0,0,height,width);
		list = new ArrayList<RibbonItem>();
	}
	
	public void draw(Graphics g, float x, float y) {
		
		Color t = g.getColor();
		g.setColor(new HexColor(color));
		s.setLocation(x, y);
		g.fill(s);
		g.setColor(t);
		
		int offsetx=50;
		for (RibbonItem item : list)
		{
			if (s.getWidth()>s.getHeight())
				item.draw(offsetx+x, 5+y);
			else if (s.getWidth()<s.getHeight())
				item.draw(5+x,offsetx+y);
			else
				item.draw(x,y);//this isn;t handled right.
			offsetx+=50;
		}
			
		
	}
	
	public void addRibbonItem(RibbonItem item)
	{
		list.add(item);
	}
}
