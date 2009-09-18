package gridrover;

import java.util.List;

/**
 * This is a Spectrum in the GridRover world.  
 * It defines a spectrum available to sensors to pick up or emitters to emit.
 *
 * @author Ben Apodaca
 * @version 0.0.1
 */
public class Spectrum {
	private String name;
	private List<String> colors;
	private List<String> shapes;

	/**
	 * Makes a new SpectrumType called the given name.
	 *
	 * @param name The name of the Spectrum
	 * @param color The color of the Spectrum
	 * @param shape the shape of the Spectrum
	 */
	public Spectrum(SpectrumBean sb) {
		this.name = sb.getName();
		this.colors = sb.getColors();
		this.shapes = sb.getShapes();
	}

	public String getName() {
		return name;
	}
	
	public List<String> getColors() {
	    return colors;
	}
	
	public List<String> getShapes() {
	    return shapes;
	}
}
