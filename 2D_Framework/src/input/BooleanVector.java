package input;
//specific class to make boolean toggles for current motion easier. see dynamics/Character.java
public class BooleanVector {
	public boolean[] storage;
	public BooleanVector(int size)
	{
		storage = new boolean[size];
	}
}
