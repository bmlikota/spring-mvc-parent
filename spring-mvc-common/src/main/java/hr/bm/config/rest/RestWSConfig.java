package hr.bm.config.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

@Configuration
public class RestWSConfig {

	@Bean
	public ViewResolver cnViewResolver(ContentNegotiationManager cnm) {
		ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
		cnvr.setContentNegotiationManager(cnm);
		return cnvr;
	}

}
