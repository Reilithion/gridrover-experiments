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

import java.util.Calendar;
import java.util.PriorityQueue;

/**
* Everything that happens in GridRover is represented by a Event.
* An Event is an event that occurs somewhere in the game.  This is
* kind of abstract, and meant to be extended by other classes, but
* all Events have a start time and can be applied.
*
* @author Lucas Adam M. Paul
* @version 0.0.0
*/
public abstract class Event
{
	protected Calendar startTime;
	protected PriorityQueue<Event> eventQueue;

	/**
	* Do some minimal setup
	*/
	protected Event(Calendar startTime, PriorityQueue<Event> eventQueue)
	{
		this.startTime = startTime;
		this.eventQueue = eventQueue;
	}

	/**
	* This method should apply its effects to the game state.
	*/
	protected abstract void apply();

	/**
	* We sort events by their start time.
	*/
	public int compareTo(Event anotherEvent)
	{
		return startTime.compareTo(anotherEvent.startTime);
	}
}
