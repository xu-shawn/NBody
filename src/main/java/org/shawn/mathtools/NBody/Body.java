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
	
	private double mass;
	
	public Body(double x, double y, double z)
	{
		this(x, y, z, 0, 0, 0, 1);
	}
	
	public Body(double x, double y, double z, double mass)
	{
		this(x, y, z, 0, 0, 0, mass);
	}
	
	public Body(double x, double y, double z, double vx, double vy, double vz)
	{
		this(x, y, z, vx, vy, vz, 1);
	}
	
	public Body(double x, double y, double z, double vx, double vy, double vz, double mass)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
		
		this.mass = mass;
	}
	
	public double distanceFrom(Body other)
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public double getZ()
	{
		return z;
	}

	public void setZ(double z)
	{
		this.z = z;
	}

	public double getVx()
	{
		return vx;
	}

	public void setVx(double vx)
	{
		this.vx = vx;
	}

	public double getVy()
	{
		return vy;
	}

	public void setVy(double vy)
	{
		this.vy = vy;
	}

	public double getVz()
	{
		return vz;
	}

	public void setVz(double vz)
	{
		this.vz = vz;
	}

	public double getMass()
	{
		return mass;
	}

	public void setMass(double mass)
	{
		this.mass = mass;
	}
}
