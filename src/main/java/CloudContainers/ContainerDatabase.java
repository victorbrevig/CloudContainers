package CloudContainers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ContainerDatabase extends HashSet<Container>{


	public Set<Container> filterClient(Client client) {
		Set<Container> filtered = this.stream()
				.filter(container -> container.getOwner().equals(client))
				.collect(Collectors.toSet());
		return filtered;	
	}
	
	public Set<Container> filterJourney(Journey journey) {
		Set<Container> filtered = this.stream()
				.filter(container -> container.getCurrentJourney().equals(journey))
				.collect(Collectors.toSet());
		return filtered;	
	}
	public Container findFreeContainer() {
		//Container 0, is returned if no vacant containers are available
		Container container = new Container(0);
		
		for (Container c: this) {
			if (!c.isOwned()) {
				return c;
			}
		}
		return container;
		}

	
}
