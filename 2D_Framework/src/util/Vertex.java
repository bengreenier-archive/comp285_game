package util;
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
	
	
	//below are the static Vertex shape constructors
	public static Vertex[] createSquare(Vertex point,float size)
	{
		util.Vertex[] t = new util.Vertex[4];
		t[0]=new util.Vertex(point.x(),point.y());
		t[1]=new util.Vertex(point.x()+size,point.y());
		t[2]=new util.Vertex(point.x()+size,point.y()+size);
		t[3]=new util.Vertex(point.x(),point.y()+size);
		return t;
	}
	
	public static Vertex[] createRectangle(Vertex point, float width, float height)
	{
		util.Vertex[] t = new util.Vertex[4];
		t[0]=new util.Vertex(point.x(),point.y());
		t[1]=new util.Vertex(point.x()+width,point.y());
		t[2]=new util.Vertex(point.x()+width,point.y()+height);
		t[3]=new util.Vertex(point.x(),point.y()+height);
		return t;
	}
	
	public static Vertex[] createTriangle(Vertex point, float length)
	{
		util.Vertex[] t = new util.Vertex[3];
		//converting a double to a float for a point might skew it slightly
		
		t[0]=new util.Vertex(point.x(),(float) (point.y()+(0.5 * length * Math.sqrt(3))));
		t[1]=new util.Vertex(point.x()+(length/2),(float) (point.y() - (0.1 * length * Math.sqrt(3))));
		t[2]=new util.Vertex(point.x()-(length/2),(float) (point.y()- (0.1 * length * Math.sqrt(3))));
		return t;
	}
	
	@Override
	public Vertex clone()
	{
		return new Vertex(x(),y());
	}
	
	public String print()
	{
		return "[x:"+x()+"] , [y:"+y()+"]";
	}
	
}
