package com.bengreenier.smashgrab.ribbons;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import com.bengreenier.slick.util.HexColor;

public class Ribbon{

	private Rectangle s;
	private String color;
	private ArrayList<RibbonItem> list;
	private int width;
	private int height;
	public Ribbon(String color,int width,int height)
	{
		this.color = color;
		this.width = width;
		this.height = height;
		
		s = new Rectangle(0,0,width,height);
		
		list = new ArrayList<RibbonItem>();
		
	}
	
	public void draw(Graphics g, float x, float y) {
		
		Color t = g.getColor();
		g.setColor(new HexColor(color));
		s.setLocation(x, y);
		g.fill(s);
		g.setColor(t);
		
		int current_item_index=0;
		
		for (RibbonItem item : list)
		{
			int r_w = item.getWidth();
			int r_h = item.getHeight();
			
			float posx = x+(current_item_index*(width/list.size()))+(width/(2*list.size()))-(0.5f*r_w);
			float posy = y+(0.5f*(height-r_h));
			
			//update bounds (for mouseover info), then draw.
			item.updateBounds(posx, posy);
			item.draw(posx, posy);
			
			current_item_index++;
		}
			
		
	}
	
	public void handleListeners(GameContainer gc)
	{
		for (RibbonItem item : list)
		{
			item.notifyListeners(gc);
		}
	}
	
	
	public void addRibbonItem(RibbonItem item)
	{
		list.add(item);
	}
}
