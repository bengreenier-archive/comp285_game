package com.bengreenier.smashgrab.ribbons;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.Renderable;

/**
 * Item for the Ribbon.
 * @author B3N
 *
 **/
public class RibbonItem implements MouseListener{
	
	private ArrayList<Renderable> list;
	
	private int width=0;
	private int height=0;
	private int location_x=-1;
	private int location_y=-1;
	private boolean enabled=true;
	
	public RibbonItem()
	{
		list = new ArrayList<Renderable>();
	}

	public void draw() {
		for (Renderable r : list)
			r.draw(location_x, location_y);
		
	}
	
	
	public void addRenderableAndResize(Renderable r,int width,int height)
	{
		this.width = width;
		this.height = height;
		list.add(r);
	}
	
	public void addImageAndResize(Image i)
	{
		this.width = i.getWidth();
		this.height = i.getHeight();
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
	
	
	public int getX() {
		return location_x;
	}

	public void setX(int location_x) {
		this.location_x = location_x;
	}

	public int getY() {
		return location_y;
	}

	public void setY(int location_y) {
		this.location_y = location_y;
	}
	

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void updateBounds(float boundx,float boundy)
	{
		location_x = (int)boundx;
		location_y = (int)boundy;
	}
	
	public boolean inBounds(int x,int y)
	{
		
		//System.out.println(width+" "+height+" , "+location_x+" "+location_y);
		
		if (location_x == -1 || location_x == -1 || width == 0 || height == 0)
			try{ throw new Exception("Dimensionless RibbonItem Failure"); }catch(Exception e){e.printStackTrace();}
		
		boolean a=false,b=false;
		
		if (getX() < x && getX()+getWidth() > x)
			a=true;
		if (getY() < y && getY()+getHeight() > y)
			b=true;
		
		if (a&&b)
			return true;
		else
			return false;
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
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
