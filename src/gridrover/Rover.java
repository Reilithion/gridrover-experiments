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
* A Rover represents... well, a rover.  It has all the characteristics of a
* PhysicalObject.  It keeps track of its own location as it moves about the
* MapGrid.  In the future, it will have an inventory.
*
* @author Lucas Adam M. Paul
* @version 0.0.1
*/
public class Rover extends Thing
{
	private double energy, maxEnergy;
	private MapSquare location;
	private RoverControlInterface controlInterface;

	/**
	* Creates a Rover with specified name, mass, bulk, energy, and control interface.
	* A rover cannot move until it is placed in a Map Square using setLocation.
	*
	* @param name The Rover's name
	* @param mass The Rover's mass in kg
	* @param bulk The Rover's bulk, in m^3
	* @param maxEnergy The Rover's energy storage capacity in Joules
	* @param controlInterface The Rover Control Interface that will be used by a player
	*                         or program to control the rover, and get information back.
	*/
	protected Rover(String name, double mass, double bulk, double maxEnergy, RoverControlInterface controlInterface)
	{
		super(name, mass, bulk);
		this.energy = this.maxEnergy = maxEnergy;
		this.controlInterface = controlInterface;
	}

	/**
	* Returns the Rover's remaining energy.
	*
	* @return Rover's energy
	*/
	public double getEnergy()
	{
		return energy;
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
	* Sets the Rover's location on the map grid.
	*
	* @param location The Rover's location on the MapGrid
	*/
	protected void setLocation(MapSquare location)
	{
		if (this.location != null)
			this.location.getInventory().remove(this);
		this.location = location;
		location.getInventory().add(this);
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
		if (nextLocation == null || energy <= 0)
			return false;
		location.getInventory().remove(this);
		nextLocation.getInventory().add(this);
		energy -= 5 + (nextLocation.getElevation() - location.getElevation());
		location = nextLocation;
		return true;
	}
}
