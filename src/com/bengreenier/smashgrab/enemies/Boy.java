package com.bengreenier.smashgrab.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.Path;
import com.bengreenier.slick.util.Vector2i;

public class Boy extends AbstractEnemy {

	private Image image;
	
	public Boy(Vector2i position,int speed,Path path,int tilesize) {
		super(position,0,speed,path,tilesize);
		try {
			image = new Image("res/Character_Boy.png").getScaledCopy(50, 50);
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
			image.draw(getPosition().getX(), getPosition().getY());
	}

	
	


}
