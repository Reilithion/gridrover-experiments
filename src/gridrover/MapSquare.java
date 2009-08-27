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

import java.util.ArrayList;

/**
* A MapSquare represents a location on the MapGrid that the rover can occupy.
* A rover can interact with objects and features in the same MapSquare.
* If a rover wants to interact with objects in a MapSquare other than the
* one it currently occupies, it must first GO to that MapSquare.
*
* @author Lucas Adam M. Paul
* @version 0.0.0
*/
public class MapSquare
{
	private double elevation;
	private ArrayList<Thing> inventory;
	private MapGrid locale;

	/**
	* Basic constructor for MapSquare.  The MapGrid must be supplied as
	* an argument to the constructor so that the MapSquare has a
	* reference back to its locale.  The elevation of a MapSquare
	* created using this constructor will be 0.0.
	*
	* @param locale The MapGrid that this MapSquare is part of
	*/
	protected MapSquare(MapGrid locale)
	{
		this.locale = locale;
		this.elevation = 0.0;
		inventory = new ArrayList<Thing>();
	}

	/**
	* Constructor for MapSquare that sets an elevation.  Same as the other
	* constructor, but also takes a double to set the elevation of this
	* MapSquare.
	*
	* @param locale The MapGrid that this MapSquare is part of
	* @param elevation The elevation of this MapSquare
	*/
	protected MapSquare(MapGrid locale, double elevation)
	{
		this.locale = locale;
		this.elevation = elevation;
		inventory = new ArrayList<Thing>();
	}

	/**
	* Returns this MapSquare's elevation
	*
	* @return The elevation, in meters, of this MapSquare
	*/
	protected double getElevation()
	{
		return elevation;
	}

	/**
	* Get this Square's inventory.  This is the naïve way to do inventory
	* management.  Ideally, the MapSquare should do its own inventory
	* management, but this is simpler to implement at the moment.
	* This method may become deprecated or change in the future.
	*
	* @return An ArrayList of PhysicalObjects, representing the contents
	* of this MapSquare
	*/
	protected ArrayList<Thing> getInventory()
	{
		return inventory;
	}

	/**
	* Get the square direction from this square.  The variable "direction"
	* is a String, currently as defined by MapGrid, describing the
	* directional relationship between this square and the desired one.
	*
	* @param direction A String describing the directional relationship
	* between this MapSquare and the desired one.
	* @return The desired MapSquare, or null if it does not exist.
	*/
	protected MapSquare getSquareDirFrom(String direction)
	{
		return locale.getSquareDirFrom(direction, this);
	}
}
