package org.shawn.mathtools.NBody;

//TODO: Implement speed using JScience
public class Body
{
	private double x;
	private double y;
	private double z;
	
	public Body(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getDistance(Body other)
	{
		return Math.sqrt(x * x + y * y + z * z);
	}
}
