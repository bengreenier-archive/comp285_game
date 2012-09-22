package input;
//specific class to make boolean toggles for current motion easier. see dynamics/Character.java
public class BooleanVector {
	public boolean[] storage;
	private int size;
	public BooleanVector(int size)
	{
		storage = new boolean[size];
		this.size=size;
	}
	
	public String print()
	{
		String t="{";
		for (int i=0;i<size;i++)
		{
			t=t+"["+i+":"+storage[i]+"] , ";
		}
		t=t+"}";
			
		return t;
	}
}
