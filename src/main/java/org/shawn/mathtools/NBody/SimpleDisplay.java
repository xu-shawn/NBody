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

	private BodyManager bodies = new BodyManager();
	private Pixel[][] pixels;

	public SimpleDisplay(int w, int h)
	{
		pixels = new Pixel[h][w];
		Arrays.fill(pixels, Pixel.Off);
	}

	public BodyManager getBodyManager()
	{
		return bodies;
	}

	public void redraw()
	{
		Arrays.fill(pixels, Pixel.Off);

		// TODO: Translate the location of planets onto the pixel map
	}

	public void update(double deltaTime)
	{
		bodies.update(deltaTime);
		this.redraw();
	}

	public String toString()
	{
		return Arrays.stream(pixels).map(line -> Arrays.stream(line)
				.map(pixel -> pixel.value.toString()).collect(Collectors.joining()))
				.collect(Collectors.joining());
	}
}
