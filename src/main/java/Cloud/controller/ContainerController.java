package Cloud.controller;

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

import Cloud.model.ClientDatabase;
import Cloud.model.*;

import net.bytebuddy.matcher.ModifierMatcher.Mode;
import Cloud.model.Container;
import Cloud.model.JSONWriter;
import Cloud.model.Client;


@Controller

public class ContainerController extends HttpServlet {
		
	@GetMapping("/")
	
	public String mainpage(Model model) {
		return "MainPage";
		
	}
	@GetMapping("/ClientLogin")
	public String add(Container container,Model model) {
		return "ClientLogin";
		
	}
	@PostMapping("/ClientLogin")
	public String add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		 response.setContentType("text/html");  
		    PrintWriter out = response.getWriter();  
		          
		    String n=request.getParameter("userName");  
		    String p=request.getParameter("userPass");  
		    JSONWriter jw = new JSONWriter(); 
		    if(jw.checkPass(n, p)){ 
		    	 ClientDatabase db = jw.dbReturner();
		         Cloud.model.Client client = db.getClient(n);
		         
		         jw.addClient2(client);
		         RequestDispatcher rd=request.getRequestDispatcher("/Welcome.html");  
		         System.out.println("Hola");

			
			return "Welcome";
		}
		    System.out.println("No good");
		return "ClientLogin";}
		
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
		@PostMapping("/UpdateInfo")
		public String updateinfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
			  response.setContentType("text/html");  
			    PrintWriter out = response.getWriter();  
			          
			    String n=request.getParameter("userMail");  
			    int pn= Integer.parseInt(request.getParameter("userNumber"));  
			    JSONWriter jw = new JSONWriter();   
			    	  	
			    	
			    	 Client client2 = jw.getClient();
			    	 jw.Remove(client2.getEmail());


			    	 
			    	 if (n!= "") {
			    		  client2.setEmail(n);
			    	 }
			    	 if(pn!=0) {
			        	 client2.setNumber(pn);

			    		 
			    	 }
			    	 jw.addClient2(client2);
			    	 jw.addClient(client2);
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
	        jw.addClient(client);
	        return "ClientLogin";
			
		}
		
	
}
