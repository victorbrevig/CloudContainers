package CloudContainers;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class Journey {
	private int journeyID;
	private String portOfOrigin;
	private String destination;
	private LogisticCompany company;
	private boolean isStarted;
	private int timeToDestination;
	private int elapsedTime;

	private ArrayList<statusTrackingObject> statusData = new ArrayList<statusTrackingObject>();
	private Random rand = new Random();
	
	
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


	public void setCompany(LogisticCompany company) {
		this.company = company;
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
	public LogisticCompany getCompany() {
		return company;
	}
	public int getJourneyID() {
		return journeyID;
	}
	public String getPortOfOrigin() {
		return portOfOrigin;
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
	
	public ResponseObject endJourney() {
		ResponseObject response = new ResponseObject();
		
		int countFree = 0;
		
		this.setStarted(false);

		countFree = updateContainers(countFree);
		
		response.setErrorMessage("Journey successfully ended. " + countFree + " containers were set free.");
		
		return response;
	}
	
	private int updateContainers( int countFree) {
//		The filter returns null
		for (Container container : company.getContainersForJourney(this)) {
				container.setCurrentJourney(null);
				container.setOnJourney(false);
				container.setContent("");
				countFree++;
		}
		return countFree;
	}

	public ResponseObject progressJourney( int timeIncrement) {
		
		ResponseObject response = new ResponseObject();
		int elapsedTime = getElapsedTime();
		int timeToDestination = getTimeToDestination();
//		Starting journey
		this.setStarted(true);
		
//		Initializing status variables
		double newTemp = 0;
		double newPressure = 0;
		double newAirHum = 0;
		int timeTraveled = 0;
		boolean oneContainerOnJourney = false;
		
		if (timeIncrement <= 0) {
			response.setErrorMessage("Travel time has to be a more than 0");
			return response;
		}
		
		
//		Add status data for each hour in time increment
		for (int i = 1; i<= timeIncrement;i++) {
//			Checking if the journey has ended
			if (timeToDestination == 0) {
				endJourney();
				break;
			} 
			
//			Generating random values
			// +- 3 C
			double randTempIncrement = rand.nextDouble() * (rand.nextBoolean() ? -1 : 1) * 3;
			// +- 0.1 atm
			double randPressureIncrement = rand.nextDouble() * (rand.nextBoolean() ? -1 : 1) * 0.1;
			// +- 0.05
			double randAirHumIncrement = rand.nextDouble() * (rand.nextBoolean() ? -1 : 1) * 0.05;
			
//			Changing status data for containers registered to this journey
			for (Container container : company.getContainersForJourney(this)) {
			
					oneContainerOnJourney = oneContainerOnJourney || true;
					newTemp = container.getTemperature() + randTempIncrement;
					container.setTemperature(newTemp);
					newPressure = container.getPressure() + randPressureIncrement;
					container.setPressure(newPressure);
					newAirHum = container.getAirHumidity() + randAirHumIncrement;
					container.setAirHumidity(newAirHum);
			}
			elapsedTime++;
			timeToDestination--;
			timeTraveled++;
			
//			Set status data in journey
			if (oneContainerOnJourney) {
				statusTrackingObject status = new statusTrackingObject(elapsedTime,newAirHum,newTemp,newPressure);
				addDataPoint(status);
			}
			
//			Increment time in journey
			setElapsedTime(elapsedTime);
			setTimeToDestination(timeToDestination);
			
		}
		
		if(oneContainerOnJourney && isStarted()){
			response.setErrorMessage("Your container on journey " + journeyID + " has traveled " + timeTraveled + " hours.");
			
		}
//		Checking if journey has ended
		else if(!isStarted()){
			response.setErrorMessage("Your container on journey " + journeyID + " has traveled " + timeTraveled + " hours and the journey has ended.");
		}
		else {
			response.setErrorMessage("No containers are on this journey");
		}
			
		return response;
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
