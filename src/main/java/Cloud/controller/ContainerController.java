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
	
}
