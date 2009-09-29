/*
    GridRover -- A game to teach programming skills
    Copyright (C) 2008-2009  "Lucas" Adam M. Paul

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

import java.util.List;
import java.util.ArrayList;

/**
* A ThingBean holds data about a Thing prototype.  This is different data from a
* Thing, really.  For instance, a ThingBean holds a maximum and minimum mass for
* its particular class of Thing.  Any given Thing, however, has a single,
* specific mass, and not a range.
*
* @author Lucas Adam M. Paul
* @version 0.0.1
*/
public class ThingBean
{
	private String name;
	private double maxMass, minMass, maxDensity, minDensity;
	private List<AppearanceBean> totalAppearance;

	public ThingBean()
	{
		totalAppearance = new ArrayList<AppearanceBean>();
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setMaxMass(double maxMass)
	{
		this.maxMass = maxMass;
	}

	public double getMaxMass()
	{
		return maxMass;
	}

	public void setMinMass(double minMass)
	{
		this.minMass = minMass;
	}

	public double getMinMass()
	{
		return minMass;
	}

	public void setMaxDensity(double maxDensity)
	{
		this.maxDensity = maxDensity;
	}

	public double getMaxDensity()
	{
		return maxDensity;
	}

	public void setMinDensity(double minDensity)
	{
		this.minDensity = minDensity;
	}

	public double getMinDensity()
	{
		return minDensity;
	}

	public void addAppearanceBean(AppearanceBean a)
	{
		totalAppearance.add(a);
	}

	public List<AppearanceBean> getAppearanceBeans()
	{
		return totalAppearance;
	}
}
