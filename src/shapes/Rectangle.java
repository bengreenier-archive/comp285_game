package shapes;

public class Rectangle extends graphical.GlObject {
	public Rectangle(graphical.Vertex point, float width, float height){
		super();
		graphical.Vertex[] t = new graphical.Vertex[4];
		t[0]=new graphical.Vertex(point.x(),point.y());
		t[1]=new graphical.Vertex(point.x()+width,point.y());
		t[2]=new graphical.Vertex(point.x()+width,point.y()+height);
		t[3]=new graphical.Vertex(point.x(),point.y()+height);
		super.vertices(t);
	}
	public Rectangle(graphical.Vertex point, float width, float height, graphical.RGBA color){
		super();
		graphical.Vertex[] t = new graphical.Vertex[4];
		t[0]=new graphical.Vertex(point.x(),point.y());
		t[1]=new graphical.Vertex(point.x()+width,point.y());
		t[2]=new graphical.Vertex(point.x()+width,point.y()+height);
		t[3]=new graphical.Vertex(point.x(),point.y()+height);
		super.vertices(t);
		super.color(color);
	}


}


