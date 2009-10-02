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
import java.util.Iterator;

/**
* This is an item in the GridRover world.  Currently inert, Things can be
* picked up and carried around.
*
* @author Lucas Adam M. Paul
* @version 0.0.1
*/
public class Thing
{
	private static int idCounter = 0;
	private String name;
	private int id;
	private double mass, bulk;
	private List<AppearanceBean> totalAppearance;

	/**
	* Makes a new Thing with specified name, mass, and bulk
	*
	* @param name A short name for this Thing.  Does not need to be unique.
	* @param mass Mass of this Thing in kilograms
	* @param bulk Very rough, boxy estimate of volume in cubic meters
	*/
	protected Thing(String name, double mass, double bulk)
	{
		this.name = name;
		this.mass = mass;
		this.bulk = bulk;
	}

	/**
	* Makes a copy of the specified item.
	*
	* @param that An item to copy
	*/
	protected Thing(Thing that)
	{
		this.name = that.name;
		this.mass = that.mass;
		this.bulk = that.bulk;
	}

	/**
	* Makes a new Thing out of the supplied ThingBean prototype.
	*
	* @param prototype A ThingBean prototype to instantiate
	*/
	protected Thing(ThingBean prototype)
	{
		this.name = prototype.getName();
		this.id = idCounter++;
		this.mass = Math.random() * (prototype.getMaxMass() - prototype.getMinMass()) + prototype.getMinMass();
		/*
		* m = mass, v = volume, d = density
		* d = m / v
		*  m
		* v d
		*/
		this.bulk = this.mass / (Math.random() * (prototype.getMaxDensity() - prototype.getMinDensity()) + prototype.getMinDensity());
		this.totalAppearance = prototype.getAppearanceBeans();
	}

	/**
	* Returns the name of the Thing
	*
	* @return Thing's name
	*/
	public String getName()
	{
		return name;
	}

	/**
	* Returns the unique ID of this Thing
	*
	* @return Thing's ID
	*/
	public int getID()
	{
		return id;
	}

	/**
	* Returns the mass of the Thing
	*
	* @return Thing's mass
	*/
	public double getMass()
	{
		return mass;
	}

	/**
	* Returns the bulk of the Thing
	*
	* @return Thing's bulk
	*/
	public double getBulk()
	{
		return bulk;
	}

	/**
	* Returns this Thing's appearance
	*
	* @return an Iterator over all of the aspects of this Thing's appearance
	*/
	public Iterator<AppearanceBean> getAppearance()
	{
		return (Iterator<AppearanceBean>) totalAppearance;
	}
}
