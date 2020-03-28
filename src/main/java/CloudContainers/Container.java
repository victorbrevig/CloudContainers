package CloudContainers;

public class Container {
	private int id;
	private double temperature;
	private double pressure;
	private double airHumidity;
	private boolean onJourney;
	
	public int getId() {
		return id;
	}
	public Container(int id, double temperature, double pressure, double airHumidity, boolean onJourney) {
		super();
		this.id = id;
		this.temperature = temperature;
		this.pressure = pressure;
		this.airHumidity = airHumidity;
		this.onJourney = onJourney;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getAirHumidity() {
		return airHumidity;
	}
	public void setAirHumidity(double airHumidity) {
		this.airHumidity = airHumidity;
	}
	
	
	
}
