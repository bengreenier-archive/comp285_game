package com.bengreenier.smashgrab.explosions;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import com.bengreenier.slick.util.Vector2i;

public class Disappear extends Explosion {

	public Disappear(Vector2i pos) throws SlickException {
		super(new SpriteSheet("res/exp/explosion13.png",128,128), 20);
		this.pos = pos;
		setLooping(false);
		this.snd = new Sound("res/exp/gone.ogg");
	}

}
