package com.bengreenier.smashgrab.projectiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import com.bengreenier.slick.util.Renderable;
import com.bengreenier.slick.util.Vector2i;

public class MachineGunBullet extends AbstractProjectile {
	
	public MachineGunBullet(Vector2i absolutePosition,Vector2i gridPosition,Vector2f velocity)
	{
		super(absolutePosition, gridPosition, new Vector2i(7,3),velocity);
		setVisual(new Renderable(){

			@Override
			public void draw(Graphics g) {
				Color t = g.getColor();
				g.setColor(Color.green);
				g.fill(shape);
				g.setColor(t);
				
			}

			@Override
			public void update(int time) {
				shape.setLocation(getPosition().getX(),getPosition().getY());
				
			}});
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		if (getVisual() != null)
			getVisual().draw(g);
		
	}

	@Override
	public void update(GameContainer gc, int delta) {
		getVisual().update(delta);
		this.setPosition(Vector2i.add(getPosition(), new Vector2i((int)(delta*velocity.getX()),(int)(delta*velocity.getY()))));//this MIGHT work, to update the location.
		
	}

	@Override
	public void init(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

}
