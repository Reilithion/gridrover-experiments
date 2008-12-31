/*
    GridRover -- A game to teach programming skills
    Copyright (C) 2008  Lucas Adam M. Paul

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import gridrover.Item;
import gridrover.Rover;
import gridrover.MapGrid;
import gridrover.MapSquare;
import gridrover.PhysicalObject;
import gridrover.OutOfBoundsException;

public class Experiment
{
	private MapGrid worldmap;
	private Rover rover;
	private boolean running;

	public Experiment(int width, int length)
	{
		worldmap = new MapGrid(width, length);
		worldmap.fillWithBlank();
		MapSquare roversquare;
		try
		{
			roversquare = worldmap.getSquare(width/2, length/2);
		}
		catch (OutOfBoundsException e)
		{
			throw new Error("Something funky happened!  Really funky!", e);
		}
		rover = new Rover(185.0, 5.52, roversquare);
		running = true;
	}

	public void run()
	{
		while (running)
		{
			System.out.print("> ");   // Scotty, prompt me up

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String inputLine = null;
			try
			{
				inputLine = reader.readLine();
			}
			catch (IOException ioe)
			{
				System.out.println("IO error trying to read from System.in");
				System.exit(1);
			}
			/* StringTokenizer tokenizer = new StringTokenizer(inputLine);

			String[] words = new String[Math.max(tokenizer.countTokens(), 2)];

			for (int x = 0; tokenizer.hasMoreTokens(); x++)
			{
				words[x] = tokenizer.nextToken();
			} */
			String[] words = inputLine.split(" ");

			if (words[0].equalsIgnoreCase("quit"))
			{
				running = false;
				break;
			}

			if (words[0].equalsIgnoreCase("look"))
				this.look();
			if (words[0].equalsIgnoreCase("go"))
				this.go(words);
		}
	}

	public void look()
	{
		MapSquare location = rover.getLocation();
		System.out.println("Elevation: " + location.getElevation());
		System.out.println("Contents of this square:");
		ArrayList<PhysicalObject> contentsOfSquare = location.getInventory();
		for (PhysicalObject p : contentsOfSquare)
		{
			System.out.println("	Physical Object:");
			System.out.println("		Mass = " + p.getMass());
			System.out.println("		Bulk = " + p.getBulk());
		}
	}

	public void go(String ... words)
	{
		System.out.println("Moving " + words[1]);
		if (rover.go(words[1]))
		{
			look();
		}
		else
		{
			System.out.println("Could not go " + words[1]);
		}
	}

	public static void main (String[] args)
	{
		for (String s : args)
		{
			if (s.equalsIgnoreCase("--oldtests"))
			{
				runOldTests(args);
				System.exit(0);
			}
		}
		System.out.println("    GridRover Experiments Copyright (C) 2008  Lucas Adam M. Paul");
		System.out.println("    This program comes with ABSOLUTELY NO WARRANTY; for details see LICENSE.");
		System.out.println("    This is free software, and you are welcome to redistribute it");
		System.out.println("    under certain conditions; see LICENSE for details.\n");
		System.out.println("Initializing the Experiment...");
		Experiment experiment = new Experiment(10, 10);
		System.out.println("Running the Experiment...");
		experiment.run();
	}

	public static void runOldTests (String[] args)
	{
		String out;
		out = "You typed: experiment ";
		for (String s : args)
		{
			out += s + " ";
		}
		System.out.println(out);

		MapGrid myMap = new MapGrid(7, 8);
		myMap.fillWithBlank();
		System.out.println("Map created.");
		System.out.println("Width: " + myMap.getWidth());
		System.out.println("Length: " + myMap.getLength());

		MapSquare sampleSquare;
		try
		{
			sampleSquare = myMap.getSquare(5, 3);
		}
		catch (OutOfBoundsException e)
		{
			throw new Error("Something funky happened!", e);
		}
		System.out.println("Sample square:");
		System.out.println("5 x 3:");
		System.out.println("Elevation = " + sampleSquare.getElevation());

		ArrayList<PhysicalObject> sampleInv = sampleSquare.getInventory();
		if (sampleInv.isEmpty())
			System.out.println("Inventory empty.");
		System.out.println("Adding something to the inventory of this square...");
		Item sampleItem = new Item(5.0, 2.3);
		System.out.println("An Item with mass 5.0 and bulk 2.3.");
		sampleInv.add(sampleItem);
		System.out.println("Added.");
		Rover sampleRover = new Rover(185.0, 5.52, sampleSquare);
		System.out.println("A Rover with mass 185.0 and bulk 5.52 (figures for Spirit and Opportunity Martian landers).");
		System.out.println("Added.");
		System.out.println("Now processing contents of inventory...");
		sampleInv = sampleSquare.getInventory();
		for (PhysicalObject p : sampleInv)
		{
			System.out.println("Physical Object:");
			System.out.println("	Mass = " + p.getMass());
			System.out.println("	Bulk = " + p.getBulk());
		}

		System.out.println("Causing rover to travel east.");
		sampleRover.go("e");
		System.out.println("Checking to see that it's left this square:");
		for (PhysicalObject p : sampleInv)
		{
			System.out.println("Physical Object:");
			System.out.println("	Mass = " + p.getMass());
			System.out.println("	Bulk = " + p.getBulk());
		}
		System.out.println("Loading square 6 x 3");
		try
		{
			sampleSquare = myMap.getSquare(6, 3);
		}
		catch (OutOfBoundsException e)
		{
			throw new Error("Something funky happened!", e);
		}
		System.out.println("Loading inventory.");
		sampleInv = sampleSquare.getInventory();
		System.out.println("Checking to see if the rover is here:");
		for (PhysicalObject p : sampleInv)
		{
			System.out.println("Physical Object:");
			System.out.println("	Mass = " + p.getMass());
			System.out.println("	Bulk = " + p.getBulk());
		}

		System.out.println("Now we go east again.  We should get an exception.");
		sampleRover.go("e");
	}
}
