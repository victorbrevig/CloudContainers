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
	private int timeToDestination;
	private int elapsedTime;
	private DataStorage statusData = new DataStorage();
	
	
	/** Create a new journey
	 * 
	 * @param portOfOrigin
	 * @param destination
	 * @param timeToDestination
	 */
	public Journey(String portOfOrigin, String destination, int timeToDestination) {
		super();
		this.portOfOrigin = portOfOrigin;
		this.destination = destination;
		this.timeToDestination = timeToDestination;
		this.elapsedTime = 0;
	}
	
	/** This method sets the ID of the journey
	 * 
	 * @param journeyID
	 */
	public void setJourneyID(int journeyID) {
		this.journeyID = journeyID;
	}

	
	/** This method gets the amount of hours that journey has been underway for
	 * 
	 * @return elapsedTime (hours) - type Integer
	 */
	public int getElapsedTime() {
		return elapsedTime;
	}

	/** This method sets the amount of hours that journey has been underway for
	 * 
	 * @param elapsedTime (hours)
	 */
	public void setElapsedTime(int elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	/** This method gets the time to destination for the journey in hours
	 * 
	 * @return timeToDestination (hours) - type Integer
	 */
	public int getTimeToDestination() {
		return timeToDestination;
	}

	/** This method sets the time to destination for the journey in hours
	 * 
	 * @param timeToDestination (hours) - type Integer
	 */
	public void setTimeToDestination(int timeToDestination) {
		this.timeToDestination = timeToDestination;
	}

	/** This method fetches the data for the journey
	 * 
	 * @return statusData - DataStorage of DataPoint
	 */
	public DataStorage getStatusData() {
		return statusData;
	}

	/** This method adds a data point to the history of the journey 
	 * 
	 * @param status - type DataPoint
	 */
	public void addDataPoint(DataPoint status) {
		statusData.add(status);
	}

	
	/** This method checks if the journey has begun
	 * 
	 * @return boolean
	 */
	public boolean isStarted() {
		return isStarted;
	}
	
	/** This method sets the status of whether the journey has begun
	 * 
	 * @param isStarted
	 */
	
	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	
	/** This method fetches the ID of this journey
	 * 
	 * @return journeyID
	 */
	public int getJourneyID() {
		return journeyID;
	}
	
	/** This method fetches the port of origin for this journey
	 * 
	 * @return portOfOrigin
	 */
	public String getPortOfOrigin() {
		return portOfOrigin;
	}
	
	/** This method sets the port of origin for the journey
	 * 
	 * @param portOfOrigin
	 */
	public void setPortOfOrigin(String portOfOrigin) {
		this.portOfOrigin = portOfOrigin;
	}
	
	/** This method get destination of the journey
	 * 
	 * @return destination
	 */
	public String getDestination() {
		return destination;
	}
	
	/** This method sets the destination of the journey
	 * 
	 * @param destination
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	

	/** This overrides equals method for Journey. Two journeys are identical if their journey ID are equal.
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

	/** This overrides hashCode method for Journey.
	 * @return hashCode - type Integer
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.journeyID;
		return result;
	}
	

	
	/**Checks if a journeys duration is in bounds
	 * 
	 * @param journey
	 * @return boolean
	 */
	
	boolean durationOutOfBounds() {
		return getTimeToDestination() <= 0 || getTimeToDestination() > 44000;
	}
	/**Checks if port is equal to destination
	 * 
	 * @return boolean
	 */

	boolean portEqualsDestination() {
		return getDestination().equals(getPortOfOrigin());
	}



	
}
