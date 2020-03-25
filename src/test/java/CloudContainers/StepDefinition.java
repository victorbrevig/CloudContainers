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
	

	Client client1;
	Client client2;
	Client client3;

	LogisticCompany lc = new LogisticCompany("Maersk",1);
	
	
	@Given("A none existing client")
	public void a_none_existing_client(){
		client1 = new Client("Bob1",1,"bigman1@dtu.dk","11-02-1994","male","10101010");
	    assertFalse(lc.exist(1));
	}
	
	
	@When("Informations is entered")
	public void informations_is_entered() {
		client1 = new Client("Bob1",1,"bigman1@dtu.dk","11-02-1994","male","10101010");
	}
	
	@Then("display success message")
	public void display_success_message() {
		lc.newClient("Bob1",1,"bigman1@dtu.dk","11-02-1994","male","10101010");
		assertTrue(lc.exist(1));
	}
	
	@Given("existing client")
	public void existing_client() {
		client1 = new Client("Bob1",1,"bigman1@dtu.dk","11-04-1995","male","10101010");
		lc.newClient("Bob1",1,"bigman1@dtu.dk","11-04-1995","male","10101010");
		assertTrue(lc.exist(1));
	}
	
	@When("repeated information is entered")
	public void repeated_information_is_entered() {
		client1 = new Client("Bob1",1,"bigman1@dtu.dk","11-04-1995","male","10101010");
	}

	@Then("error message is thrown")
	public void error_message_is_thrown() {
		assertFalse(lc.newClient("Bob1",1,"bigman1@dtu.dk","11-04-1995","male","10101010"));
	}
	
	@When("information with missing parameters")
	public void information_with_missing_parameters() {
		client3 = new Client("Bob",2,"bigman1@dtu.dk","","male","10101010");
	}

	@Then("missing parameter error message is thrown")
	public void missing_parameter_error_message_is_thrown() {
		assertFalse(lc.newClient("Bob1",1,"bigman1@dtu.dk","","male","10101010"));
		
	}
	
	@Given("A logistic company")
	public void a_logistic_company() {
	    
	}

	@When("invalid email {string} is entered")
	public void invalid_email_is_entered(String string) {
		Client client1 = new Client("Bob1",1,string,"11-04-1995","male","10101010");
	}

	@Then("invalid email error is displayed for {string}")
	public void invalid_email_error_is_displayed_for(String string) {
		assertFalse(lc.newClient("Bob1",1,string,"11-04-1995","male","10101010"));
	}

	@When("invalid birthdate {string} is entered")
	public void invalid_birthdate_is_entered(String string) {
		client1 = new Client("Bob1",1,"bigman1@dtu.dk",string,"male","10101010");
	}

	@Then("invalid birtdate error is displayed for {string}")
	public void invalid_birtdate_error_is_displayed_for(String string) {
	    assertFalse(lc.newClient("Bob1",1,"bigman1@dtu.dk",string,"male","10101010"));
	}

	
	// _________________________________________Update Info________________________________________________
	@Given("A client with email {string}")
	public void a_client_with_email(String string) {
		client1 = new Client("Karsten",1,string,"24-05-1998","male","10101010");
		lc.newClient("Karsten",1,string,"24-05-1998","male","10101010");
		
	}
	
	Client tempClient;
	
	@When("New email entered as {string} instead of {string}")
	public void new_email_entered_as_instead_of(String string, String string2) {
		tempClient = lc.findClient(string2);
		tempClient.setEmail(string);
	}
	
	@Then("Display success message for {string} \\(email)")
	public void display_success_message_for(String string) {
		assertTrue(lc.updateClient(client1,tempClient));
	}

	@Given("A client with phone number {string}")
	public void a_client_with_phone_number(String string) {
		client1 = new Client("Jenny",2,"bigstonks123@gmail.com","27-10-1987","female",string);
		lc.newClient("Jenny",2,"bigstonks123@gmail.com","27-10-1987","female",string);
	}

	@When("New phone number entered as {string} instead of {string}")
	public void new_phone_number_entered_as_instead_of(String string, String string2) {
		tempClient = lc.findClient(string2);
		tempClient.setNumber(string);
	}

	@Then("Display success message for {string} \\(number)")
	public void change_phone_number_display_success_message(String string) {
		assertTrue(lc.updateClient(client1,tempClient));
	}
	
