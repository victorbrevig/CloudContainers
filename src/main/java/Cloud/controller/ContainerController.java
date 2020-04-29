package Cloud.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Optional;

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
			model.addAttribute("client",client);
			return "Welcome";
		}
	    
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
	    System.out.println("her til");
	    if (company.getPassword().equals(pass) && company.getName().equals(name)) {
	    	RequestDispatcher rd=request.getRequestDispatcher("/WelcomeC.html"); 
			model.addAttribute("company",company);
			model.addAttribute("clientContainers",company.getContainerDatabase());

			return "WelcomeC";
			
			
	    }    
	return "CompanyLogin";}
	
	@GetMapping("/WelcomeC")
	public String WelcomeCompany(Model model) throws IOException {

	    return "WelcomeC";

	}
		
	@GetMapping("/Welcome" )
	public String welcome(ModelMap model, @ModelAttribute("client") Client client) {
		model.addAttribute("client",client);
		return "Welcome";
			

}
		@GetMapping("/container")
		public String plotpage(Model model) {
			return "ContainerPage";
			
		}
		
		@GetMapping("/UpdateInfo")
		public String updateinfo(ModelMap model) throws FileNotFoundException {
			Client client = JSONWriter.getIn();
			model.addAttribute("client",client);
			return "ClientUpdate";
			
		}
<<<<<<< HEAD
		@GetMapping("/ContainerInfo")
		public String containerInfo(Model model) {
			return "ContainerInfo";
		}
//		@PostMapping("/ContainerInfo")
//		public String containerInfo(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException   {
//		    
//	
//		}
=======

>>>>>>> redirectRefactor
		@PostMapping("/UpdateInfo")
		public String updateinfo(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
			
			response.setContentType("text/html");  
			PrintWriter out = response.getWriter();  
			          
			String email = request.getParameter("userMail");  
			int number = Integer.parseInt(request.getParameter("userNumber"));  
			Client client = JSONWriter.getIn();
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
				return "Welcome";
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
		    PrintWriter out = response.getWriter();  
		    String name = request.getParameter("userName");  
		    String email = request.getParameter("userMail"); 
		    String birthdate = request.getParameter("userBirthdate");  
		    String gender = request.getParameter("userGender"); 
		    int phonenumber = Integer.parseInt(request.getParameter("userNumber"));  
		    String password = request.getParameter("userPass"); 
		    Client client = new Client(name,email,birthdate,gender,phonenumber,password);
		    
		    LogisticCompany company = JSONWriter.getCompany();
			responseObject1 = company.newClient(client);
		 
		    if(responseObject1.getErrorMessage().equals("Client was successfully added")) {
		    	JSONWriter.saveCompany(company);
		        return "ClientLogin";
		    }
		    model.addAttribute("response", responseObject1);
	        return "Register";
			
		}
		
		@GetMapping("/container/{id}")
		public String containerPage(@PathVariable("id") int id, Container container,Model model) {
			
			
			return "ContainerPage";
			
		}
		
		@GetMapping("/logout")
		public String logout(Model model, Client client) throws IOException {
			
			//JSONWriter.clearLoggedIn();
			
			return "MainPage";
			
		}
		
		
	
}
