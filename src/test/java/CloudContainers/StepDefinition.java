package CloudContainers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import CloudContainers.Client;
import CloudContainers.Database;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition{
	

	Client client;
	Client client2;
	Client client3;
//	Client c2 = new Client("Bob2","002","bigman2@dtu.dk","22","male","10101011");
//	Client c3 = new Client("Bob3","003","bigman3@dtu.dk","22","male","10101012");
	Database database = new Database();
	
	
	@Given("A none existing client")
	public void a_none_existing_client() {
	    assertTrue(!(database.exist(client)));
	}
	
	
	@When("Informations is entered")
	public void informations_is_entered() {
	    client = new Client("Bob1","001","bigman1@dtu.dk","22","male","10101010");
		database.addClient(client);

	}
	
	@Then("display success message")
	public void display_success_message() {
		assertTrue((database.exist(client)));
	    System.out.println("works");
	}
	
	@Given("existing client")
	public void existing_client() {
		client = new Client("Bob1","001","bigman1@dtu.dk","22","male","10101010");
		database.addClient(client);
		
		assertTrue((database.exist(client)));
	}
	
	@When("repeated information is entered")
	public int repeated_information_is_entered() {
		client2 = new Client("Bob1","001","bigman1@dtu.dk","22","male","10101010");
		int count1 = database.getCount();
		database.addClient(client2);
		return count1;
		
	

	}

	@Then("error message is thrown")
	public void error_message_is_thrown() {
		int count1 = repeated_information_is_entered();
		int count2 = database.getCount();
		assertEquals(count1,count2);
		
		
	    System.out.println("Does not work: existing client");
	}
	@When("information with missing parameters")
	public void information_with_missing_parameters() {
		client3 = new Client("Bob1","001","bigman1@dtu.dk","","male","10101010");
		
		
	    
	}

	@Then("missing parameter error message is thrown")
	public void missing_parameter_error_message_is_thrown() {
		boolean b = (client3.getName()=="") 
				|| (client3.getAge()=="") 
				|| (client3.getEmail() == "")			
				|| (client3.getGender()=="") 
				|| (client3.getNumber()=="");
		assertTrue(b);
		System.out.println("Does not work: Missing information");
		
	 
	}
	
	

}