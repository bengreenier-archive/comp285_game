package com.bengreenier.smashgrab.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.Path.Step;

import com.bengreenier.slick.util.GameObject;
import com.bengreenier.slick.util.Vector2i;
import com.bengreenier.smashgrab.towers.AbstractTower;

public abstract class AbstractEnemy extends GameObject {


	protected float angle;
	protected int speed;
	
	public AbstractEnemy(Vector2i position,float angle,int speed) {
		super(position);
		this.angle = angle;
		this.speed = speed;
		
	}

	public void interact(AbstractTower t)
	{
		//this is where the last calculations go. 
		//find the distance between t and this, and if that distance < t.getRange decrease some life.
		//decreasing life must be visually represented
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
		//the update simply does nothing
	}
	
	

}
