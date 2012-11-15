package com.bengreenier.smashgrab.ribbons;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.bengreenier.slick.util.StateFreeGame;

public class RibbonTest {

	/**
	 * @param args
	 * @throws SlickException 
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer agc = new AppGameContainer(new StateFreeGame("TEST"){

			private Ribbon r;
			
			@Override
			public void initProcess(GameContainer arg0) {
				r = new Ribbon("#A60000",false,802,80);
				RibbonItem item = new RibbonItem();
				
				try {
					item.addRenderable(new Image("res/bonus.png"));
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				r.addRibbonItem(item);
			}

			@Override
			public void renderProcess(GameContainer arg0, Graphics arg1) {
				if (r!=null)
					r.draw(arg1, 0, 100);
				
			}

			@Override
			public void updateProcess(GameContainer arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}});
		
		agc.setDisplayMode(800, 600, false);
		agc.start();

	}

}
