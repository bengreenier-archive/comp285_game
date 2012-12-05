package com.bengreenier.smashgrab.ribbons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class UnLife extends RibbonItem{

		@Override
		public void instantiate()
		{
			try {
				addImageAndResize(new Image("res/unlife.png").getScaledCopy(20, 20));
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
