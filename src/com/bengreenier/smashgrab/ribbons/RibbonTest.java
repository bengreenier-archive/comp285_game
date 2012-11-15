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
				r = new Ribbon("#A60000",802,80);
				RibbonItem item = new RibbonItem();
				RibbonItem item2 = new RibbonItem();
				RibbonItem item3 = new RibbonItem();
				try {
					
					item.addImageAndResize(new Image("res/crate.png"));
					item.addRibbonListener(new RibbonListener(){

						@Override
						public void onNotify(GameContainer gc, RibbonItem item) {
							Input in = gc.getInput();
							
							if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON))
								if (item.inBounds(in.getMouseX(), in.getMouseY()))
								{
									System.out.println("Clicked Me one");
								}
							
							
						}});
					
					item2.addImageAndResize(new Image("res/tile1.png"));
					item2.addRibbonListener(new RibbonListener(){

						@Override
						public void onNotify(GameContainer gc, RibbonItem item) {
							Input in = gc.getInput();
							
							if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON))
								if (item.inBounds(in.getMouseX(), in.getMouseY()))
								{
									System.out.println("Clicked Me faggot");
								}
							
						}});
					item3.addImageAndResize(new Image("res/crate.png"));
					item3.addRibbonListener(new RibbonListener(){

						@Override
						public void onNotify(GameContainer gc,RibbonItem item) {
							Input in = gc.getInput();
							
							if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON))
								if (item.inBounds(in.getMouseX(), in.getMouseY()))
								{
									System.out.println("Clicked Me fucker");
								}
							
							
						}});
					
				} catch (SlickException e) {
					e.printStackTrace();
				}
				
				r.addRibbonItem(item);
				r.addRibbonItem(item2);
				r.addRibbonItem(item3);
			}

			@Override
			public void renderProcess(GameContainer arg0, Graphics arg1) {
				if (r!=null)
					r.draw(arg1, 0, 100);
				
			}

			@Override
			public void updateProcess(GameContainer arg0, int arg1) {
				if (r!=null)
					r.handleListeners(arg0);
			}});
		
		agc.setDisplayMode(800, 600, false);
		agc.start();

	}

}
