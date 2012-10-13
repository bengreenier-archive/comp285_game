//storage for position/velocity, for networking
public class Vec4f {
	public float a,b,c,d;
	
	public Vec4f(float a,float b,float c,float d)
	{
		this.a=a;
		this.b=b;
		this.c=c;
		this.d=d;
	}

	public void set(Vec4f val) {
		this.a=val.a;
		this.b=val.b;
		this.c=val.c;
		this.d=val.d;
		
	}
}
