package Cloud.model;

import java.util.ArrayList;




/** Class to represent a response that contains error messages which can be used for testing and visual response
 * 
 * @author Gustav Als
 * @author Victor Brevig
 */
public class ResponseObject {
	private String errorMessage;
	private ArrayList<Journey> journeyHistForClient;

	public ArrayList<Journey> getJourneyHist() {
		return journeyHistForClient;
	}
	
	public void setJourneyHistForClient(ArrayList<Journey> journeyHist) {
		this.journeyHistForClient = journeyHist;
	}

	public ResponseObject(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public ResponseObject() {}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

}
