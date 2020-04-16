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
	private int amountOfContainers;
	private Validator validator;
	
	public LogisticCompany(String name, int companyID, int amountOfContainers) {
		super();
		this.name = name;
		this.companyID = companyID;
		this.clients = new ClientDatabase();
		this.journeys = new JourneyDatabase();
		this.amountOfContainers = amountOfContainers;
		this.validator = new Validator(this);
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
	
	public void addJourney(Journey journey) {
		journey.setJourneyID(journeys.size() + 1);
		journey.setCompany(this);
		journeys.add(journey);
		
	}
	
	public void addContainer() {
		amountOfContainers++;
		containers.add(new Container(amountOfContainers));
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

	
	public ResponseObject newClient(Client client) {
		ResponseObject response = null;
		response = validator.validInput(client.getName(),client.getEmail(),client.getBirthdate(),client.getGender(),client.getNumber());
		if (response.getErrorMessage().equals("Non-existing client")) {
			// Set id
			client.setClientID(clients.size() + 1);
			client.setCompany(this);
			clients.add(client);
			response.setErrorMessage("Client was successfully added");
		}
		return response;
	}
	

	
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
	
	
	
	
//	public void updateJourneyPortOfOrigin(Journey journey, String newPortOfOrigin) {
//		journey.setPortOfOrigin(newPortOfOrigin);
//	}
//	
//	public void updateJourneyDestination(Journey journey, String newDestination) {
//		journey.setDestination(newDestination);
////		Overwrites the old journey, as equals works on journeyId, and journeys is a hashSet
//		journeys.add(journey);
//	}
	
	
	
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


