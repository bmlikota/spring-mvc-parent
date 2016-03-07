package hr.bm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import hr.bm.config.messages.MessagesConfig;
import hr.bm.config.thymeleaf.ThymeleafConfig;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "hr.bm.web" })
@Import({ThymeleafConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/static/**")
			.addResourceLocations("/static/")
			.setCachePeriod(60 * 60 * 24 * 365);
	}

//	@Override
//	public void addInterceptors(final InterceptorRegistry registry) {
//		registry.addInterceptor(new CommonInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
//	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(MessagesConfig.localeChangeInterceptor());
	}

}