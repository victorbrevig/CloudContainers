package Cloud.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


/** This class represents a container database, used to store containers.
 * 
 * @author Victor Brevig
 * @author Gustav Als
 *
 */
public class ContainerDatabase extends HashSet<Container>{

	private static final long serialVersionUID = 1L;

	public Set<Container> filterForClient(Client client) {
		Set<Container> filtered = this.stream()
				.filter(container -> container.getAccessClients().contains(client))
				.collect(Collectors.toSet());
		return filtered;
	}

	public Set<Container> filterForJourney(Journey journey) {
		Set<Container> filtered = this.stream()
				.filter(container -> journey.equals(container.getCurrentJourney()))
				.collect(Collectors.toSet());
		return filtered;	
	}

	public Set<Container> getContainersForJourney(Journey journey){
		return this.filterForJourney(journey);
	}
	
	/**A containerDatabase can consist of both owned and unowned containers, this method
	 * finds the first unowned container
	 * @return container
	 */
	
	public Container findFreeContainer() {
		
		for (Container c: this) {
			if (!c.isOwned()) {
				return c;
			}
		}
		return null;
	}

	public Container getContainer(int containerID) {
		for (Container c : this) {
			if (c.getContainerID() == containerID) {
				return c;
			}
		} return null;
	}

	public int numberOfContainersOnJourneyForClient(Client client, Journey journey) {
		int count = 0;
		for (Container container : this) {
			if (container.containerOnJourneyForClient(client, journey)) {
				count++;
			}
		}
		return count;
	}

	
}
