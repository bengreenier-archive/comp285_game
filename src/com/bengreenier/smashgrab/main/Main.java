package com.bengreenier.smashgrab.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.bengreenier.slick.messages.Message;
import com.bengreenier.slick.messages.MessageSystem;
import com.bengreenier.slick.tiling.Tile;
import com.bengreenier.slick.tiling.TileView;
import com.bengreenier.slick.util.StateFreeGame;
import com.bengreenier.slick.util.Vector2i;
import com.bengreenier.smashgrab.enemies.BlueBlob;
import com.bengreenier.smashgrab.states.Paused;
import com.bengreenier.smashgrab.towers.MachineGunTower;

public class Main {

	public static Main core;
	public static void main(String[] args) throws SlickException { core = new Main(); }
	
	@Deprecated
	public static final int TILESIZE_X=50;
	@Deprecated
	public static final int TILESIZE_Y=50;

	@Deprecated
	public enum Mode{BUILDER,VIEWER};
	@Deprecated
	private enum BUILDER{MACH};

	public AppGameContainer agc;
	public StateBasedGame sfg;
	public Mode mode;

	//store our static int id's here
	public static class ID{
		public static int PAUSED = 0;
	}
	
	private Main() throws SlickException
	{
		sfg = new StateBasedGame("SmashGrab"){

			@Override
			public void initStatesList(GameContainer arg0) throws SlickException {
				addState(new Paused(ID.PAUSED));
				enterState(ID.PAUSED);
				
			}
			
		};

		agc = new AppGameContainer(sfg);
		agc.setDisplayMode(800, 600, false);
		agc.setShowFPS(false);
		agc.setVerbose(false);
		agc.start();
	}

	public void promptShutdown()
	{
		//for now, just exit. but later, this will be configurable to display a popup first or something
		agc.exit();
	}
}
