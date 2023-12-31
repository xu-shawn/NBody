package org.shawn.mathtools.NBody;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleDisplay
{
	enum Pixel
	{
		On('*'), Off(' ');

		public final Character value;

		private Pixel(char value)
		{
			this.value = value;
		}
	}

	private BodyManager bodies;
	private Pixel[][] pixels;

	public SimpleDisplay(int width, int height)
	{
		pixels = new Pixel[height][width];
		Arrays.stream(pixels).forEach(line -> Arrays.fill(line, Pixel.Off));
		bodies = new BodyManager(0);
	}

	public BodyManager getBodyManager()
	{
		return bodies;
	}

	public void redraw()
	{
		Arrays.stream(pixels).forEach(line -> Arrays.fill(line, Pixel.Off));

		double xaxis = pixels[0].length / 2.0;
		double yaxis = pixels.length / 2.0;

		for (Body body : bodies.getBodies())
		{
			int xpos = (int) Math.round(body.getPos().getEntry(0) + xaxis);
			int ypos = (int) Math.round(body.getPos().getEntry(1) + yaxis);
			if (xpos >= 0 && xpos < pixels[0].length && ypos >= 0 && ypos < pixels.length)
			{
				pixels[ypos][xpos] = Pixel.On;
			}
		}
	}

	public void update(double deltaTime)
	{
		bodies.update(deltaTime);
		this.redraw();
	}

	public String toString()
	{
		return Arrays.stream(pixels).parallel().map(line -> Arrays.stream(line)
				.map(pixel -> pixel.value.toString()).collect(Collectors.joining()))
				.collect(Collectors.joining("\n"));
	}
}
