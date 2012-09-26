package physics;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;


public class Physics extends drawing.Drawing {//TODO decide how to implement stepping to update display, and to step
	private Body body;
	public Physics()
	{
		super();
		setBody(null);
		
		//if ever a physics object is made, and there is no static defaultWorld, create it, and store it.
		if (defaultWorld==null)
			defaultWorld = createWorld(new util.Vertex(0,-10), true);
		
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
	
	//create a world, using my Vec2 class (wraps two floats) and a boolean allowing sleep or no
	static public World createWorld(util.Vertex gravity , boolean doSleep)
	{
		return new World(new Vec2(gravity.x(),gravity.y()), doSleep);
	}
	
	//static method to step a given world.
	static public void stepWorld(World world,float timeStep,int velocityIterations, int positionIterations)
	{
		//timeStep - the amount of time to simulate, this should not vary.
		//velocityIterations - for the velocity constraint solver.
		//positionIterations - for the position constraint solver.
		world.step(timeStep, velocityIterations, positionIterations);
		
	}
	
	//static method to step a given world, returning an ArrayList<Body> of all its bodies slower then a regular step, but provides data
	static public ArrayList<Body> stepWorldSlow(World world,float timeStep,int velocityIterations, int positionIterations)
	{
		//timeStep - the amount of time to simulate, this should not vary.
		//velocityIterations - for the velocity constraint solver.
		//positionIterations - for the position constraint solver.
		world.step(timeStep, velocityIterations, positionIterations);
		ArrayList<Body> list = new ArrayList<Body>();
		Body head = world.getBodyList();//iterate head until its null
		while (head!=null)
		{
			list.add(head);
			head = head.getNext();
		}
		
		return list;
	}
	
	static public BodyDef createBodyDef(util.Vertex point,float angle,String type,boolean awake,boolean bullet)
	{
		BodyDef t = new BodyDef();
		t.active=true;
		t.allowSleep=true;
		t.angle=angle;
		t.awake=awake;
		t.bullet=bullet;
		t.position= new Vec2(point.x(),point.y());//convert to box2d?space
		t.type=BodyType.valueOf(type);
		return t;
		
	}
	
	static public World defaultWorld=null;
	
}
