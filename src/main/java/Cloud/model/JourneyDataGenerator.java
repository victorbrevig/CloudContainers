package Cloud.model;

import java.util.Random;

/**Class handles generation of status data for containers and journeys
 * @author Gustav Als
 * @author Victor Brevig
 *
 */

public class JourneyDataGenerator {
	
	private static Random rand = new Random();
	
	/**Used to give a company the possibility to generate data for a journey
	 * 
	 * @param timeIncrement - number of hours to progress
	 * @return response
	 */
	public static ResponseObject progressJourney(LogisticCompany company,Journey journey, int timeIncrement) {
		ResponseObject response = new ResponseObject();
		int elapsedTime = journey.getElapsedTime();
		int timeToDestination = journey.getTimeToDestination();
//		Starting journey
		journey.setStarted(true);
		
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
				company.endJourney(journey);
				break;
			} 
			
//			Generating random values
			// +- 3 C
			double randTempIncrement = rand.nextDouble() * (rand.nextBoolean() ? -1 : 1) * 3;
			// +- 0.01 atm
			double randPressureIncrement = rand.nextDouble() * (rand.nextBoolean() ? -1 : 1) * 0.01;
			// +- 0.1
			double randAirHumIncrement = rand.nextDouble() * (rand.nextBoolean() ? -1 : 1) * 0.1;
			
//			Changing status data for containers registered to this journey
			for (Container container : company.getContainerDatabase().filterForJourney(journey)) {
			
					oneContainerOnJourney = true;
					newTemp = container.getTemperature() + randTempIncrement;
					container.setTemperature(newTemp);
					newPressure = container.getPressure() + randPressureIncrement;
					container.setPressure(newPressure);
					newAirHum = container.getAirHumidity() + randAirHumIncrement;
					container.setAirHumidity(newAirHum);
					
					container.setCurrentJourney(journey);
					container.getJourneyHistory().get(container.getJourneyHistory().size() - 1).setJourney(journey);
			}
			elapsedTime++;
			timeToDestination--;
			timeTraveled++;
			
			
//			Set status data in journey
			if (oneContainerOnJourney) {
				DataPoint status = new DataPoint(elapsedTime,newAirHum,newTemp,newPressure);
				journey.addDataPoint(status);
			}
			
//			Increment time in journey
			journey.setElapsedTime(elapsedTime);
			journey.setTimeToDestination(timeToDestination);
			
//			When the timeIncrement doesn't exceed the time to destination
			if (timeToDestination == 0) {
				company.endJourney(journey);
			} 
		}
		
		if(oneContainerOnJourney && journey.isStarted()){
			response.setErrorMessage("Your container on journey " + journey.getJourneyID() +  " has traveled " + timeTraveled + " hours.");
			
		}
//		Checking if journey has ended
		else if(!journey.isStarted()){
			response.setErrorMessage("Your container on journey " + journey.getJourneyID() + " has traveled " + timeTraveled + " hours and the journey has ended.");
		}
		else {
			response.setErrorMessage("No containers are on this journey");
		}
		
		return response;
	}

}
