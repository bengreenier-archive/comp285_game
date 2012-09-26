package core;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;


import core.*;

public class Main {

	public static drawing.RenderLoop gameLoop=null;
	
	public static void main(String[] args)
	{
			ArrayList<GameObject> objectsList = new ArrayList<GameObject>();//dynamic list example
			
		/*	
			//create a game object, and set some properties on it.
			GameObject gameobject = new GameObject();
			gameobject.setColor(new util.RGBA(255, 255, 255, 1));
			gameobject.setVertices(util.Vertex.createSquare(new util.Vertex(100,100), 100));
			gameobject.GL_TYPE=drawing.GL_TYPES.QUAD;
			
			//note, this a multiline, not so smooth way to do this
			objectsList.add(gameobject);
			
			
			//note how smooth this is, and easy to implement, without cluttering the "namespace" up with var names.
			objectsList.add(new GameObject(){
				@Override
				public void instantiate()
				{
					this.setColor(new util.RGBA(255,0,0,1));
					this.setVertices(util.Vertex.createSquare(new util.Vertex(200,200), 100));
					this.GL_TYPE=drawing.GL_TYPES.QUAD;
				}
			});
			*/
			objectsList.add(new core.Character(new util.Vertex(20,20),20));
			
			
			//this allows KEY_ESCAPE to quit the game [a non visual object]
			objectsList.add(new GameObject(){
				@Override
				public void instantiate()
				{
					this.GL_TYPE=drawing.GL_TYPES.NOTHING;
					this.setEvent(new input.Event(){

						@Override
						public void mouse() {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void other() {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void preKeys() {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void postKeys() {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void upKeys() {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void downKeys() {
							// TODO Auto-generated method stub
							if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
					    		System.exit(0);
						}});
				}
			});
			
			
			
			drawing.RenderLoop loop = new drawing.RenderLoop();
			gameLoop=loop;
			
			loop.setGlobjects(objectsList);
			loop.start();
			System.out.println("Yay, Threaded!");
			
		
	}
	
	
}
