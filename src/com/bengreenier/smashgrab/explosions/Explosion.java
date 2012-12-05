package com.bengreenier.smashgrab.explosions;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import com.bengreenier.slick.util.Vector2i;

public class Explosion extends Animation {
	
	protected Vector2i pos;
	protected Sound snd;
	private boolean soundMarkedForPlaying=false;
	
	public Explosion(SpriteSheet s , int d) {
		super(s,d);
	}
	
	public Vector2i getPos() {
		return pos;
	}
	
	public Sound getSnd() {
		return snd;
	}
	
	public boolean soundMarkedForPlaying() {
		return soundMarkedForPlaying;
	}
	
	public void soundMarkedForPlaying(boolean val) {
		soundMarkedForPlaying=val;
	}
}
