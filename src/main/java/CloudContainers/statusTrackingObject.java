package CloudContainers;

public class statusTrackingObject {
	private double time;
	private double airHumidity;
	private double temperature;
	private double pressure;
	
	public statusTrackingObject() {
		
	}
	
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
	public void setTime(double time) {
		this.time = time;
	}
	public double getAirHumidity() {
		return airHumidity;
	}
	public void setAirHumidity(double airHumidity) {
		this.airHumidity = airHumidity;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	

}
