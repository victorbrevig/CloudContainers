package CloudContainers;
import static org.junit.Assert.assertTrue;

import CloudContainers.Client;
import CloudContainers.Database;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition{
	

	Client client;
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
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("error message is thrown")
	public void error_message_is_thrown() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

}