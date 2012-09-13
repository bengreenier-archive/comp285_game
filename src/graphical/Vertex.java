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
	public void shift(Vertex in){this.x = this.x+in.x(); this.y=this.y+in.y();}//shift the current Vertex by in
	
	public void update(Vertex in){this.x = in.x(); this.y = in.y(); }//set the current Vertex to in
	
	public static Vertex add(Vertex one, Vertex two){return new Vertex(one.x()+two.x(),one.y()+two.y());}//static vertex adder
}
