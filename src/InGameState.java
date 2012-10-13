
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

/**
 * Simple platform single state to render the tile map and the entities, update
 * the physical world and allow player input
 * 
 * @author kevin
 */
public class InGameState extends BasicGameState {
	/** The unique ID given to the state */
	private static final int ID = 1;
	
	/** The environment in which the physics demo is taking place */
	private Environment env;
	/** The player that is being controlled */
	private Actor player;
	/** The view x-axis offset */
	private float xoffset;
	/** The view y-axis offset */
	private float yoffset;
	/** The background image to be displayed */
	private Image background;
	
	/** The amount of time passed since last control check */
	private int totalDelta;
	/** The interval to check the controls at */
	private int controlInterval = 50;
	/** True if we're showing the bounds of the environment's shapes */
	private boolean showBounds = false;
	
	/** stores not my player positions, when acting as server*/
	private ArrayList<Vec4f> others = new ArrayList<Vec4f>();
	
	/**
	 * @see org.newdawn.slick.state.BasicGameState#getID()
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @see org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		background = new Image("res/genetica/Other/farbkg.png");
		
		restart();
	}

	/**
	 * Restart the demo
	 * 
	 * @throws SlickException Indicates a failure to load resources
	 */
	private void restart() throws SlickException {
		TileSet set = new TileSet("res/tiles.xml");
		MapLoader loader = new MapLoader(set);
		TileEnvironment env = loader.load("res/testmap.txt");
		env.setImageSize(32,32);
		env.init();
		
		player = new Alien(100,150,1f,24);
		env.addEntity(player);
		env.addEntity(new Crate(545,100, 46,46,5));
		env.addEntity(new Crate(545,30,46,46,5));
		env.addEntity(new Crate(545,0,46,46,5));
		env.addEntity(new Crate(545,130,46,46,5));
		env.addEntity(new Crate(200,30,46,46,5));
		env.addEntity(new Star(10,10,10,3,30,30));
		
		boolean server = true;
		boolean client = false;
		
		if (server)
		{
			Server srv = new Server();
			Kryo kryo = srv.getKryo();
			kryo.register(Vec4f.class);
			
			srv.start();
			try {
				srv.bind(54555, 54777);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//the saving to the arraylist, listener
			srv.addListener(new Listener() {
				   public void received (Connection connection, Object object) {
				      if (object instanceof Vec4f) {
				         Vec4f incoming = (Vec4f)object;
				         if (others.contains(incoming))
				        	 others.get(others.indexOf(incoming)).set(incoming);
				         else
				        	 others.add(incoming);
				         
				      }
				   }
				});
			
			//the telling others/our position(s), listener
			srv.addListener(new Listener() {
				   public void received (Connection connection, Object object) {
				     for (Vec4f obj : others)
					   connection.sendUDP(obj);
				     
				     //ours
				     connection.sendUDP(new Vec4f(player.getX(),player.getY(),player.getVelX(),player.getVelY()));
				   }
				});
		}
		
		if (client)
		{
			
			Client clnt = new Client();
			
			Kryo kryo = clnt.getKryo();
			kryo.register(Vec4f.class);
			
			
			clnt.start();
			try {
				clnt.connect(5000, clnt.discoverHost(54777, 5000), 54555, 54777);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//store the other we were just given, if it's new, otherwise update the old version
			clnt.addListener(new Listener() {
				   public void received (Connection connection, Object object) {
					   if (object instanceof Vec4f) {
					         Vec4f incoming = (Vec4f)object;
					         if (others.contains(incoming))
					        	 others.get(others.indexOf(incoming)).set(incoming);
					         else
					        	 others.add(incoming);
					         
					      }
				   }
				});
		}
		
		
		
		this.env = env;
	}
	
	/**
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		float width = container.getWidth();
		float height = container.getHeight();
		float backPar = 3f;
		float bx = ((-xoffset * backPar) % width) / -width;
		float by = ((-yoffset * backPar) % height) / -height;
		background.bind();
		g.setColor(Color.white);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(bx,by);
			GL11.glVertex2f(0,0);
			GL11.glTexCoord2f(bx+3,by);
			GL11.glVertex2f(width,0);
			GL11.glTexCoord2f(bx+3,by+3);
			GL11.glVertex2f(width,height);
			GL11.glTexCoord2f(bx,by+3);
			GL11.glVertex2f(0,height);
		GL11.glEnd();
		
		g.translate(-(int) xoffset, -(int) yoffset);
		
		env.render(g);
		if (showBounds) {
			env.renderBounds(g);
		}
		
		g.translate((int) xoffset, (int) yoffset);
		
		drawString(g,"Cursors - Move   Space - Jump   B - Show Bounds   R - Restart", 580);
	}

	/**
	 * Draw an clear string centred horizontally
	 * 
	 * @param g The graphics context on which to draw the string
	 * @param str The string to draw
	 * @param y The vertical location to draw at
	 */
	private void drawString(Graphics g, String str, int y) {
		int x = (800 - g.getFont().getWidth(str)) / 2;
		
		g.setColor(Color.black);
		g.drawString(str, x+1,y+1);
		g.setColor(Color.white);
		g.drawString(str, x,y);
		
	}
	
	/**
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		
		if (input.isKeyPressed(Input.KEY_ESCAPE))
			System.exit(0);
		
		// restart and bounds toggling
		if (input.isKeyPressed(Input.KEY_R)) {
			restart();
			return;
		}
		if (input.isKeyPressed(Input.KEY_B)) {
			showBounds = !showBounds;
		}

		// the forces applied for different actions. The move force is applied over and
		// over so is reasonably small. The jump force is a one shot deal and so is reasonably
		// big
		float moveForce = 100;
		float jumpForce = 1000;
		
		totalDelta += delta;
		
		

		
		// setup the player's moving flag, this control the animation
		player.setMoving(false);
		if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
			player.setMoving(true);
		}
		if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
			player.setMoving(true);
		}
		
		// only check controls at set interval. If we don't do this different
		// frame rates will effect how the controls are interpreted
		if (totalDelta > controlInterval) {
			controlInterval -= totalDelta;
			
			if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
				player.applyForce(-moveForce, 0);
			}
			if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
				player.applyForce(moveForce, 0);
			}
			if (input.isKeyDown(Input.KEY_V))
			{
				player.setRunning(true);
				if (player.facingRight())
					player.applyForce(2*moveForce,0);
				else
					player.applyForce(-2*moveForce,0);
			}else
				player.setRunning(false);
			
			
			if (player.onGround()) {
				if (input.isKeyPressed(Input.KEY_SPACE)) {
					if (input.isKeyDown(Input.KEY_LSHIFT))
						{
							player.applyForce(0, -(2*jumpForce));
						}else
						{
							player.applyForce(0, -jumpForce);
						}
					}
				
			}
			if (!input.isKeyDown(Input.KEY_SPACE)) {
				if (player.jumping()) {
					player.setVelocity(player.getVelX(), player.getVelY() * 0.95f);
				}
			}
		}
		
		// update the environemnt and hence the physics world
		env.update(delta);
		
		// calculate screen position clamping to the bounds of the level
		xoffset = player.getX() - 400;
		yoffset = player.getY() - 300;
		
		Rectangle bounds = env.getBounds();
		if (xoffset < bounds.getX()) {
			xoffset = bounds.getX();
		}
		if (yoffset < bounds.getY()) {
			yoffset = bounds.getY();
		}
		
		if (xoffset > (bounds.getX() + bounds.getWidth()) - 800) {
			xoffset = (bounds.getX() + bounds.getWidth()) - 800;
		}
		if (yoffset > (bounds.getY() + bounds.getHeight()) - 600) {
			yoffset = (bounds.getY() + bounds.getHeight()) - 600;
		}
		
		Client clnt = new Client();
		clnt.start();
		try {
			clnt.connect(5000, clnt.discoverHost(54777, 5000), 54555, 54777);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clnt.sendUDP(new Vec4f(player.getX(),player.getY(),player.getVelX(),player.getVelY()));
	}

}
