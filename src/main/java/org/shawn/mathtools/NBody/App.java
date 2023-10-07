package org.shawn.mathtools.NBody;

/**
 * Hello world!
 *
 */
public class App
{
	public static void main(String[] args)
	{
		SimpleDisplay mySim = new SimpleDisplay(120, 60);
		mySim.getBodyManager().getBodies().add(new Body(5, 0, 0, 0, 100, 0));
		mySim.getBodyManager().getBodies().add(new Body(0, 0, 0, 10000));

		while (true)
		{
			System.out.println("\033[H\033[2J");
			System.out.print(mySim);
			mySim.update(0.001);
			try
			{
				Thread.sleep(3);
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
			}
		}
	}
}
