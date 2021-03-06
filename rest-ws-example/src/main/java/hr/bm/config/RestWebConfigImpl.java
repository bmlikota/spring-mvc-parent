package hr.bm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import hr.bm.config.rest.RestWSConfig;

@Configuration
@EnableWebMvc
@Import({ RestWSConfig.class })
@ComponentScan(basePackages = { "hr.bm.web" })
public class RestWebConfigImpl extends WebMvcConfigurerAdapter {

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		RestWSConfig.configureContentNegotiation(configurer);
	}

}