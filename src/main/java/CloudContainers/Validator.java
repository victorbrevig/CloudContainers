package CloudContainers;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.validator.GenericValidator;

public class Validator {

	private LogisticCompany company;
	
	
	public Validator(LogisticCompany company) {
		super();
		this.company = company;
	}
	
	public LogisticCompany getCompany() {
		return company;
	}

	public void setCompany(LogisticCompany company) {
		this.company = company;
	}

	public boolean validBirthdate(String birthdate) {
		return GenericValidator.isDate(birthdate, "dd-MM-yyyy", true);
	}
	
	public boolean validEmail(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	
	public boolean validParameters(String name, String email, String birthdate, String gender, int number) {
		return !(   (name.equals("")) 
				|| (birthdate.equals("")) 
				|| (email.equals(""))			
				|| (gender.equals("")) 
				|| (number==0)   );
	}
	
	
	public boolean validNumber(int number) {
		int length = String.valueOf(number).length();
		return (length == 8);
	}
	public ResponseObject validInput(String name, String email, String birthdate, String gender, int number) {
	ResponseObject response = new ResponseObject();
	
		if (!validParameters(name,email,birthdate,gender,number)) {
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
		else if (!validNumber(number)) {
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
	
}
