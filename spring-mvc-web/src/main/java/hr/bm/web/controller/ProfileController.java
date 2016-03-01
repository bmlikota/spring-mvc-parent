package hr.bm.web.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hr.bm.dto.User;

@Controller
//@RequestMapping(value = "/profile")
public class ProfileController {

  @RequestMapping(value = "/login-page", method = RequestMethod.GET)
  public String showRegistrationForm(Model model) {
	  User user = new User();
    // Spittr spittr = null; // za testiranje AppWideExceptionHandler
    // spittr.setUsername("bmlikota");
    model.addAttribute("user", user);
    return "login/loginPage";
  }

  @RequestMapping(value = "/login-page", method = RequestMethod.POST)
  public String processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture, @Valid User user, Errors errors, RedirectAttributes model) throws IOException {
    if (errors.hasErrors()) {
      return "login/loginPage";
    }

//    File doc = new File(profilePicture.getOriginalFilename());
//    profilePicture.transferTo(doc);
//    ReadExcelFile.read(doc.getAbsolutePath());

    model.addAttribute("username", user.getUsername()); // ovo ce biti path varijabla
    model.addAttribute("lastName", user.getLastName()); // mogu dodati bilo koji kljuc - ovo ce biti request parametar
    model.addAttribute("param1", "Ne postoji request param u redirect metodi"); // mogu dodati bilo koji kljuc - ovo ce biti request parametar
    model.addFlashAttribute("user", user); // saljem cijeli objekt koji ce zivjeti samo u redirectu

    return "redirect:/profile/{username}";
    // return "redirect:/spittr/" + spittr.getUsername(); // ovo radi bez modela
  }

  @RequestMapping(value = "/profile/{username}", method = RequestMethod.GET)
  public String showProfile(@PathVariable String username, @RequestParam("lastName") String lastName, Model model) {
    System.out.println("Da li je user poslan kao flash atribut: " + model.containsAttribute("user"));
//	Spittr spittr = findByUsername(username);
//    model.addAttribute(spittr);
    return "login/profile";
  }

}