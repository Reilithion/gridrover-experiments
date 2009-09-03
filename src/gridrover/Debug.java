/*
    GridRover -- A game to teach programming skills
    Copyright (C) 2008-2009  "Lucas" Adam M. Paul <reilithion@gmail.com>

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
import java.text.DateFormat;

/**
* This class is responsible for collecting debug information from the rest
* of the program.  If a piece of code somewhere in the project needs to
* output debug information, it should do so with this class.
*
* @author Lucas Adam M. Paul
* @version 0.0.1
*/
public class Debug
{
	/**
	* This method outputs debugging information to stderr.
	*
	* @param message The debug message to be output or logged.
	*/
	protected static void debug(String message)
	{
		System.err.println("Debug: " + message);
	}

	protected static void debug(Calendar time)
	{
		System.err.println("Debug: " + DateFormat.getDateTimeInstance().format(time.getTime()));
	}
}
