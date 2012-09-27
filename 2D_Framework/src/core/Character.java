package core;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.dynamics.BodyDef;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import physics.Physics;
import util.RGBA;


public class Character extends GameObject{
	private input.BooleanVector bvec;
	private float SPEED;
	public Character(util.Vertex point,float size)
	{
		super();
		bvec = new input.BooleanVector(4);//create a new bvec with 4 toggles, for directions w(0),a(1),s(2),d(3)
		setSpeed(0.03f);
		instantiate();
	}
	
	//define the default instatiate method
	public void instantiate()
	{
		GL_TYPE=drawing.GL_TYPES.QUAD;
		setColor(new util.RGBA(0, 255, 0, 1));
		setVertices(util.Vertex.createSquare(new util.Vertex(10,10), 20));
		BodyDef bdef = physics.Physics.createBodyDef(new util.Vertex(100,100), 0, "DYNAMIC", true, false);
		//create shape fixture, and set density.
		
		setEvent(new input.Event(){

			@Override
			public void mouse() {
				// TODO Auto-generated method stub
				if (Mouse.isButtonDown(0))
				{
					
					
					//get the Default mainPtr, set to the Main instance, and get its renderloop, then call spawn on that
					//renderloop, creating a gameobject in that context, on the start of the next game loop
					core.Main.mainPtr.getRenderLoop().spawn(new core.GameObject(){
						@Override
						public void instantiate()
						{
							setVertices(util.Vertex.createSquare(new util.Vertex(Mouse.getX(), Mouse.getY()), 2));
							setColor(new util.RGBA(255,0,0,1));
							GL_TYPE=drawing.GL_TYPES.QUAD;
						}
						
					});
					
				}
			}

			@Override
			public void other() {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void upKeys() {
				// TODO Auto-generated method stub
				if (Keyboard.getEventKey() == Keyboard.KEY_W || Keyboard.getEventKey() == Keyboard.KEY_UP) {
		    		bvec.storage[0]=false;
		    	}
		        if (Keyboard.getEventKey() == Keyboard.KEY_A || Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
		        	bvec.storage[1]=false;
		        }
		    	if (Keyboard.getEventKey() == Keyboard.KEY_S || Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
		    		bvec.storage[2]=false;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D || Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
					bvec.storage[3]=false;
				}
			}


			@Override
			public void downKeys() {
				// TODO Auto-generated method stub
				
				if (Keyboard.getEventKey() == Keyboard.KEY_W || Keyboard.getEventKey() == Keyboard.KEY_UP) {
		    		bvec.storage[0]=true;
		    	}
		        if (Keyboard.getEventKey() == Keyboard.KEY_A || Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
			    bvec.storage[1]=true;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S || Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
				    bvec.storage[2]=true;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D || Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
					bvec.storage[3]=true;
				}
			}

			@Override
			public void postKeys() {
				
				
				//now query the bvec to see what's true, and concatenate a Vertex, from the true directions, using the accel vertex as a multiplier/constant
				//essentially generating the motion vector
				
				util.Vertex vtex = getPos();//this could be slowing things down, eventually
				
				
				//[DO IT]
				if (bvec.storage[0])
					vtex.shift(new util.Vertex(0,getSpeed()));
				if (bvec.storage[1])
					vtex.shift(new util.Vertex(-1*getSpeed(),0));
				if (bvec.storage[2])
					vtex.shift(new util.Vertex(0,-1*getSpeed()));
				if (bvec.storage[3])
					vtex.shift(new util.Vertex(getSpeed(),0));
				
				if (bvec.storage[0] || bvec.storage[1] || bvec.storage[2] || bvec.storage[3])
				setVertices(util.Vertex.createSquare(vtex, 20));
				
			}
			
			@Override
			public void preKeys() {
				// TODO Auto-generated method stub
				
			}
			
			
		});

		
	}

	/**
	 * @return the sPEED
	 */
	public float getSpeed() {
		return SPEED;
	}

	/**
	 * @param sPEED the sPEED to set
	 */
	public void setSpeed(float sPEED) {
		SPEED = sPEED;
	}

	
}
