package hr.bm.config.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class RestWSConfig {

	@Bean
	public ViewResolver cnViewResolver(ContentNegotiationManager cnm) {
		ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
		cnvr.setContentNegotiationManager(cnm);
		return cnvr;
	}

	/**
	 * Look up views as beans.
	 * @return
	 */
	@Bean
	public ViewResolver benaNameViewResolver() {
		return new BeanNameViewResolver();
	}

	/**
	 * Bean "Spittles" handles view "spittles"
	 * @return
	 */
	@Bean
	public View spittles() {
		return new MappingJackson2JsonView();
	}

	/**
	 * Configure ContentNegotiationManager
	 * @param configurer
	 */
	public static void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.TEXT_HTML);
	}

}
