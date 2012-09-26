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

	public void checkPreKeys() {
		// TODO Auto-generated method stub
		if (evt!=null)
			evt.preKeys();
	}

	public void checkKeysUpEvent() {
		// TODO Auto-generated method stub
		if (evt!=null)
			evt.upKeys();
	}

	public void checkKeysDownEvent() {
		// TODO Auto-generated method stub
		if (evt!=null)
			evt.downKeys();
	}

	public void checkPostKeys() {
		// TODO Auto-generated method stub
		if (evt!=null)
			evt.postKeys();
	}
	

}
