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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.prefs.Preferences;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
* This class is designed to facilitate the locating of data resources for
* GridRover.  Any time GridRover needs to find and load a data file, this class
* should be involved.
*
* @author Lucas Adam M. Paul
* @version 0.0.1
*/
public class ResourceLocater
{
	private static Log log = LogFactory.getLog(ResourceLocater.class);
	private static final String DATA_FILE_PATH = "data_file_path";

	private File preferredLocation;
	private static ClassLoader loader = GridRover.class.getClassLoader();
	private static Preferences prefs = Preferences.userNodeForPackage(GridRover.class);

	/**
	* Creates a new ResourceLocater.  When creating a ResourceLocater you can
	* specify a preferred location in which to look for data files first.  If
	* there are data files in that location, they will be returned, even
	* if there are data files available at other locations -- even from
	* the classloader.  If you do not wish to specify a preferred location,
	* simply pass the null value as this constructor's argument.
	*
	* @param preferredLocation Location this ResourceLoader should look first
	*                          for data files.
	*/
	protected ResourceLocater(String preferredLocation)
	{
		if (preferredLocation != null)
			this.preferredLocation = new File(preferredLocation);
	}

	/**
	* Find resource with the specified name and return it as an InputStream, if
	* found.  This method will try its best to find the resource that has the
	* specified name.  First, it tries to load the resource from the preferred
	* location provided to this ResourceLocater's constructor.  Next, it moves
	* on to the classloader, the location saved in GridRover's Preferences data,
	* the current directory, and finally the ./data/ directory.  It does so in
	* that order.  getResource returns the first file it comes across, whether
	* or not the file is actually valid.  If the first file getResource finds
	* with the proper name is not a valid data file, or if it contains errors
	* that prevent it from loading, it will still be returned by this method --
	* even if a valid and error-free data file exists at some other location.
	* If getResource is unable to find the desired file in any of the locations
	* it scans, it will return null.
	*
	* @param name Name of the desired file/resource to retrieve.
	* @return an InputStream of the resource, or null if it was not found.
	*/
	protected InputStream getResource(String name)
	{
		InputStream resource = null;
		// try to get the resource from the preferred location.
		if (preferredLocation != null)
		{
			try
			{
				resource = new FileInputStream(new File(preferredLocation, name));
			}
			catch (FileNotFoundException e) {}
		}
		if (resource != null)
		{
			log.debug(name + " found in preferred location.");
			return resource;
		}
		// try to get the resource from the classloader.
		resource = loader.getResourceAsStream(name);
		if (resource != null)
		{
			log.debug(name + " found via classloader.");
			return resource;
		}
		// try to get the resource from the Preferences construct.
		resource = getResourceFromPreferences(name);
		if (resource != null)
		{
			log.debug(name + " found via Preferences.");
			return resource;
		}
		// try to get the resource from the current directory.
		try
		{
			resource = new FileInputStream(new File(name));
		}
		catch (FileNotFoundException e) {}
		if (resource != null)
		{
			log.debug(name + " found in current directory.");
			return resource;
		}
		// try to get the resource from the ./data/ directory.
		try
		{
			resource = new FileInputStream(new File(new File("data"), name));
		}
		catch (FileNotFoundException e) {}
		if (resource != null)
			log.debug(name + " found in ./data directory.");
		else
			log.debug(name + " not found. ?_?");
		return resource;
	}

	// TODO:  Add a way to reset preferences
	// TODO:  Store the dataFilePath to our preferences

	/**
	* Attempt to get a resource from the location specified in GridRover's
	* Preferences.  This method will try to locate the named resource in
	* the location specified in the data_file_path field of GridRover's
	* Preferences.  If the file is found there it will return an InputStream of
	* that file.  Otherwise it will return null.
	*
	* @param name Name of the desired file/resource to retrieve.
	* @return an InputStream of the resource, or null if it was not found.
	*/
	protected InputStream getResourceFromPreferences(String name)
	{
		InputStream resource = null;
		String dataFilePath = prefs.get(DATA_FILE_PATH, null);
		if (dataFilePath != null)
		{
			log.debug("Preferences data file path = " + dataFilePath);
			File path = new File(dataFilePath);
			try
			{
				resource = new FileInputStream(new File(path, name));
			}
			catch (FileNotFoundException e) {}
		}
		return resource;
	}
}
