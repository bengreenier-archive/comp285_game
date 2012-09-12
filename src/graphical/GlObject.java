package graphical;

//wraps a GlObject, containing vertices and a color
public class GlObject {
	private RGBA color;
	private Vertex[] vertices;
	
	public GlObject(Vertex[] pts,RGBA col)
	{
		this.vertices = pts;
		this.color = col;
	}
	
	public GlObject(Vertex[] pts)
	{
		this.vertices = pts;
		this.color = new RGBA(255,255,255,1);
	}
	
	public Vertex[] vertices(){return vertices;}
	public RGBA color(){return color;}
}
