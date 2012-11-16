package com.bengreenier.smashgrab.towers;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bengreenier.slick.util.Renderable;
import com.bengreenier.slick.util.Vector2i;
import com.bengreenier.smashgrab.main.Main;

public class RocketTower extends AbstractTower {

	private Image image;
	
	public RocketTower(Vector2i absolutePosition, Vector2i gridPosition, int range) {
		super(absolutePosition, gridPosition, range);
		
		try {
			image = new Image("res/RocketTower.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final RocketTower LocalFinalMachineGunTowerPointer = this;
		
		setVisual(new Renderable(){

			@Override
			public void draw(Graphics g) {
				if (LocalFinalMachineGunTowerPointer.image!=null)
					LocalFinalMachineGunTowerPointer.image.draw();
			}

			@Override
			public void update(int time) {
				//do nothing
			}});
	}


	@Override
	public void render(GameContainer gc, Graphics g) {
		//if (getVisual() != null)
		//getVisual().draw(getPosition().getX(),getPosition().getY(),g);
		if (image!=null)
			image.draw(getGridPosition().getX(), getGridPosition().getY());
	}

	@Override
	public void update(GameContainer gc, int delta) {
		getVisual().update(delta);
		
	}

	@Override
	public void init(GameContainer gc) {
		
	}

}
