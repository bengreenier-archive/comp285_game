package com.bengreenier.smashgrab.util;

import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.Path.Step;

import com.bengreenier.slick.util.Vector2i;

public class PathWrapper {

	private int current_step_id=0;
	private int tile_size_x,tile_size_y;
	private Path path;
	
	public PathWrapper(Path path,int tilex,int tiley)
	{
		this.path = path;
		this.tile_size_x = tilex;
		this.tile_size_y = tiley;
	}
	
	public Step getNextStep()
	{
		current_step_id += 1;
		return path.getStep(current_step_id);
	}
	
	public Vector2i getNextLocation()
	{
		current_step_id += 1;
		return new Vector2i(tile_size_x * path.getStep(current_step_id).getX(),tile_size_y * path.getStep(current_step_id).getY());
	}
	
	public int getStepId()
	{
		return current_step_id;
	}
	
	public Path getPath()
	{
		return path;
	}
	
	public boolean isPathNull()
	{
		if (path==null)
			return true;
		else
			return false;
	}
	
	public boolean hasNextStep()
	{
		if (path.getLength()>current_step_id+1)
			return true;
		else
			return false;
	}
	
	public Vector2i getStepMaxDimensions(int stepid)
	{
		Step s = path.getStep(stepid);
		Vector2i r = new Vector2i();
		
		r.setX(s.getX()*tile_size_x);
		r.setY(s.getY()*tile_size_y);
		
		return r;
	}
	
	public Vector2i getStepMinDimensions(int stepid)
	{
		Step s = path.getStep(stepid-1);
		Vector2i r = new Vector2i();
		
		r.setX(s.getX()*tile_size_x);
		r.setY(s.getY()*tile_size_y);
		
		return r;
	}
	
	public boolean hasStep(int stepid)
	{
		if (path.getLength() > stepid)
			return true;
		else
			return false;
	}
	
	public void rollback()
	{
		current_step_id -= 1;
	}
}
