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
	
	public void checkInput()
	{
		if (evt!=null)
		evt.pollInput();
	}
	
	public Event getEvent() {
		return evt;
	}
	public void setEvent(Event evt) {
		this.evt = evt;
	}
	

}
