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
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
* A RoverControlInterface must provide all that is required for a user
* or program to send commands to a rover.  It must also provide a way
* to inform the user or program of the results of those commands.
*
* @author Lucas Adam M. Paul
* @version 0.0.1
*/
public interface RoverControlInterface
{
	/**
	* Get the next command from the interface.  Whatever query is
	* necessary to prompt a response from the user or program is
	* left up to the implementing class.
	*
	* @return The Command provided by the user or program
	*/
	public Command getNextCommand(Calendar now);

	/**
	* This method should inform the user or program that the command
	* that was provided is unknown by the engine, and could therefore
	* not be processed.
	*
	* @param command The Command that could not be recognized
	*/
	public void commandUnknown(Command command);

	/**
	* This method should inform the user or program that the command
	* that was provided was successfully processed and carried out
	* by the rover.
	*
	* @param command The Command that succeeded
	*/
	public void commandSucceeded(Command command);

	/**
	* This method should inform the user or program that the command
	* that was provided could not be successfully completed by the
	* rover.
	*
	* @param command The Command that failed
	*/
	public void commandFailed(Command command);

	/**
	* This method should provide information about a MapSquare to the
	* user or program.
	*
	* @param location The MapSquare on which to provide information
	*/
	public void describeLocation(MapSquare location);

	public void describeLighting(List<Spectrum> lighting);

	public void describeObjectAppearance(Set<ResponseBean> responses);

	/**
	* This method should provide information about a Rover's status.
	*
	* @param rover The rover whose status is to be gathered and reported
	*/
	public void updateStatus(Rover rover);
}
