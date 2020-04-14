package CloudContainers;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.validator.GenericValidator;

import javafx.util.Pair;

public class LogisticCompany {
	private String name;
	private int companyID;
	private ClientDatabase clients;
	private ContainerDatabase containers;
	private JourneyDatabase journeys;
	private int clientIDgen = 1;
	private int amountOfContainers;
	private Random rand = new Random();
	private int journeyIDgen = 1;
	
	public LogisticCompany(String name, int companyID, int amountOfContainers) {
		super();
		this.name = name;
		this.companyID = companyID;
		this.clients = new ClientDatabase();
		this.journeys = new JourneyDatabase();
		this.amountOfContainers = amountOfContainers;
		
		this.containers = new ContainerDatabase();
		
		// Generate existing containers
		for (int i=1; i<=amountOfContainers;i++) {
			containers.add(new Container(i));
		}
		
	}
	
	public JourneyDatabase getJourneyDatabase() {
		return journeys;
	}
	
	public ContainerDatabase getContainerDatabase() {
		return containers;
	}
	
	public void createJourney(String portOfOrigin, String destination,int timeToDestination) {
		Journey journey = new Journey(journeyIDgen, portOfOrigin, destination, this.name,timeToDestination);
		journeyIDgen++;
		journeys.add(journey);
	}
	
	public void addContainer() {
		amountOfContainers++;
		containers.add(new Container(amountOfContainers));
	}
	
	public int getClientIDgen() {
		return clientIDgen;
	}
	
