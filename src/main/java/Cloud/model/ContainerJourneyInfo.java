package Cloud.model;

import java.util.HashSet;

/**Class that stores info about a containers history (access to data, owner and journey)
 * 
 * @author Gustav Als
 * @author Victor Brevig
 *
 */
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

	public HashSet<Client> getClients() {
		return clients;
	}

	public void setJourney(Journey journey) {
		this.journey = journey;
	}

	
}
