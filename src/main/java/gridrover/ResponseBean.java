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

public class ResponseBean
{
	private String spectrum, color, shape;
	private SpectrumAction action;

	public ResponseBean()
	{
		action = SpectrumAction.TRANSPARENT;
	}

	public void setSpectrum(String spectrum)
	{
		this.spectrum = spectrum;
	}

	public String getSpectrum()
	{
		return spectrum;
	}

	public void setColor(String color)
	{
		this.color = color; // sorry, Brits
	}

	public String getColor()
	{
		return color;
	}

	public void setShape(String shape)
	{
		this.shape = shape;
	}

	public String getShape()
	{
		return shape;
	}

	public void setAction(String action)
	{
		for (SpectrumAction sa : SpectrumAction.values())
		{
			if (action.equalsIgnoreCase(sa.toString()))
				this.action = sa;
		}
	}

	public SpectrumAction getSpectrumAction()
	{
		return action;
	}

	public String getAction()
	{
		return action.toString();
	}

	@Override public boolean equals(Object aThat)
	{
		if (this == aThat) return true;
		if (!(aThat instanceof ResponseBean)) return false;
		ResponseBean that = (ResponseBean) aThat;
		return this.spectrum.equals(that.spectrum) &&
		       this.color.equals(that.color) &&
		       this.shape.equals(that.shape) &&
		       this.action.equals(that.action);
	}

	@Override public int hashCode()
	{
		return (spectrum + color + shape + action.toString()).hashCode();
	}
}
