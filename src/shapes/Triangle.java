package shapes;

public class Triangle extends graphical.GlObject{
	public Triangle(graphical.Vertex point, float length)
	{
		super();
		graphical.Vertex[] t = new graphical.Vertex[3];
		t[0]=new graphical.Vertex(point.x(),point.y());
		t[1]=new graphical.Vertex(point.x()+size,point.y());
		t[2]=new graphical.Vertex(point.x()+size,point.y()+size);
		super.vertices(t);
	}
}
