package com.bengreenier.smashgrab.towers;

import org.newdawn.slick.geom.Circle;

import com.bengreenier.slick.util.GameObject;
import com.bengreenier.slick.util.Renderable;
import com.bengreenier.slick.util.Vector2i;

public abstract class AbstractTower extends GameObject {

	private Vector2i gridPosition;
	private Renderable visual;
	private Circle range;
	
	public AbstractTower(Vector2i absolutePosition,Vector2i gridPosition,int range)
	{
		super(absolutePosition);
		this.gridPosition=gridPosition;
		this.range = new Circle(getPosition().getX(),getPosition().getY(),range);
	}

	public Renderable getVisual() {
		return visual;
	}

	public void setVisual(Renderable visual) {
		this.visual = visual;
	}

	public Circle getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = new Circle(getPosition().getX(),getPosition().getY(),range);
	}
	
	public Vector2i getGridPosition() {
		return gridPosition;
	}
	
}
