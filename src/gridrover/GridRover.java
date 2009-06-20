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
* This class is the main class of the GridRover application.  It initializes the
* Game Engine.  In the future, it will also interpret command line arguments.
*
* @author Lucas Adam M. Paul
* @version 0.0.0
*/
public class GridRover
{
	/**
	* This method does the work of getting our application started.
	*
	* @param args Command line arguments
	*/
	public static void main (String[] args)
	{
		int width = 10;
		int length = 10;
		double maxElevation = 25.0;
		int precision = 2;

		System.out.println("GridRover Copyright (C) 2008  Lucas Adam M. Paul");
		System.out.println("This program comes with ABSOLUTELY NO WARRANTY; for details see LICENSE.TXT.");
		System.out.println("This is free software, and you are welcome to redistribute it");
		System.out.println("under certain conditions; see LICENSE.TXT for details.\n");

		System.out.println("Initializing GridRover...");
		GameEngine engine = new GameEngine(new CommandlineRoverControl(), width, length, maxElevation, precision);

		System.out.println("Running GridRover...");
		engine.eventLoop();
	}
}

