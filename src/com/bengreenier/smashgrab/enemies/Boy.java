package com.bengreenier.smashgrab.enemies;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.Path;
import com.bengreenier.slick.util.Vector2i;

public class Boy extends AbstractEnemy {

	private Image image;
	private int delta_count=0;
	
	private int speed = 5;
	
	public Boy(Vector2i position, Path path) {
		super(position,0, path);
		try {
			image = new Image("res/Character_Boy.png").getScaledCopy(50, 50);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init(GameContainer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) {
		if (image!=null)
			image.draw(getPosition().getX(), getPosition().getY());
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) {
		delta_count+=arg1;
		if (delta_count>=speed)
		{
			delta_count=0;
			if (next_step != null)
			{
				//something like this.
				Vector2i direction = Vector2i.subtract(new Vector2i(next_step.getX(),next_step.getY()), getPosition());
				if (direction.getX()>0)
				setPosition(Vector2i.add(getPosition(), new Vector2i(1,0)));
				if (direction.getX()<0)
					setPosition(Vector2i.add(getPosition(), new Vector2i(-1,0)));
				if (direction.getY()>0)
					setPosition(Vector2i.add(getPosition(), new Vector2i(0,-1)));
				if (direction.getY()<0)
					setPosition(Vector2i.add(getPosition(), new Vector2i(0,1)));
					
				
				if (path!=null)
					if (path.contains(getPosition().getX(), getPosition().getY()))
						update_step();
			}
		}
		
	}
	
	


}
