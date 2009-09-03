/*
    GridRover -- A game to teach programming skills
    Copyright (C) 2008-2009  "Lucas" Adam M. Paul <reilithion@gmail.com>

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

import java.util.Calendar;
import java.util.PriorityQueue;

/**
* A TravelEvent is a simulated event in which a Rover (or possibly some other object)
* travels under its own power from one MapSquare to another.  For now, possible
* destination MapSquares are limited to those immediately adjacent to the square
* currently occupied by the Rover.  Valid adjacent squares are those to the north,
* south, east, or west of the current square.  A direction is not valid if it takes
* the Rover out of the mission grid.  Providing such a direction will result in the
* loss of some time and a commandFailed message being sent to the Rover's control
* interface.
*
* In the future, this event might be broken up into two or three sub-events.  The first
* event will represent the Rover leaving its current square.  Some time after that, a
* second event will represent the Rover entering its new square.  A third sub-event
* may be required to summarize the effects of the travel on the Rover, such as energy
* consumption, wear, and possible damage.  Breaking up the event in this way would
* help increase the chances that rovers encounter one-another during a mission.
*
* @author Lucas Adam M. Paul
* @version 0.0.1
*/
public class TravelEvent extends Event
{
	private Rover rover;
	private Command command;

	/**
	* Create a new TravelEvent.  Note that although an eventQueue is a required argument,
	* this constructor does not place the new event into the queue.  That is left to the
	* caller.
	*/
	protected TravelEvent(Calendar startTime, PriorityQueue<Event> eventQueue, Rover rover, Command command)
	{
		super(startTime, eventQueue);
		this.rover = rover;
		this.command = command;
	}

	/**
	* Cause a rover to travel from one square to another.  May fail.
	*/
	protected void apply()
	{
		Calendar eventStartTime = (Calendar) startTime.clone();
		Debug.debug("Attempting to go " + command.getArgs()[0]);
		if (rover.go(command.getArgs()[0]))
		{
			eventStartTime.add(Calendar.MILLISECOND, 50000);
			rover.getControlInterface().commandSucceeded(command);
		}
		else
		{
			eventStartTime.add(Calendar.MILLISECOND, 50);
			rover.getControlInterface().commandFailed(command);
		}
		eventQueue.add(new CommandEvent(eventStartTime, eventQueue, rover));
	}
}
