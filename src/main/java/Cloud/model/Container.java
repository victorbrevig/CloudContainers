package Cloud.model;

import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


import Cloud.model.ContainerJourneyInfo;
import Cloud.model.Client;
import Cloud.model.Container;
import Cloud.model.Journey;
import Cloud.model.LogisticCompany;
import Cloud.model.ResponseObject;
import Cloud.model.Validator;


public class Container{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column
	private int containerID;
	private double temperature;
	private double pressure;
	private double airHumidity;
	private boolean onJourney;
	@NotBlank
	@Column
	private String content;
	private HashSet<Client> accessClients;
	private ArrayList<ContainerJourneyInfo> journeyHistory;
	private Client owner;
	private Journey currentJourney;
	private Validator validator;
	/**Creates a container
	 * 
	 * @param containerId
	 * @param company
	 */
	
	public Container() {
	}
	public Container(int containerID, LogisticCompany company) {
		super();
		this.airHumidity = 0.5;
		this.temperature = 20.0;
		this.onJourney = false;
		this.pressure = 1.0;
		this.content = "";
		this.journeyHistory = new ArrayList<ContainerJourneyInfo>();
		this.containerID = containerID;
		this.accessClients = new HashSet<Client>();
		this.validator = new Validator(company);
		
	}
	
	/**Gets owner of container
	 * 
	 * @return client
	 */
	
	public Client getOwner() {
		return owner;
	}
	
	/**Sets owner of container
	 * 
	 * @param owner
	 */

	public void setOwner(Client owner) {
		this.owner = owner;
	}
	
	/**Gets current journey of container
	 * 
	 * @return Journey
	 */

	public Journey getCurrentJourney() {
		return currentJourney;
	}
	
	/**Sets current journey of container
	 * 
	 * @param currentJourney
	 */

	public void setCurrentJourney(Journey currentJourney) {
		this.currentJourney = currentJourney;
	}
	
	
	/**Gets journey history of container
	 * 
	 * @return journeyHistory
	 */
	
	public ArrayList<ContainerJourneyInfo> getJourneyHistory() {
		return journeyHistory;
	}
	
	/**Checks if container is on journey
	 * 
	 * @return boolean
	 */

	public boolean isOnJourney() {
		return onJourney;
	}
	
	/**Sets container on journey
	 * 
	 * @param onJourney a boolean describing if container is on journey
	 */
	
	public void setOnJourney(boolean onJourney) {
		this.onJourney = onJourney;
	}
	
	/**Checks if container is owned
	 * 
	 * @return owned boolean value
	 */
	
	public boolean isOwned() {
		return owner!=null;
	}
	
	/**Sets content of container
	 * 
	 * @param content
	 */
	
	public void setContent(String content) {
		this.content = content;
	}
	
	/**Gets containerID
	 * 
	 * @return containerID
	 */
	
	public int getContainerID() {
		return containerID;
	}
	

	
	/**Gets temperature inside container in Celsius
	 * 
	 * @return temperature
	 */
	
	public double getTemperature() {
		return temperature;
	}
	
	/**Sets temperature of container
	 * 
	 * @param temperature
	 */
	
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	/**Gets pressure inside container in atmosphere
	 * 
	 * @return pressure
	 */
	
	public double getPressure() {
		return pressure;
	}
	
	/**Sets pressure inside container
	 * 
	 * @param pressure
	 */
	
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	
	/**Gets air humidity inside container in percent
	 * 
	 * @return airHumidity
	 */
	
	public double getAirHumidity() {
		return airHumidity;
	}
	
	/**Sets air humidity inside container
	 * 
	 * @param airHumidity
	 */
	
	public void setAirHumidity(double airHumidity) {
		this.airHumidity = airHumidity;
	}
	
	/**Gets a hashset of clients that has access to containers data
	 * 
	 * @return accessClients
	 */
	
	public HashSet<Client> getAccessClients() {
		return accessClients;
	}
	
	/**Compares container with another container using containerID
	 * 
	 * @param client
	 * @return boolean
	 */
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Container) {
			return this.getContainerID() == ((Container) o).getContainerID();
		}
		return false;	
	}
	
	
	/** HashCode implementation for checking equality of container objects
	 * 
	 */
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.containerID;
		return result;
	}
	
	/**Adds journey to journey history
	 * 
	 * @param journey
	 */
	
	public void addJourney(Journey journey) {
		ContainerJourneyInfo containerJourneyInfo = new ContainerJourneyInfo(journey,this.owner,accessClients);
		journeyHistory.add(containerJourneyInfo);
		setOnJourney(true);
		setCurrentJourney(journey);
	}
	
	/**Grants access to the containers data to a new client
	 * 
	 * @param client
	 * @return response
	 */
	
	public ResponseObject grantAccess (Client client) {
//		Container
		ResponseObject response = new ResponseObject();

		boolean sameCompany = client.getCompany().equals(getOwner().getCompany());
		
		if (sameCompany) {
			getAccessClients().add(client);
			response.setErrorMessage("Access succesfully granted");
			return response;
		}
		
		response.setErrorMessage("You do not share company");
		
		return response;
	}
	
	/**Accesses status of container
	 * 
	 * @param client
	 * @return response containing a message and a status
	 */
	
	public ResponseObject accessStatus(Client client) {
		ResponseObject response = new ResponseObject();
		Journey journey = getCurrentJourney();
		
		boolean accessToDataContainer = accessClients.contains(client);
		
		if (!accessToDataContainer) {
			response.setErrorMessage("You don't have access to this container");
			return response;
		} 
		boolean journeyIsStarted = journey.isStarted();
		if (!journeyIsStarted) {
			response.setErrorMessage("Ship's still at harbour");
			return response;
		} 
		else {
	//		Getting the status of container
			ArrayList<DataPoint> list = journey.getStatusData();
			DataPoint status = list.get(list.size() - 1);
			response.setStatus(status);
			response.setErrorMessage("This is the current status of your container");
			return response;
		}

	}
	
	/**Gets full history of a container, where it has been owned by a specific client
	 * 
	 * @param client
	 * @return response containing a message and history of container
	 */
	
	public ResponseObject getHistoryOfContainerForClient (Client client) {
		ResponseObject response = new ResponseObject();
		
		boolean clientHasAccess = validator.clientHasAccess(client, this);
		
		if (clientHasAccess) {
			ArrayList<Journey> journeyHist = fetchContainerHistory(client);
			
			response.setJourneyHistForClient(journeyHist);
			response.setErrorMessage("Your container's history is succesfully retrieved");

		}
		else {
			response.setErrorMessage("You do not have access to this container");

		}
		return response;
		
	}
	
	/**Adds all journeys that container has been on with this client to history
	 * 
	 * @param client
	 * @return journeyHist
	 */

	private ArrayList<Journey> fetchContainerHistory(Client client) {
		ArrayList<Journey> journeyHist = new ArrayList<Journey>();
//		Acquiring all journey data related to the client
		for (ContainerJourneyInfo i : getJourneyHistory()) {
			// if (p.getRight.contains(clientId))
			if (i.getClients().contains(client)) {
				journeyHist.add(i.getJourney());
			}
		}
		return journeyHist;
	}
	

	
//	Should be in logistic company
	
	
	
//	/**Prints information of container
//	 * 
//	 */
//	
//	public void print() {
//		System.out.println(this.getContainerID());
//		System.out.println(this.getTemperature());
//		System.out.println(this.getPressure());
//		System.out.println(this.getAirHumidity());
//		System.out.println(this.isOwned());
//		System.out.println(this.isOnJourney());
//	}


	
	
}

