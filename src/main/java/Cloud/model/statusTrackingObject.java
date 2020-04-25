package Cloud.model;

public class statusTrackingObject {
	private double time;
	private double airHumidity;
	private double temperature;
	private double pressure;
	

	
	public statusTrackingObject(int time, double airHumidity, double temperature, double pressure) {
		super();
		this.time = time;
		this.airHumidity = airHumidity;
		this.temperature = temperature;
		this.pressure = pressure;
	}
	
	public double getTime() {
		return time;
	}

	

}
