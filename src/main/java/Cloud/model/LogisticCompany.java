package Cloud.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;


/** This class represents a logistic company.
 * 
 * @author Gustav Als
 * @author Victor Brevig
 */
@Entity
public class LogisticCompany {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column
	private int companyID;
	@NotBlank
	@Column
	private String name;
	@Transient
	private ClientDatabase clients; //Should be list of object
	@Transient
	private ContainerDatabase containers;
	@Transient
	private JourneyDatabase journeys;
	@NotBlank
	@Column
	private int amountOfContainers;
	@Transient
	private Validator validator;
	
	
	
	/** Creates a logistic company.
	 * 
	 * @param name
	 * @param companyID
	 * @param amountOfContainers
	 */
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
		generateExistingContainers(amountOfContainers);
		
	}

	/** This method generates a specific amount of unowned containers
	 * 
	 * @param amountOfContainers
	 */
	private void generateExistingContainers(int amountOfContainers) {
		for (int i=1; i<=amountOfContainers;i++) {
			containers.add(new Container(i,this));
		}
	}

	/** This method retrieves the ID of the company.
	 * 
	 * @return ID of company
	 */
	public int getCompanyID() {
		return companyID;
	}
	
	/** This method retrieves a set of containers on a specific journey.
	 * 
	 * @param journey
	 * @return A set of containers
	 */
	public Set<Container> getContainersForJourney(Journey journey){
		return containers.filterJourney(journey);
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
	
	/** This method fetches the container database of the company.
	 * 
	 * @return Container Database
	 */
	public ContainerDatabase getContainerDatabase() {
		return containers;
	}
	
	/** This method registers a journey to the journey database of the company.
	 * 
	 * @param journey
	 */
	public void registerJourney(Journey journey) {
		journey.setJourneyID(journeys.size() + 1);
		journey.setCompany(this);
		journeys.add(journey);
		
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
	

	/** This method allocates a container to a journey
	 * 
	 * @param client
	 * @param container
	 * @return response
	 */
	public ResponseObject allocateContainer(Client client,Container container) {
		ResponseObject response = new ResponseObject("Container succesfully allocated");
		boolean owned = container.isOwned();
		if (owned) {
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
			response.setErrorMessage("Container was successfully freed");
		}
		return response;
	}
	
	
	/** This method fetches the full history of a container
	 * 
	 * @param container
	 * @return response
	 */
	public ResponseObject getFullHistory(Container container) {
		ResponseObject response = new ResponseObject();
		response.setJourneys(container.getJourneyHistory());
		response.setErrorMessage("History successfully retrieved");
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
	
	
	
	

}


