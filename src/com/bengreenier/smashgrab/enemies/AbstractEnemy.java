package com.bengreenier.smashgrab.enemies;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import com.bengreenier.slick.util.GameObject;
import com.bengreenier.slick.util.Vector2i;
import com.bengreenier.smashgrab.ribbons.LifeRibbon;
import com.bengreenier.smashgrab.towers.AbstractTower;

public abstract class AbstractEnemy extends GameObject {


	protected float angle;
	protected int speed;
	protected int life;
	protected boolean dead=false;
	protected LifeRibbon lifeRibbon;
	
	public AbstractEnemy(Vector2i position,float angle,int speed) {
		super(position);
		this.angle = angle;
		this.speed = speed;
		this.lifeRibbon = new LifeRibbon(Color.green,100);
		
	}
	
	public boolean isDead()
	{
		return dead;
	}

	/*private int delta_count=0;
	public void interact(AbstractTower t,int delta)
	{
		delta_count+=delta;
		//this is where the last calculations go. 
		//find the distance between t and this, and if that distance < t.getRange decrease some life.
		//decreasing life must be visually represented <LifeRibbon>
		Vector2i d  = Vector2i.subtract(t.getPosition(),getPosition());
		d.setX(Math.abs(d.getX()));
		d.setY(Math.abs(d.getY()));
		double distance = Math.sqrt(Math.pow(d.getX(),2)+Math.pow(d.getY(),2));
		if (distance <= t.getRange() && delta_count>= t.getCooldown())
		{
			delta_count=0;
			if (lifeRibbon!=null)
				lifeRibbon.setHealth(lifeRibbon.getHealth()-t.getDamage());
			life-=t.getDamage();
			System.out.println("life = "+life+"   "+lifeRibbon.getHealth());
		}
		
		if (life<=0)
		{
			System.out.println("dead");
			dead=true;
		}
	}*/

	public void applyDamage(int damage) {
		//System.out.println("doing damage "+damage+" to "+this+" who had health "+lifeRibbon.getHealth());
		lifeRibbon.setHealth(lifeRibbon.getHealth()-damage);
		if (lifeRibbon.getHealth()<=0)
			dead=true;
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
