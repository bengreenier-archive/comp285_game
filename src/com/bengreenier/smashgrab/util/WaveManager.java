package com.bengreenier.smashgrab.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.newdawn.slick.util.pathfinding.Path;

import com.bengreenier.smashgrab.enemies.AbstractEnemy;

/**
 * Stores/manages waves.
 * @author B3N
 */
public class WaveManager {
	
	/**
	 * Defines the behavior of a single wave.
	 * @author B3N
	 */
	public static class Wave{
		private Collection<AbstractEnemy> enemies;
		private AbstractEnemy current;
		public Wave(Collection<AbstractEnemy> enemies) {
			this.enemies = enemies;
		}
		public Collection<AbstractEnemy> getEnemies() {
			return enemies;
		}
		
		public AbstractEnemy getNextEnemy() {
			if (enemies.iterator().hasNext())
			{
				current = enemies.iterator().next();
				return current;
			}
			
			return null;
		}
		
		public AbstractEnemy getCurrentEnemy() {
			return current;
		}
	}
	
	
	private Collection<Wave> waves;
	private Wave current;
	
	public WaveManager() {
		waves = new ArrayList<Wave>();
	}
	
	public WaveManager(Collection<Wave> waves) {
		this.waves = waves;
	}
	
	public Wave getNextWave() {
		if (waves.iterator().hasNext())
		{
			current = waves.iterator().next();
			return current;
		}
		
		return null;
	}
	
	public void addWave(Wave wave) {
		waves.add(wave);
	}
	
	public Wave getCurrentWave() {
		return current;
	}
	
	//dont do this like this...
	@Deprecated
	public void injectPath(Path path) {
		Iterator<Wave> i = waves.iterator();
		while (i.hasNext())
		{
			for (AbstractEnemy e : i.next().getEnemies())
				if (((EnemyUserData)e.getUserData()).pw!=null)
					((EnemyUserData)e.getUserData()).pw = new PathWrapper(path,50,50);
		}
	}
	
	
}
