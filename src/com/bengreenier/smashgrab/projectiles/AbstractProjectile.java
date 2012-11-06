package com.bengreenier.smashgrab.projectiles;

import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.geom.Vector2f;

import com.bengreenier.slick.util.GameObject;
import com.bengreenier.slick.util.Renderable;
import com.bengreenier.slick.util.Vector2i;

public abstract class AbstractProjectile extends GameObject {

	protected Vector2f velocity;
	private Vector2i gridPosition;
	private Renderable visual;
	
	//protected because we'll need to update this position each update.
	protected RoundedRectangle body;

	
	public AbstractProjectile(Vector2i position,Vector2i gridPosition,Vector2i bodySize,Vector2f velocity) {
		super(position);
		this.gridPosition = gridPosition;
		this.body = new RoundedRectangle(getPosition().getX(),getPosition().getY(),bodySize.getX(),bodySize.getY(),3);
		this.velocity=velocity;
	}
	
	
	public Renderable getVisual() {
		return visual;
	}

	public void setVisual(Renderable visual) {
		this.visual = visual;
	}
	
	public Vector2i getGridPosition() {
		return gridPosition;
	}
	
}
