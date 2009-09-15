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

/**
* Represents a Lander that deployed a particular Rover.
*
* @author Lucas Adam M. Paul
* @version 0.0.1
*/
public class Lander extends Thing
{
	private MapSquare location;

	/**
	* Makes a new Lander, with specified name, mass, bulk, and location.
	*
	* @param name Lander's name.  Does not need to be unique.
	* @param mass Lander's mass in kilograms.
	* @param bulk Rough, boxy estimate of Lander's volume, in cubic meters.
	* @param location Place where Lander should put itself on the map.
	*/
	protected Lander(String name, double mass, double bulk)
	{
		super(name, mass, bulk);
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

	/**
	* Sets the Lander's location on the map grid.
	*
	* @param location The Lander's location on the MapGrid
	*/
	protected void setLocation(MapSquare location)
	{
		if (this.location != null)
			this.location.getInventory().remove(this);
		this.location = location;
		location.getInventory().add(this);
	}
}
