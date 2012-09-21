package drawing;

public class Drawing {
	private util.Vertex[] vertices;
	private util.RGBA color;
	public GL_TYPES GL_TYPE;
	public Drawing()
	{
		vertices=null;
		setColor(null);
	}
	
	public util.Vertex[] getVertices(){return vertices;}
	public void setVertices(util.Vertex[] array){vertices = array;}

	public util.RGBA getColor() {
		return color;
	}

	public void setColor(util.RGBA color) {
		this.color = color;
	}
	
	public util.RGBA color(){return this.color;}
	public void color(util.RGBA color){this.color = color;}
	
	public util.Vertex[] vertices(){return this.vertices;}
	public void vertices(util.Vertex[] array){this.vertices = array;}
}
