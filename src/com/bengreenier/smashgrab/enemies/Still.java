package com.bengreenier.smashgrab.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bengreenier.slick.util.Vector2i;

public class Still extends AbstractEnemy {

	private Image image;
	
	public Still(Vector2i position, int speed,Object userData) {
		
		
		super(position,0,0); 
		try {
			image = new Image("res/Character_Boy.png").getScaledCopy(50, 50);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		setUserData(userData);
		this.life = 100;
	}

	@Override
	public void init(GameContainer arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) {
		if (image!=null)
			image.draw(getPosition().getX(), getPosition().getY());
		if (lifeRibbon != null)
			lifeRibbon.draw(getPosition().getX(), getPosition().getY());

	}

}
