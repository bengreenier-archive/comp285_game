package com.bengreenier.smashgrab.util;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;

public class MouseOverArea extends org.newdawn.slick.gui.MouseOverArea {
	private ArrayList<MouseOverAreaListener> listeners;

	public MouseOverArea(GUIContext container, Image image, int x, int y) {
		super(container, image, x, y);
		listeners = new ArrayList<MouseOverAreaListener>();
		
	}

	public void addMouseOverAreaListener(MouseOverAreaListener e){
		listeners.add(e);
	}
	
	public void notifyListeners(GameContainer gc){
		for(MouseOverAreaListener moal : listeners)
			moal.onNotify(gc, this);
	}
	
	public boolean inBounds(int x, int y){
		boolean a = false, b = true;
		if(this.getX() < x && this.getX()+this.getWidth() > x)
			a = true;
		if(this.getY() < y && this.getY()+this.getHeight() > y)
			b = true;
		if(a&&b)
			return true;
		else
			return false;
	}
}
