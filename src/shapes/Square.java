package shapes;

public class Square extends graphical.GlObject {

	public Square(graphical.Vertex point, float size)
	{
		super();
		graphical.Vertex[] t = new graphical.Vertex[4];
		t[0]=new graphical.Vertex(point.x(),point.y());
		t[1]=new graphical.Vertex(point.x()+size,point.y());
		t[2]=new graphical.Vertex(point.x()+size,point.y()+size);
		t[3]=new graphical.Vertex(point.x(),point.y()+size);
		super.vertices(t);
	}
	public Square(graphical.Vertex point, float size, graphical.RGBA color)
	{
		super();
		graphical.Vertex[] t = new graphical.Vertex[4];
		t[0]=new graphical.Vertex(point.x(),point.y());
		t[1]=new graphical.Vertex(point.x()+size,point.y());
		t[2]=new graphical.Vertex(point.x()+size,point.y()+size);
		t[3]=new graphical.Vertex(point.x(),point.y()+size);
		super.vertices(t);
		super.color(color);
	}
}
