package graphical;


import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Drawable {
	public void start(GlObject[] globjects) {
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
		
		while (!Display.isCloseRequested()) {
			
			// render OpenGL here
			// Clear the screen and depth buffer
		    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
			
		    for (GlObject obj : globjects)
		    {
			    // set the color of the quad (R,G,B,A)
			    GL11.glColor4f(obj.color().r(),obj.color().g(),obj.color().b(),obj.color().a());
			    
			    GL11.glBegin(GL11.GL_QUADS);
			    
			    for (graphical.Vertex vertex : obj.vertices())
			    {
			    	GL11.glVertex2f(vertex.x(),vertex.y());
				}
			    GL11.glEnd();
			    
		    }
			Display.update();
		}
		
		Display.destroy();
	}
}