package CloudContainers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ContainerDatabase extends HashSet<Container>{

	Container emptyContainer = new Container(0);
	public Container getContainer(int containerID) {
		for (Container c : this) {
			if (c.getContainerId() == containerID) {
				return c;
			}
		} return emptyContainer;
	}
	
	public Set<Container> filterClient(int clientId) {
		Set<Container> filtered = this.stream()
				.filter(container -> container.getClientId() == clientId)
				.collect(Collectors.toSet());
		return filtered;	
	}
	
	public Set<Container> filterJourney(int journeyId) {
		Set<Container> filtered = this.stream()
				.filter(container -> container.getJourneyId() == journeyId)
				.collect(Collectors.toSet());
		return filtered;	
	}
	
	
}
