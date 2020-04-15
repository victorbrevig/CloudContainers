package CloudContainers;

import java.util.HashSet;

public class ContainerJourneyInfo {

	Journey journey;
	Client client;
	HashSet<Client> clients;
	
	public ContainerJourneyInfo(Journey journey, Client client, HashSet<Client> clients) {
		this.journey = journey;
		this.client = client;
		this.clients = clients;
	}
	
	
	public Journey getJourney() {
		return journey;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public HashSet<Client> getClients() {
		return clients;
	}

	public void setClients(HashSet<Client> clients) {
		this.clients = clients;
	}


	
	
	
	
}
