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

	private int companyID;
	private String name;

	private String password;
	
	private ClientDatabase clients;

	private ContainerDatabase containers;
	
	private JourneyDatabase journeys;

	private int amountOfContainers;

	private int totalAmountOfJourneys;
	
	
	
	/** Creates a logistic company.
	 * 
	 * @param name
	 * @param companyID
	 * @param amountOfContainers
	 */
	public LogisticCompany(String name, int companyID, int amountOfContainers, String password) {
		super();
		this.name = name;
		this.companyID = companyID;
		this.clients = new ClientDatabase();
		this.journeys = new JourneyDatabase();
		this.amountOfContainers = amountOfContainers;
		this.containers = new ContainerDatabase();
		this.password = password;
		this.totalAmountOfJourneys = 0;
		
		// Generate existing containers
		generateExistingContainers(amountOfContainers);
		
	}

	/** This method generates a specific amount of unowned containers
	 * 
	 * @param amountOfContainers
	 */
	private void generateExistingContainers(int amountOfContainers) {
		for (int i=1; i<=amountOfContainers;i++) {
			containers.add(new Container(i));
		}
	}
	
	/**Gets password
	 * 
	 * @return password
	 */
	
	public String getPassword() {
		return password;
	}
	/**Gets companies name
	 * 
	 * @return
	 */
	
	
	public String getName() {
		return name;
	}
	
	/** Gets container database
	 * 
	 * @return containers
	 */
	
	public ContainerDatabase getContainers() {
		return containers;
	}
	/**Sets container database
	 * 
	 * @param containers
	 */

	public void setContainers(ContainerDatabase containers) {
		this.containers = containers;
	}

	/**Sets client database
	 * 
	 * @param clients
	 */


	public void setClients(ClientDatabase clients) {
		this.clients = clients;
	}
	

	/** This method retrieves the ID of the company.
	 * 
	 * @return ID of company
	 */
	public int getCompanyID() {
		return companyID;
	}
	
	
	/** This method fetches the client database of the company
	 * 
	 * @return Client Database
	 */
	public ClientDatabase getClients() {
		return clients;
	}
	
	/** This method retrieves the journey database of the company.
	 * 
	 * @return A journey database object
	 */
	public JourneyDatabase getJourneyDatabase() {
		return journeys;
	}
	/** This method sets the journey database of the company.
	 * 
	 * @return A journey database object
	 */
	public void setJourneyDatabase(JourneyDatabase journeys) {
		this.journeys = journeys;
	}
	
	/** This method fetches the container database of the company.
	 * 
	 * @return Container Database
	 */
	public ContainerDatabase getContainerDatabase() {
		return containers;
	}
	

	
	/** This method overrides equals for LogisticCompany. Two companies are equal if their ID's are equal.
	 * 
	 * @param company
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof LogisticCompany) {
			return this.getCompanyID() == ((LogisticCompany) o).getCompanyID();
		}
		return false;	
	}
	
	/** This method overrides hashCode 
	 * @return hashCode - a unique integer for the company
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.getCompanyID();
		return result;
	}
	
//	/** This method checks if a container is owned by a specific client
//	 * 
//	 * @param client
//	 * @param container
//	 * @return boolean
//	 */
//	public boolean ownedContainer(Client client, Container container) {
//		return (container.getOwner()).equals(client);
//	}

	/** This method registers a new client and validate its input
	 * 
	 * @param client
	 * @return response
	 */
	public ResponseObject newClient(Client client) {
		ResponseObject response = new ResponseObject();
		response = Validator.validInput(client.getName(),client.getEmail(),client.getBirthdate(),client.getGender(),client.getNumber());
		if (response.getErrorMessage().equals("Valid")) {
			// Set id
			client.setClientID(clients.size() + 1);
			clients.add(client);
			response.setErrorMessage("Client was successfully added");
		}
		return response;
	}
	
	/**Creates a new journey and adds it to the journey database
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
	

	/** This method allocates a container to a journey
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
	
	/**Frees a container from client
	 * 
	 * @return response containing an error message
	 */
	public ResponseObject freeContainer(Container container) {
		ResponseObject response = new ResponseObject();

		if (container.isOnJourney()) {
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
		for (Container container : containers.filterJourney(journey)) {
				container.setCurrentJourney(null);
				container.setOnJourney(false);
				container.setContent("");
				countFree++;
		}
		return countFree;
	}
	
	

}


