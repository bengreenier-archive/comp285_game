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
		public Wave(Collection<AbstractEnemy> enemies) {
			this.enemies = enemies;
		}
		public Collection<AbstractEnemy> getEnemies() {
			return enemies;
		}	
	}
	
	
	private Collection<Wave> waves;
	
	public WaveManager() {
		waves = new ArrayList<Wave>();
	}
	
	public WaveManager(Collection<Wave> waves) {
		this.waves = waves;
	}
	
	public Wave getNextWave() {
		if (waves.iterator().hasNext())
			return waves.iterator().next();
		
		return null;
	}
	
	public void addWave(Wave wave) {
		waves.add(wave);
	}
	
	//dont do this like this...
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
