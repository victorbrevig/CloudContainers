package CloudContainers;

public class Container {
	private int containerId;
	// Default is 20.0C (not on journey)
	private double temperature = 20.0;
	// Default is 1 atm (not on journey)
	private double pressure = 1.0;
	// Default is 0.5 - 50% (not on journey)
	private double airHumidity = 0.5;
	// Default is false
	private boolean onJourney = false;
	private boolean owned = false;
	
	private int clientId;
	private int journeyId;
	
	
	
	public boolean equals(Container container) {
		return this.getContainerId() == container.getContainerId();
	}
	
	public int hashCode() {
		return this.getContainerId() * 7;
	}
	
	
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(int journeyId) {
		this.journeyId = journeyId;
	}
	
	public Container(int containerId) {
		super();
		this.containerId = containerId;
	}
	
	public boolean isOnJourney() {
		return onJourney;
	}
	public void setOnJourney(boolean onJourney) {
		this.onJourney = onJourney;
	}
	public boolean isOwned() {
		return owned;
	}
	public void setOwned(boolean owned) {
		this.owned = owned;
	}

	private String content = "";
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getContainerId() {
		return containerId;
	}

	public void setContainerId(int containerId) {
		this.containerId = containerId;
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
	
	public void print() {
		System.out.println(this.getContainerId());
		System.out.println(this.getTemperature());
		System.out.println(this.getPressure());
		System.out.println(this.getAirHumidity());
		System.out.println(this.isOwned());
		System.out.println(this.isOnJourney());
	}


	
	
}
