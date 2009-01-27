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
* A Rover represents... well, a rover.  It has all the characteristics of a
* PhysicalObject.  It keeps track of its own location as it moves about the
* MapGrid.  In the future, it will have an inventory.
*
* @author Lucas Adam M. Paul
* @version 0.0.0
*/
public class Rover implements PhysicalObject
{
	private String name;
	private double mass, bulk;
	private MapSquare location;
	private RoverControlInterface controlInterface;

	/**
	* Creates a Rover with specified name, mass, bulk, and initial location.
	* Under normal circumstances, a Rover should be created in the same
	* MapSquare as its Lander.
	*
	* @param name The Rover's name
	* @param mass The Rover's mass in kg
	* @param bulk The Rover's bulk, in m^3
	* @param location The Rover's initial location on the MapGrid
	* @param controlInterface The Rover Control Interface that will be used by a player
	*                         or program to control the rover, and get information back.
	*/
	protected Rover(String name, double mass, double bulk, MapSquare location, RoverControlInterface controlInterface)
	{
		this.name = name;
		this.mass = mass;
		this.bulk = bulk;
		this.location = location;
		location.getInventory().add(this);
		this.controlInterface = controlInterface;
	}

	/**
	* Returns the name of the Rover.  "Rover" will suffice.
	*
	* @return Rover's name
	*/
	public String getName()
	{
		return name;
	}

	/**
	* Returns the mass of the Rover in kg.
	*
	* @return Mass of the Rover in kg
	*/
	public double getMass()
	{
		return mass;
	}

	/**
	* Returns the bulk of the rover in cubic meters.
	*
	* @return Bulk of the Rover in m^3
	*/
	public double getBulk()
	{
		return bulk;
	}

	/**
	* Returns the Rover's current location.
	*
	* @return Rover's location
	*/
	protected MapSquare getLocation()
	{
		return location;
	}

	/**
	* Returns this Rover's Rover Control Interface
	*
	* @return Rover's controlInterface
	*/
	protected RoverControlInterface getControlInterface()
	{
		return controlInterface;
	}

	/**
	* This method asks the Rover to try to go in a particular direction.
	* The rover will go in that direction if it is <em>able</em> to go
	* in that direction -- not if it determines it is <em>safe</em> to
	* go in that direction.  This command may result in damage to or the
	* destruction of the rover.
	*
	* @param direction The direction the rover should try to travel in
	* @return True if the rover was able to travel in the desired direction, or
	*         False if it was blocked.  True will be returned even if the rover
	*         was damaged or destroyed.
	*/
	protected boolean go(String direction)
	{
		MapSquare nextLocation = location.getSquareDirFrom(direction);
		if (nextLocation == null)
			return false;
		location.getInventory().remove(this);
		nextLocation.getInventory().add(this);
		location = nextLocation;
		return true;
	}
}
