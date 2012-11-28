package com.bengreenier.smashgrab.towers;


import com.bengreenier.slick.util.GameObject;
import com.bengreenier.slick.util.Renderable;
import com.bengreenier.slick.util.Vector2i;

public abstract class AbstractTower extends GameObject {

	private Vector2i gridPosition;
	private Renderable visual;
	private int range=0,damage=0;
	
	public AbstractTower(Vector2i absolutePosition,Vector2i gridPosition,int range)
	{
		super(absolutePosition);
		this.gridPosition=gridPosition;
		this.range = range;
	}

	public Renderable getVisual() {
		return visual;
	}

	public void setVisual(Renderable visual) {
		this.visual = visual;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	public Vector2i getGridPosition() {
		return gridPosition;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	
}
