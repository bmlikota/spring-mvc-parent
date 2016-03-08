package hr.bm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import hr.bm.config.thymeleaf.ThymeleafConfig;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "hr.bm.web" })
@Import({ThymeleafConfig.class})
public class WebConfigImpl extends WebConfig {}