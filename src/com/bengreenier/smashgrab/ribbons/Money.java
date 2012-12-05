package com.bengreenier.smashgrab.ribbons;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;

/**
 * display for the money ribbon
 * @author B3N
 *
 */
public class Money extends RibbonItem {

	private int money =0;
	
	public Money(int money) {
		this.money = money;
	}
	
	@Override
	public void instantiate()
	{
		try {
			addImageAndResize(new Image("res/money.png").getScaledCopy(20, 20));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setWidth(getWidth()+30);//size of 20+30
		/*list.add(new Renderable(){

			@Override
			public void draw(float x, float y) {
				Graphics g = new Graphics();
				g.drawString(""+money, x, y);
				Graphics.setCurrent(g);
				
			}});*/
	}
	
	@Override
	public void draw() {
		for (Renderable r : list)
			r.draw(getX(), getY());
		
		
		
	}
}
