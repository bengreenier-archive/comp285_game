package com.bengreenier.smashgrab.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.bengreenier.slick.util.Vector2i;

public class Paused implements GameState {
	private class ImagePair{
		public Image img;
		public Vector2i pos;
		public ImagePair(Image img, Vector2i pos){
			this.img = img;
			this.pos = pos;
		}
	}
	private UnicodeFont fnt;
	private int id;
	private ArrayList<ImagePair> images;
	public Paused(int id){
		this.id = id;
		images = new ArrayList<ImagePair>();
	}
	
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		//clearing inputs for state change
		Input in = arg0.getInput();
		in.clearControlPressedRecord();
		in.clearKeyPressedRecord();
		in.clearMousePressedRecord();
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		images.add(new ImagePair(new Image("res/resumeButton.png"), new Vector2i(300,200)));
		fnt = new UnicodeFont("res/VINERITC.TTF", 70, true, false);
		fnt.addAsciiGlyphs();
		fnt.getEffects().add(new ColorEffect(java.awt.Color.blue));
		fnt.loadGlyphs();
	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		Color reset = arg2.getColor();
		for(ImagePair imagePair : images)
			imagePair.img.draw(imagePair.pos.getX(), imagePair.pos.getY());
		arg2.setColor(Color.blue);
		if(fnt!=null)
			arg2.setFont(fnt);
		arg2.drawString("Paused", 270, 100);
		arg2.setColor(reset);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Input in = arg0.getInput();
		if(in.isKeyPressed(Input.KEY_ESCAPE))
			arg0.exit();
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
