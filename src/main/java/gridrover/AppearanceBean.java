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

public class AppearanceBean
{
	private String stimulus;
	private List<ResponseBean> responses;

	public AppearanceBean()
	{
		responses = new ArrayList<ResponseBean>();
	}

	public void setStimulus(String stimulus)
	{
		this.stimulus = stimulus;
	}

	public String getStimulus()
	{
		return stimulus;
	}

	public void addResponseBean(ResponseBean p)
	{
		responses.add(p);
	}

	public List<ResponseBean> getResponseBeans()
	{
		return responses;
	}
}
