package Cloud.model;

import java.util.ArrayList;

/** This class is a container for the archived ContianerJourneyInfo objects in chronologically order
 * 
 * @author Victor Brevig
 * @author Gustav Als
 *
 */
public class JourneyHistory extends ArrayList<ContainerJourneyInfo>{

	private static final long serialVersionUID = 1L;

	boolean latestJourneyIsStarted() {
		if (this.size() > 0) {
			return get(size() - 1).getJourney().isStarted();
		}
		return false;
	}
	
}
