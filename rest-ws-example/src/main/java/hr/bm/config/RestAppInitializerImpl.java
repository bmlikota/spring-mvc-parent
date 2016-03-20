package hr.bm.config;

import hr.bm.config.security.SecurityConfig;

public class RestAppInitializerImpl extends AppInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RestContextConfigImpl.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { RestWebConfigImpl.class };
	}

}
