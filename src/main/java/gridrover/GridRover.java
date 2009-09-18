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
import java.util.prefs.Preferences;
import java.util.List;

/**
* This class is the main class of the GridRover application.  It initializes the
* Game Engine.  In the future, it will also interpret command line arguments.
*
* @author Lucas Adam M. Paul
* @version 0.0.1
*/
public class GridRover
{
	// Preference keys for this package
	private static final String MAP_WIDTH = "map_width";
	private static final String MAP_HEIGHT = "map_height";
	private static final String MAX_ELEVATION = "max_elevation";
	private static final String ELEVATION_PRECISION = "elevation_precision";

	/**
	* This method does the work of getting our application started.
	*
	* @param args Command line arguments
	*/
	public static void main (String[] args)
	{
		System.out.println("GridRover Copyright (C) 2008-2009 Lucas Adam M. Paul");
		System.out.println("This program comes with ABSOLUTELY NO WARRANTY; for details see LICENSE.TXT.");
		System.out.println("This is free software, and you are welcome to redistribute it");
		System.out.println("under certain conditions; see LICENSE.TXT for details.\n");

		System.out.println("Loading preferences...");
		Preferences prefs = Preferences.userNodeForPackage(GridRover.class);
		int width = prefs.getInt(MAP_WIDTH, 10);
		int length = prefs.getInt(MAP_HEIGHT, 10);
		double maxElevation = prefs.getDouble(MAX_ELEVATION, 25.0);
		int precision = prefs.getInt(ELEVATION_PRECISION, 2);

		XmlFileParser fileParser = new XmlFileParser(new ResourceLocater(null));
		List<Spectrum> spectra = fileParser.getSpectra("spectrum_types.xml");
		Debug.debug("Checking we got our spectra.");
		for (Spectrum spec : spectra)
		{
			Debug.debug("Type: " + spec.getName());
			List<String> list = spec.getColors();
			for (String color : list)
				Debug.debug("\tColor: " + color);
			list = spec.getShapes();
			for (String shape : list)
				Debug.debug("\tShape: " + shape);
		}
		List<Thing> itemPrototypes = fileParser.getThings("physical_objects.xml");

		System.out.println("Initializing GridRover...");
		GameEngine engine = new GameEngine(width, length, maxElevation, precision);
		Rover rover = new Rover("Rover", 185.0, 5.52, 100.0, new CommandlineRoverControl()); // Mass 185.0 kg, 1.5 meters tall by 2.3 meters wide by 1.6 meters long
		engine.addRover(rover, width/2, length/2); // Add a single rover in the middle of the map
		engine.scatterItemsRandomly(itemPrototypes, 0.5, 5); // 50% chance of items in a given square, up to 5 items per square

		System.out.println("Running GridRover...");
		engine.eventLoop();
		System.out.println("All events completed.  GridRover now terminating.");
	}
}
