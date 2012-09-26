package input;
public interface Event {
	public void preKeys();
	public void postKeys();
	public void upKeys();//keyboard stuff
	public void downKeys();
	public void mouse();//mouse stuff
	public void other();//unknown
}
