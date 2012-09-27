package util;

public interface ThreadMethods {
	public void requestShutdown();//upon call, should notify and soon-after terminate the thread
	public boolean isUpSuccessfully();//returns true if all that threads elements are up, correctly. should be fairly accurate.
}
