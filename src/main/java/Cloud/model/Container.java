package Cloud.model;

import java.util.ArrayList;
import java.util.HashSet;
import Cloud.model.Container;


/**Class for containers
 * 
 * @author Gustav Als
 * @author Victor Brevig
 *
 */
public class Container{

	private int containerID;
	private double temperature;
	private double pressure;
	private double airHumidity;
	private String content;
	private HashSet<Client> accessClients;
	private JourneyHistory journeyHistory;
	private Client owner;
	private Journey currentJourney;
	

	
	public Container() {}
	
	public Container(int containerID) {
		super();
		this.airHumidity = 0.5;
		this.temperature = 20.0;
		this.pressure = 1.0;
		this.content = "";
		this.journeyHistory = new JourneyHistory();
		this.containerID = containerID;
		this.accessClients = new HashSet<Client>();
		
	}
	
	public Client getOwner() {
		return owner;
	}
	
	public void setOwner(Client owner) {
		this.owner = owner;
	}

	public Journey getCurrentJourney() {
		return currentJourney;
	}
	
	public void setCurrentJourney(Journey currentJourney) {
		this.currentJourney = currentJourney;
	}
	
	public ArrayList<ContainerJourneyInfo> getJourneyHistory() {
		return journeyHistory;
	}

	public boolean isOwned() {
		return owner!=null;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public int getContainerID() {
		return containerID;
	}
	
	public double getTemperature() {
		return temperature;
	}
	
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getPressure() {
		return pressure;
	}
	
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public double getAirHumidity() {
		return airHumidity;
	}
	
	public void setAirHumidity(double airHumidity) {
		this.airHumidity = airHumidity;
	}

	public HashSet<Client> getAccessClients() {
		return accessClients;
	}
	
	/**Made so two containers are compared by their containerID
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
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.containerID;
		return result;
	}
	
	public void addJourney(Journey journey) {
		ContainerJourneyInfo containerJourneyInfo = new ContainerJourneyInfo(journey,this.owner,accessClients);
		journeyHistory.add(containerJourneyInfo);
		setCurrentJourney(journey);
		
	}

	public void grantAccess(Client client) {
		getAccessClients().add(client);
		if (journeyHistory.latestJourneyIsStarted()) {
			journeyHistory.get(journeyHistory.size() - 1).getClients().add(client);
		}
	}

	/**Used to filter container history for a given client
	 * 
	 * @param client
	 * @return response containing a message and history of container
	 */
	
	public ResponseObject getHistoryOfContainerForClient (Client client) {
		ResponseObject response = new ResponseObject();
		
		if (Validator.clientHasAccess(client, this)) {
			ArrayList<Journey> journeyHist = fetchContainerHistory(client);
			response.setJourneyHistForClient(journeyHist);
			response.setErrorMessage("Your container's history is succesfully retrieved");
		}
		else {
			response.setErrorMessage("You do not have access to this container");

		}
		return response;
		
	}

	private ArrayList<Journey> fetchContainerHistory(Client client) {
		ArrayList<Journey> journeyHist = new ArrayList<Journey>();
//		Acquiring all journey data related to the client
		for (ContainerJourneyInfo i : getJourneyHistory()) {
			if (i.getClients().contains(client)) {
				journeyHist.add(i.getJourney());
			}
		}
		return journeyHist;
	}
	
	boolean containerOnJourneyForClient(Client client, Journey journey) {
		if (getOwner() != null && getCurrentJourney() != null) {
			return getOwner().equals(client) && getCurrentJourney().equals(journey);
		}
		return false;
	}
	
	boolean belongsToClient(Client client) {
		return getOwner().equals(client);
	}
	



	
}

