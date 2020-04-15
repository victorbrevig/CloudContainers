package CloudContainers;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.validator.GenericValidator;
import org.javatuples.Triplet;

import javafx.util.Pair;

public class LogisticCompany {
	private String name;
	private int companyID;
	private ClientDatabase clients;
	private ContainerDatabase containers;
	private JourneyDatabase journeys;
	private int clientIDgen = 1;
	private int amountOfContainers;
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
	
	public Set<Container> getContainersForJourney(Journey journey){
		return containers.filterJourney(journey);
	}
	
	public JourneyDatabase getJourneyDatabase() {
		return journeys;
	}
	
	public ContainerDatabase getContainerDatabase() {
		return containers;
	}
	
	public void createJourney(String portOfOrigin, String destination,int timeToDestination) {
		Journey journey = new Journey(journeyIDgen, portOfOrigin, destination, this,timeToDestination);
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


	
	public boolean ownedContainer(Client client, Container container) {
		return (container.getOwner()).equals(client);
	}

	
	public ResponseObject newClient(String name, String email, String birthdate, String gender, int number, String password) {
		ResponseObject response = null;
//		response = validInput(name,email,birthdate,gender,number);
		if (response.getErrorMessage().equals("Non-existing client")) {
			int clientID = clientIDgen++;
			Client client = new Client(name,clientID,email,birthdate,gender,number,this.name, password);
			clients.add(client);
			response.setErrorMessage("Client was successfully added");
		}
		return response;
	}
	
//	public ResponseObject updateClient(int clientID,String email) {
//		ResponseObject response = new ResponseObject();
//		Client c = clients.getClient(clientID);
//		// Valid new email
//		response = validInput(c.getName(),email,c.getBirthdate(),c.getGender(),c.getNumber());
//		// Check if new email belongs to a client already
//		if (response.getErrorMessage().equals("Non-existing client")) {
//			clients.getClient(clientID).setEmail(email);
//			response.setErrorMessage("Email has been updated");
//			}
//		return response;
//	}
	
//	public ResponseObject updateClient(int clientID,int number) {
//		ResponseObject response = new ResponseObject();
//		Client c = clients.getClient(clientID);
//		// Valid new email
//		response = validInput(c.getName(),c.getEmail(),c.getBirthdate(),c.getGender(),number);
//		// Check if new email belongs to a client already
//		
//		if (!validNumber(number)) {
//			return response;
//		}
//		if (!numberExists(number)) {
//			clients.getClient(clientID).setNumber(number);
//			response.setErrorMessage("Phone number has been updated");
//		}
//		else {
//			response.setErrorMessage("Existing client");
//		}
//		return response;
//	}

//	public Container findFreeContainer() {
////		Container 0, is returned if no vacant containers are available
//		Container container = new Container(0);
//		
//		for (Container c: containers) {
//			if (!c.isOwned()) {
//				return c;
//			}
//		}
//		return container;
//	}

	
	public ResponseObject allocateContainer(Client client,Container container) {
		ResponseObject response = new ResponseObject("Container succesfully allocated");
		boolean owned = container.isOwned();
		if (owned) {
			response.setErrorMessage("This container is already owned by a client");
		}
		else {
			container.setOwned(true);
			container.setOwner(client);
			container.grantAccess(client);
		}
		return response;
	}
	
	public ResponseObject freeContainer(Container container) {
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
			container.setOwner(null);
			container.setOwned(false);
			response.setErrorMessage("Container was successfully freed");
		}
		return response;
	}
	
	
	public void updateJourneyPortOfOrigin(Journey journey, String newPortOfOrigin) {
		journey.setPortOfOrigin(newPortOfOrigin);
	}
	
	public void updateJourneyDestination(Journey journey, String newDestination) {
		journey.setDestination(newDestination);
//		Overwrites the old journey, as equals works on journeyId, and journeys is a hashSet
		journeys.add(journey);
	}
	
	
	
	public ResponseObject getFullHistory(Container container) {
		ResponseObject response = new ResponseObject();
		response.setJourneys(container.getJourneyHistory());
		response.setErrorMessage("History successfully retrieved");
		return response;
		
	}

	public boolean clientExists(String email) {
		return clients.stream().anyMatch(c -> c.getEmail().equals(email));
	}
	
	
	

}


