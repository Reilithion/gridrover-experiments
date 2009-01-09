/*
    GridRover -- A game to teach programming skills
    Copyright (C) 2008  "Lucas" Adam M. Paul <reilithion@gmail.com>

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
* This class holds information about a command that was issued by the user.
* A command currently consists of a CommandWord and an array of Strings
* representing the arguments to that command.
* 
* If the command had only one word, then the array has a length of 0.
* 
* @author Lucas Adam M. Paul
* @version 0.0.0
*/
public class Command
{
	private CommandWord commandWord;
	private String[] args;
	
	/**
	* Create a command object. A CommandWord must be supplied, but any number
	* of Strings can be provided as arguments.
	*
	* @param commandWord A recognized command for the GridRover engine
	* @param args Arguments to the command
	*/
	public Command(CommandWord commandWord, String ... args)
	{
		this.commandWord = commandWord;
		this.args = args;
	}
	
	/**
	* Returns the named component of the Command
	*/
	public CommandWord getCommandWord()
	{
		return commandWord;
	}
	
	/**
	* Returns the arguments component of the Command
	*/
	public String[] getArgs()
	{
		return args;
	}
}
