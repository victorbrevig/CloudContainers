package CloudContainers;

import java.util.ArrayList;
import javafx.util.Pair;

public class Container {
	private int containerId;
	private double temperature;
	private double pressure;
	private double airHumidity;
	private boolean onJourney;
	private boolean owned;
//	<journeyId,clientId>
	private ArrayList<Pair<Integer,Integer>> journeyHistory;
	private int clientId;
	private int journeyId;
	
	public void addJourney(int journeyID) {
		Pair<Integer,Integer> pair = new Pair<Integer,Integer>(journeyID,this.clientId);
		journeyHistory.add(pair);
		this.setOnJourney(true);
		this.setJourneyId(journeyID);
	}
	
	public ArrayList<Pair<Integer,Integer>> getJourneyHistory() {
		return journeyHistory;
	}

	public void setJourneyHistory(ArrayList<Pair<Integer,Integer>> journeyHistory) {
		this.journeyHistory = journeyHistory;
	}

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
		this.airHumidity = 0.5;
		this.temperature = 20.0;
		this.onJourney = false;
		this.pressure = 1.0;
		this.owned = false;
		this.journeyHistory = new ArrayList<Pair<Integer,Integer>>();
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

