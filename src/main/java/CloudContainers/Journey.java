package CloudContainers;
import java.util.HashSet;

public class Journey {
	private int journeyID;
	private String portOfOrigin;
	private String destination;
	private LogisticCompany company;
	private HashSet<Container> containers;
	public String getPortOfOrigin() {
		return portOfOrigin;
	}
	
	
	public Journey(int journeyID, String portOfOrigin, String destination, LogisticCompany company,
			HashSet<Container> containers) {
		super();
		this.journeyID = journeyID;
		this.portOfOrigin = portOfOrigin;
		this.destination = destination;
		this.containers = containers;
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
	public HashSet<Container> getContainers() {
		return containers;
	}
	public void setContainers(HashSet<Container> containers) {
		this.containers = containers;
	}
	
	public LogisticCompany getCompany() {
		return company;
	}
	
	public void addContainer(Container container) {
		containers.add(container);
	}
	
	
}
