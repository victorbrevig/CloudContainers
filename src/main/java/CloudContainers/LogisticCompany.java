package CloudContainers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.validator.GenericValidator;

public class LogisticCompany {
	private String name;
	private int companyID;
	private Database db;
	private JourneyDatabase journeys;
	private int clientIDgen = 1;
	private int amountOfContainers;
	
	Map<Container,Client> containerMap;
	
	private int journeyIDgen = 1;
	Map<Container,Journey> journeyMap;
	
	public LogisticCompany(String name, int companyID, int amountOfContainers) {
		super();
		this.name = name;
		this.companyID = companyID;
		this.db = new Database();
		this.journeys = new JourneyDatabase();
		this.amountOfContainers = amountOfContainers;
		
		this.containerMap = new HashMap<Container,Client>();
		this.journeyMap = new HashMap<Container,Journey>();
		
		for (int i=0; i<amountOfContainers;i++) {
			containerMap.put(new Container(i), new Client("",0,"","","",0,this.name));
			//containers.add(new Container(i));
		}
		
	}
	
	public void createJourney(String portOfOrigin, String destination) {
		Journey journey = new Journey(journeyIDgen, portOfOrigin, destination, this.name);
		journeyIDgen++;
		journeys.add(journey);
	}
	
	public void addContainer() {
		this.amountOfContainers++;
		containerMap.put(new Container(amountOfContainers), new Client("",0,"","","",0,this.name));
	}
	
	public int getClientIDgen() {
		return clientIDgen;
	}
	
