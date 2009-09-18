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

import java.util.List;
import java.util.ArrayList;

/**
* This is a Bean for making a Spectrum.
*
* @author Lucas Adam M. Paul
* @version 0.0.1
*/
public class SpectrumBean
{
	private String name;
	private ArrayList<String> colors;
	private ArrayList<String> shapes;

	public SpectrumBean()
	{
		colors = new ArrayList<String>();
		shapes = new ArrayList<String>();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void addColor(String color)
	{
		colors.add(color);
	}

	public void addShape(String shape)
	{
		shapes.add(shape);
	}

	public List<String> getColors()
	{
		return colors;
	}

	public List<String> getShapes()
	{
		return shapes;
	}
}
