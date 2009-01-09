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
* A PhysicalObject is something that a rover can interact with.  A
* PhysicalObject might be carried, bumped into, shot at with a laser
* or merely observed.  A PhysicalObject has a name, a mass, and a
* bulk.
*
* @author Lucas Adam M. Paul
* @version 0.0.0
*/
public interface PhysicalObject
{
	/**
	* Return the PhysicalObject's name.  Names need not be unique.
	*
	* @return A String representing the PhysicalObject's name
	*/
	public String getName();

	/**
	* Return the mass of the PhysicalObject in kilograms.
	*
	* @return the mass of the PhysicalObject, in kg
	*/
	public double getMass();

	/**
	* Return the bulk of the PhysicalObject in cubic meters.
	*
	* @return the bulk of the PhysicalObject in m^3
	*/
	public double getBulk();
}
