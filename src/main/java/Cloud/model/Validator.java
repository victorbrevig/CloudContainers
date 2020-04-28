package Cloud.model;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.Entity;

import org.apache.commons.validator.GenericValidator;
import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * @author Gustav Als
 * @author Victor Brevig
 *
 */
public class Validator {

	@JsonIgnore
	private LogisticCompany company;
	
	/** Creates a new validator object belonging to a company.
	 * 
	 * @param company
	 */
	public Validator(LogisticCompany company) {
		super();
		this.company = company;
	}
	
	/** This method fetches the company that the validator belongs to.
	 * 
	 * @return logistic company - type LogisticCompany
	 */
	public LogisticCompany getCompany() {
		return company;
	}

	/** This method sets the company that the validator belongs to.
	 * 
	 * @param company - type LogisticCompany
	 */
	public void setCompany(LogisticCompany company) {
		this.company = company;
	}

	/** Static method to check if birth date has correct format
	 * 
	 * @param birthdate - type String
	 * @return boolean
	 */
	public static boolean validBirthdate(String birthdate) {
		return GenericValidator.isDate(birthdate, "dd-MM-yyyy", true);
	}
	
	/** Static method the check if an email is valid
	 * 
	 * @param email - type String
	 * @return boolean
	 */
	public static boolean validEmail(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	/** Method to check if any parameters used in client registration are empty 
	 * 
	 * @param name - type String
	 * @param email - type String
	 * @param birthdate - type String
	 * @param gender - type String
	 * @param number - type Integer
	 * @return boolean
	 */
	public static boolean emptyParameters(String name, String email, String birthdate, String gender, int number) {
		return !(   (name.equals("")) 
				|| (birthdate.equals("")) 
				|| (email.equals(""))			
				|| (gender.equals("")) 
				|| (number==0)   );
	}
	
	/** Method to check if a phone number is valid
	 * 
	 * @param number
	 * @return boolean
	 */
	public boolean validPhoneNumber(int number) {
		int length = String.valueOf(number).length();
		return (length == 8);
	}
	
	/** Checks if new client info is valid 
	 * 
	 * @param name
	 * @param email
	 * @param birthdate
	 * @param gender
	 * @param number
	 * @return response - type ResponseObject
	 */
	public ResponseObject validInput(String name, String email, String birthdate, String gender, int number) {
	ResponseObject response = new ResponseObject();
	
		if (!emptyParameters(name,email,birthdate,gender,number)) {
			response = new ResponseObject("There is a missing parameter");
			return response;
		}
		else if (!validEmail(email)) {
			response = new ResponseObject(email + " is not a valid email");
			return response;
		}
		else if (!validBirthdate(birthdate)) {
			response = new ResponseObject(birthdate + " is not a valid birthdate");
			return response;
		}
		else if (!validPhoneNumber(number)) {
			response = new ResponseObject(number + " is not a valid phone number");
			return response;
		}
		
		
		if (!company.clientExists(email)) {
			response = new ResponseObject("Non-existing client");
		}
		else {
			response = new ResponseObject("Existing client");
		}
		return response;
	
	}
	
	/** Checks if a client has access to a given container
	 * 
	 * @param client
	 * @param container
	 * @return boolean
	 */
	public boolean clientHasAccess(Client client, Container container) {
		return container.getAccessClients().stream().anyMatch(c -> c.equals(client));
	}
	
}
