package hr.bm.config.messages;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

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

}
