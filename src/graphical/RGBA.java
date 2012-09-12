package graphical;

//define/store RGBA values as floats.


public class RGBA {
	private float r;
	private float g;
	private float b;
	private float a;
	public RGBA(float r,float g,float b,float a)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public float r(){return r;}
	public float g(){return g;}
	public float b(){return b;}
	public float a(){return a;}
}
