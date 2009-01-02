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

public class GridRover
{
	public static void main (String[] args)
	{
		int width = 10;
		int length = 10;
		
		System.out.println("GridRover Copyright (C) 2008  Lucas Adam M. Paul");
		System.out.println("This program comes with ABSOLUTELY NO WARRANTY; for details see LICENSE.TXT.");
		System.out.println("This is free software, and you are welcome to redistribute it");
		System.out.println("under certain conditions; see LICENSE.TXT for details.\n");
		
		System.out.println("Initializing GridRover...");
		GameEngine engine = new GameEngine(new CommandlineRoverControl(), width, length);
		
		System.out.println("Running GridRover...");
		engine.roverLoop();
	}
}

