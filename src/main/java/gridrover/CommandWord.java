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
* This class enumerates our recognized engine commands.  If a command
* is listed here, our engine should handle it somehow.
*
* @author Lucas Adam M. Paul
* @version 0.0.1
*/
public enum CommandWord
{
	GO
	{
		void apply(Calendar startTime, PriorityQueue<Event> eventQueue, Rover rover, Command command)
		{
			Calendar eventStartTime = (Calendar) startTime.clone();
			eventStartTime.add(Calendar.MILLISECOND, 50);
			if (command.getArgs().length < 1)
			{
				Debug.debug("Argument length of less than 1.  Expected a direction.");
				rover.getControlInterface().commandFailed(command);
				eventQueue.add(new CommandEvent(eventStartTime, eventQueue, rover));
				return;
			}
			eventQueue.add(new TravelEvent(eventStartTime, eventQueue, rover, command));
		}
	},

	LOOK
	{
		/*
		* I kind of cheat in the implementation here.  Shouldn't a "look" be
		* an event in its own right?
		*/
		void apply(Calendar startTime, PriorityQueue<Event> eventQueue, Rover rover, Command command)
		{
			Calendar eventStartTime = (Calendar) startTime.clone();
			eventStartTime.add(Calendar.MILLISECOND, 50);
			rover.getControlInterface().describeLocation(rover.getLocation());
			eventQueue.add(new CommandEvent(eventStartTime, eventQueue, rover));
		}
	},

	WAIT
	{
		/*
		* Eventually, I'd like to add argument processing to this method so you
		* can tell the rover how long to wait.
		*/
		void apply(Calendar startTime, PriorityQueue<Event> eventQueue, Rover rover, Command command)
		{
			Calendar eventStartTime = (Calendar) startTime.clone();
			eventStartTime.add(Calendar.MILLISECOND, 50);
			eventQueue.add(new CommandEvent(eventStartTime, eventQueue, rover));
		}
	},

	STATUS
	{
		/*
		* This command should provide the Control Interface with status about the
		* Rover that doesn't take a long time to gather, such as remaining energy,
		* damaged systems and so forth.  Like the LOOK command, this implementation
		* cheats -- there is no "RoverCheckStatus" event; it happens instantly.
		*/
		void apply(Calendar startTime, PriorityQueue<Event> eventQueue, Rover rover, Command command)
		{
			Calendar eventStartTime = (Calendar) startTime.clone();
			eventStartTime.add(Calendar.MILLISECOND, 50);
			rover.getControlInterface().updateStatus(rover);
			eventQueue.add(new CommandEvent(eventStartTime, eventQueue, rover));
		}
	},

	QUIT
	{
		/*
		* QUIT's apply method is intentionally empty.  All other command events
		* need to add a new CommandEvent to the eventQueue in order to keep the
		* simulation going.  All QUIT has to do in order to end the simulation
		* is to fail to do this.  The eventQueue, then, must eventually be
		* exhausted, and when there are no more events to process, the simulation
		* ends.
		*
		* Of course, this may fail to end the simulation if there are any types
		* of event that can produce new events without user interaction.  For this
		* reason, a FORCEQUIT command may become necessary in the future.  All it
		* would need to do is empty the eventQueue.
		*/
		void apply(Calendar startTime, PriorityQueue<Event> eventQueue, Rover rover, Command command)
		{
		}
	};

	abstract void apply(Calendar startTime, PriorityQueue<Event> eventQueue, Rover rover, Command command);
}
