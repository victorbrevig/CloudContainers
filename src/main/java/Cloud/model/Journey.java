package Cloud.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;



/** Class to respresent a journey
 * 
 * @author Gustav Als
 * @author Victor Brevig
 *
 */
public class Journey {
	private int journeyID;
	private String portOfOrigin;
	private String destination;
	private boolean isStarted;
//	Time unit is Hours
	private int timeToDestination;
	private int elapsedTime;
	private DataStorage statusData = new DataStorage();

	public Journey(String portOfOrigin, String destination, int timeToDestination) {
		super();
		this.portOfOrigin = portOfOrigin;
		this.destination = destination;
		this.timeToDestination = timeToDestination;
		this.elapsedTime = 0;
	}

	public void setJourneyID(int journeyID) {
		this.journeyID = journeyID;
	}
	
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

	public DataStorage getStatusData() {
		return statusData;
	}

	public void addDataPoint(DataPoint status) {
		statusData.add(status);
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public int getJourneyID() {
		return journeyID;
	}

	public String getPortOfOrigin() {
		return portOfOrigin;
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
	

	/** Used to compare journeys by their ID
	 * 
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Journey) {
			return this.getJourneyID() == ((Journey) o).getJourneyID();
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.journeyID;
		return result;
	}

	/**A bound for journey duration is needed
	 * 
	 * @param journey
	 * @return boolean
	 */
	
	boolean durationOutOfBounds() {
		return getTimeToDestination() <= 0 || getTimeToDestination() > 44000;
	}

	boolean portEqualsDestination() {
		return getDestination().equals(getPortOfOrigin());
	}

	
}
