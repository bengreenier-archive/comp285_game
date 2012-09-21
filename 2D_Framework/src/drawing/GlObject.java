package drawing;
import util.RGBA;
import util.Vertex;
//wraps a GlObject, containing vertices and a color,and a physical.Body, implementing an input.Event
public class GlObject implements input.Event {
	private RGBA color;
	private Vertex[] vertices;
	public drawing.GL_TYPES GL_TYPE;

	
	public GlObject(Vertex[] pts,RGBA col)
	{
		this.vertices = pts;
		this.color = col;
		GL_TYPE=GL_TYPES.QUAD;
	}
	
	public GlObject(Vertex[] pts)
	{
		this.vertices = pts;
		this.color = new RGBA(255,255,255,1);
		GL_TYPE=GL_TYPES.QUAD;
	}
	
	public GlObject() {
		this.vertices = null;
		this.color = new RGBA(255,255,255,1);
		GL_TYPE=GL_TYPES.QUAD;
	}

	public Vertex[] vertices(){return vertices;}
	public void vertices(Vertex[] val){this.vertices = val;}
	public RGBA color(){return color;}
	public void color(RGBA val){this.color = val;}

	@Override
	public void pollInput() {
		// TODO Auto-generated method stub
		
	}
}
