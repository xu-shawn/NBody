package org.shawn.mathtools.NBody;

import java.util.*;

public class BodyManager
{
	List<Body> bodies;
	
	public BodyManager()
	{
		this.bodies = new ArrayList<>();
	}
	
	public BodyManager(List<Body> bodies)
	{
		this.bodies = bodies;
	}
}
