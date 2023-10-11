package org.shawn.mathtools.NBody;

import java.util.*;

public class BodyManager
{
	List<Body> bodies;
	double currTime;
	double updateInterval;
	
	public BodyManager(double updateInterval)
	{
		this(new ArrayList<>(), updateInterval);
	}
	
	public BodyManager(List<Body> bodies, double updateInterval)
	{
		this.bodies = bodies;
		this.currTime = 0;
		this.updateInterval = updateInterval;
	}
	
	public BodyManager(BodyManager other, double updateInterval)
	{
		this.bodies = new ArrayList<>(other.bodies);
		this.currTime = 0;
		this.updateInterval = updateInterval;
	}
	
	public void update()
	{
		update(updateInterval);
	}
	
	public void update(double deltaTime)
	{
		this.currTime += deltaTime;
		bodies.parallelStream().forEach(body -> body.update(this.bodies, deltaTime));
	}

	public List<Body> getBodies()
	{
		return bodies;
	}

	public double getUpdateInterval()
	{
		return updateInterval;
	}

	public void setUpdateInterval(double updateInterval)
	{
		this.updateInterval = updateInterval;
	}
}
