package hr.bm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

  @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
  public String home() {
    return "home/home";
  }

  @RequestMapping(value = { "/fragment" }, method = RequestMethod.GET)
  public String fragment() {
    return "fragments/header :: copy";
  }

}