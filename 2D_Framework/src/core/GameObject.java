package core;

public class GameObject extends input.UI{
	private util.Vertex pos;
	final GameObject self=this;
	
	public GameObject()
	{
		super();
		instantiate();
	}
	

	public void instantiate()
	{
		//TODO this is so that you can create a gameobject in one call. by overriding this.
	}


	public util.Vertex getPos() {
		return pos;
	}


	public void setPos(util.Vertex pos) {
		this.pos = pos;
	}
	
	
}
