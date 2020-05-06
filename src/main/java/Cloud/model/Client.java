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
	
	public Client() {}
	
	public Client(String name, String email, String birthdate, String gender, long number, String password) {
		super();
		this.name = name;
		this.email = email;
		this.birthdate = birthdate;
		this.gender = gender;
		this.number = number;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
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

	public String getGender() {
		return gender;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}
	
	/**Used to compare clients using clientID
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

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.clientID;
		return result;
	}
	
	/**Needs to validate email and then update it
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
	
	/**Needs to validate phone number and then update it
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

	public ResponseObject containerToJourney(Container container, Journey journey) {
		ResponseObject response = new ResponseObject("Container successfully added to journey");
		
		if (!container.belongsToClient(this)) {
			response.setErrorMessage("Container does not belong to client");
			return response;
		}
		
		container.addJourney(journey);
		return response;
	}

	public void removeContainer(Container container) {
		
		if (container.belongsToClient(this)) {
			container.setOwner(null);
			container.getAccessClients().remove(this);
		}

		container.getAccessClients().remove(this);
		
	}
	
	
	
}


