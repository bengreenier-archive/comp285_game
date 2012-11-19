package com.bengreenier.smashgrab.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.Path.Step;

import com.bengreenier.slick.util.GameObject;
import com.bengreenier.slick.util.Vector2i;

public class Boy extends GameObject {

	private Image still;
	private Step step;
	public Boy(Vector2i position) {
		super(position);
		
		try {
			still = new Image("res/Character_Boy.png").getScaledCopy(50, 50);
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
		if (still!=null)
			still.draw(getPosition().getX(), getPosition().getY());
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) {
		if (getStep()!=null)
		setPosition(new Vector2i(getStep().getX(),getStep().getY()));
		
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

}
