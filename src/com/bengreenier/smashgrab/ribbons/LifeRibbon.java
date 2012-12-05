package com.bengreenier.smashgrab.ribbons;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
//TODO find a way to scale width
public class LifeRibbon {

	private Color start;
	private int fullhealth,health,width,height;
	
	public LifeRibbon(Color start,int health) {
		this.start = start;
		this.fullhealth = health;
		this.health = health;
		this.width = 30;
		this.height = 10;
	}
	
	public void resize(int width,int height) {
		this.width = width;
		this.height = height;
	}
	
	public void draw(int x,int y) {
		Graphics g = new Graphics();
		
		g.setColor(Color.gray);
		g.drawRect(x, y, fullhealth, height);
		g.setColor(start);
		g.fillRect(x, y, health, height);
		//System.out.println(width*(health/fullhealth));
		Graphics.setCurrent(g);//swap to this buffer, essentially
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getFullHealth() {
		return fullhealth;
	}
}
