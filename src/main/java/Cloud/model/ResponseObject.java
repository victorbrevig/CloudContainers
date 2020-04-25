package Cloud.model;

import java.util.ArrayList;




/** Class to represent a response that contains error messages which can be used for testing and visual response
 * 
 * @author Gustav Als
 * @author Victor Brevig
 */
public class ResponseObject {
	private String errorMessage;
	private DataPoint status;
	// All journeys
	private ArrayList<ContainerJourneyInfo> journeyHistory;
	// Client specific journeys
	private ArrayList<Journey> journeyHistForClient;
	
	/** This method fetches an ArrayList of journeys
	 * 
	 * @return ArrayList of journeys
	 */
	public ArrayList<Journey> getJourneyHist() {
		return journeyHistForClient;
	}
	public void setJourneyHistForClient(ArrayList<Journey> journeyHist) {
		this.journeyHistForClient = journeyHist;
	}
	public ArrayList<ContainerJourneyInfo> getJourneys() {
		return journeyHistory;
	}
	public void setJourneys(ArrayList<ContainerJourneyInfo> journeyHistory) {
		this.journeyHistory = journeyHistory;
	}
	
	/** Creates a new response object with initial error message
	 * 
	 * @param errorMessage
	 */
	public ResponseObject(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
	/** Creates a new response with no initial error message
	 * 
	 */
	public ResponseObject() {
		
	}
	
	
	/** Gets statusTrackingObject
	 * 
	 * @return statusTrackingObject
	 */
	public DataPoint getStatus() {
		return status;
	}
	/** Sets statusTrackingObject
	 * 
	 * @param status
	 */
	public void setStatus(DataPoint status) {
		this.status = status;
	}
	
	/** This method fetches the error message of the response
	 * 
	 * @return error message - a string 
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/** This method sets the error message of the responseObject
	 * 
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	

}
