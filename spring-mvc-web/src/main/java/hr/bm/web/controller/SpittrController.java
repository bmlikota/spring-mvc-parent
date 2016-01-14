package hr.bm.web.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import hr.bm.dto.Spittr;

@Controller
@RequestMapping(value = "/spittr")
public class SpittrController {

  @Autowired
  DataSource dataSource;

  private static ArrayList<Spittr> spittrRepository = new ArrayList<Spittr>();

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String showRegistrationForm(Model model) {
    Spittr spittr = new Spittr();
    // Spittr spittr = null; // za testiranje AppWideExceptionHandler
    // spittr.setUsername("bmlikota");
    model.addAttribute("spittr", spittr);
    return "registerForm";
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture, @Valid Spittr spittr, Errors errors, Model model) throws IOException {
    if (errors.hasErrors()) {
      return "registerForm";
    }

//    File doc = new File("/home/bmlikota/MyTools/" + profilePicture.getOriginalFilename());
//    profilePicture.transferTo(doc);
//    ReadExcelFile.read(doc.getAbsolutePath());

    spittrRepository.add(spittr);
    model.addAttribute("username", spittr.getUsername());
    // model.addAttribute("lastName", spittr.getLastName()); // mogu dodati bilo
    // koji kljuc i vrijednost

    return "redirect:/spittr/{username}";
    // return "redirect:/spittr/" + spittr.getUsername(); // ovo radi bez modela
  }

  @RequestMapping(value = "/{username}", method = RequestMethod.GET)
  public String showspittrProfile(@PathVariable String username, Model model) {
    Spittr spittr = findByUsername(username);
    model.addAttribute(spittr);
    return "profile";
  }

  public Spittr findByUsername(String userame) {
    for (Spittr spitt : spittrRepository) {
      if (userame.equals(spitt.getUsername())) {
        return spitt;
      }
    }
    return null;
  }

}