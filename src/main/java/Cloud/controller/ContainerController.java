package Cloud.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import Cloud.model.*;

import net.bytebuddy.matcher.ModifierMatcher.Mode;


@Controller

public class ContainerController extends HttpServlet {
	ResponseObject responseObject1 = new ResponseObject();
	ResponseObject responseObject2 = new ResponseObject();
	
	@GetMapping("/")
	public String mainpage(Model model) {
		
		
		return "index";
		
	}
	@PostMapping("/")
	public String add3(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		response.setContentType("text/html"); 
		String name=request.getParameter("companyName"); 
	    String mail=request.getParameter("userName"); 
	    String pass=request.getParameter("userPass");
	    System.out.println(name);
	    System.out.println(mail);
	    System.out.println(pass);
	    LogisticCompany company = JSONWriter.getCompany();
	    if (name==null) {
	    if (company.clientExists(mail)==false) {
	    	responseObject1.setErrorMessage("Sir your mail is not in our database, please register");
		    model.addAttribute("response1",responseObject1);
	    	return "/index";
	    }
	    
	    else if (JSONWriter.checkPass(mail, pass)){
			ClientDatabase ClientDB = company.getClients();
			Client client = ClientDB.getClient(mail);
		    JSONWriter.setIn(client);
		    RequestDispatcher rd=request.getRequestDispatcher("/Welcome.html"); 
			Set<Container> clientContainers = company.getContainerDatabase().filterClient(client);
			model.addAttribute("client",client);
			model.addAttribute("clientContainers",clientContainers);
			model.addAttribute("clients",company.getClients());
			return "redirect:Welcome";
		}
	    responseObject1.setErrorMessage("Password does not match your email sir");
	    model.addAttribute("response",responseObject1);
		return "/index";
		}
		else if (mail==null) {
			if (company.getName().equals(name)==false) {
				responseObject1.setErrorMessage("Not valid company name");
			    model.addAttribute("response3",responseObject1);
		    	return "/index";
			}
	    	if (company.getPassword().equals(pass) && company.getName().equals(name)) {
		    	RequestDispatcher rd=request.getRequestDispatcher("/WelcomeC.html"); 
				model.addAttribute("company",company);
				model.addAttribute("clientContainers",company.getContainerDatabase());
				model.addAttribute("clients",company.getClients());
				return "redirect:WelcomeC";
		    }   
		    else {
		    responseObject1.setErrorMessage("Invalid company password");
		    model.addAttribute("response4",responseObject1);
		    return "/index";}
		    }
	    return "Yeet";}

	
	@GetMapping("/ClientLogin")
	public String add(Client client ,Model model) {
		model.addAttribute("client",client);
		return "ClientLogin";
		
	}
	@PostMapping("/ClientLogin")
	public String add(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		response.setContentType("text/html");   
	    String mail=request.getParameter("userName"); 
	    String pass=request.getParameter("userPass");
	    if (JSONWriter.checkPass(mail, pass)){ 
		    LogisticCompany company = JSONWriter.getCompany();
			ClientDatabase ClientDB = company.getClients();
			Client client = ClientDB.getClient(mail);
		    JSONWriter.setIn(client);
		    RequestDispatcher rd=request.getRequestDispatcher("/Welcome.html"); 
			Set<Container> clientContainers = company.getContainerDatabase().filterClient(client);
			model.addAttribute("client",client);
			model.addAttribute("clientContainers",clientContainers);
			model.addAttribute("clients",company.getClients());
			model.addAttribute("journeys", company.getJourneyDatabase());
			return "redirect:Welcome";
		}
	    responseObject1.setErrorMessage("Invalid login");
	    model.addAttribute("response",responseObject1);
		return "ClientLogin";}
	
	@GetMapping("/CompanyLogin")
	public String add2(Container container,Model model) {
		return "CompanyLogin";
		
	}
	@PostMapping("/CompanyLogin")
	public String add2(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		response.setContentType("text/html");   
	    String name=request.getParameter("userName"); 
	    String pass=request.getParameter("userPass");
	    LogisticCompany company = JSONWriter.getCompany();
	    if (company.getPassword().equals(pass) && company.getName().equals(name)) {
	    	RequestDispatcher rd=request.getRequestDispatcher("/WelcomeC.html"); 
			model.addAttribute("company",company);
			model.addAttribute("clientContainers",company.getContainerDatabase());
			model.addAttribute("clients",company.getClients());
			return "redirect:WelcomeC";
	    }   
	    else {
	    responseObject1.setErrorMessage("Invalid login");
	    model.addAttribute("response",responseObject1);
	    return "CompanyLogin";}}
	
