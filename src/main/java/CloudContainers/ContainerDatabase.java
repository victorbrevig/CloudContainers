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
				.filter(container -> journey.equals(container.getCurrentJourney()))
				.collect(Collectors.toSet());
		return filtered;	
	}
	public Container findFreeContainer() {
		
		for (Container c: this) {
			if (!c.isOwned()) {
				return c;
			}
		}
		return null;
		}

	
}
