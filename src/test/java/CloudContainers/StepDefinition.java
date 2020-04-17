package CloudContainers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import CloudContainers.Client;
import CloudContainers.ClientDatabase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition{
	
	Client client1;
	Client client2;
	Client client3;
	ResponseObject response;
	LogisticCompany lc;
	
	@Given("A logistic company")
	public void a_logistic_company() {
	    lc  = new LogisticCompany("Maersk",1,100);
	}
	
	@Given("A none existing client")
	public void a_none_existing_client(){
		client1 = new Client("Karsten","smallmoney123@gmail.com","24-05-1998","male",10101010,"1234");
	    assertFalse(lc.getClients().contains(client1));
	}
	
	
	@When("Informations is entered")
	public void informations_is_entered() {
		response = lc.newClient(client1);
	}
	
	@Then("display success message")
	public void display_success_message() {
		assertTrue(lc.getClients().contains(client1));
		assertEquals(response.getErrorMessage(),"Client was successfully added");
	}
	
	
	@Given("existing client")
	public void existing_client() {
		client1 = new Client("Karsten","smallmoney123@gmail.com","24-05-1998","male",10101010,"1234");
		lc.newClient(client1);
		assertTrue(lc.getClients().contains(client1));
	}
	
	@When("repeated information is entered")
	public void repeated_information_is_entered() {
		response = lc.newClient(client1);
	}

	@Then("error message is thrown")
	public void error_message_is_thrown() {
		assertEquals(response.getErrorMessage(),"Existing client");
		
	}
	
	@Given("A none existing client with missing information")
	public void a_none_existing_client_with_missing_information(){
		client1 = new Client("Karsten","","24-05-1998","male",10101010,"1234");
	    assertFalse(lc.getClients().contains(client1));
	}

	@Then("missing parameter error message is thrown")
	public void missing_parameter_error_message_is_thrown() {
		assertEquals(response.getErrorMessage(),"There is a missing parameter");
		
	}

	@When("invalid email {string} is entered")
	public void invalid_email_is_entered(String string) {
		client1 = new Client("Karsten",string,"24-05-1998","male",10101010,"1234");
		response = lc.newClient(client1);
	}

	@Then("invalid email error is displayed for {string}")
	public void invalid_email_error_is_displayed_for(String string) {
		assertEquals(response.getErrorMessage(),string + " is not a valid email");
	}

	@When("invalid birthdate {string} is entered")
	public void invalid_birthdate_is_entered(String string) {
		client1 = new Client("Karsten","smallmoney123@gmail.com",string,"male",10101010,"1234");
		response = lc.newClient(client1);
	}

	@Then("invalid birtdate error is displayed for {string}")
	public void invalid_birtdate_error_is_displayed_for(String string) {
	    assertEquals(response.getErrorMessage(),string + " is not a valid birthdate");
	}

	
	// _________________________________________Update Info________________________________________________
	
	
	@Given("A client with email {string}")
	public void a_client_with_email(String string) {
		client1 = new Client("Karsten",string,"04-04-1998","male",10101010,"1234");
		lc.newClient(client1);
		
	}
	
	@When("New email entered as {string}")
	public void new_email_entered_as(String string) {
		response = client1.updateClient(string);
	}
	
	@Then("Display email update success message")
	public void display_email_update_success_message() {
		assertEquals(response.getErrorMessage(),"Email has been updated");
		assertFalse(lc.getClients().getClient("bigmoney123@gmail.com").equals(null));
	}

	@Given("A client with phone number {int}")
	public void a_client_with_phone_number(Integer int1) {
		client1 = new Client("Karsten","bigmoney123@gmail.com","04-04-1998","male",int1,"1234");
		lc.newClient(client1);
	}

	@When("A client with ID {int} New phone number entered as {int}")
	public void a_client_with_email_new_phone_number_entered_as(Integer clientID,Integer int1) {
		response = client1.updateClient(int1);
	}

	@Then("Display phonenumber update success message")
	public void display_phonenumber_update_success_message() {
		assertEquals(response.getErrorMessage(),"Phone number has been updated");
		
	}
	
	@Then("Display error message saying phone number is not correct for {int}")
	public void display_error_message_saying_phone_number_is_not_correct_for(Integer int1) {
		assertEquals(response.getErrorMessage(), int1 + " is not a valid phone number");
	}
	
	// _________________________________________Search Client________________________________________________


	@Given("A client with the email {string}")
	public void a_client_with_the_email(String string) {
		client1 = new Client("Karsten",string,"04-04-1998","male",10101010,"1234");
		lc.newClient(client1);
	}

	@When("The logistic company searches for {string}")
	public Client the_logistic_company_searches_for(String string) {
	    return lc.getClients().getClient(string);
	}

	@Then("The client is returned for {string}")
	public void the_client_is_returned_for(String string) {
		Client c = the_logistic_company_searches_for(string);
		assertTrue(lc.getClients().contains(c));
		assertEquals(c.getEmail(),string);
	}

	
	
	@Given("A client with the clientID {int}")
	public void a_client_with_the_clientID(Integer int1) {
		client1 = new Client("Karsten","s181111@student.dtu.dk","04-04-1998","male",10101010,"1234");
		lc.newClient(client1);
	}

	@When("The logistic company searches for {int}")
	public Client the_logistic_company_searches_for(Integer int1) {
		return lc.getClients().getClient(int1);
	}

	@Then("The client is returned for {int}")
	public void the_client_is_returned_for(Integer int1) {
		Client c = the_logistic_company_searches_for(int1);
		assertTrue(lc.getClients().contains(c));
		assertTrue(c.getClientID() == int1);
	}
	
	
	@Then("no client with Id {int} exists")
	public void no_client_with_Id_exists(Integer int1) {
		Client c = the_logistic_company_searches_for(int1);
		assertFalse(lc.getClients().contains(c));
	}
	@Then("no client with email {string} exists")
	public void no_client_with_email_exists(String string) {
		Client c = the_logistic_company_searches_for(string);
		assertFalse(lc.getClients().contains(c));
		
	}

// _________________________________________Remove Client________________________________________________
	
	
	


	@When("the logistic company removes a client with the clientID {int}")
	public void the_logistic_company_removes_a_client_with_the_clientID(Integer id) {
	    lc.getClients().remove(lc.getClients().getClient(id));
	}
	

	@When("the logistic company removes a client with an email {string}")
	public void the_logistic_company_removes_a_client_with_an_email(String string) {
		lc.getClients().remove(lc.getClients().getClient(string));
	}

	@Then("the client is deleted with clientID {int}")
	public void the_client_is_deleted_with_clientID(Integer int1) {
	    assertFalse(lc.getClients().contains(client1));
	}

	@Then("the client is deleted with email {string}")
	public void the_client_is_deleted_with_email(String string) {
	    assertFalse(lc.clientExists(string));
	}
	
	@Then("nothing happens")
	public void nothing_happens() {
	    assertTrue(lc.getClients().size() == 0);

	}
	// _________________________________________Add container to client________________________________________________
	
	Client client;
	Container container;
	
	@Given("an unowned container")
	public void an_unowned_container() {
		container = lc.getContainerDatabase().findFreeContainer();
		assertTrue(!container.isOwned() && container.getContainerId() != 0);
		
	}
	
	@When("a container is allocated")
	public void a_container_is_allocated() {
		response = lc.allocateContainer(client1,container);
	    
	}

	@Then("an allocation succes message is displayed")
	public void an_allocation_succes_message_is_displayed() {
		assertTrue(response.getErrorMessage().equals("Container succesfully allocated"));
		assertTrue((container.getOwner().equals(client1)));		
		assertTrue(container.isOwned());

	}


	@Given("an owned container")
	public void an_owned_container() {
		container = lc.getContainerDatabase().findFreeContainer();
		client2 = new Client("Jenny","email@dtu.dk","11-10-1998","female",12345678,"1234");
		lc.newClient(client2);
		lc.allocateContainer(client2,container);
	    assertTrue(container.isOwned());
	}
	
	@When("a logistic company tries to allocate container")
	public void a_container_is_not_allocated() {
	    response = lc.allocateContainer(client1, container);
	}

	@Then("an allocation failure message is displayed")
	public void an_allocation_failure_message_is_displayed() {
		assertTrue(response.getErrorMessage().equals("This container is already owned by a client"));
		assertTrue((container.getOwner().equals(client2)));		
		assertTrue(container.isOwned());
	}
	
	
	// ____________________________________containerOnJourney____________________________________________
	Journey journey;

	@Given("a container owned by the client")
	public void a_container_owned_by_the_client() {
		container = lc.getContainerDatabase().findFreeContainer();
		lc.allocateContainer(client1,container);
	}

	@Given("a valid journey")
	public void a_valid_journey() {
	    journey = new Journey("Texas", "Zimbabwe",50);
	    lc.addJourney(journey);
	}

	@When("logistic company tries to put container on journey")
	public void logistic_company_tries_to_put_container_on_journey() {
	    response = client1.containerToJourney(container, journey, "Bananas");
	    
	}
	@Then("success message is displayed")
	public void success_message_is_displayed() {
	    assertTrue(response.getErrorMessage().equals("Container successfully added to journey"));
	    assertTrue(container.isOnJourney());
	    assertTrue(container.isOwned());
	}


	@Then("error message displayed saying that container does not belong to client")
	public void error_message_displayed_saying_that_container_does_not_belong_to_client() {
	    assertTrue(response.getErrorMessage().equals("Container does not belong to client"));
	    assertFalse(container.isOnJourney());
	}
	

	// _____________________________updateJourney_____________________________________________________
	
	@Given("a registered journey with PoO of {string}")
	public void a_registered_journey_with_PoO_of(String string) {
		journey = new Journey(string, "Zimbabwe",50);
	    lc.addJourney(journey);
	}

	@When("the logistic company tries to update port of origin to {string}")
	public void the_logistic_company_tries_to_update_port_of_origin_to(String string) {
	    journey.setPortOfOrigin(string);
	}

	@Then("poO is updated to {string}")
	public void poO_is_updated_to(String string) {
	    assertEquals(journey.getPortOfOrigin(),string);
	}

	@Given("a registered journey with destination of {string}")
	public void a_registered_journey_with_destination_of(String string) {
	    journey = new Journey("Bahamas", string,50);
	    lc.addJourney(journey);
	}

	@When("the logistic company tries to update destination to {string}")
	public void the_logistic_company_tries_to_update_destination_to(String string) {
	    journey.setDestination(string);
	}

	@Then("success message is displayed for destination {string}")
	public void success_message_is_displayed_for_destination(String string) {
	    assertEquals(journey.getDestination(),string);
	}


	// ______________________________filterJourney___________________________________________________
	Set<Journey> filtered;
	Journey journey1;
	Journey journey2;
	Journey journey3;
	@Given("three journeys with port of origin {string}, {string} and {string}")
	public void three_journeys_with_port_of_origin_and(String string, String string2, String string3) {
		journey1 = new Journey(string, "Copenhagen",50);
		journey2 = new Journey(string2, "Copenhagen",50);
		journey3 = new Journey(string3, "Copenhagen",50);
	    lc.addJourney(journey1);
	    lc.addJourney(journey2);
	    lc.addJourney(journey3);
	}

	@When("journeys are filtered for {string}")
	public void journeys_are_filtered_for(String string) {
		filtered = lc.getJourneyDatabase().filterPortOfOrigin(string);
	}

	@Then("display filtered set of journeys with port of origin {string}")
	public void display_filtered_set_of_journeys_with_port_of_origin(String string) {
		for (Journey j : filtered) {
			assertTrue(j.getPortOfOrigin().equals(string));
		}
	}
	
	
	@Given("three journeys with destination {string}, {string} and {string}")
	public void three_journeys_with_destination_and(String string, String string2, String string3) {
		journey1 = new Journey("Copenhagen",string,50);
		journey2 = new Journey("Copenhagen",string2,50);
		journey3 = new Journey("Copenhagen",string3,50);
	    lc.addJourney(journey1);
	    lc.addJourney(journey2);
	    lc.addJourney(journey3);
	}
	
	@When("journeys are filtered for destination {string}")
	public void journeys_are_filtered_for_destination(String string) {
		filtered = lc.getJourneyDatabase().filterDestination(string);
	}
	
	@Then("display filtered set of journeys with destination {string}")
	public void display_filtered_set_of_journeys_with_destination(String string) {
		for (Journey j : filtered) {
			assertTrue(j.getDestination().equals(string));
		}
	}
	
	@Then("get empty set")
	public void get_empty_set() {
	    assertEquals(filtered.size(),0);
	}
	
	// ______________________________endJourney____________________________________________________
	
	@Given("a registered journey from {string} to {string}")
	public void a_registered_journey_from_to(String string, String string2) {
		journey = new Journey(string, string2,50);
	    lc.addJourney(journey);
	}


	@When("logistic company tries to end journey")
	public void logistic_company_tries_to_end_journey() {
	    response = journey.endJourney();
	}

	@Then("message displayed saying journey successfully ended for {int} containers")
	public void message_displayed_saying_journey_successfully_ended_for_containers(Integer int1) {
		assertEquals(response.getErrorMessage(),"Journey successfully ended. " + int1 + " containers were set free.");
	    assertFalse(container.isOnJourney());
	}
	
	

	// ____________________________freeContainer______________________________________________________
	Container container1;
	Container container2;
	Container container3;
	

	@When("logistic company frees one container")
	public void logistic_company_frees_one_container() {
	    response = container.freeContainer();
	    
	}

	@Then("A succes message is displayed for freeing a container")
	public void a_succes_message_is_displayed_for_freeing_a_container() {

	    assertEquals(response.getErrorMessage(),"Container was successfully freed");
	}

	@Then("A error message is displayed that container is on journey")
	public void a_error_message_is_displayed_that_container_is_on_journey() {
	    assertEquals(response.getErrorMessage(),"This container is on a journey");
	}
	
	@Then("A error message is displayed as container is not owned")
	public void a_error_message_is_displayed_as_container_is_not_owned() {
		assertEquals(response.getErrorMessage(),"This container does not belong to a client");
	}
	
	// ____________________________runJourney______________________________________________________

	
	@Given("a journey from {string} to {string} with {int} hours to destination")
	public void a_journey_from_to_with_hours_to_destination(String string, String string2, Integer int1) {
		journey = new Journey(string, string2,int1);
	    lc.addJourney(journey);
	}

	@Given("three containers registered to the client")
	public void three_containers_registered_to_the_client() {
		container1 = lc.getContainerDatabase().findFreeContainer();
		lc.allocateContainer(client1, container1);
		container2 = lc.getContainerDatabase().findFreeContainer();
		lc.allocateContainer(client1, container2);
		container3 = lc.getContainerDatabase().findFreeContainer();
		lc.allocateContainer(client1, container3);
	}
	@Given("the containers are put on the journey containing {string}, {string} and {string} respectively")
	public void the_containers_are_put_on_the_journey_containing_and_respectively(String string, String string2, String string3) {
		client1.containerToJourney(container1, journey, string);
	    client1.containerToJourney(container2, journey, string2);
	    client1.containerToJourney(container3, journey, string3);
	}
	
	
	@When("journey is started and run for {int} hours")
	public void journey_is_started_and_run_for_hours(Integer int1) {
	    response = journey.progressJourney(int1);
	}
	
	@Then("journey elapsed time is updated to {int} hours")
	public void journey_elapsed_time_is_updated_to_hours(Integer int1) {
	    assertTrue(journey.getElapsedTime()==int1);
	    
	}

	@Then("data has been collected up till {int} hours")
	public void data_has_been_collected_up_till_hours(Integer int1) {
		ArrayList<statusTrackingObject> list;
		list = journey.getStatusData();
		assertTrue(list.get(list.size()-1).getTime() == int1);
	}

	@Then("a succes response is given for journey {int} elapsed time {int}")
	public void a_succes_response_is_given_for_journey_elapsed_time(Integer int1, Integer int2) {
	    assertEquals(response.getErrorMessage(),"Your container on journey " + int1 + " has traveled " + int2 + " hours.");
	}
	
	@Then("a response is returned that the journey has ended for journey {int} after {int} hours")
	public void a_response_is_returned_that_the_journey_has_ended_for_journey_after_hours(Integer int1, Integer int2) {
	    assertEquals(response.getErrorMessage(),"Your container on journey " + int1 + " has traveled " + int2 + " hours and the journey has ended.");
	}
	
	@Then("no data has been collected")
	public void no_data_has_been_collected() {
		ArrayList<statusTrackingObject> list;
		list = journey.getStatusData();
		assertTrue(list.size() == 0);
	}

	@Then("a response is returned that the journey has no containers")
	public void a_response_is_returned_that_the_journey_has_no_containers() {
		assertEquals(response.getErrorMessage(),"No containers are on this journey");
	}
	
	
	
	@Then("a response is given for travel time too low")
	public void a_response_is_given_for_travel_time_too_low() {
	    assertEquals(response.getErrorMessage(), "Travel time has to be a more than 0");
	}
	
	// ____________________________accessData______________________________________________________
	
	@When("client request the data for his container")
	public void client_request_the_data_for_his_container() {
	    response = container1.accessStatus(client1);
	}
	@When("client request the data for a container he does not own")
	public void client_request_the_data_for_a_container_he_does_not_own() {
	    response = container.accessStatus(client1);
	}

	@Then("the status of the container is returned")
	public void the_status_of_the_container_is_returned() {
	    assertTrue(response.getStatus().getTime() != 0);
	}

	@Then("a succes for data access is displayed")
	public void a_succes_for_data_access_is_displayed() {
	    assertEquals(response.getErrorMessage(),"This is the current status of your container");
	}

	@Then("a error message is returned for data access")
	public void a_error_message_is_returned_for_data_access() {
	    assertEquals(response.getErrorMessage(),"You don't have access to this container");
	}
	@Then("a error message is returned for journey has not started")
	public void a_error_message_is_returned_for_journey_has_not_started() {
	    assertEquals(response.getErrorMessage(),"Ship's still at harbour");
	}

	// ________________________________________containerHistory_____________________________________________________________
	

	@Given("the container has been on one journey from {string} to {string} and another journey from {string} to {string}")
	public void the_container_has_been_on_one_journey_from_to_and_another_journey_from_to(String string, String string2, String string3, String string4) {
		journey1 = new Journey(string, string2,10);
	    lc.addJourney(journey1);
	    client1.containerToJourney(container, journey1, "Chocolate");
		journey1.progressJourney(10);
		
		journey2 = new Journey(string3, string4,20);
	    lc.addJourney(journey2);
	    client1.containerToJourney(container, journey2, "Banana");
		journey2.progressJourney(20);
		
	}

	@When("container history is requested by logistic company")
	public void container_history_is_requested_by_logistic_company() {
	    response = lc.getFullHistory(container);
	}

	@Then("an array containing container journey information is returned")
	public void an_array_containing_container_journey_information_is_returned() {
	    assertEquals(response.getJourneys().size(), 2);
	}

	@Then("response message saying that history of container was successfully retrieved")
	public void response_message_saying_that_history_of_container_was_successfully_retrieved() {
	    assertEquals(response.getErrorMessage(),"History successfully retrieved");
	}
	
	@Given("an non-registered container")
	public void an_non_registered_container() {
	    container = new Container(0);
	}
	
	@When("container history is requested by client")
	public void container_history_is_requested_by_client() {
	    response = container.getHistoryOfContainerForClient(client1);
	}

	@Then("an array containing relevant journeys are returned")
	public void an_array_containing_relevant_journeys_are_returned() {
	    assertEquals(response.getJourneyHist().size(),2);
	}

	@Then("response message saying that history of container was successfully retrieved to client")
	public void response_message_saying_that_history_of_container_was_successfully_retrieved_to_client() {
	    assertEquals(response.getErrorMessage(),"Your container's history is succesfully retrieved");
	}

	
	@Then("an array containing relevant journeys are returned with size {int}")
	public void an_array_containing_relevant_journeys_are_returned_with_size(Integer int1) {
	    assertTrue(response.getJourneyHist().size() == int1);
	}

	
	
}