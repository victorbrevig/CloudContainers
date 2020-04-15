package CloudContainers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JourneyDatabase extends HashSet<Journey> {
	
	
	
	public Journey getJourney(int journeyID) {
		for (Journey j : this) {
			if (j.getJourneyID() == journeyID) {
				return j;
			}
		} return null;
	}
	
	public Set<Journey> filterPortOfOrigin(String portOfOrigin) {
		Set<Journey> filtered = this.stream()
				.filter(journey -> journey.getPortOfOrigin().equals(portOfOrigin))
				.collect(Collectors.toSet());
		return filtered;		
	}
	public Set<Journey> filterDestination(String destination) {
		Set<Journey> filtered = this.stream()
				.filter(journey -> journey.getDestination().equals(destination))
				.collect(Collectors.toSet());
		return filtered;		
	}
	
	
	
}
