package com.bengreenier.smashgrab.enemies;

import org.newdawn.slick.geom.Circle;

import com.bengreenier.slick.util.GameObject;
import com.bengreenier.slick.util.Renderable;
import com.bengreenier.slick.util.Vector2i;

public abstract class AbstractEnemy extends GameObject {

	protected Circle body;
	private Renderable visual;
	
	public AbstractEnemy(Vector2i position,int radius) {
		super(position);
		this.body = new Circle(getPosition().getX(),getPosition().getY(),radius);
	}

	public Renderable getVisual() {
		return visual;
	}

	public void setVisual(Renderable visual) {
		this.visual = visual;
	}

}
