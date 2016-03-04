package hr.bm.config.messages;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MessagesConfig {

	/**
	 * Bean koji sluzi da bi locirali properties datoteke za poruke.
	 * @return
	 */
	@Bean
	public ResourceBundleMessageSource messageSource() {
		final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		String[] basenamesSpecific = { "properties.messages.common.messages", "properties.messages.app.thymeleaf" };
		source.setBasenames(basenamesSpecific);
		source.setUseCodeAsDefaultMessage(true);
		source.setDefaultEncoding("UTF-8");
		return source;
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		resolver.setCookieName("localeCookie");
		resolver.setCookieMaxAge(4800);
		return resolver;
	}

	public static LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

}
