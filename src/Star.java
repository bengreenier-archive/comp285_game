import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Circle;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Star extends AbstractEntity {

	/** The image to display for the crate */
	private Image image;
	/** The width of the crate */
	private float width;
	/** The height of the crate */
	private float height;
	/** The world to which the crate has been added */
	@SuppressWarnings("unused")
	private World world;
	
	/**
	 * Create a new crate
	 * 
	 * @param x The x position of the centre of the crate
	 * @param y The y position fo the centre of the crate
	 * @param radius the radius of the circle
	 * @param mass The mass of the crate
	 * @param isize_x the x scale to scale the image by
	 * @param isize_y the y scale to scale the image by
	 * @throws SlickException Indicates a failure to load the resources
	 */
	public Star(float x, float y, float radius, float mass,int isize_x,int isize_y) throws SlickException {
		this.height=radius;
		this.width=radius;
		
		image = new Image("res/coin.png").getScaledCopy(isize_x,isize_y);
		body = new Body(new Circle(radius), mass);
		body.setPosition(x,y);
		body.setFriction(0.1f);
	}

	/**
	 * @see org.newdawn.penguin.Entity#getBody()
	 */
	public Body getBody() {
		return body;
	}

	/**
	 * @see org.newdawn.penguin.Entity#preUpdate(int)
	 */
	public void preUpdate(int delta) {
	}

	/**
	 * @see org.newdawn.penguin.Entity#render(org.newdawn.slick.Graphics)
	 */
	public void render(Graphics g) {
		g.translate(getX(), getY());
		g.rotate(0,0,(float) Math.toDegrees(body.getRotation()));
		image.draw(-width/2,-height/2,width,height);
		g.rotate(0,0,(float) -Math.toDegrees(body.getRotation()));
		g.translate(-getX(), -getY());
	}

	/**
	 * @see org.newdawn.penguin.Entity#setWorld(net.phys2d.raw.World)
	 */
	public void setWorld(World world) {
		this.world = world;
	}

	/**
	 * @see org.newdawn.penguin.Entity#update(int)
	 */
	public void update(int delta) {
	}

}
