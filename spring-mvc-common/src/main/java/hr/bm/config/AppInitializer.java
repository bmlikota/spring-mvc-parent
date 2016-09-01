package hr.bm.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public abstract class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
//		return new String[] { "/do/*" };
		/*
		 * If you had mapped the DispatcherServlet to /do/*, the URL patterns in the @RequestMapping
		 * annotations wouldn’t have changed, but in the browser address bar they would have /do in front
		 * of them.
		*/
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// ovo potrebno da bi css radio
		servletContext.getServletRegistration("default").addMapping("/static/*");

		// Set profile
		servletContext.setInitParameter("spring.profiles.active", "development");

		super.onStartup(servletContext);
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		// 2097152, 4194304, 0 -- to limit files to no more than 2 MB, to limit
		// the
		// entire request to no
		// more than 4 MB
		registration.setMultipartConfig(new MultipartConfigElement("C:/Users/Korisnik/MyTools", 2097152, 4194304, 0));
	}

}

// @Override
// protected WebApplicationContext createRootApplicationContext() {
// WebApplicationContext context = (WebApplicationContext)
// super.createRootApplicationContext();
// ((ConfigurableEnvironment)
// context.getEnvironment()).setActiveProfiles("development");
// return context;
// }