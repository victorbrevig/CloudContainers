package Cloud.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/** This class represents a logistic company.
 * 
 * @author Gustav Als
 * @author Victor Brevig
 * 
 */

public class LogisticCompany {

	private String name;
	private String password;
	private ClientDatabase clients;
	private ContainerDatabase containers;
	private JourneyDatabase journeys;
	private int amountOfContainers;
	private int totalAmountOfJourneys;

	public LogisticCompany(String name, int amountOfContainers, String password) {
		super();
		this.name = name;
		this.clients = new ClientDatabase();
		this.journeys = new JourneyDatabase();
		this.amountOfContainers = amountOfContainers;
		this.containers = new ContainerDatabase();
		this.password = password;
		this.totalAmountOfJourneys = 0;
		
		// Generate existing containers
		generateExistingContainers(amountOfContainers);
		
	}

	private void generateExistingContainers(int amountOfContainers) {
		for (int i=1; i<=amountOfContainers;i++) {
			containers.add(new Container(i));
		}
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public ContainerDatabase getContainers() {
		return containers;
	}

	public void setContainerDatabase(ContainerDatabase containers) {
		this.containers = containers;
	}

	public void setClients(ClientDatabase clients) {
		this.clients = clients;
	}

	public ClientDatabase getClients() {
		return clients;
	}

	public JourneyDatabase getJourneyDatabase() {
		return journeys;
	}

	public void setJourneyDatabase(JourneyDatabase journeys) {
		this.journeys = journeys;
	}

	public ContainerDatabase getContainerDatabase() {
		return containers;
	}

	/**Needs to validate all input and add client object to client database
	 * 
	 * @param client
	 * @return response
	 */
	public ResponseObject newClient(Client client) {
		ResponseObject response = new ResponseObject();
		response = Validator.validInput(client.getName(),client.getEmail(),client.getBirthdate(),client.getGender(),client.getNumber());
		if (response.getErrorMessage().equals("Valid")) {
			client.setClientID(clients.size() + 1);
			clients.add(client);
			response.setErrorMessage("Client was successfully added");
		}
		return response;
	}
	
	/**Used to validate a journey and add it to the journey database
	 * 
	 * @param journey
	 * @return response
	 */

	public ResponseObject newJourney(Journey journey) {
		ResponseObject response = new ResponseObject();
		response = Validator.validJourney(journey);
		
		if(response.getErrorMessage().equals("Valid")) {
			totalAmountOfJourneys++;
			journey.setJourneyID(totalAmountOfJourneys);
			journeys.add(journey);
			response.setErrorMessage("Journey was successfully added");
		}
		return response;
	}
	

	/**The company needs to allocate a container to a client for him/her to use it
	 * 
	 * @param client
	 * @param container
	 * @return response
	 */
	public ResponseObject allocateContainer(Client client,Container container) {
		ResponseObject response = new ResponseObject("Container succesfully allocated");

		if (container.isOwned()) {
			response.setErrorMessage("This container is already owned by a client");
		}
		else {
			container.setOwner(client);
			container.grantAccess(client);
		}
		return response;
	}
	
	/**A container can be freed by the company from the ownership of a client
	 * 
	 * @return response containing an error message
	 */
	public ResponseObject freeContainer(Container container) {
		ResponseObject response = new ResponseObject();

		if (container.getCurrentJourney() != null) {
			response.setErrorMessage("This container is on a journey");
		}else if (!container.isOwned()) {
			response.setErrorMessage("This container does not belong to a client");
		}else {
			container.setOwner(null);
			response.setErrorMessage("Container was successfully freed");
		}
		return response;
	}
	
	

	/** This method checks if a client is registered from the client's email
	 * 
	 * @param email
	 * @return boolean
	 */
	public boolean clientExists(String email) {
		return clients.stream().anyMatch(c -> c.getEmail().equals(email));
	}

	
	/** This method ends the journey.
	 * 
	 * @return response
	 */
	public ResponseObject endJourney(Journey journey) {
		ResponseObject response = new ResponseObject();
		
		int countFree = 0;
		
		journey.setStarted(false);

		countFree = freeUpContainers(countFree, journey);
		
		journeys.remove(journey);
		
		response.setErrorMessage("Journey successfully ended. " + countFree + " containers were set free.");
		
		return response;
	}
	
	/** This method frees up all containers associated with this journey.
	 * 
	 * @param countFree 
	 * @return countFree - how many containers was freed
	 */
	private int freeUpContainers(int countFree,Journey journey) {
		for (Container container : containers.filterForJourney(journey)) {
				container.setCurrentJourney(null);
				container.setContent("");
				countFree++;
		}
		return countFree;
	}
	
	

}


