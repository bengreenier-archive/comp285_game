import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Platformer extends StateBasedGame {
	/** The container in which the game is running */
    private GameContainer container;
	/** True if vertical sync is turned on */
    private boolean vsync = true;
    
	public Platformer(String name) {
		super(name);
		
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.container = container;
        
        container.setTargetFrameRate(100);
        container.setVSync(vsync);
        
        addState(new InGameState());
		
	}
	
	/**
     * @see org.newdawn.slick.state.StateBasedGame#keyPressed(int, char)
     */
    public void keyPressed(int key, char c) {
            super.keyPressed(key, c);
            
            if (key == Input.KEY_F7) {
                    vsync = !vsync;
                    container.setVSync(vsync);
            }
    }

}
