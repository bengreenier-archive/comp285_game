package core;

import org.lwjgl.input.Keyboard;


public class Character extends GameObject{

	public Character(util.Vertex point,float size)
	{
		super();
		
		this.GL_TYPE=drawing.GL_TYPES.QUAD;
		this.setColor(new util.RGBA(0, 255, 0, 1));
		this.setVertices(util.Vertex.createSquare(new util.Vertex(10,10), 20));
		
		
		
		this.setEvent(new input.Event(){

			@Override
			public void pollInput() {
				// TODO Auto-generated method stub
				input.BooleanVector bvec = new input.BooleanVector(4);//create a new bvec with 4 toggles, for directions w(0),a(1),s(2),d(3)
				
				float SPEED_CONSTANT=0.2f;//define speed multiplier thingy
				
				while (Keyboard.next()) {
				    if (Keyboard.getEventKeyState()) {
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
				    } else {
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
				}
				
				//now query the bvec to see what's true, and concatenate a Vertex, from the true directions, using the accel vertex as a multiplier/constant
				//essentially generating the motion vector
				util.Vertex vtex = self.getPos();
				//[DO IT]
				if (bvec.storage[0])
					vtex.shift(new util.Vertex(0,SPEED_CONSTANT));
				if (bvec.storage[1])
					vtex.shift(new util.Vertex(-1*SPEED_CONSTANT,0));
				if (bvec.storage[2])
					vtex.shift(new util.Vertex(0,-1*SPEED_CONSTANT));
				if (bvec.storage[3])
					vtex.shift(new util.Vertex(SPEED_CONSTANT,0));
				
				self.setPos(vtex);
			}
			
			
		});
	}

	
}
