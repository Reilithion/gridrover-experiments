package gridrover;

/**
 * This is a Spectrum Type in the GridRover world.  It defines the various spectrum available to sensors to pick up or emitters to emit.
 *
 * @author Ben Apodaca
 * @version 0.0.1
 */
public class SpectrumType {
	private String name;

	/**
	 * Makes a new SpectrumType called the given name.
	 *
	 * @param name The name of the Spectrum
	 */
	public SpectrumType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
