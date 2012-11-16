package com.bengreenier.smashgrab.states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bengreenier.smashgrab.ribbons.Ribbon;
import com.bengreenier.smashgrab.ribbons.RibbonItem;

import com.bengreenier.slick.tiling.TileView;
import com.bengreenier.slick.util.GameObject;
import com.bengreenier.slick.util.Vector2i;

public class Build implements GameState {

	private ArrayList<GameObject> objects;
	private Ribbon ribbon;
	private TileView tv;
	private Image mouseSprite;
	private int id;
	
	public Build(int id)
	{
		this.id = id;
		objects = new ArrayList<GameObject>();
		ribbon = new Ribbon("#3399CC",802,80,0,500);
		tv = new TileView(16, 12, 50, 50);
		mouseSprite=null;
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
		
		final Build localFinalBuildPointer = this;
		
		arg0.getInput().addListener(ribbon);
		
		
		
		ribbon.addRibbonItem(new RibbonItem(){
			
			private Vector2i start;
			
			
			@Override
			public void instantiate()
			{
				try {
					addImageAndResize(new Image("res/MachineGunTower.png"));
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void mousePressed(int arg0, int arg1, int arg2)
			{
				if (arg0 == Input.MOUSE_LEFT_BUTTON && inBounds(arg1,arg2))
					{
						start = new Vector2i(arg1,arg2);
						//bad practice, creating an Image here
						Image t=null;
						try {
							t = new Image("res/MachineGunTower.png").getScaledCopy(.5f);
						} catch (SlickException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if (t!=null)
						localFinalBuildPointer.mouseSprite = t;
					}
			}
			
			@Override
			public void mouseReleased(int arg0, int arg1, int arg2)
			{
				if (arg0 == Input.MOUSE_LEFT_BUTTON && start!=null)
				{
					System.out.println("you moved from "+start+" to "+new Vector2i(arg1,arg2));
					localFinalBuildPointer.mouseSprite=null;
				}
			}
			
		});
		
		ribbon.addRibbonItem(new RibbonItem(){
			
			private Vector2i start;
			
			
			@Override
			public void instantiate()
			{
				try {
					addImageAndResize(new Image("res/RocketTower.png"));
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void mousePressed(int arg0, int arg1, int arg2)
			{
				if (arg0 == Input.MOUSE_LEFT_BUTTON && inBounds(arg1,arg2))
					{
						start = new Vector2i(arg1,arg2);
						//bad practice, creating an Image here
						Image t=null;
						try {
							t = new Image("res/RocketTower.png").getScaledCopy(.5f);
						} catch (SlickException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if (t!=null)
						localFinalBuildPointer.mouseSprite = t;
					}
			}
			
			@Override
			public void mouseReleased(int arg0, int arg1, int arg2)
			{
				if (arg0 == Input.MOUSE_LEFT_BUTTON && start!=null)
				{
					System.out.println("you moved from "+start+" to "+new Vector2i(arg1,arg2));
					localFinalBuildPointer.mouseSprite = null;
				}
			}
			
		});

		ribbon.addRibbonItem(new RibbonItem(){
			
			private Vector2i start;
			
			
			@Override
			public void instantiate()
			{
				try {
					addImageAndResize(new Image("res/LaserTower.png"));
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void mousePressed(int arg0, int arg1, int arg2)
			{
				if (arg0 == Input.MOUSE_LEFT_BUTTON && inBounds(arg1,arg2))
					{
						start = new Vector2i(arg1,arg2);
						//bad practice, creating an Image here
						Image t=null;
						try {
							t = new Image("res/LaserTower.png").getScaledCopy(.5f);
						} catch (SlickException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						if (t!=null)
						localFinalBuildPointer.mouseSprite = t;
					}
			}
			
			@Override
			public void mouseReleased(int arg0, int arg1, int arg2)
			{
				if (arg0 == Input.MOUSE_LEFT_BUTTON && start!=null)
				{
					System.out.println("you moved from "+start+" to "+new Vector2i(arg1,arg2));
					localFinalBuildPointer.mouseSprite = null;
				}
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
		else
			try{throw new Exception("Ribbon is null");}catch(Exception e){e.printStackTrace();}
		
		/*if (tv != null)
			tv.debugDraw(arg0, arg2);
		else
			try{throw new Exception("TileView is null");}catch(Exception e){e.printStackTrace();}
		*/
		for (GameObject o : objects)
			o.render(arg0, arg2);
		
		
		if (mouseSprite!=null)
			mouseSprite.draw(arg0.getInput().getMouseX(),arg0.getInput().getMouseY());
		
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
