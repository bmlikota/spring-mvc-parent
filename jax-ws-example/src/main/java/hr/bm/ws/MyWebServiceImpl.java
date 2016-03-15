package hr.bm.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.stereotype.Component;

//Service Implementation
// url: http://localhost:8095/services/myWebService?wsdl
@Component
@WebService(serviceName = "myWebService")
public class MyWebServiceImpl implements MyWebService {

	@WebMethod
	public String printMethod(String name) {
		return "Hello World JAX-WS " + name;
	}

}
