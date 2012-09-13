import graphical.Vertex;



public class Main {

	public static void main(String[] args)
	{
		//define array of globjects
		graphical.GlObject[] GlObjects = new graphical.GlObject[2];
		
		//set globjects[0] to a new rectangle shape
		GlObjects[0] = new shapes.Rectangle(new graphical.Vertex(10,10), 100, 200, new graphical.RGBA(0, 0, 255, 1));
		
		//define some custom vertices, to craft a 2d square type shape
		graphical.Vertex[] i = new graphical.Vertex[4];
		i[0]=new graphical.Vertex(400,20);
		i[1]=new graphical.Vertex(700,20);
		i[2]=new graphical.Vertex(700,100);
		i[3]=new graphical.Vertex(400,100);

		//using those vertices, and a color create a new globject
		GlObjects[1]=new graphical.GlObject(i,new graphical.RGBA(0,0,255,1));

		//create the rendering loop, and start it running with the globjects.
		graphical.Drawable displayExample = new graphical.Drawable();
		displayExample.start(GlObjects);
		
	}
}
