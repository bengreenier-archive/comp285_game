package com.bengreenier.smashgrab.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.bengreenier.slick.messages.Message;
import com.bengreenier.slick.messages.MessageSystem;
import com.bengreenier.slick.tiling.Tile;
import com.bengreenier.slick.tiling.TileView;
import com.bengreenier.slick.util.StateFreeGame;
import com.bengreenier.slick.util.Vector2i;
import com.bengreenier.smashgrab.enemies.BlueBlob;
import com.bengreenier.smashgrab.towers.MachineGunTower;

public class Main {

	public static Main core;
	public static void main(String[] args) throws SlickException { core = new Main(); }
	public static final int TILESIZE_X=50;
	public static final int TILESIZE_Y=50;

	public enum Mode{BUILDER,VIEWER};
	private enum BUILDER{MACH};

	public AppGameContainer agc;
	public StateFreeGame sfg;
	public Mode mode;

	private Main() throws SlickException
	{
		sfg = new StateFreeGame("Smash Grab"){

			public final Vector2i tileSize = new Vector2i(TILESIZE_X,TILESIZE_Y);
			public TileView tileView;
			public MessageSystem msys;

			private BUILDER builderCurrent=null;

			@Override
			public void renderProcess(GameContainer gc, Graphics g) {

				tileView.debugDraw(gc, g);
				switch(mode)
				{
				case BUILDER:
					tileView.renderAllFilledObjects(gc,g);
					g.drawString("-- BUILDR MODE --", 20, gc.getHeight()-20);
					break;
				case VIEWER:
					break;
				}

				msys.render(gc, g);
			}

			@Override
			public void initProcess(GameContainer gc) {
				tileView = new TileView(gc.getWidth()/tileSize.getX(),gc.getHeight()/tileSize.getY(),tileSize.getX(),tileSize.getY());
				msys = new MessageSystem();
				mode = Mode.BUILDER;
				builderCurrent = BUILDER.MACH;
			}

			@Override
			public void updateProcess(GameContainer gc, int delta) {
				msys.update(gc, delta);

				Input in = gc.getInput();

				if (in.isKeyPressed(Input.KEY_ESCAPE))
					promptShutdown();

				switch(mode)
				{
				case BUILDER:
					if (in.isMousePressed(Input.MOUSE_LEFT_BUTTON))
					{
						Vector2i v = tileView.resolveClick(in.getMouseX(), in.getMouseY());

						if (v != null)
						{
							Tile t = tileView.getTile(v);
							if (!t.isFilled())
							{
								//populate t with the current selection for tower building, if there is one.
								if (builderCurrent!=null)
								{
									switch (builderCurrent)
									{
									case MACH:
										t.fill(new MachineGunTower(tileView.cellAbsolutes(v),v,40));
										break;
									}

									msys.register(new Message("Built: Machine Gun Tower!",Message.Type.SUCCESS));
								}
							}
						}
					}

					if (in.isMousePressed(Input.MOUSE_RIGHT_BUTTON))
					{
						Vector2i v = tileView.resolveClick(in.getMouseX(), in.getMouseY());

						if (v != null)
						{
							Tile t = tileView.getTile(v);
							if (!t.isFilled())
							{
								t.fill(new BlueBlob(tileView.cellAbsolutes(v),10));
								System.out.println("Click filled "+v.toString()+" with bblob");

							}
						}
					}
					break;
				case VIEWER:
					break;
				}

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
