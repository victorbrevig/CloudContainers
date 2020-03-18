package CloudContainers;
import static org.junit.Assert.assertTrue;

import CloudContainers.Client;
import CloudContainers.Database;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition{
	

//	Client c1 = new Client("Bob1","001","bigman1@dtu.dk","22","male","10101010");
//	Client c2 = new Client("Bob2","002","bigman2@dtu.dk","22","male","10101011");
//	Client c3 = new Client("Bob3","003","bigman3@dtu.dk","22","male","10101012");
	Database database = new Database();
	Client client = new Client();
	
	@Given("A none existing client")
	public void a_none_existing_client() {
	    assertTrue(!(database.exist(client)));
	}
	
	@Given("client with a name {string}")
	public void client_with_a_name(String string) {
//		database.addClient(c1);database.addClient(c2);database.addClient(c3);
		client.setName(string);
	}
	
	@Given("client with a mail {string}")
	public void client_with_a_mail(String string) {
		client.setEmail(string);
	}
	
	
	@Given("client with a phone number {string}")
	public void client_with_a_phone_number(String string) {
		client.setNumber(string);
	}
	
	@Given("client with an age {string}")
	public void client_with_an_age(String string) {
		client.setAge(string);
	}
	
	
	@Given("client with a gender {string}")
	public void client_with_a_gender(String string) {
		client.setGender(string);
	}
	
	@When("Informations is entered")
	public void informations_is_entered() {
		database.addClient(client);
	    throw new cucumber.api.PendingException();
	}
	
	@Then("display success message")
	public void display_success_message() {
		assertTrue((database.exist(client)));
	    System.out.println("works");
	    throw new cucumber.api.PendingException();
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