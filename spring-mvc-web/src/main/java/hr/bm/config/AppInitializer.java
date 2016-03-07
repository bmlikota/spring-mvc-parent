package hr.bm.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import hr.bm.config.security.SecurityConfig;

public abstract class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ContextConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// ovo potrebno da bi css radio
		servletContext.getServletRegistration("default").addMapping("/static/*");

		// Set profile
		servletContext.setInitParameter("spring.profiles.active", "posao");

		super.onStartup(servletContext);
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		// 2097152, 4194304, 0 -- to limit files to no more than 2 MB, to limit
		// the
		// entire request to no
		// more than 4 MB
		registration.setMultipartConfig(new MultipartConfigElement("C:/Users/bmlikota/MyTools", 2097152, 4194304, 0));
	}

}

// @Override
// protected WebApplicationContext createRootApplicationContext() {
// WebApplicationContext context = (WebApplicationContext)
// super.createRootApplicationContext();
// ((ConfigurableEnvironment)
// context.getEnvironment()).setActiveProfiles("posao");
// return context;
// }