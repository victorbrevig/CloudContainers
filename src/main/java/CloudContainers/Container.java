package CloudContainers;

import java.util.ArrayList;
import java.util.HashSet;
import org.javatuples.Triplet;

import javafx.util.Pair;

public class Container{
	private int containerId;
	private double temperature;
	private double pressure;
	private double airHumidity;
	private boolean onJourney;
	private boolean owned;
	private String content;
	private HashSet<Client> accessClients;
	private ArrayList<ContainerJourneyInfo> journeyHistory;
//	Create class
	private Client owner;
	private Journey currentJourney;

	
	public Container(int containerId) {
		super();
		this.airHumidity = 0.5;
		this.temperature = 20.0;
		this.onJourney = false;
		this.pressure = 1.0;
		this.owned = false;
		this.content = "";
		this.journeyHistory = new ArrayList<ContainerJourneyInfo>();
		this.containerId = containerId;
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
	
	
	public void addJourney(Journey journey) {
		ContainerJourneyInfo containerJourneyInfo = new ContainerJourneyInfo(journey,this.owner,accessClients);
		journeyHistory.add(containerJourneyInfo);
		setOnJourney(true);
		setCurrentJourney(journey);
	}
	
	public ArrayList<ContainerJourneyInfo> getJourneyHistory() {
		return journeyHistory;
	}

	public void setJourneyHistory(ArrayList<ContainerJourneyInfo> journeyHistory) {
		this.journeyHistory = journeyHistory;
	}

	public boolean equals(Container container) {
		if (container instanceof Container) {
			return this.getContainerId() == container.getContainerId();
		}
		return false;	
	}
	
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.containerId;
		return result;
	}
	
	public HashSet<Client> getAccessClients() {
		return accessClients;
	}
	
	public void grantAccess (Client client) {
		accessClients.add(client);
	}
	
	
	public boolean isOnJourney() {
		return onJourney;
	}
	public void setOnJourney(boolean onJourney) {
		this.onJourney = onJourney;
	}
	public boolean isOwned() {
		return owned;
	}
	public void setOwned(boolean owned) {
		this.owned = owned;
	}


	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getContainerId() {
		return containerId;
	}

	public void setContainerId(int containerId) {
		this.containerId = containerId;
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
			ArrayList<statusTrackingObject> list = journey.getStatusData();
			statusTrackingObject status = list.get(list.size() - 1);
			response.setStatus(status);
			response.setErrorMessage("This is the current status of your container");
			return response;
		}

	}
	
	public ResponseObject getHistoryOfContainerForClient (Client client) {
		ResponseObject response = new ResponseObject();
		
		
		ArrayList<Journey> journeyHist = fetchContainerHistory(client);
		
		response.setJourneyHistForClient(journeyHist);
		response.setErrorMessage("Your container's history is succesfully retrieved");
		return response;
		
	}

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
	public ResponseObject freeContainer() {
		ResponseObject response = new ResponseObject();
//		container is not on journey
//		container is owned
		boolean onJourney = isOnJourney();
		boolean owned = isOwned();
		if (onJourney) {
			response.setErrorMessage("This container is on a journey");
		}else if (!owned) {
			response.setErrorMessage("This container does not belong to a client");
		}else {
			setOwner(null);
			setOwned(false);
			response.setErrorMessage("Container was successfully freed");
		}
		return response;
	}
	
	public void print() {
		System.out.println(this.getContainerId());
		System.out.println(this.getTemperature());
		System.out.println(this.getPressure());
		System.out.println(this.getAirHumidity());
		System.out.println(this.isOwned());
		System.out.println(this.isOnJourney());
	}


	
	
}

