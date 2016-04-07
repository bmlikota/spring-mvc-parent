package hr.bm.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class RestAppInitializerImpl extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RestContextConfigImpl.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { RestWebConfigImpl.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
