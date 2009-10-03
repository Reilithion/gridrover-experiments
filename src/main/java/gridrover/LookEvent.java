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
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class LookEvent extends Event
{
	private Rover rover;
	private Command command;

	protected LookEvent(Calendar startTime, PriorityQueue<Event> eventQueue, Rover rover, Command command)
	{
		super(startTime, eventQueue);
		this.rover = rover;
		this.command = command;
	}

	protected void apply()
	{
		Calendar eventStartTime = (Calendar) startTime.clone();
		eventStartTime.add(Calendar.MILLISECOND, 50);
		eventQueue.add(new CommandEvent(eventStartTime, eventQueue, rover));
		RoverControlInterface rci = rover.getControlInterface();
		MapSquare location = rover.getLocation();
		rci.describeLocation(location);
		List<Spectrum> lighting = location.getLighting();
		rci.describeLighting(lighting);
		List<Thing> objects = location.getInventory();
		for (Thing thing : objects)
		{
			if (thing == rover)
				continue;
			Set<ResponseBean> thingAppearance = new HashSet<ResponseBean>();
			List<AppearanceBean> sensorData = thing.getAppearance();
			if (sensorData == null) return;
			for (AppearanceBean appearance : sensorData)
			{
				if (appearance.getStimulus() == null || appearance.getStimulus().equals(""))
				{
					thingAppearance.addAll(appearance.getResponseBeans());
					continue;
				}
				for (Spectrum spec : lighting)
				{
					if (spec.getName().equals(appearance.getStimulus()))
					{
						thingAppearance.addAll(appearance.getResponseBeans());
					}
				}
			}
			rci.describeObjectAppearance(thingAppearance);
		}
	}
}