	public ClientDatabase getClients() {
		return clients;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCompanyID() {
		return companyID;
	}

	public Client findClient(int id) {
		return clients.getClient(id);
	}
	
	public Client findClient(String email) {
		return clients.getClient(email);
	}
	
	public boolean removeClient(int clientID) {
		return clients.remove(findClient(clientID));
	}
	public boolean removeClient(String email) {
		return clients.remove(findClient(email));
	}

	
	
	public boolean clientExists(int clientID) {
		return clients.contains(clients.getClient(clientID));
	}
	public boolean clientExists(String email) {
		return clients.contains(clients.getClient(email));
	}
	public boolean numberExists(int number) {
		return clients.contains(clients.getClient(number));
	}
	public boolean containerExists(int containerID) {
		return containers.contains(containers.getContainer(containerID));
	}
	public boolean journeyExists(int journeyID) {
		return journeys.contains(journeys.getJourney(journeyID));
	}
	
	public boolean ownedContainer(int clientID, int containerID) {
		return getContainerDatabase().getContainer(containerID).getClientId() == clientID;
	}
	
	public Container getContainer(int containerID) {
		return getContainerDatabase().getContainer(containerID);
	}

	
	public boolean validBirthdate(String birthdate) {
		return GenericValidator.isDate(birthdate, "dd-MM-yyyy", true);
	}
	
	public boolean validEmail(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	
	public boolean validParameters(String name, String email, String birthdate, String gender, int number) {
		return !(   (name=="") 
				|| (birthdate=="") 
				|| (email == "")			
				|| (gender=="") 
				|| (number==0)   );
	}
	
	
	public boolean validNumber(int number) {
		int length = String.valueOf(number).length();
		return (length == 8);
	}
	
	
	public ResponseObject validInput(String name, String email, String birthdate, String gender, int number) {
		ResponseObject response = new ResponseObject();
		
		if (!validParameters(name,email,birthdate,gender,number)) {
			response = new ResponseObject("There is a missing parameter");
			return response;
		}
		else if (!validEmail(email)) {
			response = new ResponseObject(email + " is not a valid email");
			return response;
		}
		else if (!validBirthdate(birthdate)) {
			response = new ResponseObject(birthdate + " is not a valid birthdate");
			return response;
		}
		else if (!validNumber(number)) {
			response = new ResponseObject(number + " is not a valid phone number");
			return response;
		}
		
		if (!clientExists(email)) {
			response = new ResponseObject("Non-existing client");
		}else {
			response = new ResponseObject("Existing client");
		}
		return response;
		
	}


	
	public ResponseObject newClient(String name, String email, String birthdate, String gender, int number, String password) {
		ResponseObject response;
		response = validInput(name,email,birthdate,gender,number);
		if (response.getErrorMessage().equals("Non-existing client")) {
			int clientID = clientIDgen++;
			Client client = new Client(name,clientID,email,birthdate,gender,number,this.name, password);
			clients.add(client);
			response.setErrorMessage("Client was successfully added");
		}
		return response;
	}
	
	public ResponseObject updateClient(int clientID,String email) {
		ResponseObject response = new ResponseObject();
		Client c = findClient(clientID);
		// Valid new email
		response = validInput(c.getName(),email,c.getBirthdate(),c.getGender(),c.getNumber());
		// Check if new email belongs to a client already
		if (response.getErrorMessage().equals("Non-existing client")) {
			findClient(clientID).setEmail(email);
			response.setErrorMessage("Email has been updated");
			}
		return response;
	}
	
	public ResponseObject updateClient(int clientID,int number) {
		ResponseObject response = new ResponseObject();
		Client c = findClient(clientID);
		// Valid new email
		response = validInput(c.getName(),c.getEmail(),c.getBirthdate(),c.getGender(),number);
		// Check if new email belongs to a client already
		
		if (!validNumber(number)) {
			return response;
		}
		if (!numberExists(number)) {
			findClient(clientID).setNumber(number);
			response.setErrorMessage("Phone number has been updated");
		}
		else {
			response.setErrorMessage("Existing client");
		}
		return response;
	}

	public Container findFreeContainer() {
//		Container 0, is returned if no vacant containers are available
		Container container = new Container(0);
		
		for (Container c: containers) {
			if (!c.isOwned()) {
				return c;
			}
		}
		return container;
	}

	
	public ResponseObject allocateContainer(int clientId,Container container) {
		ResponseObject response = new ResponseObject("Container succesfully allocated");
		boolean existClient = clientExists(clientId);
		boolean existContainer = containerExists(container.getContainerId());
		boolean owned = container.isOwned();
		
		if(!existClient) {
			response.setErrorMessage("Client does not exist");
		}
		else if (!existContainer) {
			response.setErrorMessage("Container does not exist");
		}
		else if (owned) {
			response.setErrorMessage("This container is already owned by a client");
		}
		else {
			container.setOwned(true);
			container.setClientId(clientId);
		}
		return response;
	}
	
	public ResponseObject freeContainer(int containerID) {
		Container container = getContainerDatabase().getContainer(containerID);
		ResponseObject response = new ResponseObject();
//		container is not on journey
//		container is owned
		boolean onJourney = container.isOnJourney();
		boolean owned = container.isOwned();
		if (onJourney) {
			response.setErrorMessage("This container is on a journey");
		}else if (!owned) {
			response.setErrorMessage("This container does not belong to a client");
		}else {
			getContainer(containerID).setClientId(0);
			getContainer(containerID).setOwned(false);
			response.setErrorMessage("Container was successfully freed");
		}
		return response;
	}
	
	public ResponseObject containerToJourney(int clientID, Container container, int journeyID, String content) {
		ResponseObject response = new ResponseObject("Container successfully added to journey");
		// Conditions to check
		// Client exists
		boolean existingClient = clientExists(clientID);
		// Container belongs to client
		boolean belongsToClient = container.getClientId() == clientID;
		// Journey exists
		boolean existingJourney = journeyExists(journeyID);
		
		if (!existingClient) {
			response.setErrorMessage("Client does not exist");
			return response;
		}
		else if (!belongsToClient) {
			response.setErrorMessage("Container does not belong to client");
			return response;
		}
		else if (!existingJourney) {
			response.setErrorMessage("Journey does not exist");
			return response;
		}
		
		container.setContent(content);
		container.addJourney(journeyID);
		
		return response;
	}
	
	public ResponseObject updateJourneyPortOfOrigin(int journeyID, String newPortOfOrigin) {
		ResponseObject response = new ResponseObject();
		if (journeyExists(journeyID)) {
			journeys.getJourney(journeyID).setPortOfOrigin(newPortOfOrigin);
			response.setErrorMessage(newPortOfOrigin + " successfully updated as port of origin");
		}
		else {
			response.setErrorMessage("Journey does not exist");
		}
		return response;
	}
	
	public ResponseObject updateJourneyDestination(int journeyID, String newDestination) {
		ResponseObject response = new ResponseObject();
		if (journeyExists(journeyID)) {
			journeys.getJourney(journeyID).setDestination(newDestination);
			response.setErrorMessage(newDestination + " successfully updated as destination");
		}
		else {
			response.setErrorMessage("Journey does not exist");
		}
		return response;
	}
	
	public ResponseObject endJourney(int journeyID) {
		ResponseObject response = new ResponseObject();
		
		if (!journeyExists(journeyID)) {
			response.setErrorMessage("No such journey exist");
			return response;
		}
		
		int countFree = 0;
		
		journeys.getJourney(journeyID).setStarted(false);

		countFree = updateContainers(journeyID, countFree);
		
		response.setErrorMessage("Journey successfully ended. " + countFree + " containers were set free.");
		
		return response;
	}

	private int updateContainers(int journeyID, int countFree) {
		for (Container container : containers) {
			if (container.getJourneyId() == journeyID) {
				container.setJourneyId(0);
				container.setOnJourney(false);
				container.setContent("");
				countFree++;
			}
		}
		return countFree;
	}
	
	
	public ResponseObject progressJourney(int journeyID, int timeIncrement) {
		
		ResponseObject response = new ResponseObject();
		int elapsedTime = journeys.getJourney(journeyID).getElapsedTime();
		int timeToDestination = journeys.getJourney(journeyID).getTimeToDestination();
//		Starting journey
		journeys.getJourney(journeyID).setStarted(true);
		
//		Initializing status variables
		
		
		
		if (!journeyExists(journeyID)) {
			response.setErrorMessage("Journey does not exist");
			return response;
		}else if (timeIncrement <= 0) {
			response.setErrorMessage("Travel time has to be a more than 0");
			return response;
		}
		
		double newTemp = 0;
		double newPressure = 0;
		double newAirHum = 0;
		int timeTraveled = 0;
		boolean containerExists = false;
		
//		Add status data for each hour in time increment
		for (int i = 1; i<= timeIncrement;i++) {
//			Checking if the journey has ended
			if (timeToDestination == 0) {
				endJourney(journeyID);
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
			for (Container container : containers) {
				if (container.getJourneyId() == journeyID) {
					containerExists = containerExists || true;
					newTemp = container.getTemperature() + randTempIncrement;
					container.setTemperature(newTemp);
					newPressure = container.getPressure() + randPressureIncrement;
					container.setPressure(newPressure);
					newAirHum = container.getAirHumidity() + randAirHumIncrement;
					container.setAirHumidity(newAirHum);
				}
			}
			elapsedTime++;
			timeToDestination--;
			timeTraveled++;
			
//			Set status data in journey
			if (containerExists) {
				statusTrackingObject status = new statusTrackingObject(elapsedTime,newAirHum,newTemp,newPressure);
				journeys.getJourney(journeyID).addDataPoint(status);
			}
			
//			Increment time in journey
			journeys.getJourney(journeyID).setElapsedTime(elapsedTime);
			journeys.getJourney(journeyID).setTimeToDestination(timeToDestination);
			
		}
		
		if(containerExists && journeys.getJourney(journeyID).isStarted() == true){
			response.setErrorMessage("Your container on journey " + journeyID + " has traveled " + timeTraveled + " hours.");
			
		}
//		Checking if journey has ended
		else if(journeys.getJourney(journeyID).isStarted() == false){

			response.setErrorMessage("Your container on journey " + journeyID + " has traveled " + timeTraveled + " hours and the journey has ended.");
		}
		else {
			response.setErrorMessage("No containers are on this journey");
		}
			
		return response;
	}

	
	
	
	public ResponseObject accessStatus(Integer clientID, Integer containerID) {
		ResponseObject response = new ResponseObject();
		Container container = containers.getContainer(containerID);
		Journey journey = journeys.getJourney(container.getJourneyId());
		
		boolean containerExists = containerExists(containerID);
		boolean ownedContainer = ownedContainer(clientID,containerID);
		boolean journeyIsStarted = journey.isStarted();
		
		if (!containerExists || !ownedContainer) {
			response.setErrorMessage("You don't own this container");
			return response;
		} 
		else if (!journeyIsStarted) {
			response.setErrorMessage("Ship's still at harbour");
			return response;
		} 
		else {
//		Getting the status of container
		ArrayList<statusTrackingObject> list = journey.getStatusData();
		statusTrackingObject status = list.get(list.size() - 1);
		response.setStatus(status);
		response.setErrorMessage("This is the current status of your container");
		return response;
		}

	}

	public ResponseObject getHistory(int containerId) {
		Container container = getContainerDatabase().getContainer(containerId);
		ResponseObject response = new ResponseObject();
		
		boolean containerExists = containerExists(containerId);
		
		if (!containerExists) {
			response.setErrorMessage("Container does not exist");
			return response;
		}
		
		response.setJourneys(container.getJourneyHistory());
		response.setErrorMessage("History successfully retrieved");
		return response;
		
	}
	
	public ResponseObject getHistoryOfContainerForClient (int clientId, int containerId) {
		ResponseObject response = new ResponseObject();
		
		boolean clientExists = clientExists(clientId);
		boolean containerExists = containerExists(containerId);
		
		if (!clientExists) {
			response.setErrorMessage("Client does not exist");
			return response;
		}
		else if (!containerExists) {
			response.setErrorMessage("Container does not exist");
			return response;
		}
		
		ArrayList<Journey> journeyHist = fetchContainerHistory(clientId, containerId);
		
		response.setJourneyHist(journeyHist);
		response.setErrorMessage("Your container's history is succesfully retrieved");
		return response;
		
	}

	private ArrayList<Journey> fetchContainerHistory(int clientId, int containerId) {
		ArrayList<Journey> journeyHist = new ArrayList<Journey>();
//		Acquiring all journey data related to the client
		for (Pair<Integer, Integer> p : getHistory(containerId).getJourneys()) {
			if (p.getValue() == clientId) {
				journeyHist.add(getJourneyDatabase().getJourney(p.getKey()));
			}
		}
		return journeyHist;
	}

	

}


