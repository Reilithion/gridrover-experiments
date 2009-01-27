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

import java.util.PriorityQueue;
import java.util.Calendar;

/**
* This is the game engine.  It initializes a map, a rover, and a lander.
* It interprets all rover commands and is primarily responsible for
* the game mechanics.
*
* @author Lucas Adam M. Paul
* @version 0.0.0
*/
public class GameEngine
{
	private RoverControlInterface controlInterface;
	private MapGrid missionMap;
	private Rover rover;
	//private Lander lander; // Not yet used
	private PriorityQueue<Event> eventQueue;

	/**
	* Makes a new game, with specified control interface, and blank map dimensions.
	*
	* @param controlInterface The interface with which the user, or program, will control the rover
	* @param width Desired width of the blank map, in squares
	* @param length Desired length of the blank map, in squares
	*/
	/*private GameEngine(RoverControlInterface controlInterface, int width, int length)
	{
		this.controlInterface = controlInterface;
		missionMap = new MapGrid(width, length);
		MapSquare startsquare = missionMap.getSquare(width/2, length/2);
		if (startsquare == null)
		{
			throw new Error("Something funky happened!  Really funky!");
		}
		new Lander("Lander", 348.0, 11.236, startsquare); // Mass 348.0 kg, 2.65 meters diameter by 1.6 meters tall
		rover = new Rover("Rover", 185.0, 5.52, startsquare); // Mass 185.0 kg, 1.5 meters tall by 2.3 meters wide by 1.6 meters long
	}/*

	/**
	* Makes a new game, with specified control interface, and randomly-generated map dimensions.
	*
	* @param controlInterface The interface with which the user, or program, will control the rover
	* @param width Desired width of the randomly-generated map, in squares
	* @param length Desired length of the randomly-generated map, in squares
	* @param maxElevation The maximum elevation of any generated square, in meters
	* @param precision The precision to which any given elevation might be generated.
	*                  2, for instance, might result in an elevation of 12.34 and 4
	*                  might result in an elevation of 12.3456
	*/
	protected GameEngine(RoverControlInterface controlInterface, int width, int length, double maxElevation, int precision)
	{
		this.controlInterface = controlInterface;
		missionMap = new MapGrid(width, length, maxElevation, precision);
		MapSquare startsquare = missionMap.getSquare(width/2, length/2);
		if (startsquare == null)
		{
			throw new Error("Something funky happened!  Really funky!");
		}
		/*lander = */
		new Lander("Lander", 348.0, 11.236, startsquare); // Mass 348.0 kg, 2.65 meters diameter by 1.6 meters tall
		rover = new Rover("Rover", 185.0, 5.52, startsquare, controlInterface); // Mass 185.0 kg, 1.5 meters tall by 2.3 meters wide by 1.6 meters long
		eventQueue = new PriorityQueue<Event>();
		Event initialCommand = new CommandEvent(Calendar.getInstance(), eventQueue, rover);
		eventQueue.add(initialCommand);
	}

	/**
	* This is the new main game loop.  Event evaluation takes place here.
	*/
	protected void eventLoop()
	{
		while (!eventQueue.isEmpty())
		{
			eventQueue.poll().apply();
		}
	}

	/**
	* This is the main game loop.  Command evaluation takes place here.
	*/
	protected void roverLoop()
	{
		boolean running = true;
		Command command;
		while (running)
		{
			command = controlInterface.getNextCommand();
			switch (command.getCommandWord())
			{
			case QUIT:
				running = false;
				break;
			case GO:
				go(command);
				break;
			case LOOK:
				look();
				break;
			case WAIT:
				break;
			default:
				controlInterface.commandUnknown(command);
			}
		}
	}

	/**
	* Executes the look command
	*/
	private void look()
	{
		MapSquare location = rover.getLocation();
		controlInterface.describeLocation(location);
	}

	/**
	* Executes the go command
	*
	* @param command The command and all its arguments
	*/
	private void go(Command command)
	{
		if (command.getArgs().length < 1)
		{
			Debug.debug("Argument length of less than 1.  Expected a direction.");
			controlInterface.commandFailed(command);
			return;
		}
		Debug.debug("Attempting to go " + command.getArgs()[0]);
		if (rover.go(command.getArgs()[0]))
			controlInterface.commandSucceeded(command);
		else
			controlInterface.commandFailed(command);
	}
}