	@GetMapping("/WelcomeC")
	public String WelcomeCompany(Model model) throws IOException {
		LogisticCompany company = JSONWriter.getCompany();
		model.addAttribute("journeys",company.getJourneyDatabase());
		model.addAttribute("company",company);
		model.addAttribute("clientContainers",company.getContainerDatabase());
		model.addAttribute("clients",company.getClients());
	    return "WelcomeC";
	}
		
	@GetMapping("/Welcome" )
	public String welcome(Model model) throws FileNotFoundException {
		LogisticCompany company = JSONWriter.getCompany();
		Client client = JSONWriter.getIn();
		Set<Container> clientContainers = company.getContainerDatabase().filterClient(client);
		model.addAttribute("client",client);
		model.addAttribute("clientContainers",clientContainers);
		model.addAttribute("clients",company.getClients());
		model.addAttribute("journeys", company.getJourneyDatabase());
		return "Welcome";
			

}

		
		@GetMapping("/UpdateInfo")
		public String updateinfo(ModelMap model) throws FileNotFoundException {
			Client client = JSONWriter.getIn();
			model.addAttribute("client",client);
			return "ClientUpdate";
			
		}

		@GetMapping("/ContainerInfo")
		public String containerInfo(Model model) {
			return "ContainerInfo";
		}

		@PostMapping("/UpdateInfo")
		public String updateinfo(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
			
			response.setContentType("text/html");  

			          
			String email = request.getParameter("userMail");  
			long number = Long.parseLong(request.getParameter("userNumber"));  
			Client client = JSONWriter.getIn();
			model.addAttribute("client",client);
			LogisticCompany company = JSONWriter.getCompany();
			ClientDatabase clients = company.getClients();
			responseObject1 = client.updateClient(email);
			responseObject2 = client.updateClient(number);
			
			if (!company.clientExists(email) && responseObject1.getErrorMessage().equals("Email has been updated")) {
				clients.remove(client);
				clients.add(client);
				company.setClients(clients);
				JSONWriter.saveCompany(company);
				JSONWriter.setIn(client);
				model.addAttribute("client",client);
				model.addAttribute("clientContainers",company.getContainerDatabase());
				model.addAttribute("clients",company.getClients());
				model.addAttribute("journeys",company.getJourneyDatabase());
				return "redirect:Welcome";
			}
			else if (company.clientExists(email)){
				responseObject1.setErrorMessage("This email is already in use");
			}
			model.addAttribute("responseEmail", responseObject1);
			model.addAttribute("responseNumber", responseObject2);
			model.addAttribute("client",client);
	        return "ClientUpdate";
			
			}
	
		@GetMapping("/Register")
		public String Register()  {
			return "Register";
			
		}
		
