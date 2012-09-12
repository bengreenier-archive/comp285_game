

public class Main {
	
	public static void main(String[] args)
	{
		graphical.GlObject[] GlObjects = new graphical.GlObject[2];
		graphical.Vertex[] t = new graphical.Vertex[4];
		t[0]=new graphical.Vertex(100,100);
		t[1]=new graphical.Vertex(100+200,100);
		t[2]=new graphical.Vertex(100+200,100+200);
		t[3]=new graphical.Vertex(100,100+200);
		
		graphical.Vertex[] i = new graphical.Vertex[4];
		i[0]=new graphical.Vertex(400,20);
		i[1]=new graphical.Vertex(700,20);
		i[2]=new graphical.Vertex(700,100);
		i[3]=new graphical.Vertex(400,100);
		
		
		GlObjects[0]=new graphical.GlObject(t,new graphical.RGBA(255,0,0,1));
		GlObjects[1]=new graphical.GlObject(i,new graphical.RGBA(0,0,255,1));
		
		graphical.Drawable displayExample = new graphical.Drawable();
		displayExample.start(GlObjects);
	}
}
