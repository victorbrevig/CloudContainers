package CloudContainers;

import java.util.HashSet;

public class JourneyDatabase extends HashSet<Journey> {
	
	Journey emptyJourney = new Journey(0,"","","");
	public Journey getJourney(int journeyID) {
		for (Journey j : this) {
			if (j.getJourneyID() == journeyID) {
				return j;
			}
		} return emptyJourney;
	}
}
