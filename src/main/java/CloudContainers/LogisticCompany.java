package CloudContainers;

import java.util.ArrayList;
import java.util.HashSet;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.validator.GenericValidator;

public class LogisticCompany {
	private String name;
	private int companyID;
	private Database db;
	private ArrayList<Container> containers;
	
	
	public LogisticCompany(String name, int companyID) {
		super();
		this.name = name;
		this.companyID = companyID;
		this.db = new Database();
		
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
	
	public boolean exist(int clientID) {
		return db.contains(db.getClient(clientID));
	}
	public boolean exist(String email) {
		return db.contains(db.getClient(email));
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
	
	public void validParameters(String name, int clientID, String email, String birthdate, String gender, int number) 
			throws invalidParameterException{
		if (    (name=="") 
				|| (birthdate=="") 
				|| (email == "")			
				|| (gender=="") 
				|| (number==0)) {
			throw new invalidParameterException("There is a missing parameter");
		}
	}
	
	public void validInput(String name, int clientID, String email, String birthdate, String gender, int number) 
			throws invalidEmailException, invalidParameterException, invalidBirthdateException {
		validParameters(name,clientID,email,birthdate,gender,number);
		validEmail(email);
		validBirthdate(birthdate);
	}
	
	public ResponseObject newClient(String name, int clientID, String email, String birthdate, String gender, int number) {
		ResponseObject response;
		try {
//			Make a random generator for clientID
			validInput(name,clientID,email,birthdate,gender,number);
			Client client = new Client(name,clientID,email,birthdate,gender,number);
			if (!this.exist(clientID)) {
				this.db.add(client);
				response = new ResponseObject("Client was successfully added");
			}else {
				response = new ResponseObject("Client already exists");
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
	
	public ResponseObject updateClient(Client client,String email) {
		ResponseObject response;
		Client tempClient = client;
		tempClient.setEmail(email);
		this.removeClient(client.getClientID());
		response = this.newClient(client.getName(), client.getClientID(), email, 
				client.getBirthdate(), client.getGender(), client.getNumber());
		if (response.getErrorMessage().equals("Client was successfully added")) {
			response.setErrorMessage("Email has been updated");
		}
		return response;	 
	}
	public ResponseObject updateClient(Client client,int number) {
		ResponseObject response;
		Client tempClient = client;
		tempClient.setNumber(number);
		this.removeClient(client.getClientID());
		response = this.newClient(client.getName(), client.getClientID(), client.getEmail(), 
				client.getBirthdate(), client.getGender(), number);
		if (response.getErrorMessage().equals("Client was successfully added")) {
			response.setErrorMessage("Phone number has been updated");
		}
		return response;	 
	}
//	public static void main(String[] args) {
//		LogisticCompany lc = new LogisticCompany("Maersk",1);
//		lc.newClient("Bob1",1,"bigman1@dtu.dk","11-02-2021","male","10101010");
//		Client client1 = new Client("Bob1",1,"bigman1@dtu.dk","11-02-2021","male","10101010");
//		Client tC = lc.findClient("bigman1@dtu.dk");
//		tC.setEmail("smallman@dtu.dk");
//		System.out.println(lc.updateClient(client1, tC));
//		
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

