package com.bengreenier.smashgrab.ribbons;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
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
				r = new Ribbon("#A60000",802,80,0,100);
				
				//configure the ribbon to listen for Input
				arg0.getInput().addListener(r);
				
				
				//configure the crate and something to do on mouseReleased
				RibbonItem crate = new RibbonItem(){
					
					@Override
					public void mouseReleased(int arg0, int arg1, int arg2) {
						if (arg0 == Input.MOUSE_LEFT_BUTTON)
							if (inBounds(arg1, arg2))
								System.out.println("Crate Clicked");
						
					}
				};
				
				//configure the tile and something to do on mouseReleased
				RibbonItem tile = new RibbonItem(){
					
					@Override
					public void mouseReleased(int arg0, int arg1, int arg2) {
						if (arg0 == Input.MOUSE_LEFT_BUTTON)
							if (inBounds(arg1, arg2))
								System.out.println("Tile Clicked");
						
					}
				};
				
				
				//try to load our images.
				try {
					
					crate.addImageAndResize(new Image("res/crate.png"));
					tile.addImageAndResize(new Image("res/tile1.png"));
					
				} catch (SlickException e) {
					e.printStackTrace();
				}
				
				
				//add our create and tile to the ribbon
				r.addRibbonItem(crate);
				r.addRibbonItem(tile);
			}

			@Override
			public void renderProcess(GameContainer arg0, Graphics arg1) {
				if (r!=null)
					r.draw(arg1);//draw the ribbon, once its made.
				
			}

			@Override
			public void updateProcess(GameContainer arg0, int arg1) {
				
			}});
		
		agc.setDisplayMode(800, 600, false);
		agc.start();

	}

}
