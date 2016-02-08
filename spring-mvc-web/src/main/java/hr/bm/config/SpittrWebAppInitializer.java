package hr.bm.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import hr.bm.config.security.CommonSecurityConfig;

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ApplicationContextConfig.class, CommonSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

//	@Override
//	protected WebApplicationContext createRootApplicationContext() {
//		WebApplicationContext context = (WebApplicationContext) super.createRootApplicationContext();
//		((ConfigurableEnvironment) context.getEnvironment()).setActiveProfiles("posao");
//		return context;
//	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// ovo potrebno da bi css radio
		servletContext.getServletRegistration("default").addMapping("/static/*");

	    // Set profile
		servletContext.setInitParameter("spring.profiles.active", "posao");

		super.onStartup(servletContext);
	}

}

//public class SpittrWebAppInitializer impled ments WebApplicationInitializer {
//
//  public void onStartup(ServletContext servletContext) throws ServletException
//  {
//    // 2097152, 4194304, 0 -- to limit files to no more than 2 MB, to limit the
//    // entire request to no
//    // more than 4 MB
//    dispatcher.setMultipartConfig(new MultipartConfigElement("C:/Users/bmlikota/MyTools", 2097152, 4194304, 0));
//  }
//
//}