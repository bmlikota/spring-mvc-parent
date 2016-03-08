package hr.bm.config;

import hr.bm.config.security.SecurityConfig;

public class AppInitializerImpl extends AppInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ContextConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfigImpl.class };
	}

}
