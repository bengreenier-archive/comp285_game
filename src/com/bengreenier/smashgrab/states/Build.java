package com.bengreenier.smashgrab.states;

import org.newdawn.slick.Color;
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
import com.bengreenier.smashgrab.towers.EndPoint;
import com.bengreenier.smashgrab.towers.LaserTower;
import com.bengreenier.smashgrab.towers.MachineGunTower;
import com.bengreenier.smashgrab.towers.RocketTower;
import com.bengreenier.smashgrab.util.TileUserData;

import com.bengreenier.slick.tiling.TileSystem.Tile;
import com.bengreenier.slick.tiling.TileSystem;
import com.bengreenier.slick.util.Vector2i;

public class Build implements GameState {

	private Ribbon ribbon;
	private TileSystem tileSystem;
	private Image mouseSprite;
	@SuppressWarnings("rawtypes")
	private Class mouseType;
	private int id;
	private Image background;
	
	public Build(int id)
	{
		this.id = id;
		ribbon = new Ribbon("#3399CC",802,80,0,500);
		mouseSprite=null;
		mouseType=null;
		tileSystem = Main.core.tileSystem;//do this on enter too
	}
	
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		tileSystem = Main.core.tileSystem;//do this in init too
		
		
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
		
		background = new Image("res/runStateBackground.png");
		
		arg0.getInput().addListener(ribbon);//makes it so the ribbon can listen to input
		
		//add our platform
		try {
			tileSystem.getTile(15, 9).setUserData(new TileUserData(new EndPoint(tileSystem.reverseLocate(15, 9))));
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
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
					
					int c_loop=0;
					while (c == null && c_loop<5)
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
						Vector2i rev_loc = null;
						try {
							rev_loc = tileSystem.reverseLocate(c);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (rev_loc != null)
						{
							MachineGunTower tower = new MachineGunTower(new Vector2i(arg1,arg2),rev_loc,30);
							Tile t = null;
							try {
								t = localFinalBuildPointer.tileSystem.getTile(c);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (t!=null)
							{
								t.setFilled(true);
								if (tileSystem.getAStarPath(0, 0, tileSystem.getWidthInTiles()-1, tileSystem.getHeightInTiles()-1)!=null)
								{
								t.setUserData(new TileUserData(tower));
								}else
								{
									t.setFilled(false);
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
					while (c == null && c_loop<5)
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
						Vector2i rev_loc = null;
						try {
							rev_loc = tileSystem.reverseLocate(c);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (rev_loc != null)
						{
							RocketTower tower = new RocketTower(new Vector2i(arg1,arg2),rev_loc,30);
							Tile t = null;
							try {
								t = localFinalBuildPointer.tileSystem.getTile(c);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (t!=null)
							{
								t.setFilled(true);
								if (tileSystem.getAStarPath(0, 0, tileSystem.getWidthInTiles()-1, tileSystem.getHeightInTiles()-1)!=null)
								{
								t.setUserData(new TileUserData(tower));
								}else
								{
									t.setFilled(false);
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
					while (c == null && c_loop<5)
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
						Vector2i rev_loc = null;
						try {
							rev_loc = tileSystem.reverseLocate(c);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (rev_loc != null)
						{
							
							LaserTower tower = new LaserTower(new Vector2i(arg1,arg2),rev_loc,30);
							Tile t = null;
							try {
								t = localFinalBuildPointer.tileSystem.getTile(c);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (t!=null)
							{
								t.setFilled(true);
								if (tileSystem.getAStarPath(0, 0, tileSystem.getWidthInTiles()-1, tileSystem.getHeightInTiles()-1)!=null)
								{
								t.setUserData(new TileUserData(tower));
								}else
								{
									t.setFilled(false);
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
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		if(background!=null)
			background.draw(0,0);
		
		//draw our grid
				Color t = arg2.getColor();
				arg2.setColor(Color.gray);
				for (int i=0;i<tileSystem.getWidthInTiles();i++)
					for (int j=0;j<tileSystem.getHeightInTiles();j++)
					{
						arg2.drawRect(i*50, j*50, 50, 50);
					}
				arg2.setColor(t);
				
		
		
		if (ribbon!=null)
			ribbon.draw(arg2);
		else
			try{throw new Exception("Ribbon is null");}catch(Exception e){e.printStackTrace();}
		
		
		for (Tile o : tileSystem.getTiles())
			if (o!=null)
				if (((TileUserData) o.getUserData())!=null)
					if (((TileUserData) o.getUserData()).object!=null)
						((TileUserData) o.getUserData()).object.render(arg0, arg2);
		
		
		if (mouseSprite!=null)
			mouseSprite.draw(arg0.getInput().getMouseX(),arg0.getInput().getMouseY());
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {

		
		
		
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
