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

import gridrover.MapSquare;
import gridrover.OutOfBoundsException;

public class MapGrid
{
	private MapSquare grid[][];

	/**
	Basic constructor for MapGrid
	*/
	public MapGrid(int width, int length)
	{
		grid = new MapSquare[width][length];
		fillWithBlank();
	}
	
	/**
	Random constructor for MapGrid
	*/
	public MapGrid(int width, int length, double maxElevation, int precision)
	{
		grid = new MapSquare[width][length];
		fillWithRandom(maxElevation, precision);
	}

	/**
	Get a square from the map
	*/
	public MapSquare getSquare(int x, int y) throws OutOfBoundsException
	{
		try
		{
			return grid[x][y];
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			throw new OutOfBoundsException("Square at [" + x + "] [" + y + "] is out of bounds.", e);
		}
	}

	/**
	Get the width of the map
	*/
	public int getWidth()
	{
		return grid.length;
	}

	/**
	Get the Length of the map.
	*/
	public int getLength()
	{
		return grid[0].length;
	}

	/**
	Fill the map with blank squares
	*/
	public void fillWithBlank()
	{
		for (int x=0; x < grid.length; x++)
		{
			for (int y=0; y < grid[x].length; y++)
			{
				grid[x][y] = new MapSquare(this);
			}
		}
	}
	
	/**
	Fill the map with squares of random elevation
	*/
	public void fillWithRandom(double maxElevation, int precision)
	{
		for (int x=0; x < grid.length; x++)
		{
			for (int y=0; y < grid[x].length; y++)
			{
				grid[x][y] = new MapSquare(this, Math.floor(Math.random() * (maxElevation * Math.pow(10,  precision))) / Math.pow(10, precision));
			}
		}
	}
	
	/**
	Get the square that is in the desired direction from the supplied square
	*/
	public MapSquare getSquareDirFrom(String direction, MapSquare start) throws OutOfBoundsException
	{
		int x=0, y=0;
		boolean found = false;
		for(int u=0; u < grid.length; u++)
		{
			for(int v=0; v < grid[u].length; v++)
			{
				if(start == grid[u][v])
				{
					found = true;
					x = u;
					y = v;
					// DEBUG
					// System.out.println("Inside getSquareDirFrom for loop, x = " + x + " y = " + y);
				}
			}
		}
		// DEBUG
		// System.out.println("Just outside for loop: x = " + x + " y = " + y);
		if(!found)
			return null;
		if(direction.equals("n"))
			return getSquare(x, y+1);
		if(direction.equals("s"))
			return getSquare(x, y-1);
		if(direction.equals("e"))
			return getSquare(x+1, y);
		if(direction.equals("w"))
			return getSquare(x-1, y);
		return null;
	}
}
