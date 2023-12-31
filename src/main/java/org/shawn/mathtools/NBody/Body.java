package org.shawn.mathtools.NBody;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import java.util.*;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Body
{
	private RealVector pos;
	private RealVector vel;
	private RealVector acl;

	private double mass;
	private double radius;
	
	private Circle dot;

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
		this.dot = new Circle(1, Color.ORANGE);
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
		return others.parallelStream().map(this::gravityFrom).reduce(RealVector::add)
				.orElse(new ArrayRealVector(new double[] { 0, 0, 0 }));
	}
	
	public void update(List<Body> others, double deltaTime)
	{
		acl = gravityFrom(others).mapDivide(mass);
		pos = pos.add(vel.mapMultiply(deltaTime)).add(acl.mapMultiply(deltaTime * deltaTime / 2));
		vel = vel.add(acl.mapMultiply(deltaTime));
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

	public Circle getDot()
	{
		return dot;
	}
}