		@PostMapping("/Register")
		public String Register(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException { 
			response.setContentType("text/html");
		    String name = request.getParameter("userName");  
		    String email = request.getParameter("userMail"); 
		    String birthdate = request.getParameter("userBirthdate");  
		    String gender = request.getParameter("userGender"); 
		    long phonenumber = Long.parseLong(request.getParameter("userNumber"));  
		    String password = request.getParameter("userPass"); 
		    System.out.println(password);
		    Client client = new Client(name,email,birthdate,gender,phonenumber,password);
		    model.addAttribute("response", responseObject1);
		    LogisticCompany company = JSONWriter.getCompany();
			if(!Validator.validEmail(email)) {
				responseObject1.setErrorMessage("Please write valid email");
				return "Register";
			}
			else if(company.clientExists(email)) {
				responseObject1.setErrorMessage("Email Already exist, try again");
				return "Register";
			}
			else if (!Validator.validPhoneNumber(phonenumber)){
				responseObject1.setErrorMessage("Please write valid 8-digit phone number");
				return "Register";}
			else if (!Validator.validBirthdate(birthdate)) {
				responseObject1.setErrorMessage("Please write birthdate in the form dd-mm-yyyy");
				return "Register";}
			else if (password.length()<4) {
				responseObject1.setErrorMessage("Please the password should be at least 4 characters long");
				return "Register";}
			else {
			    responseObject1 = company.newClient(client);
				JSONWriter.saveCompany(company);
		        return "/index";
			}
			
		}
		
		@GetMapping("/container/{id}")
		public String containerPage(@PathVariable("id") int id,Model model) {
			
			
			return "ContainerPage";
		}
		
		
		@GetMapping("/containerC/{containerID}/{journeyID}")
		public String containerPageC(@PathVariable("containerID") int containerID,@PathVariable("journeyID") int journeyID,Model model) throws FileNotFoundException {
			
			LogisticCompany company = JSONWriter.getCompany();
			Container container = company.getContainerDatabase().getContainer(containerID);
			ArrayList<ContainerJourneyInfo> journeyHist = company.getContainers().getContainer(containerID).getJourneyHistory();
			Journey journey = null;
			if (journeyID == -1) {
				// Get most current journey
				journey = journeyHist.get(journeyHist.size() - 1).getJourney();
				
				model.addAttribute("journey",journey);
				model.addAttribute("container",container);
				model.addAttribute("journeysInfo",journeyHist);
				return "ContainerPageC";
			}
			
			for (ContainerJourneyInfo info : journeyHist) {
				if (info.getJourney().getJourneyID() == journeyID) {
					journey = info.getJourney();
				}
			}
			
			model.addAttribute("journey",journey);
			model.addAttribute("container",container);
			model.addAttribute("journeysInfo",journeyHist);
			return "ContainerPageC";
		}
		
		@GetMapping("/container/{containerID}/{journeyID}")
		public String containerPage(@PathVariable("containerID") int containerID,@PathVariable("journeyID") int journeyID,Model model) throws FileNotFoundException {
			Journey journey = null;
			LogisticCompany company = JSONWriter.getCompany();
			Container container = company.getContainerDatabase().getContainer(containerID);
			Client client = JSONWriter.getIn();
			ArrayList<ContainerJourneyInfo> journeyHist = company.getContainers().getContainer(containerID).getJourneyHistory();
			ArrayList<Journey> journeys = container.getHistoryOfContainerForClient(client).getJourneyHist();
			if (journeyID == -1) {
				// Get most current journey
				journey = journeyHist.get(journeyHist.size() - 1).getJourney();
				
				model.addAttribute("journey",journey);
				model.addAttribute("container",container);
				model.addAttribute("journeys",journeys);
				return "ContainerPage";
			}
			
			for (ContainerJourneyInfo info : journeyHist) {
				if (info.getJourney().getJourneyID() == journeyID) {
					journey = info.getJourney();
				}
			}
		
			
			model.addAttribute("journey",journey);
			model.addAttribute("container",container);
			model.addAttribute("journeys",journeys);
			return "ContainerPage";
		}
		
		
		
		@GetMapping("/updateCompany/{id}")
		public String updateCompany(@PathVariable("id") int id, LogisticCompany company,Model model) {
			return "/WelcomeC";
		}
		
		@GetMapping("/logout")
		public String logout(Model model, Client client) throws IOException {

			return "index";
			
		}
		
		@GetMapping("/about")
		public String about() {
			return "about";
		}
		
		@GetMapping("/covid")
		public String covid() {
			return "covid";
		}
		@GetMapping("/journeys")
		public String journeys(Model model) throws FileNotFoundException {
			LogisticCompany company = JSONWriter.getCompany();
			JourneyDatabase journeys = company.getJourneyDatabase();
			model.addAttribute("journeys",journeys);
			return "journeys";
		}
		
		@GetMapping("/createJourney")
		public String createJourney(Model model) {
			return "createJourney";
		}
		
		@PostMapping("/createJourney")
		public String createJourney(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
			String port = request.getParameter("port");  
			String destination = request.getParameter("destination");  
			int duration = Integer.parseInt(request.getParameter("duration")); 
			
			Journey journey = new Journey(port,destination,duration);
			LogisticCompany company = JSONWriter.getCompany();
			JourneyDatabase journeys = company.getJourneyDatabase();
			responseObject1 = company.newJourney(journey);
			if (responseObject1.getErrorMessage().equals("Journey was successfully added")) {
				
				JourneyDatabase journeys2 = company.getJourneyDatabase();
				model.addAttribute("journeys",journeys);
				JSONWriter.saveCompany(company);
				return "redirect:journeys";
			}
			else {
			model.addAttribute("response",responseObject1);
			return "createJourney";
			}
			
			
		}
		
		@GetMapping("/removeJourney/{id}")
		public String endJourney(@PathVariable("id") int id	,Model model) throws IOException {
			
			LogisticCompany company = JSONWriter.getCompany();
			JourneyDatabase journeys = company.getJourneyDatabase();
			Journey journey = journeys.getJourney(id);
			company.endJourney(journey);
			company.setJourneyDatabase(journeys);
			JSONWriter.saveCompany(company);
			model.addAttribute("journeys",journeys);
			
			return "redirect:/journeys";
			
		}
		


	    
	    @GetMapping("/allocateContainer/{clientID}/{containerID}")
	    public String allocateContainer(@PathVariable("clientID") int clientID,@PathVariable("containerID") int containerID,Model model) throws IOException {
	    	
			LogisticCompany company = JSONWriter.getCompany();
			
			Client client = company.getClients().getClient(clientID);
			Container container = company.getContainerDatabase().getContainer(containerID);
			
			company.allocateContainer(client, container);
			
			JSONWriter.saveCompany(company);
	    	
			model.addAttribute("company",company);
			model.addAttribute("clientContainers",company.getContainerDatabase());
			model.addAttribute("clients",company.getClients());
			
	    	return "redirect:/WelcomeC";
	    }
	    
	    @GetMapping("/grantAccess/{toClientID}/{containerID}")
	    public String grantAccess(@PathVariable("toClientID") int toClientID,@PathVariable("containerID") int containerID,Model model) throws IOException {
	    	
			LogisticCompany company = JSONWriter.getCompany();
			
			Client toClient = company.getClients().getClient(toClientID);
			company.getContainerDatabase().getContainer(containerID).grantAccess(toClient);

			
			JSONWriter.saveCompany(company);
	    	
			Client client = JSONWriter.getIn();
			
			model.addAttribute("client",client);
			model.addAttribute("clientContainers",company.getContainerDatabase());
			model.addAttribute("clients",company.getClients());
			model.addAttribute("journeys",company.getJourneyDatabase());
			
			
	    	return "redirect:/Welcome";
	    }
	    
	    @GetMapping("/toJourney/{journeyID}/{containerID}")
	    public String toJourney(@PathVariable("journeyID") int journeyID,@PathVariable("containerID") int containerID,Model model) throws IOException {
	    	
			LogisticCompany company = JSONWriter.getCompany();
			Client client = JSONWriter.getIn();
			client.containerToJourney(company.getContainerDatabase().getContainer(containerID), company.getJourneyDatabase().getJourney(journeyID));
			JSONWriter.saveCompany(company);
			
			model.addAttribute("client",client);
			model.addAttribute("clientContainers",company.getContainerDatabase());
			model.addAttribute("journeys",company.getJourneyDatabase());
			model.addAttribute("clients",company.getClients());
	    	return "redirect:/Welcome";
	    }
	    
	    @PostMapping("/content/{id}")
	    public String content(@PathVariable("id")int containerID,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	String content = request.getParameter("content");  
	    	LogisticCompany company = JSONWriter.getCompany();
	    	company.getContainerDatabase().getContainer(containerID).setContent(content);
	    	JSONWriter.saveCompany(company);
	    	return "redirect:/Welcome";
	    }
	    

	    
	    @PostMapping("/progressJourney/{id}")
	    public String progressInput(@PathVariable("id")int journeyID,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	LogisticCompany company = JSONWriter.getCompany();
	    	try {
	    		int timeIncrement = Integer.parseInt(request.getParameter("timeIncrement"));   
		    	
		    	JourneyDataGenerator.progressJourney(company,company.getJourneyDatabase().getJourney(journeyID),timeIncrement);
				JSONWriter.saveCompany(company);
				model.addAttribute("journeys",company.getJourneyDatabase());
	    	} catch(Exception e) {
	    		responseObject2.setErrorMessage("Insert number please");
	    		model.addAttribute("company",company);
				model.addAttribute("journeys",company.getJourneyDatabase());
	    		model.addAttribute("responseObject2",responseObject2);
	    	}
	    	
	    	return "journeys";
	    }
	
	    
	    @GetMapping("/removeContainer/{containerID}")
	    public String removeContainer(@PathVariable("containerID") int containerID,Model model) throws IOException {
	    	
			LogisticCompany company = JSONWriter.getCompany();
			
			Client client = JSONWriter.getIn();
			
			client.removeContainer(company.getContainerDatabase().getContainer(containerID));
			
			JSONWriter.saveCompany(company);
			
			model.addAttribute("client",client);
			model.addAttribute("clientContainers",company.getContainerDatabase());
			model.addAttribute("clients",company.getClients());
			model.addAttribute("journeys",company.getJourneyDatabase());
			
	    	return "redirect:/Welcome";
	    }
	    
	    
	    @PostMapping("/viewClient")
	    public String viewClientPost(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	String email = request.getParameter("email");
	    	LogisticCompany company = JSONWriter.getCompany();

	    	if (company.getClients().getClient(email) == null) {
	    		
	    		responseObject1.setErrorMessage("Client not found");
	    		model.addAttribute("company",company);
	    		model.addAttribute("responseObject1",responseObject1);
				model.addAttribute("clientContainers",company.getContainerDatabase());
				model.addAttribute("clients",company.getClients());
				model.addAttribute("journeys",company.getJourneyDatabase());
	    		return "WelcomeC";
	    	}
	    	
	    	Client client = company.getClients().getClient(email);
	    	model.addAttribute("client",client);
	    	model.addAttribute("clientContainers",company.getContainerDatabase());
			model.addAttribute("clients",company.getClients());
			model.addAttribute("journeys",company.getJourneyDatabase());
	    	return "ViewClient";
	    }
	    
	    
	    @GetMapping("/clientJourneys")
	    public String removeContainer(Model model) throws IOException {
	    	
			LogisticCompany company = JSONWriter.getCompany();
			
			Client client = JSONWriter.getIn();
			
			Set<Container> clientContainers = company.getContainerDatabase().filterClient(client);
			
			Set<Journey> journeys = company.getJourneyDatabase().getJourneysFromContainers(clientContainers);
			
			
			JSONWriter.saveCompany(company);
			
			model.addAttribute("client",client);
			model.addAttribute("clientContainers",company.getContainerDatabase());
			model.addAttribute("journeys",journeys);
			
	    	return "clientJourneys";
	    }
	    
	    
		@PostMapping("/findJourneys")
		public String findJourneys(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
			
			response.setContentType("text/html");
			
			Set<Journey> journeys;
			          
			String portOfOrigin = request.getParameter("portOfOrigin");  
			String destination = request.getParameter("destination");
			 
			Client client = JSONWriter.getIn();
			LogisticCompany company = JSONWriter.getCompany();
			
			if (portOfOrigin.equals("") && !(destination.equals(""))) {
				journeys = company.getJourneyDatabase().filterDestination(destination);
			}
			else if (!(portOfOrigin.equals("")) && destination.equals("")) {
				journeys = company.getJourneyDatabase().filterPortOfOrigin(portOfOrigin);
			}
			else if (!(portOfOrigin.equals("")) && !(destination.equals(""))) {
				JourneyDatabase db = new JourneyDatabase();
				db.addAll(company.getJourneyDatabase().filterDestination(destination));
				journeys = db.filterPortOfOrigin(portOfOrigin);
			}
			else {
				Set<Container> clientContainers = company.getContainerDatabase().filterClient(client);
				journeys = company.getJourneyDatabase().getJourneysFromContainers(clientContainers);
			}
			
			model.addAttribute("client",client);
			model.addAttribute("clientContainers",company.getContainerDatabase());
			model.addAttribute("journeys",journeys);
			
			return "clientJourneys";
		}
			
			
			
			
			
			
	    
	    
}
