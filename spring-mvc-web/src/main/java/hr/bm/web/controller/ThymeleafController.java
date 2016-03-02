package hr.bm.web.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.bm.context.MyXmlBean;
import hr.bm.context.NotBeanClass;
import hr.bm.dto.Thymeleaf;
import hr.bm.dto.User;
import hr.bm.service.MyWebService;
import hr.bm.web.validator.ThymeleafValidator;

@Controller
public class ThymeleafController {

	@Autowired
	DataSource dataSource;

	@Autowired
	ThymeleafValidator thymeleafValidator;

	@Autowired
	Environment env;

	@Autowired
	MyWebService myWebService;

	@Autowired
	MyXmlBean myXmlBean;

	@RequestMapping(value = "/thymeleaf", method = RequestMethod.GET)
	public String thymeLeafExercise(Model model, @Valid Thymeleaf thymeleaf, Errors errors) {

		thymeleafValidator.validate(thymeleaf, errors);

		// TESTOVI //
		new NotBeanClass().print();
		System.out.println(myWebService.printMethod(": mmoj web servis :-)"));
		dataBaseTest();
		System.out.println("env.getProperty() = " + env.getProperty("datasource.url"));
		myXmlBean.print();
		// TESTOVI - END //

		setUsers(thymeleaf);
		thymeleaf.setUsername("bmlikota");
		// thymeleaf.setFirstName("Branko");
		thymeleaf.setLastName("Mlikota");
		// thymeleaf.setPassword("pass");
		model.addAttribute("thymeleaf", thymeleaf);
		model.addAttribute("today", new java.util.Date());
		model.addAttribute("dijakritik", "�");

		return "thymeleaf/thymeleaf";
	}

	@RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
    public @ResponseBody
    String getTime() {
        String result = "Current time <b>" + new Date().toString() + "</b>";
        return result;
    }

	private void dataBaseTest() {
		String sql = "SELECT username FROM users WHERE username = 'bmlikota'";
		PreparedStatement ps = null;

		ResultSet rs = null;
		try {
			Connection conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println(
						"Ovo je test konekcije na bazu. Iz baze sam dohvatio korisnika: "
								+ rs.getString("username"));
			}

		} catch (SQLException se) {
			throw new RuntimeException(se);
		}
	}

	private void setUsers(Thymeleaf thymeleaf) {
		ArrayList<User> list = new ArrayList<User>();
		list.add(new User("Iva", "Ivi�", "iivic", "pass1"));
		list.add(new User("Maja", "Maji�", "mmajic", "pass2"));
		list.add(new User("Bruna", "Bruni", "", "pass3"));
		list.add(new User("Lolita", "Lol", "llol", "pass4"));
		thymeleaf.setList(list);
	}

}
