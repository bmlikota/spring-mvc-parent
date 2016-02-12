package hr.bm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import hr.bm.config.aspect.AspectConfig;
import hr.bm.config.cache.EhCacheConfig;
import hr.bm.config.datasource.H2Config;
import hr.bm.config.fileupload.FileUploadConfig;
import hr.bm.config.messages.MessagesConfig;
import hr.bm.config.webservice.MyServiceConfig;

@Configuration
@ComponentScan(basePackages = { "hr.bm.context" })
@ImportResource({ "classpath:/config/xmlbeans/bean-context.xml" })
@Import({EhCacheConfig.class, H2Config.class, MyServiceConfig.class, MessagesConfig.class, AspectConfig.class, FileUploadConfig.class})
class ApplicationContextConfig {}
