package Cloud.model;

import java.util.HashSet;


public class ContainerJourneyInfo {

	Journey journey;
	Client client;
	HashSet<Client> clients;
	
	/** Creates a containerJourneyInfo object
	 * 
	 * @param journey
	 * @param client
	 * @param clients
	 */
	
	public ContainerJourneyInfo(Journey journey, Client client, HashSet<Client> clients) {
		this.journey = journey;
		this.client = client;
		this.clients = clients;
	}
	
	/**Gets journey
	 * 
	 * @return journey
	 */
	
	public Journey getJourney() {
		return journey;
	}
	
	/**Gets a hashset of clients
	 * 
	 * @return clients
	 */

	public HashSet<Client> getClients() {
		return clients;
	}
	


	
}
