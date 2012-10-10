import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Main {

	/**
	 * @param args
	 * @throws SlickException 
	 */
	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Platformer("BAJ"), 800, 600, false);
        container.start();
	}

}
