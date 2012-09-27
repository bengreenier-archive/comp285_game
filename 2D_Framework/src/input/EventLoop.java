package input;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**************************************
 * This class is designed as a Thread,
 * to provide a dedicated checker of
 * all user input. This may not be a 
 * super efficient way of working, but
 * it does get the job done, and lowers
 * the amount of work that RenderLoop
 * needs to do.
 * 
 * @author B3N
 */
public class EventLoop extends Thread implements util.ThreadMethods {
	private ConcurrentLinkedQueue<UI> objects;
	private volatile boolean requireShutdown;
	public EventLoop()
	{
		objects = new ConcurrentLinkedQueue<UI>();
		requireShutdown=false;
	}
	
	public void register(UI event)
	{
		objects.add(event);
	}
	
	public boolean unregister(UI event)
	{
		return objects.remove(event);
	}
	
	public void run()
	{
		
		while(true)
		{
				
			/**********************************************
			 * Check and handle shutdown callback.        *
			 **********************************************/
			if (!requireShutdown)
			{	
			
				/**********************************************
				 * First, check keyboard input in all objects *
				 * PLEASE NOTE: this is wildly inefficient    *
				 **********************************************/
				for (UI obj : objects)
					obj.checkPreKeys();
	
				while (Keyboard.next()) 
				{
					if (Keyboard.getEventKeyState())
						for (UI obj : objects)
							obj.checkKeysDownEvent();
					else
						for (UI obj : objects)
							obj.checkKeysUpEvent();
	
				}
	
				for (UI obj : objects)
					obj.checkPostKeys();
	
	
				/*********************************************
				 * Then, Handle Mouse Input                  *
				 *********************************************/
				for (UI obj : objects)
					obj.checkMouseEvent();
	
				/****************************************
				 * Then, handle any other defined input *
				 ****************************************/
				for (UI obj : objects)
					obj.checkOtherEvent();
	
			}
			else
			{
				return;
			}
		}
	}

	@Override
	public void requestShutdown() {
		requireShutdown=true;
	}

	@Override
	public boolean isUpSuccessfully() {
		// TODO Auto-generated method stub
		if (objects!=null && Keyboard.isCreated() && Mouse.isCreated())
			return true;
		else
			return false;
	}
}
