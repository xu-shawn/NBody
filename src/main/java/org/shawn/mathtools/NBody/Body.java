package org.shawn.mathtools.NBody;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import java.util.*;

public class Body
{
	private RealVector pos;
	private RealVector vel;
	private RealVector acl;

	private double mass;
	private double radius;

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

	public Body(double x, double y, double z, double vx, double vy, double vz, double mass)
	{
		this(new ArrayRealVector(new double[] { x, y, z }),
				new ArrayRealVector(new double[] { vx, vy, vz }), mass, 1);
	}

	public Body(RealVector pos, RealVector vel, double mass, double radius)
	{
		this(pos, vel, new ArrayRealVector(new double[] { 0, 0, 0 }), mass, radius);
	}

	public Body(RealVector pos, RealVector vel, RealVector acl, double mass, double radius)
	{
		this.pos = pos;
		this.vel = vel;
		this.acl = acl;
		this.mass = mass;
	}

	public double distanceFrom(Body other)
	{
		return this.pos.getDistance(other.getPos());
	}

	public RealVector gravityFrom(Body other)
	{
		if (this.getPos().equals(other.getPos()))
		{
			return new ArrayRealVector(new double[] { 0, 0, 0 });
		}
		return other.getPos().subtract(this.getPos()).unitVector()
				.mapMultiply(G * (this.getMass() + other.getMass()) / this.distanceFrom(other));
	}

	public RealVector gravityFrom(List<Body> others)
	{
		return others.stream().map(this::gravityFrom).reduce(RealVector::add)
				.orElse(new ArrayRealVector(new double[] { 0, 0, 0 }));
	}
	
	public void updateAcceleration(List<Body> others)
	{
		acl = gravityFrom(others).mapDivide(mass);
	}

	public void updatePositionVelocity(double deltaTime)
	{
		vel = vel.add(acl.mapMultiply(deltaTime));
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

	public RealVector getAcl()
	{
		return acl;
	}

	public void setAcl(RealVector acl)
	{
		this.acl = acl;
	}

	public double getRadius()
	{
		return radius;
	}

	public void setRadius(double radius)
	{
		this.radius = radius;
	}
}
