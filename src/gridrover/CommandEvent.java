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
* A CommandEvent is an opportunity for a rover control interface to
* provide a new command for its rover.  When processed, it accepts a
* command from the rover control interface and passes it on to the
* CommandWord enum for processing.
*
* @author Lucas Adam M. Paul
* @version 0.0.1
*/
public class CommandEvent extends Event
{
	private Rover rover;

	/**
	* Creates an event that will get a command fromm the rover's control
	* interface.
	*
	* @param startTime The time when the rover will have an opportunity to
	*                  provide a new command.
	* @param rover Which rover will have a new command
	*/
	protected CommandEvent(Calendar startTime, PriorityQueue<Event> eventQueue, Rover rover)
	{
		super(startTime, eventQueue);
		this.rover = rover;
	}

	/**
	* Get the command from the rover control interface and process it.
	*/
	protected void apply()
	{
		//Debug.debug(startTime);
		if (rover.getEnergy() <= 0)
			return;
		Command command = rover.getControlInterface().getNextCommand(startTime);
		command.getCommandWord().apply(startTime, eventQueue, rover, command);
	}
}
