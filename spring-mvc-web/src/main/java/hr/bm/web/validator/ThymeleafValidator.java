package hr.bm.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import hr.bm.dto.Thymeleaf;

@Component
public class ThymeleafValidator implements Validator {

  @SuppressWarnings("rawtypes")
  public boolean supports(Class clazz) {
    return Thymeleaf.class.isAssignableFrom(clazz);
  }

  public void validate(Object target, Errors errors) {
    if (((Thymeleaf) target).getEmailAddress() == null) {
      errors.rejectValue("emailAddress", "emailAddress.manadatory", null, "Wrong email address!");
    }

    if (((Thymeleaf) target).getSpittr() == null || ((Thymeleaf) target).getSpittr().getFirstName() == null) {
      // ovo je za <input type="text"
      // th:field="${thymeleaf.spittr.firstName}"></input>
      errors.rejectValue("spittr.firstName", "spittr.firstName.manadatory", null, "Wrong spittr first name!");
      // ovo je za <input type="text" th:field="${thymeleaf.firstName}"></input>
      errors.rejectValue("firstName", "firstName.manadatory", null, "Wrong spittr first name!");
    }
  }

}
