package com.bengreenier.smashgrab.states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.Path.Step;

import com.bengreenier.slick.tiling.TileSystem;
import com.bengreenier.slick.tiling.TileSystem.Tile;
import com.bengreenier.slick.util.Vector2i;

import com.bengreenier.smashgrab.enemies.AbstractEnemy;
import com.bengreenier.smashgrab.enemies.Boy;
import com.bengreenier.smashgrab.main.Main;
import com.bengreenier.smashgrab.util.EnemyUserData;
import com.bengreenier.smashgrab.util.TileUserData;

public class Run implements GameState {

	private int id;
	private TileSystem tileSystem;
	private Path path;
	private ArrayList<AbstractEnemy> objects;
	
	public Run(int id)
	{
		this.id = id;
		tileSystem = Main.core.tileSystem;
		objects = new ArrayList<AbstractEnemy>();
	}
	
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		tileSystem = Main.core.tileSystem;
		
		Input in = arg0.getInput();
		in.clearControlPressedRecord();
		in.clearKeyPressedRecord();
		in.clearMousePressedRecord();
		
		path = tileSystem.getAStarPath(0, 0, tileSystem.getWidthInTiles()-1, tileSystem.getHeightInTiles()-1);
		objects.add(new Boy(new Vector2i(),5,path,50));
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Main.core.tileSystem = tileSystem;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		
		if (tileSystem!=null)
			tileSystem.draw(0,0);

		
		for (Tile o : tileSystem.getTiles())
			if (o!=null)
				if (((TileUserData) o.getUserData())!=null)
					if (((TileUserData) o.getUserData()).object!=null)
						((TileUserData) o.getUserData()).object.render(arg0, arg2);
		
		for (AbstractEnemy o : objects)
			o.render(arg0, arg2);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {

		for (Tile o : tileSystem.getTiles())
			if (o!=null)
				if (((TileUserData) o.getUserData())!=null)
					if (((TileUserData) o.getUserData()).object!=null)
						((TileUserData) o.getUserData()).object.update(arg0, arg2);
		
		
		//this is where we make our objects follow the path.
		for (AbstractEnemy o : objects)
		{
			Vector2i pos = o.getPosition();
			Path pa = o.getPath();
			if (pa!=null)
			{
				//first run through, instantiate userData
				if (o.getUserData() == null)
					o.setUserData(new EnemyUserData());
				
				//configure the starting userData values
				if (((EnemyUserData)o.getUserData()).current == null)
					((EnemyUserData)o.getUserData()).current = pa.getStep(0);
				
				//""
				if (((EnemyUserData)o.getUserData()).next == null && pa.getLength()>1)
					((EnemyUserData)o.getUserData()).next = pa.getStep(1);
					
				//this method doesn't work, perhaps the reverlocate api is off (not coded right). (yeah, this is off)
				Vector2i current_grid_location=null;
				
				
				
				try {
					if (((EnemyUserData)o.getUserData()).next != null)
					{
						System.out.println(new Vector2i(((EnemyUserData)o.getUserData()).next.getX(),((EnemyUserData)o.getUserData()).next.getY()));
						current_grid_location = tileSystem.reverseLocate(new Vector2i(((EnemyUserData)o.getUserData()).next.getX(),((EnemyUserData)o.getUserData()).next.getY()));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				
				
				if (current_grid_location!=null)
				{
					System.out.println(current_grid_location);
					if (current_grid_location.equals(new Vector2i(((EnemyUserData)o.getUserData()).next.getX(),((EnemyUserData)o.getUserData()).next.getY())))
						System.out.println("You're in grid "+current_grid_location);
				}
				
				Vector2i motion = new Vector2i();
				
				if (((EnemyUserData)o.getUserData()).next.getX() > ((EnemyUserData)o.getUserData()).current.getX())
					motion.add(new Vector2i(1,0));
				else if (((EnemyUserData)o.getUserData()).next.getX() < ((EnemyUserData)o.getUserData()).current.getX())
					motion.add(new Vector2i(-1,0));
				
				if (((EnemyUserData)o.getUserData()).next.getY() > ((EnemyUserData)o.getUserData()).current.getY())
					motion.add(new Vector2i(0,1));
				else if (((EnemyUserData)o.getUserData()).next.getY() < ((EnemyUserData)o.getUserData()).current.getY())
					motion.add(new Vector2i(0,1));
				
				o.setPosition(Vector2i.add(o.getPosition(), motion));
			}
			
			
			/*Step st = o.getStep();
			Step nst = null;
			if (pa!=null)
				for (int i=0;i>pa.getLength();i++)
					if (st!=null)
						if (st.equals(pa.getStep(i)))
							if (i+1< pa.getLength())
								nst = pa.getStep(i+1);
			//setup configured. begin motion.
			if (nst!=null)
			{
				System.out.println("more motion");
			}*/
		}
		
		
		
	}
	
	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonPressed(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonReleased(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}



}
