package com.bengreenier.smashgrab.states;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bengreenier.smashgrab.main.Main;
import com.bengreenier.smashgrab.ribbons.Ribbon;
import com.bengreenier.smashgrab.ribbons.RibbonItem;
import com.bengreenier.smashgrab.towers.LaserTower;
import com.bengreenier.smashgrab.towers.MachineGunTower;
import com.bengreenier.smashgrab.towers.RocketTower;

import com.bengreenier.slick.tiling.TileSystem.Tile;
import com.bengreenier.slick.tiling.TileSystem;
import com.bengreenier.slick.util.GameObject;
import com.bengreenier.slick.util.Vector2i;

public class Build implements GameState {

	private ArrayList<GameObject> objects;
	private Ribbon ribbon;
	private TileSystem tileSystem;
	private Image mouseSprite;
	@SuppressWarnings("rawtypes")
	private Class mouseType;
	private int id;
	
	public Build(int id)
	{
		this.id = id;
		objects = Main.core.tileObjects;
		ribbon = new Ribbon("#3399CC",802,80,0,500);
		mouseSprite=null;
		mouseType=null;
		tileSystem = Main.core.tileSystem;//do this on enter too
	}
	
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		tileSystem = Main.core.tileSystem;//do this in init too
		objects = Main.core.tileObjects;
		
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
		final StateBasedGame localFinalStateBasedGame = arg1;
		
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
						{
							localFinalBuildPointer.mouseSprite = t;
							localFinalBuildPointer.mouseType = MachineGunTower.class;
						}
					}
			}
			
			@Override
			public void mouseReleased(int arg0, int arg1, int arg2)
			{
				if (localFinalBuildPointer.mouseType!=null)
				if (arg0 == Input.MOUSE_LEFT_BUTTON && start!=null && localFinalBuildPointer.mouseType.equals(MachineGunTower.class))
				{
					localFinalBuildPointer.mouseSprite=null;
					localFinalBuildPointer.mouseType = null;
					Vector2i c=null;
					try {
						c = localFinalBuildPointer.tileSystem.locate(arg1, arg2);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					int c_loop=1;
					while (c == null)
					{
						try {
							c = localFinalBuildPointer.tileSystem.locate(arg1+(1*c_loop), arg2+(1*c_loop));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						c_loop++;
					}
					if (c!=null)
					{
						
						//damn messy way to fill 4 tiles, and check that they aren't filled first...
						boolean ok=true;
						MachineGunTower mgt=null;
						try {
							mgt = new MachineGunTower(new Vector2i(arg1,arg2),localFinalBuildPointer.tileSystem.reverseLocate(c),30);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for (int x=0;x<2;x++)
							for (int y=0;y<2;y++)
							{
								Vector2i cell = Vector2i.add(c, new Vector2i(x,y));
								
								Tile t=null;
								try {
									t = localFinalBuildPointer.tileSystem.getTile(cell);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if (t==null)
									ok=false;
								else
									if (t.isFilled())
									{
										ok=false;
									}
									
							}
						
						if (ok)
						{
							for (int x=0;x<2;x++)
								for (int y=0;y<2;y++)
								{
									Vector2i cell = Vector2i.add(c, new Vector2i(x,y));
									
									Tile t=null;
									try {
										t = localFinalBuildPointer.tileSystem.getTile(cell);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									if (t!=null)
									{
										if (x==0&&y==0)
											localFinalBuildPointer.objects.add(mgt);
										
										t.setUserData(mgt);
									}
								}
							
						}
						 
						
						
					}
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
						{
							localFinalBuildPointer.mouseSprite = t;
							localFinalBuildPointer.mouseType = RocketTower.class;
						}
					}
			}
			
			@Override
			public void mouseReleased(int arg0, int arg1, int arg2)
			{
				if (localFinalBuildPointer.mouseType!=null)
				if (arg0 == Input.MOUSE_LEFT_BUTTON && start!=null && localFinalBuildPointer.mouseType.equals(RocketTower.class))
				{
					localFinalBuildPointer.mouseSprite = null;
					localFinalBuildPointer.mouseType = null;
					Vector2i c=null;
					try {
						c = localFinalBuildPointer.tileSystem.locate(arg1, arg2);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int c_loop=1;
					while (c == null)
					{
						try {
							c = localFinalBuildPointer.tileSystem.locate(arg1+(1*c_loop), arg2+(1*c_loop));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						c_loop++;
					}
					
					if (c!=null)
					{
						//damn messy way to fill 4 tiles, and check that they aren't filled first...
						boolean ok=true;
						RocketTower mgt=null;
						try {
							mgt = new RocketTower(new Vector2i(arg1,arg2),localFinalBuildPointer.tileSystem.reverseLocate(c),30);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for (int x=0;x<2;x++)
							for (int y=0;y<2;y++)
							{
								Vector2i cell = Vector2i.add(c, new Vector2i(x,y));
								
								Tile t=null;
								try {
									t = localFinalBuildPointer.tileSystem.getTile(cell);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if (t==null)
									ok=false;
								else
									if (t.isFilled())
									{
										ok=false;
									}
									
							}
						
						if (ok)
						{
							for (int x=0;x<2;x++)
								for (int y=0;y<2;y++)
								{
									Vector2i cell = Vector2i.add(c, new Vector2i(x,y));
									
									Tile t=null;
									try {
										t = localFinalBuildPointer.tileSystem.getTile(cell);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									if (t!=null)
									{
										if (x==0&&y==0)
											localFinalBuildPointer.objects.add(mgt);
										
										t.setUserData(mgt);
									}
								}
							
						}
						 
						
					
					}
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
						{
							localFinalBuildPointer.mouseSprite = t;
							localFinalBuildPointer.mouseType = LaserTower.class;
						}
						
					}
			}
			
			@Override
			public void mouseReleased(int arg0, int arg1, int arg2)
			{
				if (localFinalBuildPointer.mouseType!=null)
				if (arg0 == Input.MOUSE_LEFT_BUTTON && start!=null && localFinalBuildPointer.mouseType.equals(LaserTower.class))
				{
					localFinalBuildPointer.mouseSprite = null;
					localFinalBuildPointer.mouseType = null;
					Vector2i c=null;
					try {
						c = localFinalBuildPointer.tileSystem.locate(arg1, arg2);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					int c_loop=1;
					while (c == null)
					{
						try {
							c = localFinalBuildPointer.tileSystem.locate(arg1+(1*c_loop), arg2+(1*c_loop));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						c_loop++;
					}
					
					if (c!=null)
					{
						//damn messy way to fill 4 tiles, and check that they aren't filled first...
						boolean ok=true;
						LaserTower mgt=null;
						try {
							mgt = new LaserTower(new Vector2i(arg1,arg2),localFinalBuildPointer.tileSystem.reverseLocate(c),30);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for (int x=0;x<2;x++)
							for (int y=0;y<2;y++)
							{
								Vector2i cell = Vector2i.add(c, new Vector2i(x,y));
								
								Tile t=null;
								try {
									t = localFinalBuildPointer.tileSystem.getTile(cell);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								if (t==null)
									ok=false;
								else
									if (t.isFilled())
									{
										ok=false;
									}
									
							}
						
						if (ok)
						{
							for (int x=0;x<2;x++)
								for (int y=0;y<2;y++)
								{
									Vector2i cell = Vector2i.add(c, new Vector2i(x,y));
									
									Tile t=null;
									try {
										t = localFinalBuildPointer.tileSystem.getTile(cell);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									if (t!=null)
									{
										if (x==0&&y==0)
											localFinalBuildPointer.objects.add(mgt);
										
										t.setUserData(mgt);
									}
								}
							
						}
						 
						
					
					}
				}
			}
			
		});
		
		ribbon.addRibbonItem(new RibbonItem(){
			@Override
			public void instantiate()
			{
				try {
					addImageAndResize(new Image("res/BuildGo.png"));
				} catch (SlickException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void mousePressed(int arg0, int arg1, int arg2) {
				if (Input.MOUSE_LEFT_BUTTON == arg0 && inBounds(arg1,arg2))
				localFinalStateBasedGame.enterState(Main.ID.RUN);
			}
		});
		
	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		Main.core.tileSystem = tileSystem;
		Main.core.tileObjects = objects;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		if (ribbon!=null)
			ribbon.draw(arg2);
		else
			try{throw new Exception("Ribbon is null");}catch(Exception e){e.printStackTrace();}
		
		if (tileSystem != null)
			tileSystem.draw(0, 0);
		else
			try{throw new Exception("tileSystem is null");}catch(Exception e){e.printStackTrace();}
		
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
