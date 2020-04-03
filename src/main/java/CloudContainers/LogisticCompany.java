package CloudContainers;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.validator.GenericValidator;

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
		this.amountOfContainers++;
		containers.add(new Container(amountOfContainers));
	}
	
	public int getClientIDgen() {
		return clientIDgen;
	}
	
	public ClientDatabase getClients() {
		return clients;
	}
	
	public void setClients(ClientDatabase clients) {
		this.clients = clients;
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
		return clients.remove(clients.getClient(clientID));
	}
	public boolean removeClient(String email) {
		return clients.remove(clients.getClient(email));
	}
	// Does not work
	public boolean exist(Client client) {
		return clients.contains(client);
	}
	public boolean exist(int clientID) {
		return clients.contains(clients.getClient(clientID));
	}
	public boolean exist(String email) {
		return clients.contains(clients.getClient(email));
	}
	public boolean existN(int number) {
		return clients.contains(clients.getClient(number));
	}
	public boolean existCon(int containerID) {
		return containers.contains(containers.getContainer(containerID));
	}
	public boolean existJ(int journeyID) {
		return journeys.contains(journeys.getJourney(journeyID));
	}
	

	
	public void validBirthdate(String birthdate) throws invalidBirthdateException {
		if (!GenericValidator.isDate(birthdate, "dd-MM-yyyy", true)) {
			throw new invalidBirthdateException(birthdate + " is not a valid birthdate");
		}
	}
	
	public boolean correctEmail(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	public void validEmail(String email) throws invalidEmailException {
		if (!correctEmail(email)) {
			throw new invalidEmailException(email + " is not a valid email");
		}
	}
	
	public void validParameters(String name, String email, String birthdate, String gender, int number) 
			throws invalidParameterException{
		if (    (name=="") 
				|| (birthdate=="") 
				|| (email == "")			
				|| (gender=="") 
				|| (number==0)) {
			throw new invalidParameterException("There is a missing parameter");
		}
	}
	
	public ResponseObject validInput(String name, String email, String birthdate, String gender, int number) {
		ResponseObject response;
		try {
			validParameters(name,email,birthdate,gender,number);
			validEmail(email);
			validBirthdate(birthdate);
			if (!this.exist(email)) {
				response = new ResponseObject("Non-existing client");
			}else {
				response = new ResponseObject("existing client");
			}
		}catch(invalidEmailException err){
			response = new ResponseObject(err.getMessage());
		
		}catch(invalidBirthdateException err){
			response = new ResponseObject(err.getMessage());
			
		}catch(invalidParameterException err){
			response = new ResponseObject(err.getMessage());
		}
		return response;
		
	}


	
	public ResponseObject newClient(String name, String email, String birthdate, String gender, int number, String password) {
			ResponseObject response;
			response = validInput(name,email,birthdate,gender,number);
			if (response.getErrorMessage().equals("Non-existing client")) {
				int clientID = this.clientIDgen++;
				Client client = new Client(name,clientID,email,birthdate,gender,number,this.name, password);
				this.clients.add(client);
				response.setErrorMessage("Client was successfully added");
			}return response;
	}
	
	public ResponseObject updateClient(String oldEmail,String email) {
		ResponseObject response = new ResponseObject();
		Client c = this.findClient(oldEmail);
		response = validInput(c.getName(),email,c.getBirthdate(),c.getGender(),c.getNumber());
		if (response.getErrorMessage().equals("Non-existing client")) {
			this.findClient(oldEmail).setEmail(email);
			response.setErrorMessage("Email has been updated");}
		return response;}
	
	public ResponseObject updateClient(String oldEmail,int number) {
		ResponseObject response = new ResponseObject();
		if (!this.existN(number)) {
			this.findClient(oldEmail).setNumber(number);
			response.setErrorMessage("Phone number has been updated");
		}else {
			response.setErrorMessage("existing client");
		}
		return response;}

	public Container findFreeContainer() {
//		Container 0, is returned if no vacant containers are available
		Container container = new Container(0);
		
		for (Container c: containers) {
			if (!c.isOwned()) {
				return c;
			}
		}return container;
	}

	
	public ResponseObject allocateContainer(int clientId,Container container) {
		ResponseObject response = new ResponseObject("Container succesfully allocated");
		boolean existClient = this.exist(clientId);
		boolean existContainer = this.existCon(container.getContainerId());
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
		Container container = this.getContainerDatabase().getContainer(containerID);
		ResponseObject response = new ResponseObject();
//		container is not on journey
//		container is owned
		boolean onJourney = container.isOnJourney();
		boolean owned = container.isOwned();
		if (!onJourney) {
			response.setErrorMessage("This container is on a journey");
		}else if (!owned) {
			response.setErrorMessage("This container does not belong to a client");
		}else {
			this.getContainerDatabase().getContainer(containerID).setClientId(0);
			this.getContainerDatabase().getContainer(containerID).setOwned(false);
			response.setErrorMessage("Container was successfully freed");
		}
		return response;
	}
	
	public ResponseObject containerToJourney(int clientID, Container container, int journeyID, String content) {
		ResponseObject response = new ResponseObject("Container successfully added to journey");
		// Conditions to check
		// Client exists OR clients.contains(client);
		boolean c1 = exist(clientID);
		// Container belongs to client
		boolean c2 = container.getClientId() == clientID;
		// Journey exists
		boolean c3 = existJ(journeyID);
		
		if (c1 && c2 && c3) {
			container.setContent(content);
			container.addJourney(journeyID);
		}
		else if (!c1) {
			response.setErrorMessage("Client does not exist");
		}
		else if (!c2) {
			response.setErrorMessage("Container does not belong to client");
		}
		else if (!c3) {
			response.setErrorMessage("Journey does not exist");
		}
		
		return response;
	}
	
	public ResponseObject updateJourneyPortOfOrigin(int journeyID, String newPortOfOrigin) {
		ResponseObject response = new ResponseObject();
		if (existJ(journeyID)) {
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
		if (existJ(journeyID)) {
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
		
		if (!existJ(journeyID)) {
			response.setErrorMessage("No such journey exist");
			return response;
		}
		
		int countFree = 0;
		
		journeys.getJourney(journeyID).setStarted(false);
		
		for (Container container : containers) {
			if (container.getJourneyId() == journeyID) {
				container.setJourneyId(0);
				container.setOnJourney(false);
				container.setContent("");
				countFree++;
				

			}
		}
		response.setErrorMessage("Journey successfully ended. " + countFree + " containers were set free.");
		
		return response;
	}
	
	public ResponseObject progressJourney(int journeyID, int timeIncrement) {
		
		ResponseObject response = new ResponseObject();
		int elapsedTime = journeys.getJourney(journeyID).getElapsedTime();
		int timeToDestination = journeys.getJourney(journeyID).getTimeToDestination();
		journeys.getJourney(journeyID).setStarted(true);

		double newTemp = 0;
		double newPressure = 0;
		double newAirHum = 0;
		int timeTraveled = 0;
		boolean existingContainer = false;
		
		
		if (!this.existJ(journeyID)) {
			response.setErrorMessage("Journey does not exist");
			return response;
		}else if (timeIncrement <= 0) {
			response.setErrorMessage("Travel time has to be a more than 0");
			return response;
		}
		
		
		for (int i = 1; i<=timeIncrement;i++) {
//			Checking if the journey has ended
			if (timeToDestination == 0) {
				this.endJourney(journeyID);
				break;
			} 
			
			double randTempIncrement = rand.nextDouble() * (rand.nextBoolean() ? -1 : 1) * 3;
			// +- 0.1 atm
			double randPressureIncrement = rand.nextDouble() * (rand.nextBoolean() ? -1 : 1) * 0.1;
			// +- 0.05
			double randAirHumIncrement = rand.nextDouble() * (rand.nextBoolean() ? -1 : 1) * 0.05;
			for (Container container : containers) {
				if (container.getJourneyId() == journeyID) {
					existingContainer = existingContainer || true;
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
			if (existingContainer) {
				statusTrackingObject status = new statusTrackingObject(elapsedTime,newAirHum,newTemp,newPressure);
				journeys.getJourney(journeyID).addDataPoint(status);
			}
//			Increment time
			journeys.getJourney(journeyID).setElapsedTime(elapsedTime);
			journeys.getJourney(journeyID).setTimeToDestination(timeToDestination);
			
		}
		if(existingContainer && journeys.getJourney(journeyID).isStarted() == true){
			response.setErrorMessage("Your container on journey " + journeyID + " has traveled " + timeIncrement + " hours.");
			
		}else if(journeys.getJourney(journeyID).isStarted() == false){
//			Checking if journey has ended
			response.setErrorMessage("Your container on journey " + journeyID + " has traveled " + timeTraveled + " hours and the journey has ended.");
		}
		else {
			response.setErrorMessage("No containers are on this journey");
		}
			
		
		return response;
	}

	
	public boolean ownedContainer(int clientID, int containerID) {
		return this.getContainerDatabase().getContainer(containerID).getClientId() == clientID;
	}
	
	public ResponseObject accessStatus(Integer clientID, Integer containerID) {
		ResponseObject response = new ResponseObject();
		Container container = containers.getContainer(containerID);
		Journey journey = journeys.getJourney(container.getJourneyId());
//		Container exists
		boolean c1 = this.existCon(containerID);
//		Container owned
		boolean c2 = ownedContainer(clientID,containerID);
//		Journey is started, and data exists
		boolean c3 = journey.isStarted();
		
		if (!c1 || !c2) {
			response.setErrorMessage("You don't own this container");
			return response;
		} else if (!c3) {
			response.setErrorMessage("Ship's still at harbour");
			return response;
		} else {
//		Getting the status of container
		ArrayList<statusTrackingObject> list;
		list = journey.getStatusData();
		statusTrackingObject status = list.get(list.size()-1);
		response.setStatus(status);
		response.setErrorMessage("This is the current status of your container");
		return response;}

	}

	

}



class invalidEmailException extends Exception { 
    public invalidEmailException(String errorMessage) {
        super(errorMessage);
    }
    
}

class invalidBirthdateException extends Exception { 
    public invalidBirthdateException(String errorMessage) {
        super(errorMessage);
    }
}

class invalidParameterException extends Exception { 
    public invalidParameterException(String errorMessage) {
        super(errorMessage);
    }
}


