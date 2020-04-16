package CloudContainers;

import java.util.ArrayList;
import java.util.HashSet;

import org.javatuples.Triplet;

// New version
public class Client {
	
	private String name;
	private int clientID;
	private String email;
	private String birthdate;
	private String gender;
	private int number;
	private LogisticCompany company;
	private String password;
	private Validator validator;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Client(String name, String email, String birthdate, String gender, int number, String password) {
		super();
		this.name = name;
		this.email = email;
		this.birthdate = birthdate;
		this.gender = gender;
		this.number = number;
		this.password = password;
	}
	
	public LogisticCompany getCompany() {
		return company;
	}

	public void setCompany(LogisticCompany company) {
		this.company = company;
		this.validator = new Validator(company);
	}

	public boolean equals(Client client) {
		if (client instanceof Client) {
			return (this.getEmail()).equals(client.getEmail());
		}
		return false;
	}
	
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.clientID;
		return result;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	public ResponseObject updateClient(String email) {
		ResponseObject response = new ResponseObject();
		// Valid new email
		response = validator.validInput(getName(),email,getBirthdate(),getGender(),getNumber());
		// Check if new email belongs to a client already
		if (response.getErrorMessage().equals("Non-existing client")) {
			this.setEmail(email);
			response.setErrorMessage("Email has been updated");
			}
		return response;
	}
	public ResponseObject updateClient(int number) {
		ResponseObject response = new ResponseObject();
		// Valid new phone number
		boolean validNumber = validator.validNumber(number);
		if (validNumber) {
			this.setNumber(number);
			response.setErrorMessage("Phone number has been updated");
			}
		else {
			response.setErrorMessage(number + " is not a valid phone number");
		}
		return response;
	}
	
	public ResponseObject containerToJourney(Container container, Journey journey, String content) {
		ResponseObject response = new ResponseObject("Container successfully added to journey");
		// Conditions to check

		boolean belongsToClient = container.getOwner().equals(this);
		
		if (!belongsToClient) {
			response.setErrorMessage("Container does not belong to client");
			return response;
		}
		
		container.setContent(content);
		container.addJourney(journey);
		
		return response;
	}
	
	

	
	
	public void print() {
		System.out.println("Name:" + this.getName());
		System.out.println("Company: " + this.getCompany());
		System.out.println("Client ID:" + this.getClientID());
		System.out.println("Email:" + this.getEmail());
		System.out.println("Birthdate:" + this.getBirthdate());
		System.out.println("Gender:" + this.getGender());
		System.out.println("Phone number:" + this.getNumber());
		
	}
	

}


