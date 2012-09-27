package core;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;


public class Main {

	public static drawing.RenderLoop gameLoop=null;
	
	public static Main mainPtr=null;
	
	private drawing.RenderLoop renderLoop;
	private ArrayList<GameObject> objectsList;
	public String title;
	
	public Main()
	{
		mainPtr=this;
		title="";
		setRenderLoop(null);
		setObjectsList(null);
		instantiate();
	}
	
	public void instantiate()
	{
		//TODO override this when you create a Main
		
	}
	
	public static void main(String[] args)
	{
		//define the main instance, and instantiate it, creating object
			Main main = new Main(){
				@Override
				public void instantiate()
				{
					title="Core Main Instance";//just to identify Main objects by string
					//create the mains objectsList and renderLoop
					setObjectsList(new ArrayList<GameObject>());
					setRenderLoop(new drawing.RenderLoop());
					
					getObjectsList().add(new core.Character(new util.Vertex(20,20),20));
					//this allows KEY_ESCAPE to quit the game [a non visual object]
					getObjectsList().add(new GameObject(){
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
					
					getRenderLoop().setGlobjects(getObjectsList());
					getRenderLoop().start();
				}
			};
			
			
			
			
			System.out.println("Yay, Threaded! "+main.title+" created.");
			
		
	}

	/**
	 * @return the renderLoop
	 */
	public drawing.RenderLoop getRenderLoop() {
		return renderLoop;
	}

	/**
	 * @param renderLoop the renderLoop to set
	 */
	public void setRenderLoop(drawing.RenderLoop renderLoop) {
		this.renderLoop = renderLoop;
	}

	/**
	 * @return the objectsList
	 */
	public ArrayList<GameObject> getObjectsList() {
		return objectsList;
	}

	/**
	 * @param objectsList the objectsList to set
	 */
	public void setObjectsList(ArrayList<GameObject> objectsList) {
		this.objectsList = objectsList;
	}
	
	
}
