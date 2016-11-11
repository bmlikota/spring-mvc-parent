package hr.bm.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hr.bm.context.MyXmlBean;
import hr.bm.context.NotBeanClass;
import hr.bm.context.ReportingFileUtil;
import hr.bm.dto.ReportExample;
import hr.bm.dto.Thymeleaf;
import hr.bm.dto.User;
import hr.bm.web.validator.ThymeleafValidator;
import hr.bm.ws.MyWebService;
import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateFactory;

@Controller
public class ThymeleafController {

	final static Logger logger = LogManager.getLogger(ThymeleafController.class);

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

	OfficeManager officeManager;

	@PostConstruct
	public void start() {
		DefaultOfficeManagerConfiguration defaultOfficeManagerConfiguration = new DefaultOfficeManagerConfiguration();
		File file = new File("C:\\Users\\bmlikota\\Install\\OpenOffice\\OpenOfficePortable\\App\\openoffice");
		defaultOfficeManagerConfiguration.setOfficeHome(file);
		officeManager = defaultOfficeManagerConfiguration.buildOfficeManager();
		officeManager.start();
	}

	@PreDestroy
	public void stop() {
		officeManager.stop();
	}

	@RequestMapping(value = "/thymeleaf", method = RequestMethod.GET)
	public String thymeLeafExercise(Model model, @Valid Thymeleaf thymeleaf, Errors errors, HttpSession session) {

		thymeleafValidator.validate(thymeleaf, errors);

		// TESTOVI //
		logger.info("This is log4j info : Testovi - POCETAK");

		new NotBeanClass().print();
		System.out.println(myWebService.printMethod(": mmoj web servis :-)"));
		dataBaseTest(session);
		System.out.println("env.getProperty() = " + env.getProperty("datasource.url"));
		myXmlBean.print();

		logger.info("This is log4j info : Testovi - KRAJ");
		logger.debug("Debug log example.");
		logger.error("Error log example.");
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
	public @ResponseBody String getTime() {
		String result = "Current time <b>" + new Date().toString() + "</b>";
		return result;
	}

	@RequestMapping(value = "/thymeleaf/export", method = RequestMethod.GET)
	@ResponseBody
	public void exportPaymentOrder(final HttpServletResponse response) {

		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;

		final ReportExample reportExample = new ReportExample("reportName");

		final DocumentTemplateFactory templateFactory = new DocumentTemplateFactory();
		templateFactory.getFreemarkerConfiguration().setEncoding(new Locale("en_HR"), "UTF-8");
		final OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);

		final File documentFromTemplate = new File(ReportingFileUtil.createFileName("tmp-template", "odt"));
		final File outputDocument = new File(ReportingFileUtil.createFileName("tmp-out", "pdf"));
		try {
			final ClassPathResource classpathResource = new ClassPathResource("/META-INF/reports/reportExample.odt");
			inputStream = classpathResource.getInputStream();

			final DocumentTemplate template = templateFactory.getTemplate(inputStream);
			fileOutputStream = new FileOutputStream(documentFromTemplate);
			template.createDocument(reportExample.getReportData(), fileOutputStream);

	        converter.convert(documentFromTemplate, outputDocument);
	        byte[] outputFile = ReportingFileUtil.load(outputDocument);

			exportReport(response, outputFile, reportExample.getTemplateName(), ReportType.pdf);
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			ReportingFileUtil.closeStream(inputStream);
			ReportingFileUtil.delete(outputDocument, documentFromTemplate);
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void dataBaseTest(HttpSession session) {
		String sql = "SELECT username FROM users WHERE username = '" + session.getAttribute("username") + "'";
		PreparedStatement ps = null;

		ResultSet rs = null;
		try {
			Connection conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println(
						"Ovo je test konekcije na bazu. Iz baze sam dohvatio korisnika: " + rs.getString("username"));
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

	public enum ReportType {
		pdf, excel
	}

	public void exportReport(final HttpServletResponse p_response, final byte[] p_document, final String p_fileName,
			final ReportType p_reportType) throws IOException {
		p_response.setHeader("Pragma", "public");
		p_response.setHeader("Cache-control", "max-age=0");

		switch (p_reportType) {
		case pdf:
			p_response.setContentType("application/pdf");
			p_response.setHeader("Content-Disposition", "attachment; filename=" + p_fileName + ".pdf");
			break;
		case excel:
			p_response.setContentType("application/ms-excel");
			p_response.setHeader("Content-Disposition", "attachment; filename=" + p_fileName + ".xls");
		}
		p_response.getOutputStream().write(p_document);
	}

}
