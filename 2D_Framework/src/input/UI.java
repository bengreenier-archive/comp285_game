package input;

public class UI extends physics.Physics {
	private Event evt;
	public UI()
	{
		super();
		setEvent(new Event(){

			@Override
			public void pollInput() {
				// TODO Auto-generated method stub
				
			}});
	}
	
	public void checkInput()
	{
		evt.pollInput();
	}
	
	public Event getEvent() {
		return evt;
	}
	public void setEvent(Event evt) {
		this.evt = evt;
	}
	

}
