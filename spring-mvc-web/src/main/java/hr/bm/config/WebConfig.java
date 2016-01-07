package hr.bm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "hr.bm.web" })
public class WebConfig extends WebMvcConfigurerAdapter {

	// @Bean
	// public ViewResolver viewResolver() {
	// InternalResourceViewResolver resolver = new
	// InternalResourceViewResolver();
	//
	// resolver.setPrefix("/WEB-INF/views/");
	// resolver.setSuffix(".jsp");
	// resolver.setExposeContextBeansAsAttributes(true);
	// resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
	//
	// return resolver;
	// }

	// <link th:href="@{/static/app/styles.css}" rel="stylesheet"
		// type="text/css" />
	// @Override
	// public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	// }
	//
	// @Override
	// public void
	// configureDefaultServletHandling(DefaultServletHandlerConfigurer
	// conf) {
	// conf.enable();
	// }

	/*
	 * ****************** Thymeleaf ****************************************
	 */


	@Bean
	public ServletContextTemplateResolver templateResolver() {
		final ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

	@Bean
	public ViewResolver thymeleafViewResolver() {
		final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		return viewResolver;
	}

}