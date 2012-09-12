package graphical;
//stores/defines vertex points as required by the glVertex2f function
public class Vertex {
	private float x;
	private float y;
	
	public Vertex(float x,float y)
	{
		this.x=x;
		this.y=y;
	}
	
	public float x(){return x;}
	public float y(){return y;}
}
