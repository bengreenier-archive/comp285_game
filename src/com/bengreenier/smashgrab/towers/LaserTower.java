package com.bengreenier.smashgrab.towers;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bengreenier.slick.util.Vector2i;
import com.bengreenier.smashgrab.main.Main;

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
		setDamage(20);
		setRange((4*Main.TILESIZE)+Main.TILESIZE/2);//this is silly, as i override the passed range, which really shouldn't be passed. fix it.
		setCooldown(700);
	}


	@Override
	public void render(GameContainer gc, Graphics g) {
		if (image!=null)
			image.draw(getGridPosition().getX(), getGridPosition().getY());
		g.setColor(Color.pink);
		g.drawOval(getGridPosition().getX()+25-(getRange()), getGridPosition().getY()+25-(getRange()), getRange()*2, getRange()*2);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		
	}

	@Override
	public void init(GameContainer gc) {
		
	}

}
