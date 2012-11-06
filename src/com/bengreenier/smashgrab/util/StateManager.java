package com.bengreenier.smashgrab.util;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.newdawn.slick.state.GameState;

public class StateManager {
	private class StateWrapper {
		private GameState state;
		private int id;
		private StateWrapper(GameState state, int id) {
			this.state = state;
			this.id = id;
		}
		public GameState getState() {
			return state;
		}
		public int getId() {
			return id;
		}
	}
	private ConcurrentLinkedQueue<StateWrapper> states;
	private static StateManager ptr;
	private int nextId;
	
	private StateManager() {
		states = new ConcurrentLinkedQueue<StateWrapper>();
		nextId = 0;
	}
	
	public static StateManager getStateManager() {
		if(ptr == null)
			ptr = new StateManager();
		
		return ptr;
	}
	
	public void addState(GameState state) {
		if(!states.contains(state)) {
			states.add(new StateWrapper(state, nextId));
			nextId++;
		}
	}
	
	public void removeState(GameState state) {
		if(states.contains(state))
			states.remove(state);
	}
	
	public GameState getState(String type) {
		for(StateWrapper w : states) {
			if(w.getState().getClass().getName().equals(type))
				return w.getState();
		}
		return null;
	}
}
