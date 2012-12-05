package com.bengreenier.smashgrab.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.MouseOverArea;

import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bengreenier.slick.tiling.TileSystem;
import com.bengreenier.slick.tiling.TileSystem.Tile;
import com.bengreenier.smashgrab.main.Main;
import com.bengreenier.smashgrab.towers.EndPoint;
import com.bengreenier.smashgrab.util.TileUserData;

public class MainMenu implements GameState {
	
	private UnicodeFont fnt;
	private int id;
	private ArrayList<MouseOverArea> mouseOverArea;
	private MouseOverArea play, exit;
	private Image backGround;
	private Sound sound;
	public MainMenu(int id){
		this.id = id;
		mouseOverArea = new ArrayList<MouseOverArea>();
	}

	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		//clearing inputs for state change
		Input in = arg0.getInput();
		in.clearControlPressedRecord();
		in.clearKeyPressedRecord();
		in.clearMousePressedRecord();
		play.setAcceptingInput(true);
		exit.setAcceptingInput(true);
		
		//enable the mouseovers here. you need to make them member vars first.
		
	}

	@Override
	public int getID() {
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {		
		final StateBasedGame localFinalStateBasedGame = arg1;
		final GameContainer localFinalGameContainer = arg0;

		backGround = new Image("res/mainMenuBackground.png");
		sound = new Sound("res/buttonSound.ogg");
		
		play = new MouseOverArea(arg0, new Image("res/playButton.PNG"), 300, 350){
			@Override
			public void mouseReleased(int button, int mx, int my){
				if(button == Input.MOUSE_LEFT_BUTTON){
					if((getX() < mx && getX()+getWidth() > mx) && (getY() < my && getY()+getHeight() > my)){
						localFinalStateBasedGame.enterState(Main.ID.BUILD);
						Main.core.tileSystem = new TileSystem(16, 10, new Tile(Main.TILESIZE, Main.TILESIZE,0));
						if (localFinalStateBasedGame.getState(Main.ID.RUN) instanceof Run)
						{
							((Run)localFinalStateBasedGame.getState(Main.ID.RUN)).resetAllInternalObjectLists();
						}
						
						//readd our flag
						try {
							Main.core.tileSystem.getTile(15, 9).setUserData(new TileUserData(new EndPoint(Main.core.tileSystem.reverseLocate(15, 9))));
						} catch (Exception e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
					}
				}
			}
		};
		play.setMouseOverImage(new Image("res/playButtonHovered.PNG"));
		play.setMouseDownImage(new Image("res/playButtonPressed.PNG"));
		
		exit = new MouseOverArea(arg0, new Image("res/exitButton.PNG"), 300, 450){
			@Override
			public void mouseReleased(int button, int mx, int my){
				if(button == Input.MOUSE_LEFT_BUTTON){
					if((getX() < mx && getX()+getWidth() > mx) && (getY() < my && getY()+getHeight() > my)){
						localFinalGameContainer.exit();
					}
				}
			}
		};
		exit.setMouseOverImage(new Image("res/exitButtonHovered.PNG"));
		exit.setMouseDownImage(new Image("res/exitButtonPressed.PNG"));
		
		mouseOverArea.add(play);
		mouseOverArea.add(exit);
		
		fnt = new UnicodeFont("res/VINERITC.TTF", 70, true, false);
		fnt.addAsciiGlyphs();
		fnt.getEffects().add(new ColorEffect(java.awt.Color.blue));
		fnt.loadGlyphs();

	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		//disable the mouseovers here. you need to make them member vars first.
		play.setAcceptingInput(false);
		exit.setAcceptingInput(false);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		Color reset = arg2.getColor();
		backGround.draw(0,0);
		for(MouseOverArea moa : mouseOverArea)
			moa.render(arg0, arg2);
		arg2.setColor(Color.blue);
		if(fnt!=null)
			arg2.setFont(fnt);
		arg2.drawString("Smash N' Grab!", 140, 75);
		arg2.setColor(reset);
		
	}

	private boolean play_sound_toggle=false,exit_sound_toggle=false;
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Input in = arg0.getInput();
		if(in.isKeyPressed(Input.KEY_ESCAPE))
			arg0.exit();
		if(play.isMouseOver())
			{
				if  (!play_sound_toggle)
				{
					play_sound_toggle=true;
					if (!sound.playing())
						sound.play();
				}
			}else{
				if (play_sound_toggle)
					play_sound_toggle=false;
			}
		if(exit.isMouseOver())
		{
			if  (!exit_sound_toggle)
			{
				exit_sound_toggle=true;
				if (!sound.playing())
					sound.play();
			}
		}else{
			if (exit_sound_toggle)
				exit_sound_toggle=false;
		}
				
					
	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {

	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {

	}

	@Override
	public void mouseWheelMoved(int arg0) {

	}

	@Override
	public void inputEnded() {

	}

	@Override
	public void inputStarted() {

	}

	@Override
	public boolean isAcceptingInput() {
		return false;
	}

	@Override
	public void setInput(Input arg0) {

	}

	@Override
	public void keyPressed(int arg0, char arg1) {

	}

	@Override
	public void keyReleased(int arg0, char arg1) {

	}

	@Override
	public void controllerButtonPressed(int arg0, int arg1) {

	}

	@Override
	public void controllerButtonReleased(int arg0, int arg1) {

	}

	@Override
	public void controllerDownPressed(int arg0) {

	}

	@Override
	public void controllerDownReleased(int arg0) {

	}

	@Override
	public void controllerLeftPressed(int arg0) {

	}

	@Override
	public void controllerLeftReleased(int arg0) {

	}

	@Override
	public void controllerRightPressed(int arg0) {

	}

	@Override
	public void controllerRightReleased(int arg0) {

	}

	@Override
	public void controllerUpPressed(int arg0) {

	}

	@Override
	public void controllerUpReleased(int arg0) {

	}

}
