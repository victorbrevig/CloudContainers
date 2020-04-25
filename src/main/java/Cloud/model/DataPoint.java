package Cloud.model;

/** Class that represents a data point with information about a container with time stamp
 * 
 * @author Gustav Als
 * @author Victor Brevig
 *
 */
public class DataPoint {
	private double time;
	private double airHumidity;
	private double temperature;
	private double pressure;
	

	/** Creates a new data point with status information
	 * 
	 * @param time
	 * @param airHumidity
	 * @param temperature
	 * @param pressure
	 */
	public DataPoint(int time, double airHumidity, double temperature, double pressure) {
		super();
		this.time = time;
		this.airHumidity = airHumidity;
		this.temperature = temperature;
		this.pressure = pressure;
	}
	
	/** This method retrieves the time of the data point
	 * 
	 * @return time - as a double
	 */
	public double getTime() {
		return time;
	}

	/** This method retrieves the air humidity of the data point
	 * 
	 * @return air humidity - as a double
	 */
	public double getAirHumidity() {
		return airHumidity;
	}

	/** This method retrieves the pressure of the data point
	 * 
	 * @return pressure - as a double
	 */
	public double getPressure() {
		return pressure;
	}

	/** This method retrieves the temperature of the data point
	 * 
	 * @return temperature - as a double
	 */
	public double getTemperature() {
		return temperature;
	}
	

}
