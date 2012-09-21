package physics;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

public class Physics extends drawing.Drawing {
	private Body body;
	public Physics()
	{
		super();
		setBody(null);
		instantiate();
	}
	
	public void instantiate()
	{
		//TODO can be overridden, to configure Physics creation
	}
	
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
	
	public void activate()
	{
		try{
		body.setAwake(true);
		body.setSleepingAllowed(true);
		}catch(Exception e){e.printStackTrace();}
	}
	
	public void deactivate()
	{
		try{
		body.setAwake(false);
		body.setSleepingAllowed(false);
		}catch(Exception e){e.printStackTrace();}
	}
	
	
	//to create a body, from a bodydef, in a given world
	static public Body createBody(World world,BodyDef bdef)
	{
		return world.createBody(bdef);
	}
	
	
}
