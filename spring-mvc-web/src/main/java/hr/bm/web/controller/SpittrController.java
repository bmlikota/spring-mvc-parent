package hr.bm.web.controller;

import java.io.IOException;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import hr.bm.dto.Spittr;

@Controller
//@RequestMapping(value = "/spittr")
public class SpittrController {

  @Autowired
  DataSource dataSource;

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String showRegistrationForm(Model model) {
    Spittr spittr = new Spittr();
    // Spittr spittr = null; // za testiranje AppWideExceptionHandler
    // spittr.setUsername("bmlikota");
    model.addAttribute("spittr", spittr);
    return "login/loginPage";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture, @Valid Spittr spittr, Errors errors, RedirectAttributes model) throws IOException {
    if (errors.hasErrors()) {
      return "login/loginPage";
    }

//    File doc = new File(profilePicture.getOriginalFilename());
//    profilePicture.transferTo(doc);
//    ReadExcelFile.read(doc.getAbsolutePath());

    model.addAttribute("username", spittr.getUsername()); // ovo ce biti path varijabla
    model.addAttribute("lastName", spittr.getLastName()); // mogu dodati bilo koji kljuc - ovo ce biti request parametar
    model.addAttribute("param1", "Ne postoji request param u redirect metodi"); // mogu dodati bilo koji kljuc - ovo ce biti request parametar
    model.addFlashAttribute("spittr", spittr); // saljem cijeli objekt koji ce zivjeti samo u redirectu

    return "redirect:/spittr/{username}";
    // return "redirect:/spittr/" + spittr.getUsername(); // ovo radi bez modela
  }

  @RequestMapping(value = "/spittr/{username}", method = RequestMethod.GET)
  public String showspittrProfile(@PathVariable String username, @RequestParam("lastName") String lastName, Model model) {
    System.out.println("Da li je spittr poslan kao flash atribut: " + model.containsAttribute("spittr"));
//	Spittr spittr = findByUsername(username);
//    model.addAttribute(spittr);
    return "login/profile";
  }

}