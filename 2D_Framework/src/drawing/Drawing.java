package drawing;

public class Drawing {
	private util.Vertex[] vertices;
	private util.RGBA color;
	public GL_TYPES GL_TYPE;
	private util.Vertex pos;
	public Drawing()
	{
		vertices=null;
		setColor(null);
		instantiate();
	}
	
	public void instantiate()
	{
		//default instantiate method
	}
	
	public util.Vertex[] getVertices(){return vertices;}
	
	//this pos method is inaccurate with triangles
	public void setVertices(util.Vertex[] array){vertices = array; pos=array[0].clone();}

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

	public util.Vertex getPos() {
		return pos;
	}

}
