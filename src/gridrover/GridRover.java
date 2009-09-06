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
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;

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
	// private static final String DATA_FILE_PATH = "data_file_path";

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
		/*
		String dataFilePath = prefs.get(DATA_FILE_PATH, null);

		Debug.debug("Data File Path = " + dataFilePath);

		System.out.println("Loading data files...");
		//TODO: Try the classloader first.  Save the user some mess.
		while (dataFilePath == null)
		{
			System.out.println("No data file path found.  Please type the path to the data files.");
			System.out.print(">");
			//TODO: Consolidate console user input code
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try
			{
				dataFilePath = reader.readLine();
			}
			catch (IOException ioe)
			{
				System.err.println("IO error trying to read from System.in");
				System.exit(1);
			}
		}
		// Not storing the dataFilePath to its prefs entry until we have a way to reset it.
		// TODO:  Add a way to reset preferences
		// TODO:  Store the dataFilePath to our preferences
		File dataFilePathAbstract = new File(dataFilePath);
		*/
		ResourceLocater locater = new ResourceLocater(null);
		ArrayList<Thing> itemPrototypes = loadItems(locater.getResource("physical_objects.xml"));

		System.out.println("Initializing GridRover...");
		//GameEngine engine = new GameEngine(new CommandlineRoverControl(), width, length, maxElevation, precision);
		GameEngine engine = new GameEngine(width, length, maxElevation, precision);
		Rover rover = new Rover("Rover", 185.0, 5.52, 100.0, new CommandlineRoverControl()); // Mass 185.0 kg, 1.5 meters tall by 2.3 meters wide by 1.6 meters long
		engine.addRover(rover, width/2, length/2); // Add a single rover in the middle of the map
		engine.scatterItemsRandomly(itemPrototypes, 0.5, 5); // 50% chance of items in a given square, up to 5 items per square

		System.out.println("Running GridRover...");
		engine.eventLoop();
		System.out.println("All events completed.  GridRover now terminating.");
	}

	/**
	* This method produces an ArrayList of PhysicalObjects representing each
	* type of Physical Object read from the file at the specified URL.
	*
	* @param objectsFile The location at which to find the physical_objects.xml file
	* @return An ArrayList of PhysicalObjects read from the provided file, or an empty ArrayList
	*/
	private static ArrayList<Thing> loadItems(InputStream objectsFile)
	{
		ArrayList<Thing> retVal = new ArrayList<Thing>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try
		{
			builder = factory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e)
		{
			System.out.println("ParserConfigurationException while trying to get a builder.");
			System.out.println(e.toString());
			return retVal;
		}
		try
		{
			Document doc = builder.parse(objectsFile);
			NodeList thingList = doc.getDocumentElement().getChildNodes();
			int numberOfItems = thingList.getLength();
			Debug.debug("Number of Items to read from file: " + numberOfItems);
			for (int i = 0; i < numberOfItems; i++)
			{
				Node thingNode = thingList.item(i);
				if (thingNode.getNodeType() == Node.ELEMENT_NODE && ((Element) thingNode).getTagName().equalsIgnoreCase("THING"))
				{
					String thingName = null;
					double thingMass = -1.0;
					double thingBulk = -1.0;
					NodeList thingPropertyList = thingNode.getChildNodes();
					for (int j = 0; j < thingPropertyList.getLength(); j++)
					{
						Node propertyNode = thingPropertyList.item(j);
						if (propertyNode.getNodeName().equalsIgnoreCase("NAME"))
							thingName = propertyNode.getTextContent();
						if (propertyNode.getNodeName().equalsIgnoreCase("MASS"))
							thingMass = Double.parseDouble(propertyNode.getTextContent());
						if (propertyNode.getNodeName().equalsIgnoreCase("BULK"))
							thingBulk = Double.parseDouble(propertyNode.getTextContent());
					}
					Thing thing = new Thing(thingName, thingMass, thingBulk);
					retVal.add(thing);
				}
			}
		}
		catch (SAXException e)
		{
			System.out.println("SAXException while trying to parse xml.");
			System.out.println(e.toString());
		}
		catch (IOException e)
		{
			System.out.println("IOException while trying to parse xml.");
			System.out.println("Tried to open: " + objectsFile.toString());
			System.out.println(e.toString());
		}
		return retVal;
	}
}
