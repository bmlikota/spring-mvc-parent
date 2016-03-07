package hr.bm.config;

import hr.bm.config.security.SecurityConfig;

public class StockAppInitializer extends AppInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { StockContextConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { StockWebConfig.class };
	}

}
