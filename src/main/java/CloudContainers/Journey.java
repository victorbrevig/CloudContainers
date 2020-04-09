package CloudContainers;

import java.util.ArrayList;

public class Journey {
	private int journeyID;
	private String portOfOrigin;
	private String destination;
	private String company;
	private boolean isStarted;
	private int timeToDestination;
	private int elapsedTime;
	private ArrayList<statusTrackingObject> statusData = new ArrayList<statusTrackingObject>();
	
	public int getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public int getTimeToDestination() {
		return timeToDestination;
	}

	public void setTimeToDestination(int timeToDestination) {
		this.timeToDestination = timeToDestination;
	}

	public ArrayList<statusTrackingObject> getStatusData() {
		return statusData;
	}

	public void setStatusData(ArrayList<statusTrackingObject> statusData) {
		this.statusData = statusData;
	}

	public void addDataPoint(statusTrackingObject s) {
		statusData.add(s);
	}
	
	public void clearData() {
		statusData.clear();
	}
	
	
	public boolean isStarted() {
		return isStarted;
	}
	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	public String getCompany() {
		return company;
	}
	public int getJourneyID() {
		return journeyID;
	}
	public String getPortOfOrigin() {
		return portOfOrigin;
	}
	
	public Journey(int journeyID, String portOfOrigin, String destination, String company,int timeToDestination) {
		super();
		this.journeyID = journeyID;
		this.portOfOrigin = portOfOrigin;
		this.destination = destination;
		this.company = company;
		this.timeToDestination = timeToDestination;
		this.elapsedTime = 0;
	}


	public boolean equals(Journey journey) {
		if (journey instanceof Journey) {
			return this.getJourneyID() == journey.getJourneyID();
		}
		return false;
	}

	public int hashCode() {
		int result = 17;
		result = 31 * result + this.journeyID;
		return result;
	}

	public void setPortOfOrigin(String portOfOrigin) {
		this.portOfOrigin = portOfOrigin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	public void print() {
		System.out.println("JourneyID: " + this.getJourneyID());
		System.out.println("Company: " + this.getCompany());
		System.out.println("PortOfOrigin: " + this.getPortOfOrigin());
		System.out.println("Destination: " + this.getDestination());
		System.out.println("Elapsed time: ");
		System.out.println("Time to destination: " + this.getTimeToDestination());
	}

	
	
	
}
