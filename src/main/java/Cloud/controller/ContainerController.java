package Cloud.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Cloud.model.*;

import net.bytebuddy.matcher.ModifierMatcher.Mode;


@Controller

public class ContainerController extends HttpServlet {
		
	@GetMapping("/")
	public String mainpage(Model model) {
		
		
		return "index";
		
	}
	
	@GetMapping("/ClientLogin")
	public String add(Container container,Model model) {
		return "ClientLogin";
		
	}
	@PostMapping("/ClientLogin")
	public String add(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
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
			return "WelcomeC";
	    }    
	return "CompanyLogin";}
	
	@GetMapping("/WelcomeC")
	public String WelcomeCompany(Model model) throws IOException {
	    LogisticCompany company = JSONWriter.getCompany();
	    model.addAttribute("clientContainers", company.getContainerDatabase());
	    
	    return "WelcomeC";

	}
		
	@GetMapping("/Welcome" )
	public String welcome() {
		return "Welcome";
			

}
		@GetMapping("/container")
		public String plotpage(Model model) {
			
			
			return "ContainerPage";
			
		}
		
		@GetMapping("/UpdateInfo")
		public String updateinfo(Model model) {
			return "ClientUpdate";
			
		}
		@GetMapping("/ContainerInfo")
		public String containerInfo(Model model) {
			return "ContainerInfo";
		}
//		@PostMapping("/ContainerInfo")
//		public String containerInfo(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException   {
//		    
//	
//		}
		@PostMapping("/UpdateInfo")
		public String updateinfo(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
			response.setContentType("text/html");  
			PrintWriter out = response.getWriter();  
			          
			String n=request.getParameter("userMail");  
			int pn= Integer.parseInt(request.getParameter("userNumber"));  
			JSONWriter jw = new JSONWriter();   
			LogisticCompany company = jw.getCompany();
			   	
	    	ClientDatabase ClientDB = company.getClients();
//	    	ClientDB.getClient(email)
//	    	
//	    	jw.Remove(client2.getEmail());
//	    	client2.updateClient(pn);
//	    	client2.updateClient(n);
//	
//	    	jw.addClient2(client2);
//	    	jw.addClient(client2);
//	    	model.addAttribute("client",client2);
	    	return "Welcome";
	    	       		
		}
		@GetMapping("/Register")
		public String Register()  {
			return "Register";
			
		}
		@PostMapping("/Register")
		public String Register(HttpServletRequest request, HttpServletResponse response) throws IOException {
			 
			
			response.setContentType("text/html");  
		    PrintWriter out = response.getWriter();  
		    String n=request.getParameter("userName");  
		    String m=request.getParameter("userMail"); 
		    String a=request.getParameter("userAge");  
		    String g=request.getParameter("userGender"); 
		    int pn= Integer.parseInt(request.getParameter("userNumber"));  
		    String p=request.getParameter("userPass"); 
		    Client client = new Client(n,m,a,g,pn,p);
		    
		    JSONWriter jw = new JSONWriter();
		    ResponseObject Response = new ResponseObject();
		    Validator.validInput(n,m,a,g,pn);
		    if(Response.getErrorMessage().equals("Valid")) {
		    	//jw.addClient(client);
		        return "ClientLogin";
		    }
	        
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
