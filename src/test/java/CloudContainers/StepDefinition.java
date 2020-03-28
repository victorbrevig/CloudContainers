package CloudContainers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import CloudContainers.Client;
import CloudContainers.Database;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition{
	

	Client client2;
	Client client3;
	ResponseObject response;
	LogisticCompany lc = new LogisticCompany("Maersk",1,100);
	
	
	@Given("A none existing client")
	public void a_none_existing_client(){
	    assertFalse(lc.exist(1));
	}
	
	
	@When("Informations is entered")
	public void informations_is_entered() {
	}
	
	@Then("display success message")
	public void display_success_message() {
		response = lc.newClient("Bob1","bigman1@dtu.dk","11-02-1994","male",10101010);
		assertTrue(lc.exist(1));
		assertEquals(response.getErrorMessage(),"Client was successfully added");
	}
	
	@Given("existing client")
	public void existing_client() {
		lc.newClient("Bob1","bigman1@dtu.dk","11-04-1995","male",10101010);
		assertTrue(lc.exist(1));
	}
	
	@When("repeated information is entered")
	public void repeated_information_is_entered() {
		response = lc.newClient("Bob2","bigman1@dtu.dk","11-04-1995","male",10101010);
	}

	@Then("error message is thrown")
	public void error_message_is_thrown() {
		assertEquals(response.getErrorMessage(),"existing client");
		
	}
	
	@When("information with missing parameters")
	public void information_with_missing_parameters() {
		response = lc.newClient("Bob3","bigman3@dtu.dk","","male",10101010);
	}

	@Then("missing parameter error message is thrown")
	public void missing_parameter_error_message_is_thrown() {
		assertEquals(response.getErrorMessage(),"There is a missing parameter");
		
	}
	
	@Given("A logistic company")
	public void a_logistic_company() {
		
	}

	@When("invalid email {string} is entered")
	public void invalid_email_is_entered(String string) {
		response = lc.newClient("Bob4",string,"11-04-1995","male",10101010);
	}

	@Then("invalid email error is displayed for {string}")
	public void invalid_email_error_is_displayed_for(String string) {
		assertEquals(response.getErrorMessage(),string + " is not a valid email");
	}

	@When("invalid birthdate {string} is entered")
	public void invalid_birthdate_is_entered(String string) {
		response = lc.newClient("Bob5","bigman5@dtu.dk",string,"male",10101010);
	}

	@Then("invalid birtdate error is displayed for {string}")
	public void invalid_birtdate_error_is_displayed_for(String string) {
	    assertEquals(response.getErrorMessage(),string + " is not a valid birthdate");
	}

	
	// _________________________________________Update Info________________________________________________
	
	Client client1 = new Client("Karsten",1,"smallmoney123@gmail.com","24-05-1998","male",10101010);
	@Given("A client with email {string}")
	public void a_client_with_email(String string) {
		lc.newClient("Karsten",string,"24-05-1998","male",10101010);
		
	}
	
	@When("New email entered as {string}")
	public void new_email_entered_as(String string) {
		response = lc.updateClient(client1.getEmail(),string);
	}
	
	@Then("Display email update success message")
	public void display_email_update_success_message() {
		assertEquals(response.getErrorMessage(),"Email has been updated");
	}

	@Given("A client with phone number {int}")
	public void a_client_with_phone_number(Integer int1) {
		lc.newClient("Jenny","bigstonks123@gmail.com","27-10-1987","female",int1);
	}

	@When("A client with email {string} New phone number entered as {int}")
	public void a_client_with_email_new_phone_number_entered_as(String email,Integer int1) {
		response = lc.updateClient(email, int1);
	}

	@Then("Display phonenumber update success message")
	public void display_phonenumber_update_success_message() {
		System.out.println(response.getErrorMessage());
		assertEquals(response.getErrorMessage(),"Phone number has been updated");
		
	}
	
	// _________________________________________Search Client________________________________________________

	
	Client client4;
	
	@Given("A client with the email {string}")
	public void a_client_with_the_email(String string) {
		client4 = new Client("Jenny",1,string,"02-02-1998","female",10101010);
		lc.newClient("Jenny",string,"02-02-1998","female",10101010);
	}

	@When("The logistic company searches for {string}")
	public Client the_logistic_company_searches_for(String string) {
	    return lc.findClient(string);
	}

	@Then("The client is returned for {string} and a succes message is displayed")
	public void the_client_is_returned_for_and_a_succes_message_is_displayed(String string) {
		Client c = the_logistic_company_searches_for(string);
		assertTrue(lc.exist(c.getClientID()));
		c.print();
	}

	
	
	@Given("A client with the clientID {int}")
	public void a_client_with_the_clientID(Integer int1) {
		client4 = new Client("Jenny",1,"email123@mail.dk","11-10-1992","female",10101010);
		lc.newClient("Jenny","email123@mail.dk","11-10-1992","female",10101010);
	}

	@When("The logistic company searches for {int}")
	public Client the_logistic_company_searches_for(Integer int1) {
		return lc.findClient(int1);
	}

	@Then("The client is returned for {int} and a succes message is displayed")
	public void the_client_is_returned_for_and_a_succes_message_is_displayed(Integer int1) {
		Client c = the_logistic_company_searches_for(int1);
		assertTrue(lc.exist(c.getClientID()));
		c.print();
	}
	
	@Given("A none existing client for email search")
	public void a_none_existing_client_for_email_search() {
	}

	@Given("A none existing client for ID search")
	public void a_none_existing_client_for_ID_search() {
	   
	}
	@When("The logistic company searches {string}")
	public void the_logistic_company_searches(String string) {
		lc.findClient(string);
	}

	@When("The logistic company searches {int}")
	public void the_logistic_company_searches(Integer int1) {
		lc.findClient(int1);
	}
	
	@Then("A failure message is returned for {int}")
	public void a_failure_message_is_returned_for(Integer int1) {
		assertTrue(lc.findClient(int1).getClientID() == 0);
	}
	@Then("A failure message is returned for {string}")
	public void a_failure_message_is_returned_for(String string) {
		assertTrue(lc.findClient(string).getClientID() == 0);
		
	}

// _________________________________________Remove Client________________________________________________
	
	
	
	@Given("A logistic company that has a client with clientID {int}")
	public void a_logistic_company_with_a_client_with_clientID(Integer int1) {
		lc.newClient("Jenny","j1@gmail.com","11-10-1998","female",12345678);
		assertTrue(lc.exist(int1));
	}

	@When("the logistic company removes a client with the clientID {int}")
	public void the_logistic_company_removes_a_client_with_the_clientID(Integer id) {
	    lc.removeClient(id);
	}
	
	@Given("A logistic company that has a client with an email {string}")
	public void a_logistic_company_that_has_a_client_with_an_email(String string) {
		lc.newClient("Jenny",string,"11-10-1998","female",12345678);
		assertTrue(lc.exist(string));
	}

	@When("the logistic company removes a client with an email {string}")
	public void the_logistic_company_removes_a_client_with_an_email(String string) {
	    lc.removeClient(string);
	}

	@Then("the client is deleted and succes message is displayed clientID {int}")
	public void the_client_is_deleted_and_succes_message_is_displayed_clientID(Integer int1) {
	    assertFalse(lc.exist(int1));
//	    Add response object message
	}

	@Then("the client is deleted and succes message is displayed email {string}")
	public void the_client_is_deleted_and_succes_message_is_displayed_email(String string) {
	    assertFalse(lc.exist(string));
	}


	@Given("A none existing client for remove")
	public void a_none_existing_client_for_remove() {
	    
	}

	@When("the logistic company removes a client with clientID {int}")
	public void the_logistic_company_removes_a_client_with_clientID(Integer int1) {
	    lc.removeClient(int1);
	}
	
	@Then("A remove failure message is returned for {string}")
	public void a_remove_failure_message_is_returned_for(String string) {
	    assertFalse(lc.removeClient(string));
	}

	@Then("A remove failure message is returned for {int}")
	public void a_remove_failure_message_is_returned_for(Integer int1) {
		assertFalse(lc.removeClient(int1));
	}
	
	
}