package Cloud.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import Cloud.model.Client;
import Cloud.model.Container;
import Cloud.model.Journey;
import Cloud.model.LogisticCompany;
import Cloud.model.ResponseObject;
import Cloud.model.Validator;



/**Represents a client entity
 * @Author: Victor
 * @Author Gustav
 */
// New version
public class Client {

	private int clientID;

	private String name;

	private String email;

	private String birthdate;

	private String gender;

	private long number;


	private String password;



	
	
	
	/**Creates a client
	 * @param name
	 * @param email
	 * @param birthdate
	 * @param gender
	 * @param number
	 * @param password
	 */
	
	public Client() {
		
	}
	public Client(String name, String email, String birthdate, String gender, long number, String password) {
		super();
		this.name = name;
		this.email = email;
		this.birthdate = birthdate;
		this.gender = gender;
		this.number = number;
		this.password = password;
	}
	
	/**Gets the clients password
	 * @return password
	 */
	
	public String getPassword() {
		return password;
	}
	/**Sets the clients password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**Gets clients name
	 * 
	 * @return name
	 */
	
	public String getName() {
		return name;
	}
	
	/**Gets clients clientID
	 * 
	 * @return clientID
	 */
	
	public int getClientID() {
		return clientID;
	}
	
	/**Sets clients clientID
	 * 
	 * @param clientID
	 */
	
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	/**Gets clients email
	 * 
	 * @return email
	 */
	
	public String getEmail() {
		return email;
	}
	
	/**Sets clients email
	 * 
	 * @param email
	 */
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**Get clients birthdate
	 * 
	 * @return birthdate
	 */
	
	public String getBirthdate() {
		return birthdate;
	}
	
	/**Gets clients gender	
	 * 
	 * @return gender
	 */
	
	public String getGender() {
		return gender;
	}
	
	/**Gets clients number
	 * 
	 * @return number
	 */
	
	public long getNumber() {
		return number;
	}
	
	/**Sets clients number
	 * 
	 * @param number
	 */
	
	public void setNumber(long number) {
		this.number = number;
	}
	
	/**Compares client with another client using email
	 * 
	 * @param client
	 * @return boolean
	 */
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Client) {
			return (this.getClientID() == ((Client) o).getClientID());
		}
		return false;
	}
	
	/** HashCode implementation for checking equality of client objects
	 * 
	 */
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.clientID;
		return result;
	}
	
	/** Updates clients email
	 * 
	 * @param email
	 * @return response
	 */
	
	public ResponseObject updateClient(String email) {
		ResponseObject response = new ResponseObject();
		// Valid new email
		response = Validator.validInput(getName(),email,getBirthdate(),getGender(),getNumber());
		// Check if new email belongs to a client already
		if (response.getErrorMessage().equals("Valid")) {
			this.setEmail(email);
			response.setErrorMessage("Email has been updated");
			}
		return response;
	}
	
	/** Updates clients number
	 * 
	 * @param number
	 * @return response
	 */
	
	public ResponseObject updateClient(long number) {
		ResponseObject response = new ResponseObject();
		
		if (Validator.validPhoneNumber(number)) {
			this.setNumber(number);
			response.setErrorMessage("Valid Phone number");
			}
		else {
			response.setErrorMessage(number + " is not a valid phone number");
		}
		return response;
	}
	
	/** Puts a container belonging to client on a journey
	 * 
	 * @param container
	 * @param journey
	 * @param content
	 * @return response
	 */
	
	public ResponseObject containerToJourney(Container container, Journey journey) {
		ResponseObject response = new ResponseObject("Container successfully added to journey");
		
		if (!container.belongsToClient(this)) {
			response.setErrorMessage("Container does not belong to client");
			return response;
		}
		
		container.addJourney(journey);
		return response;
	}

	
	/** This method removes the container from the client
	 * 
	 * @param container
	 */
	public void removeContainer(Container container) {
		
		if (container.belongsToClient(this)) {
			container.setOwner(null);
			container.getAccessClients().remove(this);
		}

		container.getAccessClients().remove(this);
		
	}
	
	
	
}


