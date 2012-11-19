package com.bengreenier.smashgrab.towers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bengreenier.slick.util.Vector2i;

public class LaserTower extends AbstractTower {

	private Image image;
	
	public LaserTower(Vector2i absolutePosition, Vector2i gridPosition, int range) {
		super(absolutePosition, gridPosition, range);
		
		try {
			image = new Image("res/LaserTower.png").getScaledCopy(50, 50);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
		setVisual(null);
	}


	@Override
	public void render(GameContainer gc, Graphics g) {
		if (image!=null)
			image.draw(getGridPosition().getX(), getGridPosition().getY());
	}

	@Override
	public void update(GameContainer gc, int delta) {
		
	}

	@Override
	public void init(GameContainer gc) {
		
	}

}
