package hr.bm.config.webservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import hr.bm.service.MyWebService;

@Configuration
public class MyServiceConfig {

	@Bean
	@Profile("posao")
	public HttpInvokerProxyFactoryBean myWebService() {
		HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
		proxy.setServiceUrl("http://localhost:8095/jax-ws-example/myService.service");
		proxy.setServiceInterface(MyWebService.class);
		return proxy;
	}

	// @Bean
	// public JaxWsPortProxyFactoryBean myWebService() {
	// JaxWsPortProxyFactoryBean proxy = new JaxWsPortProxyFactoryBean();
	// URL wsdlDocumentUrl = null;
	// try {
	// wsdlDocumentUrl = new
	// URL("http://localhost:8094/services/myWebService?wsdl");
	// } catch (Exception e) {
	// throw new RuntimeException(e);
	// }
	// proxy.setWsdlDocumentUrl(wsdlDocumentUrl);
	// proxy.setServiceName("myWebService");
	// proxy.setPortName("MyWebServiceImplPort");
	// proxy.setServiceInterface(MyWebService.class);
	// proxy.setNamespaceUri("http://ws.bm.hr/");
	// return proxy;
	// }

}
