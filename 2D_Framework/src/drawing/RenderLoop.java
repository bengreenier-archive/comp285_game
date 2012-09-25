package drawing;


import java.util.ArrayList;

import input.UI;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import core.GameObject;

public class RenderLoop extends Thread {
	private ArrayList<GameObject> globjects;
	
	public void run() {
		try {
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		// init OpenGL here
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		//the following is configured for the GL_TYPES for a solid color per obj only. no textures/gradients/etc
		while (!Display.isCloseRequested()) {
			
			// render OpenGL here
			// Clear the screen and depth buffer
		    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
			
		    
		    
		    //draw all objects
		    for (Drawing obj : globjects)
		    {
		    	if (obj.GL_TYPE != GL_TYPES.NOTHING)
		    	{
			    		
				    // set the color of the shape (R,G,B,A)
				    GL11.glColor4f(obj.color().r(),obj.color().g(),obj.color().b(),obj.color().a());
				    
				     
				     if (obj.GL_TYPE == GL_TYPES.QUAD)
				    	//handle quads. note that quads are slow and not good, and should be made of two triangles
					    GL11.glBegin(GL11.GL_QUADS);
					   
				    else if (obj.GL_TYPE == GL_TYPES.TRIANGLE)
				    	//handle triangles
				    	GL11.glBegin(GL11.GL_TRIANGLES);
					
				    else if (obj.GL_TYPE == GL_TYPES.POLYGON)
				    	//handle gl polygons
				    	GL11.glBegin(GL11.GL_POLYGON);
				    
				    else if (obj.GL_TYPE == GL_TYPES.LINE)
				    	//handle lines
				    	GL11.glBegin(GL11.GL_LINES);
				    
				    else if (obj.GL_TYPE == GL_TYPES.POINTS)
				    	//handle points
				    	GL11.glBegin(GL11.GL_POINTS);
				    else
				    	try{
				    		throw new Exception("No GL_TYPE specified on object: "+obj);
				    		
				    	}catch(Exception e){e.printStackTrace(); System.exit(-1);}
				    
				    for (util.Vertex vertex : obj.vertices())
				    {
				    	GL11.glVertex2f(vertex.x(),vertex.y());
					}
				    GL11.glEnd();
				    
		    	}    
				    
		    }//! nothing drops to here !
		    
		    while (Keyboard.next()) {//? GROWFACE
			    //handle all objects inputs
			    for (UI obj : globjects)
			    {
			    	obj.checkKeysEvent();
			    }
		    }
		    
			Display.update();
		}
		
		Display.destroy();
	}
	
	
	public void setGlobjects(ArrayList<GameObject> globjects){
		this.globjects=globjects;
	}
	
	public ArrayList<GameObject> getGlobjects(){
		return this.globjects;
	}

	
}