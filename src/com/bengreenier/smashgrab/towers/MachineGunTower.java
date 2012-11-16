package com.bengreenier.smashgrab.towers;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.bengreenier.slick.util.Renderable;
import com.bengreenier.slick.util.Vector2i;
import com.bengreenier.smashgrab.main.Main;

public class MachineGunTower extends AbstractTower {

	public MachineGunTower(Vector2i absolutePosition, Vector2i gridPosition, int range) {
		super(absolutePosition, gridPosition, range);
		setVisual(new Renderable(){

			@Override
			public void draw(Graphics g) {
				Color t = g.getColor();
				g.setColor(Color.red);
				//g.fillRoundRect(getPosition().getX(), getPosition().getY(), Main.TILESIZE_X, Main.TILESIZE_Y, 15);
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
		getVisual().update(delta);
		
	}

	@Override
	public void init(GameContainer gc) {
		
	}

}
