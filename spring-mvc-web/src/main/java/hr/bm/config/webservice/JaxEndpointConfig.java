package hr.bm.config.webservice;

import java.net.URL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;

import hr.bm.annotation.DevelopmentProfile;
import hr.bm.annotation.ProductionProfile;
import hr.bm.ws.MyWebService;

@Configuration
public class JaxEndpointConfig {

	@ProductionProfile
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

	@DevelopmentProfile
	@Bean(name = "myWebService")
	public MyWebServiceMock myWebServiceMock() {
		return new MyWebServiceMock();
	}

	private class MyWebServiceMock implements MyWebService {

		public String printMethod(String name) {
			return "Print from JaxEndpointConfig.MyWebServiceMock! :-))))";
		}
		
	}

}
