package CloudContainers;

import java.util.ArrayList;

import javafx.util.Pair;

public class ResponseObject {
	private String errorMessage;
	private statusTrackingObject status;
	private ArrayList<Pair<Integer,Integer>> journeys;
	private ArrayList<Journey> journeyHist;
	
	public ArrayList<Journey> getJourneyHist() {
		return journeyHist;
	}
	public void setJourneyHist(ArrayList<Journey> journeyHist) {
		this.journeyHist = journeyHist;
	}
	public ArrayList<Pair<Integer, Integer>> getJourneys() {
		return journeys;
	}
	public void setJourneys(ArrayList<Pair<Integer, Integer>> journeys) {
		this.journeys = journeys;
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
