package com.bengreenier.smashgrab.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.Path.Step;

import com.bengreenier.slick.util.GameObject;
import com.bengreenier.slick.util.Vector2i;

public abstract class AbstractEnemy extends GameObject {


	protected Path path;

	protected float angle;
	protected int speed;
	protected int tilesize;
	
	public AbstractEnemy(Vector2i position,float angle,int speed,Path path,int tilesize) {
		super(position);
		this.path = path;
		this.angle = angle;
		this.speed = speed;
		this.tilesize = tilesize;
		
	}

	/**
	 * cycles stepping info. internal access only.
	 */
	/*protected void update_step()
	{
		//System.out.println("update_step");
		
		if (path!=null)
			for (int i=0;i<path.getLength();i++)
			{
				if (path.getStep(i).equals(step) && i+1 < path.getLength())
					step = path.getStep(i+1);
				if (i+2 < path.getLength())
				{
					next_step = path.getStep(i+2);
					return;
				}
				else
				{
					//System.out.println("nulling");
					next_step = null;
				}
			}
	}*/

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void update(GameContainer gc,int delta)
	{
		//the update simply calls the private navigate_path() method
		navigate_path(delta);
	}
	
	protected int delta_count=0;
	protected void navigate_path(int delta)
	{
		delta_count+=delta;
		if (delta_count>getSpeed())
		{
			delta_count = 0;
			
		}
	}

}
