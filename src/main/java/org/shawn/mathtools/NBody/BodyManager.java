package org.shawn.mathtools.NBody;

import java.util.*;

public class BodyManager
{
	List<Body> bodies;
	double currTime;
	
	public BodyManager()
	{
		this(new ArrayList<>());
	}
	
	public BodyManager(List<Body> bodies)
	{
		this.bodies = bodies;
		this.currTime = 0;
	}

	public List<Body> getBodies()
	{
		return bodies;
	}
	
	public void update(double deltaTime)
	{
		this.currTime += deltaTime;
		bodies.stream().forEach(body -> body.update(deltaTime, this.bodies));
	}
}
