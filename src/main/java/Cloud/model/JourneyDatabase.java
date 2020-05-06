package Cloud.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


/** This class represents a database for the journeys. It extends HashSet.
 * 
 * @author Victor Brevig
 * @author Gustav Als
 *
 */
public class JourneyDatabase extends HashSet<Journey> {

	private static final long serialVersionUID = 1L;

	public Set<Journey> filterForPortOfOrigin(String portOfOrigin) {
		Set<Journey> filtered = this.stream()
				.filter(journey -> journey.getPortOfOrigin().equals(portOfOrigin))
				.collect(Collectors.toSet());
		return filtered;
	}

	public Set<Journey> filterForDestination(String destination) {
		Set<Journey> filtered = this.stream()
				.filter(journey -> journey.getDestination().equals(destination))
				.collect(Collectors.toSet());
		return filtered;		
	}

	public Journey getJourney(int journeyID) {
		for (Journey j :this) {
			if (j.getJourneyID() == journeyID) {
				return j;
			}
		} return null;
	}

	public Set<Journey> getJourneysFromContainers(Set<Container> containers) {
		Set<Journey> journeys = new HashSet<Journey>();
		
		for (Container container : containers) {
			if (!(container.getCurrentJourney() == null)) {
				journeys.add(container.getCurrentJourney());
			}
		}	
		return journeys;
	}
	
}