//	// _________________________________________Search Client________________________________________________
//
//	Client client4 = new Client("Jenny",2,"","02-02-1998","female","10101010");
//	
//	@Given("A client with the email {string}")
//	public void a_client_with_the_email(String string) {
//		client4.setEmail(string);
//		db2.addClient(client4);
//		lc.setDb(db2);
//	}
//
//	@When("The logistic company searches for {string}")
//	public Client the_logistic_company_searches_for(String string) {
//	    return lc.findClient(string);
//	}
//
//	@Then("The client is returned for {string} and a succes message is displayed")
//	public void the_client_is_returned_for_and_a_succes_message_is_displayed(String string) {
//		Client c = the_logistic_company_searches_for(string);
//		assertTrue(c.equals(client4));
//		System.out.println("Here is the client you searched for: ");
//		c.print();
//	}
//
//	
//	Client client5 = new Client("Jenny",2,"email123@mail.dk","11-10-1992","female","10101010");
//	@Given("A client with the clientID {int}")
//	public void a_client_with_the_clientID(Integer int1) {
//		database.addClient(client5);
//		lc.setDb(database);
//	}
//
//	@When("The logistic company searches for {int}")
//	public Client the_logistic_company_searches_for(Integer int1) {
//		return lc.findClient(int1);
//	}
//
//	@Then("The client is returned for {int} and a succes message is displayed")
//	public void the_client_is_returned_for_and_a_succes_message_is_displayed(Integer int1) {
//		Client c = the_logistic_company_searches_for(int1);
//		assertTrue(c.equals(client5));
//		System.out.println("Here is the client you searched for: ");
//		c.print();
//	}
//	
//	@Given("A none existing client for email search")
//	public void a_none_existing_client_for_email_search() {
//		db2.addClient(client4);
//		lc.setDb(db2);
//	}
//
//	@Given("A none existing client for ID search")
//	public void a_none_existing_client_for_ID_search() {
//	    db2.addClient(client4);
//	    lc.setDb(db2);
//	}
//	@When("The logistic company searches {string}")
//	public void the_logistic_company_searches(String string) {
//	    
//	}
//
//	@When("The logistic company searches {int}")
//	public void the_logistic_company_searches(Integer int1) {
//	    
//	}
//	
//	@Then("A failure message is returned for {int}")
//	public void a_failure_message_is_returned_for(Integer int1) {
//		assertTrue(lc.findClient(int1).getClientID() == 0);
//	}
//	@Then("A failure message is returned for {string}")
//	public void a_failure_message_is_returned_for(String string) {
//		assertTrue(lc.findClient(string).getClientID() == 0);
//		
//	}
//
//// _________________________________________Remove Client________________________________________________
//	
//	LogisticCompany lc2 = new LogisticCompany("maersk","No.1");
//	Client c5 = new Client("Jenny",4,"j1@gmail.com","11-10-1998","female","12345678");
//	
//	@Given("A logistic company that has a client with clientID {int}")
//	public void a_logistic_company_with_a_client_with_clientID(Integer int1) {
//		assertTrue(c5.getClientID()==int1);
//		lc2.addClient(c5);
//		
//	}
//
//	@When("the logistic company removes a client with the clientID {int}")
//	public void the_logistic_company_removes_a_client_with_the_clientID(Integer id) {
//	    lc2.removeClient(id);
//	}
//	
//	@Given("A logistic company that has a client with an email {string}")
//	public void a_logistic_company_that_has_a_client_with_an_email(String string) {
//		assertTrue(c5.getEmail().equals(string));
//		lc2.addClient(c5);
//	}
//
//	@When("the logistic company removes a client with an email {string}")
//	public void the_logistic_company_removes_a_client_with_an_email(String string) {
//	    lc2.removeClient(string);
//	}
//
//	@Then("the client is deleted and succes message is displayed")
//	public void the_client_is_deleted_and_succes_message_is_displayed() {
//		assertTrue(!(lc2.exist(c5)));
//		System.out.println("Client has succesfully been removed");
//	}
//

	
	
}