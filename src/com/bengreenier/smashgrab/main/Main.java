package com.bengreenier.smashgrab.main;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.bengreenier.slick.tiling.TileSystem;
import com.bengreenier.slick.tiling.TileSystem.Tile;
import com.bengreenier.smashgrab.states.Build;
import com.bengreenier.smashgrab.states.MainMenu;
import com.bengreenier.smashgrab.states.Paused;
import com.bengreenier.smashgrab.states.Run;

import com.bengreenier.slick.util.GameObject;

public class Main {

	public static Main core;
	public static void main(String[] args) throws SlickException { core = new Main(); core.agc.start(); }

	public AppGameContainer agc;
	public StateBasedGame sfg;
	public TileSystem tileSystem;
	public ArrayList<GameObject> tileObjects;
	
	//store our static int id's here
	public static class ID{
		public static int PAUSED = 0;
		public static int MAINMENU = 1;
		public static int BUILD = 2;
		public static int RUN = 3;
	}
	
	private Main() throws SlickException
	{
		tileSystem = new TileSystem(16, 10, new Tile(50, 50,0));
		
		
		tileObjects = new ArrayList<GameObject>();
		
		
		sfg = new StateBasedGame("SmashGrab"){

			@Override
			public void initStatesList(GameContainer arg0) throws SlickException {
				//addState(new Paused(ID.PAUSED));
				//addState(new MainMenu(ID.MAINMENU));
				addState(new Build(ID.BUILD));
				addState(new Run(ID.RUN));
				enterState(ID.BUILD);
				
			}
			
		};

		agc = new AppGameContainer(sfg);
		agc.setDisplayMode(800, 600, false);
		agc.setShowFPS(false);
		agc.setVerbose(false);
	}

	public void promptShutdown()
	{
		//for now, just exit. but later, this will be configurable to display a popup first or something
		agc.exit();
	}
}
