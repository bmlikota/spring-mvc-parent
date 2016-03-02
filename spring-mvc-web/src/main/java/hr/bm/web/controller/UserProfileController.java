package hr.bm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/profile")
public class UserProfileController {

	 @RequestMapping(value = "/{username}", method = RequestMethod.GET)
	  public String showProfile(@PathVariable String username, @RequestParam("lastName") String lastName, Model model) {
	    System.out.println("Da li je user poslan kao flash atribut: " + model.containsAttribute("user"));
//		Spittr spittr = findByUsername(username);
//	    model.addAttribute(spittr);
	    return "register/profile";
	  }
}
