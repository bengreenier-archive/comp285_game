package com.bengreenier.smashgrab.states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.pathfinding.Path;

import com.bengreenier.slick.tiling.TileSystem;
import com.bengreenier.slick.tiling.TileSystem.Tile;
import com.bengreenier.slick.util.Vector2i;

import com.bengreenier.smashgrab.enemies.AbstractEnemy;
import com.bengreenier.smashgrab.enemies.Boy;
import com.bengreenier.smashgrab.main.Main;
import com.bengreenier.smashgrab.util.EnemyUserData;
import com.bengreenier.smashgrab.util.PathWrapper;
import com.bengreenier.smashgrab.util.TileUserData;
import com.bengreenier.smashgrab.util.Tweener;

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

		burst_spawn(arg2);
		
		for (Tile o : tileSystem.getTiles())
			if (o!=null)
				if (((TileUserData) o.getUserData())!=null)
					if (((TileUserData) o.getUserData()).object!=null)
						((TileUserData) o.getUserData()).object.update(arg0, arg2);
		
		
		for (AbstractEnemy e : objects)
		{
			if (e.getUserData() instanceof EnemyUserData)
			{
				if (e.getUserData()!=null)
				{
					((EnemyUserData)e.getUserData()).delta_count+=arg2;
					
					EnemyUserData ud = (EnemyUserData) e.getUserData();
					PathWrapper pw = ud.pw;
					
					
					//something inside here causes it to be off by 1 tile at the end
					if (pw.hasNextStep() && ud.delta_count>=e.getSpeed()) {
							if (ud.tweener==null) {
								ud.tweener = new Tweener(e.getPosition(),pw.getNextLocation());
							}
							
							if (!ud.tweener.isFinished())
								e.setPosition(ud.tweener.getNextPoint());
							else
								ud.tweener = null;
							
							
							((EnemyUserData)e.getUserData()).delta_count = 0;
						}
						
				}
			}
		}
								
			/*	Vector2i motion = new Vector2i();
				
				if (((EnemyUserData)o.getUserData()).next.getX() > ((EnemyUserData)o.getUserData()).current.getX())
					motion.add(new Vector2i(1,0));
				else if (((EnemyUserData)o.getUserData()).next.getX() < ((EnemyUserData)o.getUserData()).current.getX())
					motion.add(new Vector2i(-1,0));
				
				if (((EnemyUserData)o.getUserData()).next.getY() > ((EnemyUserData)o.getUserData()).current.getY())
					motion.add(new Vector2i(0,1));
				else if (((EnemyUserData)o.getUserData()).next.getY() < ((EnemyUserData)o.getUserData()).current.getY())
					motion.add(new Vector2i(0,1));
				
				o.setPosition(Vector2i.add(o.getPosition(), motion));
			*/
			
		
	}
	
	private int burst_delta_count = 0;
	private void burst_spawn(int delta)
	{
		burst_delta_count += delta;
		if (burst_delta_count>(850 + (int)(Math.random() * ((2000 - 850) + 1))))//randomized within a range 850-1400
		{
			burst_delta_count=0;
			//this is spawning code
			EnemyUserData userData = new EnemyUserData(new PathWrapper(path,50,50));
			objects.add(new Boy(new Vector2i(0,0),5,userData));//(int)(Math.random() * ((1000 - 500) + 1)),userData));//randomized speed within a range 1000-500
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
