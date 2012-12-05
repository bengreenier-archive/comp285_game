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

import com.bengreenier.smashgrab.main.Main;

public class Paused implements GameState {
	
	private UnicodeFont fnt;
	private int id;
	private ArrayList<MouseOverArea> mouseOverArea;
	private MouseOverArea resume, mainMenu, exitButton;
	private Sound sound;
	public Paused(int id){
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
		
		for (MouseOverArea a : mouseOverArea)
			a.setAcceptingInput(true);
		
		final GameContainer localFinalGameContainer = arg0;
		final StateBasedGame localFinalStateBasedGame = arg1;
		
		resume = new MouseOverArea(arg0, new Image("res/resumeButton.PNG"), 300, 200){
			@Override
			public void mouseReleased(int button, int mx, int my){
				if(button == Input.MOUSE_LEFT_BUTTON){
					if((getX() < mx && getX()+getWidth() > mx) && (getY() < my && getY()+getHeight() > my)){
						localFinalStateBasedGame.enterState(Main.ID.RUN);
					}
				}
			}
		};
		resume.setMouseOverImage(new Image("res/resumeButtonDown.PNG"));
		resume.setMouseDownImage(new Image("res/resumeButtonPressed.PNG"));
		mouseOverArea.add(resume);
		
		mainMenu = new MouseOverArea(arg0, new Image("res/mainMenu.PNG"), 300, 300){
			@Override
			public void mouseReleased(int button, int mx, int my){
				if(button == Input.MOUSE_LEFT_BUTTON){
					if((getX() < mx && getX()+getWidth() > mx) && (getY() < my && getY()+getHeight() > my)){
						localFinalStateBasedGame.enterState(Main.ID.MAINMENU);
					}
				}
			}
		};
		mainMenu.setMouseOverImage(new Image("res/mainMenuDown.PNG"));
		mainMenu.setMouseDownImage(new Image("res/mainMenuPressed.PNG"));
		mouseOverArea.add(mainMenu);
		
		exitButton = new MouseOverArea(arg0, new Image("res/exitButton.PNG"), 300, 400){
			@Override
			public void mouseReleased(int button, int mx, int my){
				if(button == Input.MOUSE_LEFT_BUTTON){
					if((getX() < mx && getX()+getWidth() > mx) && (getY() < my && getY()+getHeight() > my)){
						localFinalGameContainer.exit();
					}
				}
			}
		};
		exitButton.setMouseOverImage(new Image("res/exitButtonHovered.PNG"));
		exitButton.setMouseDownImage(new Image("res/exitButtonPressed.PNG"));
		mouseOverArea.add(exitButton);
	}

	@Override
	public int getID() {
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {		
		sound = new Sound("res/buttonSound.ogg");
		
		fnt = new UnicodeFont("res/VINERITC.TTF", 70, true, false);
		fnt.addAsciiGlyphs();
		fnt.getEffects().add(new ColorEffect(java.awt.Color.blue));
		fnt.loadGlyphs();

	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1) throws SlickException {
			for (MouseOverArea a : mouseOverArea){
				a.setAcceptingInput(false);
				a.setFocus(false);
			}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		Color reset = arg2.getColor();
		arg1.getState(Main.ID.RUN).render(arg0, arg1, arg2);
		arg2.setColor(new Color(0,0,0,120));
		arg2.fillRect(0, 0, arg0.getWidth(), arg0.getHeight());
		
		for(MouseOverArea moa : mouseOverArea)
			moa.render(arg0, arg2);
		arg2.setColor(Color.blue);
		if(fnt!=null)
			arg2.setFont(fnt);
		arg2.drawString("Paused", 270, 100);
		arg2.setColor(reset);
	}

	private boolean resume_sound_toggle=false, mainMenu_sound_toggle=false, exitButton_sound_toggle=false;	
	
	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Input in = arg0.getInput();
		if(in.isKeyPressed(Input.KEY_ESCAPE))
			arg1.enterState(Main.ID.RUN);
		if(resume.isMouseOver())
		{
			if  (!resume_sound_toggle)
			{
				resume_sound_toggle=true;
				if (!sound.playing())
					sound.play();
			}
		}else{
			if (resume_sound_toggle)
				resume_sound_toggle=false;
		}
		
		if(mainMenu.isMouseOver())
		{
			if  (!mainMenu_sound_toggle)
			{
				mainMenu_sound_toggle=true;
				if (!sound.playing())
					sound.play();
			}
		}else{
			if (mainMenu_sound_toggle)
				mainMenu_sound_toggle=false;
		}
		
		if(exitButton.isMouseOver())
		{
			if  (!exitButton_sound_toggle)
			{
				exitButton_sound_toggle=true;
				if (!sound.playing())
					sound.play();
			}
		}else{
			if (exitButton_sound_toggle)
				exitButton_sound_toggle=false;
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
