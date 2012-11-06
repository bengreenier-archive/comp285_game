package com.bengreenier.smashgrab.enemies;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.bengreenier.slick.util.Renderable;
import com.bengreenier.slick.util.Vector2i;

public class BlueBlob extends AbstractEnemy {

	public BlueBlob(Vector2i position, int radius) {
		super(position, radius);
		setVisual(new Renderable(){

			@Override
			public void draw(Graphics g) {
				Color t = g.getColor();
				g.setColor(Color.blue);
				g.fill(body);
				g.setColor(t);
				
			}

			@Override
			public void update(int time) {
				// TODO Auto-generated method stub
				
			}});
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		if (getVisual() != null)
			getVisual().draw(g);
		
	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

}
