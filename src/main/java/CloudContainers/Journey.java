package CloudContainers;


public class Journey {
	private int journeyID;
	private String portOfOrigin;
	private String destination;
	private String company;
	private boolean isStarted;
	
	
	
	public boolean isStarted() {
		return isStarted;
	}
	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	public String getCompany() {
		return company;
	}
	public int getJourneyID() {
		return journeyID;
	}
	public String getPortOfOrigin() {
		return portOfOrigin;
	}
	
	public Journey(int journeyID, String portOfOrigin, String destination, String company) {
		super();
		this.journeyID = journeyID;
		this.portOfOrigin = portOfOrigin;
		this.destination = destination;
		this.company = company;
	}


	public boolean equals(Journey journey) {
		return this.getJourneyID() == journey.getJourneyID();
	}



	public void setPortOfOrigin(String portOfOrigin) {
		this.portOfOrigin = portOfOrigin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	public void print() {
		System.out.println("JourneyID: " + this.getJourneyID());
		System.out.println("Company: " + this.getCompany());
		System.out.println("PortOfOrigin: " + this.getPortOfOrigin());
		System.out.println("Destination: " + this.getDestination());
	}

	
	
	
}
