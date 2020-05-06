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

	/**Needs to validate the format of the entered birthdate
	 * 
	 * @param birthdate - type String
	 * @return boolean
	 */
	public static boolean validBirthdate(String birthdate) {
		return GenericValidator.isDate(birthdate, "dd-MM-yyyy", true);
	}
	
	/**Used to validate format of the entered email
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
	
	public static boolean emptyParameters(String name, String email, String birthdate, String gender, long number) {
		return !(   (name.equals("")) 
				|| (birthdate.equals("")) 
				|| (email.equals(""))			
				|| (gender.equals("")) 
				|| (number==0)   );
	}
	
	public static boolean validPhoneNumber(long number) {
		int length = String.valueOf(number).length();
		return (length == 8);
	}

	public static ResponseObject validInput(String name, String email, String birthdate, String gender, long number) {
	ResponseObject response = new ResponseObject();
	
		if (!emptyParameters(name,email,birthdate,gender,number)) {
			response.setErrorMessage("There is a missing parameter");
			return response;
		}
		else if (!validEmail(email)) {
			response.setErrorMessage(email + " is not a valid email");
			return response;
		}
		else if (!validBirthdate(birthdate)) {
			response.setErrorMessage(birthdate + " is not a valid birthdate");
			return response;
		}
		else if (!validPhoneNumber(number)) {
			response.setErrorMessage(number + " is not a valid phone number");
			return response;
		}
		
		response.setErrorMessage("Valid");
		return response;
	
	}

	public static boolean clientHasAccess(Client client, Container container) {
		return container.getAccessClients().stream().anyMatch(c -> c.equals(client));
	}

	public static ResponseObject validJourney(Journey journey) {
		ResponseObject response = new ResponseObject();
		
		if (journey.portEqualsDestination()) {
			response.setErrorMessage("Destination cannot be the same as port of origin");
			return response;
		}
		else if (journey.durationOutOfBounds()) {
			response.setErrorMessage("Duration is out of bounds");
			return response;
		}
		response.setErrorMessage("Valid");
		return response;
	}
	
}