	public Database getDb() {
		return db;
	}
	public void setDb(Database db) {
		this.db = db;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCompanyID() {
		return companyID;
	}

	public Client findClient(int id) {
		return db.getClient(id);
	}
	
	public Client findClient(String email) {
		return db.getClient(email);
	}
	
	public boolean removeClient(int clientID) {
		return db.remove(db.getClient(clientID));
	}
	public boolean removeClient(String email) {
		return db.remove(db.getClient(email));
	}
	// Does not work
	public boolean exist(Client client) {
		return db.contains(client);
	}
	public boolean exist(int clientID) {
		return db.contains(db.getClient(clientID));
	}
	public boolean exist(String email) {
		return db.contains(db.getClient(email));
	}
	public boolean existN(int number) {
		return db.contains(db.getClient(number));
	}
	public boolean exist(Container container) {
		return containerMap.containsKey(container);
	}
	public boolean existJ(int journeyID) {
		return journeys.contains(journeys.getJourney(journeyID));
	}
	

	
	public void validBirthdate(String birthdate) throws invalidBirthdateException {
		if (!GenericValidator.isDate(birthdate, "dd-MM-yyyy", true)) {
			throw new invalidBirthdateException(birthdate + " is not a valid birthdate");
		}
	}
	
	public boolean correctEmail(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	public void validEmail(String email) throws invalidEmailException {
		if (!correctEmail(email)) {
			throw new invalidEmailException(email + " is not a valid email");
		}
	}
	
	public void validParameters(String name, String email, String birthdate, String gender, int number) 
			throws invalidParameterException{
		if (    (name=="") 
				|| (birthdate=="") 
				|| (email == "")			
				|| (gender=="") 
				|| (number==0)) {
			throw new invalidParameterException("There is a missing parameter");
		}
	}
	
	public ResponseObject validInput(String name, String email, String birthdate, String gender, int number) {
		ResponseObject response;
		try {
			validParameters(name,email,birthdate,gender,number);
			validEmail(email);
			validBirthdate(birthdate);
			if (!this.exist(email)) {
				response = new ResponseObject("Non-existing client");
			}else {
				response = new ResponseObject("existing client");
			}
		}catch(invalidEmailException err){
			response = new ResponseObject(err.getMessage());
		
		}catch(invalidBirthdateException err){
			response = new ResponseObject(err.getMessage());
			
		}catch(invalidParameterException err){
			response = new ResponseObject(err.getMessage());
		}
		return response;
		
	}


	
	public ResponseObject newClient(String name, String email, String birthdate, String gender, int number) {
			ResponseObject response;
			response = validInput(name,email,birthdate,gender,number);
			if (response.getErrorMessage().equals("Non-existing client")) {
				int clientID = this.clientIDgen++;
				Client client = new Client(name,clientID,email,birthdate,gender,number,this.name);
				this.db.add(client);
				response.setErrorMessage("Client was successfully added");
			}return response;
	}
	
	public ResponseObject updateClient(String oldEmail,String email) {
		ResponseObject response = new ResponseObject();
		Client c = this.findClient(oldEmail);
		response = validInput(c.getName(),email,c.getBirthdate(),c.getGender(),c.getNumber());
		if (response.getErrorMessage().equals("Non-existing client")) {
			this.findClient(oldEmail).setEmail(email);
			response.setErrorMessage("Email has been updated");}
		return response;}
	
	public ResponseObject updateClient(String oldEmail,int number) {
		ResponseObject response = new ResponseObject();
		if (!this.existN(number)) {
			this.findClient(oldEmail).setNumber(number);
			response.setErrorMessage("Phone number has been updated");
		}else {
			response.setErrorMessage("existing client");
		}
		return response;}

	public Container findFreeContainer() {
//		Container 0, is returned if no vacant containers are available
		Container container = new Container(0);
		
		for (Container c: containerMap.keySet()) {
			if (!c.isOwned()) {
				return c;
			}
		}return container;
	}

	
	public ResponseObject allocateContainer(String email,Container container) {
		ResponseObject response = new ResponseObject("Container succesfully allocated");
		boolean existClient = this.exist(email);
		boolean existContainer = this.containerMap.containsKey(container);
		boolean owned = container.isOwned();
		
		if(!existClient) {
			response.setErrorMessage("Client does not exist");
		}
		else if (!existContainer) {
			response.setErrorMessage("Container does not exist");
		}
		else if (owned) {
			response.setErrorMessage("This container is already owned by a client");
		}
		else {
			container.setOwned(true);
			containerMap.replace(container,this.findClient(email));
			
		}
		return response;
	}
	
	public ResponseObject containerToJourney(Client client, Container container, Journey journey, String content) {
		ResponseObject response = new ResponseObject("Container successfully added to journey");
		// Conditions to check
		// Client exists OR db.contains(client);
		boolean c1 = exist(client.getEmail());
		// Container belongs to client
		boolean c2 = (containerMap.get(container)).equals(client);
		// Journey exists
		boolean c3 = exist(journey.getJourneyID());
		
		if (c1 && c2 && c3) {
			container.setOnJourney(true);
			container.setContent(content);
			journeyMap.put(container,journey);
		}
		else if (!c1) {
			response.setErrorMessage("Client does not exist");
		}
		else if (!c2) {
			response.setErrorMessage("Container does not belong to client");
		}
		else if (!c3) {
			response.setErrorMessage("Journey does not exist");
		}
		
		return response;
	}
	
	
//	public static void main(String[] args) {
//
//		ResponseObject response;
//		LogisticCompany lc = new LogisticCompany("Maersk",1,100);
//		lc.newClient("Jenny","email@dtu.dk","11-10-1998","female",12345678);
//		Container container = lc.findFreeContainer();
//		System.out.println(container.isOwned());
//		lc.allocateContainer("email@dtu.dk",container);
//		System.out.println(lc.containerMap.get(container).getEmail());
//		System.out.println(container.isOwned());
//	}
}



class invalidEmailException extends Exception { 
    public invalidEmailException(String errorMessage) {
        super(errorMessage);
    }
    
}

class invalidBirthdateException extends Exception { 
    public invalidBirthdateException(String errorMessage) {
        super(errorMessage);
    }
}

class invalidParameterException extends Exception { 
    public invalidParameterException(String errorMessage) {
        super(errorMessage);
    }
}


