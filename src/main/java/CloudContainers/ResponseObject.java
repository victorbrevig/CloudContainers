package CloudContainers;

import java.util.ArrayList;
import java.util.HashSet;

import org.javatuples.Triplet;

public class ResponseObject {
	private String errorMessage;
	private statusTrackingObject status;
	// All journeys
	private ArrayList<Triplet<Journey,Client,HashSet<Client>>> journeyHistory;
	
	private ArrayList<Journey> journeyHistForClient;
	
	public ArrayList<Journey> getJourneyHist() {
		return journeyHistForClient;
	}
	public void setJourneyHistForClient(ArrayList<Journey> journeyHist) {
		this.journeyHistForClient = journeyHist;
	}
	public ArrayList<Triplet<Journey,Client,HashSet<Client>>> getJourneys() {
		return journeyHistory;
	}
	public void setJourneys(ArrayList<Triplet<Journey,Client,HashSet<Client>>> journeyHistory) {
		this.journeyHistory = journeyHistory;
	}
	public ResponseObject(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	public ResponseObject() {
		
	}
	
	public statusTrackingObject getStatus() {
		return status;
	}
	public void setStatus(statusTrackingObject status) {
		this.status = status;
	}
	

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	

}
