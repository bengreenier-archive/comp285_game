package com.bengreenier.smashgrab.ribbons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Life extends RibbonItem{

		@Override
		public void instantiate()
		{
			try {
				addImageAndResize(new Image("res/life.png").getScaledCopy(20, 20));
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
