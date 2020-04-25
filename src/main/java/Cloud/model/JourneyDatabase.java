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
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** This method fetches all journeys in the database with port of origin as requested.
	 * 
	 * @param portOfOrigin
	 * @return filtered - A set of journeys with port of origin as requested.
	 */
	public Set<Journey> filterPortOfOrigin(String portOfOrigin) {
		Set<Journey> filtered = this.stream()
				.filter(journey -> journey.getPortOfOrigin().equals(portOfOrigin))
				.collect(Collectors.toSet());
		return filtered;
	}
	
	/** This method fetches all journeys in the database with destination as requested.
	 * 
	 * @param destination
	 * @return filtered - A set of journeys with destination as requested.
	 */
	public Set<Journey> filterDestination(String destination) {
		Set<Journey> filtered = this.stream()
				.filter(journey -> journey.getDestination().equals(destination))
				.collect(Collectors.toSet());
		return filtered;		
	}
	
	
	
}
