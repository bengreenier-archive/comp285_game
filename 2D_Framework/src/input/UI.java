package input;

public class UI extends physics.Physics {
	private Event evt;
	public UI()
	{
		super();
		setEvent(null);
		instantiate();
	}
	
	public void instantiate()
	{
		//default instantiate method
	}
	
	public void checkKeysEvent()
	{
		if (evt!=null)
		evt.keys();
		else
		System.out.println("Null");
	}
	
	public void checkMouseEvent()
	{
		if (evt!=null)
		evt.mouse();
	}
	
	public void checkOtherEvent()
	{
		if (evt!=null)
		evt.other();
	}
	
	public Event getEvent() {
		return evt;
	}
	public void setEvent(Event evt) {
		this.evt = evt;
	}
	

}
