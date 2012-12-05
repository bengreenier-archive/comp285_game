package com.bengreenier.smashgrab.states;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Random;

import java.util.concurrent.CopyOnWriteArrayList;

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
import com.bengreenier.smashgrab.enemies.Still;
import com.bengreenier.smashgrab.main.Main;
import com.bengreenier.smashgrab.util.EnemyUserData;
import com.bengreenier.smashgrab.util.PathWrapper;
import com.bengreenier.smashgrab.util.TileUserData;
import com.bengreenier.smashgrab.util.Tweener;
import com.bengreenier.smashgrab.util.WaveManager;
import com.bengreenier.smashgrab.util.WaveManager.Wave;

public class Run implements GameState {

	private int id;
	private TileSystem tileSystem;
	private Path path;
	private CopyOnWriteArrayList<AbstractEnemy> objects;

	private CopyOnWriteArrayList<Explosion> anims;
	private CopyOnWriteArrayList<MachineGunBullet> bullets;
	private int remaining_good_life;
	private Image background;
	private WaveManager waveManager;

	
	public Run(int id)
	{
		this.id = id;
		tileSystem = Main.core.tileSystem;
		objects = new CopyOnWriteArrayList<AbstractEnemy>();
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

		configureWaveManager();
		

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {

		background = new Image("res/runStateBackground.png");
		
		

		
	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Main.core.tileSystem = tileSystem;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		
		
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

		//burst_spawn(arg2);
		
		
		
		/*if (arg0.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))
		{
			EnemyUserData userData = new EnemyUserData(new PathWrapper(path,50,50));
			objects.add(new Still(new Vector2i(arg0.getInput().getMouseX(),arg0.getInput().getMouseY()),8,userData));
		}*/
			
		
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
					if (ud.delta_count>=e.getSpeed()) {
							if (pw.hasNextStep() && ud.tweener==null) {
								ud.tweener = new Tweener(e.getPosition(),pw.getNextLocation());
							}
							
							if (ud.tweener != null){
								if (!ud.tweener.isFinished())
									e.setPosition(ud.tweener.getNextPoint());
								else
									ud.tweener = null;
							}
							
							((EnemyUserData)e.getUserData()).delta_count = 0;
						}
						
				}
			}
			
			
			if (e.isDead())
			{
				objects.remove(e);
			}
		}
								
		for (Tile t : tileSystem.getTiles())
		{
			if (((TileUserData) t.getUserData()) != null)
				if (((TileUserData) t.getUserData()).object != null)
				{
					//find dudes in range
					ArrayList<AbstractEnemy> inRange = new ArrayList<AbstractEnemy>();
					for (AbstractEnemy e : objects)
					{
						
						Vector2i d  = Vector2i.subtract(Vector2i.add(((TileUserData)t.getUserData()).object.getGridPosition(), new Vector2i(25,25)),Vector2i.add(e.getPosition(),new Vector2i(25,25)));
						d.setX(Math.abs(d.getX()));
						d.setY(Math.abs(d.getY()));
						double distance = Math.sqrt(Math.pow(d.getX(),2)+Math.pow(d.getY(),2));
						if (distance <= ((TileUserData)t.getUserData()).object.getRange())
							inRange.add(e);
						
					}
					//doDamage to dudes in range
					((TileUserData)t.getUserData()).object.doDamage(inRange, arg2);
				}
		}
		
	}
	
	private int burst_delta_count = 0;
	private void burst_spawn(int delta)
	{
		burst_delta_count += delta;
		if (burst_delta_count>(850 + (int)(Math.random() * ((2000 - 850) + 1))))//randomized within a range 850-1400
		{
			burst_delta_count=0;
			//this is spawning code

			if (waveManager.getNextWave()!=null)
				for (AbstractEnemy e : waveManager.getNextWave().getEnemies())
					objects.add(e);
			else
				System.out.println("NULL WHORE");
			/*EnemyUserData userData = new EnemyUserData(new PathWrapper(path,50,50));
			
			if (Math.random()>0.5)
				objects.add(new Boy(new Vector2i(0,0),8,userData));//(int)(Math.random() * ((7 - 2) + 1)),userData));//randomized speed within a range 1000-500
			else
				objects.add(new Bug(new Vector2i(0,0),8,userData));*/

		}
	}
	

	
	public void configureWaveManager() {
		waveManager = new WaveManager();
		waveManager.addWave(new Wave(generateEnemies()));
		waveManager.addWave(new Wave(generateEnemies()));
		waveManager.addWave(new Wave(generateEnemies()));
	}
	
	private Collection<AbstractEnemy> generateEnemies() {
		ArrayList<AbstractEnemy> list = new ArrayList<AbstractEnemy>();
		
		EnemyUserData userData = new EnemyUserData(new PathWrapper(path,50,50));
		for (int i=0;i<Math.random()*20;i++)
		if (Math.random()>0.5)
			list.add(new Boy(new Vector2i(0,0),8,userData));
		else
			list.add(new Bug(new Vector2i(0,0),10,userData));
		
		return list;
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
