package Cloud.controller;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Cloud.model.Container;
import net.bytebuddy.matcher.ModifierMatcher.Mode;

@Controller
public class CloudContainerController {
	@GetMapping("/")
	public String mainpage(Model model) {
		return "MainPage";
	}
	
	@GetMapping("/container/{id}")
	public String containerpage(@PathVariable("id") int id, Container container, Model model) {
		
		
		
		
		return "ContainerPage";
		
		
	}
	
	@GetMapping("/container")
	public String plotpage(Model model) {
		
		
		return "ContainerPage";
		
	}
	
	
	
//	@GetMapping("/displayTempChart")
//	public String tempChart(Model model) {
//		return "";
//		
//	}

}
