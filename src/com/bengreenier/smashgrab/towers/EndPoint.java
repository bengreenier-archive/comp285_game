package com.bengreenier.smashgrab.towers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bengreenier.slick.util.Vector2i;

public class EndPoint extends AbstractTower {

	private Image image;
	
	public EndPoint(Vector2i a) {
		super(null, a, 0);
		try {
			image = new Image("res/flag.png").getScaledCopy(50, 50);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(GameContainer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) {
		if (image!=null)
			image.draw(getGridPosition().getX(), getGridPosition().getY());
	}

	@Override
	public void update(GameContainer arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}
