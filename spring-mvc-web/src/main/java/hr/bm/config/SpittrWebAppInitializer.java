package hr.bm.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// public class SpittrWebAppInitializer extends
// AbstractAnnotationConfigDispatcherServletInitializer {
//
// @Override
// protected Class<?>[] getRootConfigClasses() {
// return new Class<?>[] { ContextConfig.class };
// }
//
// @Override
// protected Class<?>[] getServletConfigClasses() {
// return new Class<?>[] { WebConfig.class };
// }
//
// @Override
// protected String[] getServletMappings() {
// return new String[] { "/" };
// }
//
// }

public class SpittrWebAppInitializer implements WebApplicationInitializer {

  public void onStartup(ServletContext servletContext) throws ServletException
  {
    // static resources
    servletContext.getServletRegistration("default").addMapping("/static/*");

    AnnotationConfigWebApplicationContext rootContext = new
        AnnotationConfigWebApplicationContext();
    rootContext.register(ApplicationContextConfig.class);
    servletContext.addListener(new ContextLoaderListener(rootContext));

    AnnotationConfigWebApplicationContext webServletContext = new
        AnnotationConfigWebApplicationContext();
    webServletContext.register(WebConfig.class);
    ServletRegistration.Dynamic dispatcher =
        servletContext.addServlet("springWebDispatcher", new DispatcherServlet(
            webServletContext));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");


    // 2097152, 4194304, 0 -- to limit files to no more than 2 MB, to limit the
    // entire request to no
    // more than 4 MB
    dispatcher.setMultipartConfig(new MultipartConfigElement("/home/bmlikota/MyTools", 2097152, 4194304, 0));
  }

}