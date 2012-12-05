package com.bengreenier.smashgrab.projectiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.bengreenier.slick.util.Vector2i;

public class MachineGunBullet{
	
	private Vector2i position;
	private float angle;
	
	public MachineGunBullet(Vector2i position,float angle) {
		this.position = position;
		this.angle = angle;
	}
	public MachineGunBullet(Vector2i position,Vector2i other) {
		this.position = position;
		Vector2i calc = Vector2i.subtract(position, other).positron();
		this.angle = (float) ((float) Math.atan2(calc.getX(), calc.getY()));
	}
	
	public void draw(Graphics g) {
		Color t = g.getColor();
		g.setColor(Color.gray);
		g.drawOval(position.getX(), position.getY(), 3, 3);
		g.setColor(t);
	}
	
	public void update(int delta) {
		if (angle>0 && angle<90)
			position.add(new Vector2i(1*delta,1*delta));
		else if (angle>90 && angle<180)
			position.add(new Vector2i(-1*delta,1*delta));
		else if (angle>180 && angle<270)
			position.add(new Vector2i(-1*delta,-1*delta));
		else if (angle>270 && angle<360)
			position.add(new Vector2i(-1*delta,1*delta));
		else if (angle == 0)
			position.add(new Vector2i(1*delta,0*delta));
		else if (angle == 90)
			position.add(new Vector2i(0*delta,1*delta));
		else if (angle == 180)
			position.add(new Vector2i(-1*delta,0*delta));
		else if (angle == 270)
			position.add(new Vector2i(0*delta,-1*delta));
	}

}
