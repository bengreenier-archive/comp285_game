package com.bengreenier.smashgrab.states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bengreenier.smashgrab.ribbons.Ribbon;
import com.bengreenier.smashgrab.ribbons.RibbonItem;

import com.bengreenier.slick.util.GameObject;

public class Build implements GameState {

	private ArrayList<GameObject> objects;
	private Ribbon ribbon;
	private int id;
	
	public Build(int id)
	{
		this.id = id;
		objects = new ArrayList<GameObject>();
		ribbon = new Ribbon("#A60000",802,80,0,700);
	}
	
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Input in = arg0.getInput();
		in.clearControlPressedRecord();
		in.clearKeyPressedRecord();
		in.clearMousePressedRecord();
		
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
		ribbon.addRibbonItem(new RibbonItem(){
			@Override
			public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
				System.out.println("Dragged from "+arg0+","+arg1+" "+arg2+","+arg3);
			}
		});
		
		
	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		if (ribbon!=null)
			ribbon.draw(arg2);
		
		for (GameObject o : objects)
			o.render(arg0, arg2);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {

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
