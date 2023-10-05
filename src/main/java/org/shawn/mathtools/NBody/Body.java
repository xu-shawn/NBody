package org.shawn.mathtools.NBody;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class Body
{
	private RealVector pos;
	private RealVector vel;

	private double mass;

	public static final double G = 6.6743;

	public Body()
	{
		this(0, 0, 0);
	}

	public Body(double x, double y, double z)
	{
		this(x, y, z, 1);
	}

	public Body(double x, double y, double z, double mass)
	{
		this(x, y, z, 0, 0, 0, mass);
	}

	public Body(double x, double y, double z, double vx, double vy, double vz)
	{
		this(x, y, z, vx, vy, vz, 1);
	}

	public Body(RealVector pos, RealVector vel)
	{
		this(pos, vel, 1);
	}

	public Body(double x, double y, double z, double vx, double vy, double vz, double mass)
	{
		this(new ArrayRealVector(new double[] { x, y, z }),
				new ArrayRealVector(new double[] { vx, vy, vz }), mass);
	}

	public Body(RealVector pos, RealVector vel, double mass)
	{
		this.pos = pos;
		this.vel = vel;
		this.mass = mass;
	}

	public double distanceFrom(Body other)
	{
		return this.pos.getDistance(other.getPos());
	}

	public RealVector gravityFrom(Body other)
	{
		return other.getPos().subtract(this.getPos()).unitVector()
				.mapMultiply(G * (this.getMass() + other.getMass()) / this.distanceFrom(other));
	}

	public void update(double deltaTime)
	{
		pos = pos.add(vel.mapMultiply(deltaTime));
	}

	public RealVector getPos()
	{
		return pos;
	}

	public void setPos(RealVector pos)
	{
		this.pos = pos;
	}

	public RealVector getVel()
	{
		return vel;
	}

	public void setVel(RealVector vel)
	{
		this.vel = vel;
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
