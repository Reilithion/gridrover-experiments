/*
    GridRover -- A game to teach programming skills
    Copyright (C) 2008  "Lucas" Adam M. Paul

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

/**
* Represents a Lander that deployed a particular Rover.
*
* @author Lucas Adam M. Paul
* @version 0.0.0
*/
public class Lander implements PhysicalObject
{
	private String name;
	private double mass, bulk;
	private MapSquare location;

	/**
	* Makes a new Lander, with specified name, mass, bulk, and location.
	*
	* @param name Lander's name.  Does not need to be unique.
	* @param mass Lander's mass in kilograms.
	* @param bulk Rough, boxy estimate of Lander's volume, in cubic meters.
	* @param location Place where Lander should put itself on the map.
	*/
	protected Lander(String name, double mass, double bulk, MapSquare location)
	{
		this.name = name;
		this.mass = mass;
		this.bulk = bulk;
		this.location = location;
		location.getInventory().add(this);
	}

	/**
	* Return the Lander's name.
	*
	* @return Lander's name
	*/
	public String getName()
	{
		return name;
	}
	
	/**
	* Return the Lander's mass.
	*
	* @return Lander's mass
	*/
	public double getMass()
	{
		return mass;
	}

	/**
	* Return the Lander's bulk.
	*
	* @return Lander's bulk
	*/
	public double getBulk()
	{
		return bulk;
	}
	
	/**
	* Return the MapSquare where the Lander is.
	*
	* @return Lander's location
	*/
	private MapSquare getLocation()
	{
		return location;
	}
}
