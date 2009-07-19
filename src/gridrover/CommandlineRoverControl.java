/*
    GridRover -- A game to teach programming skills
    Copyright (C) 2008  "Lucas" Adam M. Paul <reilithion@gmail.com>

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

package gridrover;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormat;

/**
* This class acts as an interface between a console user and
* the GridRover engine.  It allows a single player to control
* a rover directly.
*
* @author Lucas Adam M. Paul
* @version 0.0.0
*/
public class CommandlineRoverControl implements RoverControlInterface
{
	private BufferedReader reader;

	/**
	* Create a new CommandlineRoverControl object
	*/
	protected CommandlineRoverControl()
	{
		reader = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	* Get a command from the user and return it
	*
	* @return A Command object
	*/
	public Command getNextCommand(Calendar now)
	{
		Command command = null;

		while (command == null)
		{
			System.out.print(DateFormat.getDateTimeInstance().format(now.getTime()) + "> ");   // Scotty, prompt me up

			String inputLine = null;
			try
			{
				inputLine = reader.readLine();
			}
			catch (IOException ioe)
			{
				System.err.println("IO error trying to read from System.in");
				System.exit(1);
			}
			String[] words = inputLine.split(" ");

			String[] args = new String[words.length - 1];
			System.arraycopy(words, 1, args, 0, args.length);

			for (CommandWord w : CommandWord.values())
			{
				if (words[0].equalsIgnoreCase(w.toString()))
					command = new Command(w, args);
			}

			if (command == null)
				System.out.println("Unrecognized command: " + words[0]);
		}

		return command;
	}

	/**
	* Informs the user that the provided command was unknown.
	*
	* @param command The command we couldn't recognize
	*/
	public void commandUnknown(Command command)
	{
		System.err.println("Unknown command \"" + command.getCommandWord().toString() + "\" ... which is weird, because we only produce known commands.");
		Debug.debug("Something funky happened with the CommandlineRoverControl module.");
	}

	/**
	* Informs the user that the provided command succeeded.
	*
	* @param command The command that succeeded
	*/
	public void commandSucceeded(Command command)
	{
		switch (command.getCommandWord())
		{
		case GO:
			System.out.println("Moved " + command.getArgs()[0]);
			break;
		default:
			System.out.println("Unknown command succeeded: " + command.getCommandWord().toString());
		}
	}

	/**
	* Informs the user that the provided command failed.
	*
	* @param command The command that failed
	*/
	public void commandFailed(Command command)
	{
		switch (command.getCommandWord())
		{
		case GO:
			if (command.getArgs().length < 1)
				System.out.println("Go where?");
			else
				System.out.println("Unable to move " + command.getArgs()[0]);
			break;
		default:
			System.out.println("Unknown command failed: " + command.getCommandWord().toString());
		}
	}

	/**
	* Describes a given location to the user (usually the result of a "look" command)
	* Warning:  This method is likely to change somehow to accomodate
	* limited information available to the rover.  In particular, the
	* supplied parameter type might change.
	*
	* @param location The location to describe to the user
	*/
	public void describeLocation(MapSquare location)
	{
		System.out.println("Elevation: " + location.getElevation());
		System.out.println("Contents of this square:");
		ArrayList<PhysicalObject> contentsOfSquare = location.getInventory();
		for (PhysicalObject p : contentsOfSquare)
		{
			System.out.println("	Physical Object: " + p.getName());
			System.out.println("		Mass = " + p.getMass());
			System.out.println("		Bulk = " + p.getBulk());
		}
	}

	/**
	* Describes a Rover's status to the user
	*
	* @param rover The rover whose status is to be gathered and reported
	*/
	public void updateStatus(Rover rover)
	{
		System.out.println("Energy remaining: " + rover.getEnergy());
	}
}
