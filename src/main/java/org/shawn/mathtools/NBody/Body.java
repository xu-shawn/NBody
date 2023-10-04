package org.shawn.mathtools.NBody;

//TODO: Implement speed using JScience
public class Body
{
	private double x;
	private double y;
	private double z;

	private double vx;
	private double vy;
	private double vz;
	
	public Body(double x, double y, double z)
	{
		this(x, y, z, 0, 0, 0);
	}
	
	public Body(double x, double y, double z, double vx, double vy, double vz)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
	}
	
	public double getDistance(Body other)
	{
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	public double getSpeed()
	{
		return Math.sqrt(vx * vx + vy * vy + vz * vz);
	}
}
