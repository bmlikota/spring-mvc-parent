package hr.bm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;

import hr.bm.config.aspect.AspectConfig;
import hr.bm.config.cache.EhCacheConfig;
import hr.bm.config.datasource.H2Config;
import hr.bm.config.fileupload.FileUploadConfig;
import hr.bm.config.mail.MailConfig;
import hr.bm.config.messages.MessagesConfig;
import hr.bm.config.webservice.JaxEndpointConfig;

@Configuration
@ComponentScan(basePackages = { "hr.bm.context", "hr.bm.utils" }, excludeFilters = @ComponentScan.Filter(Controller.class))
@ImportResource({ "classpath:/config/xmlbeans/bean-context.xml" })
@Import({EhCacheConfig.class, H2Config.class, JaxEndpointConfig.class, MessagesConfig.class, AspectConfig.class, FileUploadConfig.class, MailConfig.class})
public class ContextConfigImpl extends ContextConfig {}
