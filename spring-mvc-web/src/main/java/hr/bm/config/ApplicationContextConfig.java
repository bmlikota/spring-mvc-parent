package hr.bm.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import hr.bm.service.MyWebService;
import liquibase.integration.spring.SpringLiquibase;

@Configuration
@ComponentScan(basePackages = { "hr.bm.context", "hr.bm.aspect" })
@EnableAspectJAutoProxy(proxyTargetClass = true)
@PropertySources({ @PropertySource("classpath:/properties/config/datasource.properties") })
@ImportResource({"classpath:/config/xmlbeans/bean-context.xml"})
class ApplicationContextConfig {

	/**
	 * Bean koji sluzi da bi locirali properties datoteke za poruke.
	 * 
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("properties.messages.common.messages");
		messageSource.setCacheSeconds(10);
		return messageSource;
	}

	@Autowired
	Environment env;

	@Profile("posao")
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(env.getProperty("datasource.url")); // Runtime
																// injection
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		dataSource.setInitialSize(20);
		dataSource.setMaxActive(30);
		return dataSource;
	}

	@Bean
	public SpringLiquibase springLiquibase() {
		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setDataSource(dataSource());
		springLiquibase.setChangeLog("classpath:config/liquibase/db-changelog.xml");
		return springLiquibase;
	}

//	@Bean
//	public JaxWsPortProxyFactoryBean myWebService() {
//		JaxWsPortProxyFactoryBean proxy = new JaxWsPortProxyFactoryBean();
//		URL wsdlDocumentUrl = null;
//		try {
//			wsdlDocumentUrl = new URL("http://localhost:8094/services/myWebService?wsdl");
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		proxy.setWsdlDocumentUrl(wsdlDocumentUrl);
//		proxy.setServiceName("myWebService");
//		proxy.setPortName("MyWebServiceImplPort");
//		proxy.setServiceInterface(MyWebService.class);
//		proxy.setNamespaceUri("http://ws.bm.hr/");
//		return proxy;
//	}

	@Bean
	public HttpInvokerProxyFactoryBean myWebService() {
		HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
		proxy.setServiceUrl("http://localhost:8095/jax-ws-example/myService.service");
		proxy.setServiceInterface(MyWebService.class);
		return proxy;
	}


	@Bean
	public MultipartResolver multipartResolver() throws IOException {
		return new StandardServletMultipartResolver();
	}
	
}