package com.bengreenier.smashgrab.enemies;

import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.Path.Step;

import com.bengreenier.slick.util.GameObject;
import com.bengreenier.slick.util.Vector2i;

public abstract class AbstractEnemy extends GameObject {


	protected Path path;
	protected Step step;
	protected Step next_step;
	protected float angle;
	
	public AbstractEnemy(Vector2i position,float angle,Path path) {
		super(position);
		this.path = path;
		this.angle = angle;
		if (path!=null)
		{
			if (path.getLength()>0)
				this.step = path.getStep(0);
			if (path.getLength()>1)
				this.next_step = path.getStep(1);
		}
	}

	protected void update_step()
	{
		if (path!=null)
			for (int i=0;i<path.getLength();i++)
			{
				if (path.getStep(i).equals(step) && i+1 < path.getLength())
					step = path.getStep(i+1);
				if (i+2 < path.getLength())
					next_step = path.getStep(i+2);
				else
					next_step = null;
			}
	}

}
