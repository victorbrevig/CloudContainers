package CloudContainers;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.validator.GenericValidator;

public class Validator {
	
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
	
}
