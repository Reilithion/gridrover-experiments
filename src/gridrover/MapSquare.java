/*
    GridRover -- A game to teach programming skills
    Copyright (C) 2008  Lucas Adam M. Paul

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
import gridrover.PhysicalObject;
import gridrover.MapGrid;

public class MapSquare
{
	private double elevation;
	private ArrayList<PhysicalObject> inventory;
	private MapGrid locale;

	/**
	Basic constructor for MapSquare
	*/
	public MapSquare(MapGrid locale)
	{
		this.locale = locale;
		this.elevation = 0.0;
		inventory = new ArrayList<PhysicalObject>();
	}

	/**
	Constructor for MapSquare that sets an elevation
	*/
	public MapSquare(MapGrid locale, double elevation)
	{
		this.locale = locale;
		this.elevation = elevation;
		inventory = new ArrayList<PhysicalObject>();
	}

	/**
	Get this Square's elevation
	*/
	public double getElevation()
	{
		return elevation;
	}
	
	/**
	Get this Square's inventory
	*/
	public ArrayList<PhysicalObject> getInventory()
	{
		return inventory;
	}
	
	/**
	Get the square direction from this square.
	*/
	public MapSquare getSquareDirFrom(String direction)
	{
		return locale.getSquareDirFrom(direction, this);
	}
}
