package hr.bm.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

@Configuration
@ComponentScan(basePackages = { "hr.bm.ws" })
class ApplicationContextConfig {

	@Bean
	public SimpleJaxWsServiceExporter jaxWsExporter() {
		SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
		exporter.setBaseAddress("http://localhost:8095/services/");
		return exporter;
	}

}