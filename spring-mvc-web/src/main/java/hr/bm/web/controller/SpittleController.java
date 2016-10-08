package hr.bm.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hr.bm.annotation.Cold;
import hr.bm.annotation.Creamy;
import hr.bm.context.SpittleRepository;
import hr.bm.dto.Spittle;
import hr.bm.error.SpittleNotFoundException;
import hr.bm.error.WrongIdException;

@Controller
public class SpittleController {

  @Autowired
  @Cold
  @Creamy
  public SpittleRepository srep;

  @Autowired
  ApplicationContext applicationContext;

  @ModelAttribute("modelAttributeMsg")
  public String isLimitCheckNeeded() {
	    return "This is model attribute msg, visible only in the SpittleController :-)";
  }

  @RequestMapping(value = "/spittles", method = RequestMethod.GET)
  public String spittles(Model model) {
    // System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
    model.addAttribute("spittleList", srep.getSpittles());
    return "spittle/spittles";
  }


  @RequestMapping(value = "/spittle/{spittleId}", method = RequestMethod.GET)
  public String spittle(@PathVariable("spittleId") Long spittleId, Model model) {
    // ((Object) null).equals(null); // za testiranje AppWideExceptionHandler
    try {
      Spittle spittle = srep.findOne(spittleId);
      if (spittleId > 400) {
        throw new WrongIdException(); // ovo hendla handleDuplicateSpittle()
      } else if (spittle == null) {
        throw new SpittleNotFoundException();
      }
      model.addAttribute("one", spittle);
    } catch (SpittleNotFoundException e) {
      // throw e; // HTTP Status 404 - Spittle Not Found
      return "errors/error";
    }
    return "spittle/spittle";
  }

  @ExceptionHandler(WrongIdException.class)
  public String handleDuplicateSpittle() {
    return "errors/error2";
  }

  @RequestMapping(value = "/add-spittle", method = RequestMethod.GET)
  public String showRegistrationForm(Model model) {
    Spittle spittle = new Spittle(new Long("-1"), "(message)");
    model.addAttribute("spittle", spittle);
    return "spittle/add-spittle";
  }

  @RequestMapping(value = "/add-spittle", method = RequestMethod.POST)
  public String processRegistration(@Valid Spittle spittle, Errors errors) {
    if (errors.hasErrors()) {
      return "spittle/add-spittle";
    }

    srep.add(spittle);
    return "spittle/add-spittle";
  }

  // TODO Ovo smeta websocket-u!!!!!!!!!!!!!!!!!! Ovo ne kuzim jos.
//  @RequestMapping(method = RequestMethod.GET)
//  public List<Spittle> spittles(
//      @RequestParam(value = "min", defaultValue = "0") int min,
//      @RequestParam(value = "max", defaultValue = "20") int max) {
//    return findSpittlesForTest(min, max);
//  }

  public List<Spittle> findSpittlesForTest(int min, int max) {
    ArrayList<Spittle> spittles = new ArrayList<Spittle>();
    for (int i = min; i < max; i++) {
      spittles.add(new Spittle(new Long("" + i), "Spittle " + i));
    }
    return spittles;
  }

}
