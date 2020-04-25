package Cloud.model;

import java.util.ArrayList;



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
	private LogisticCompany company;
	private boolean isStarted;
	private int timeToDestination;
	private int elapsedTime;
	private ArrayList<DataPoint> statusData = new ArrayList<DataPoint>();
	
	
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

	/** This method sets the company that the journey belongs to
	 * 
	 * @param company
	 */
	public void setCompany(LogisticCompany company) {
		this.company = company;
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
	 * @return statusData - ArrayList of DataPoint
	 */
	public ArrayList<DataPoint> getStatusData() {
		return statusData;
	}

	/** This method adds a data point to the history of the journey 
	 * 
	 * @param status - type DataPoint
	 */
	public void addDataPoint(DataPoint status) {
		statusData.add(status);
	}
	
	/** This method clears the status data for the journey
	 * 
	 */
	public void clearData() {
		statusData.clear();
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
	
	/** This method 
	 * 
	 * @param isStarted
	 */
	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	
	/** This method gets the company that has this journey
	 * 
	 * @return company - type LogisticCompany
	 */
	public LogisticCompany getCompany() {
		return company;
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
	
	/** This method ends the journey.
	 * 
	 * @return response
	 */
	
//	In logistic company
	
	public ResponseObject endJourney() {
		ResponseObject response = new ResponseObject();
		
		int countFree = 0;
		
		this.setStarted(false);

		countFree = freeUpContainers(countFree);
		
		response.setErrorMessage("Journey successfully ended. " + countFree + " containers were set free.");
		
		return response;
	}
	
	/** This method frees up all containers associated with this journey.
	 * 
	 * @param countFree 
	 * @return countFree - how many containers was freed
	 */
	private int freeUpContainers(int countFree) {
//		The filter returns null
		for (Container container : company.getContainersForJourney(this)) {
				container.setCurrentJourney(null);
				container.setOnJourney(false);
				container.setContent("");
				countFree++;
		}
		return countFree;
	}



//	public void print() {
//		System.out.println("JourneyID: " + this.getJourneyID());
//		System.out.println("Company: " + this.getCompany());
//		System.out.println("PortOfOrigin: " + this.getPortOfOrigin());
//		System.out.println("Destination: " + this.getDestination());
//		System.out.println("Elapsed time: ");
//		System.out.println("Time to destination: " + this.getTimeToDestination());
//	}
	
	
}
