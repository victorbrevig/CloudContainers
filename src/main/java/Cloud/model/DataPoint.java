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

	public DataPoint(int time, double airHumidity, double temperature, double pressure) {
		super();
		this.time = time;
		this.airHumidity = airHumidity;
		this.temperature = temperature;
		this.pressure = pressure;
	}

	public double getTime() {
		return time;
	}

	public double getAirHumidity() {
		return airHumidity;
	}

	public double getPressure() {
		return pressure;
	}

	public double getTemperature() {
		return temperature;
	}
	

}
