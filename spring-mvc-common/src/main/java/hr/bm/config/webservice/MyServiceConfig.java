package hr.bm.config.webservice;

import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import hr.bm.ws.MyWebService;

@Configuration
public class MyServiceConfig {

	@Bean
	public JaxWsPortProxyFactoryBean myWebService() {
		JaxWsPortProxyFactoryBean proxy = new JaxWsPortProxyFactoryBean();
		URL wsdlDocumentUrl = null;
		try {
			wsdlDocumentUrl = new URL("http://localhost:8095/services/myWebService?wsdl");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		proxy.setWsdlDocumentUrl(wsdlDocumentUrl);
		proxy.setServiceName("myWebService");
		proxy.setPortName("MyWebServiceImplPort");
		proxy.setServiceInterface(MyWebService.class);
		proxy.setNamespaceUri("http://ws.bm.hr/");
		return proxy;
	}

}
