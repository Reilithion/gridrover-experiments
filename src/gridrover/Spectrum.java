package gridrover;

/**
 * This is a Spectrum in the GridRover world.  
 * It defines a spectrum available to sensors to pick up or emitters to emit.
 *
 * @author Ben Apodaca
 * @version 0.0.1
 */
public class Spectrum {
	private String name;
	private String color;
	private String shape;

	/**
	 * Makes a new SpectrumType called the given name.
	 *
	 * @param name The name of the Spectrum
	 * @param color The color of the Spectrum
	 * @param shape the shape of the Spectrum
	 */
	public Spectrum(String name, String color, String shape) {
		this.name = name;
		this.color = color;
		this.shape = shape;
	}

	public String getName() {
		return name;
	}
	
	public String getColor() {
	    return color;
	}
	
	public String getShape() {
	    return shape;
	}
}
