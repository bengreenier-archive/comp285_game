package com.bengreenier.smashgrab.ribbons;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.geom.Rectangle;

import com.bengreenier.slick.util.HexColor;

public class Ribbon implements InputListener{

	private Rectangle s;
	private String color;
	private ArrayList<RibbonItem> list;
	private int width;
	private int height;
	private int location_x=0;
	private int location_y=0;
	private boolean enabled=true;
	
	public Ribbon(String color,int width,int height)
	{
		this.color = color;
		this.width = width;
		this.height = height;
		
		s = new Rectangle(0,0,width,height);
		
		list = new ArrayList<RibbonItem>();
		
	}
	
	public Ribbon(String color,int width,int height,int locx,int locy)
	{
		this.color = color;
		this.width = width;
		this.height = height;
		location_x = locx;
		location_y = locy;
		
		s = new Rectangle(0,0,width,height);
		
		list = new ArrayList<RibbonItem>();
		
	}
	
	public void setLocation(int x,int y)
	{
		location_x = x;
		location_y = y;
	}
	
	public void draw(Graphics g) {
		
		Color t = g.getColor();
		g.setColor(new HexColor(color));
		s.setLocation(location_x, location_y);
		g.fill(s);
		g.setColor(t);
		
		for (RibbonItem item :list)
			item.draw();
			
		
	}
	
	
	
	public void addRibbonItem(RibbonItem a)
	{
		list.add(a);
		
		int current_item_index=0;
		
		for (RibbonItem item : list)
		{
			int r_w = item.getWidth();
			int r_h = item.getHeight();
			
			float posx = location_x+(current_item_index*(width/list.size()))+(width/(2*list.size()))-(0.5f*r_w);
			float posy = location_y+(0.5f*(height-r_h));
			
			//update bounds (for mouseover info).
			item.updateBounds(posx, posy);
			
			current_item_index++;
		}
	}
	
	

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		//System.out.println("Ribbon registered mouseClicked");
	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		//System.out.println("Ribbon Registered mouseReleased");
		for (RibbonItem item : list)
			item.mouseReleased(arg0,arg1,arg2);
		
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return enabled;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int arg0, char arg1) {
		System.out.println("Ribbon registered keyPressed");
		
	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonPressed(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonReleased(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}
}
