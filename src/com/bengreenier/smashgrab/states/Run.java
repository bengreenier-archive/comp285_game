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
import com.bengreenier.slick.util.GameObject;
import com.bengreenier.slick.util.Vector2i;

import com.bengreenier.smashgrab.enemies.Boy;
import com.bengreenier.smashgrab.main.Main;
import com.bengreenier.smashgrab.util.TileUserData;

public class Run implements GameState {

	private int id;
	private TileSystem tileSystem;
	private Path path;
	private ArrayList<GameObject> objects;
	
	public Run(int id)
	{
		this.id = id;
		tileSystem = Main.core.tileSystem;
		objects = new ArrayList<GameObject>();
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
		objects.add(new Boy(new Vector2i(),path));
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
		
		for (GameObject o : objects)
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
		for (GameObject o : objects)
			o.update(arg0, arg2);
		
		
		
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
