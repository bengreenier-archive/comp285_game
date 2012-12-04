package com.bengreenier.smashgrab.towers;


import java.util.ArrayList;

import com.bengreenier.slick.util.GameObject;
import com.bengreenier.slick.util.Renderable;
import com.bengreenier.slick.util.Vector2i;
import com.bengreenier.smashgrab.enemies.AbstractEnemy;

public abstract class AbstractTower extends GameObject {

	private Vector2i gridPosition;
	private Renderable visual;
	private int range=0,damage=0,cooldown;
	
	public AbstractTower(Vector2i absolutePosition,Vector2i gridPosition,int range)
	{
		super(absolutePosition);
		this.gridPosition=gridPosition;
		this.range = range;
	}

	public Renderable getVisual() {
		return visual;
	}

	public void setVisual(Renderable visual) {
		this.visual = visual;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	public Vector2i getGridPosition() {
		return gridPosition;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getCooldown() {
		return cooldown;
	}
	
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
	private int damage_delta_store = 0;
	public void doDamage(ArrayList<AbstractEnemy> list,int delta) {
		damage_delta_store+=delta;
		
		if (list.isEmpty())
			return;
			
		System.out.println(list);
		
		
		if (damage_delta_store>=getCooldown())
		{
			for (AbstractEnemy e : list)
			{
				//imma fuckin hurt you!
				e.applyDamage(getDamage());
				
			}
			damage_delta_store=0;
		}
	}
	
	
}
