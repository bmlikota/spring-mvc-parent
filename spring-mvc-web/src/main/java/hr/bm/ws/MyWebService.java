package hr.bm.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Hello world!
 */
@WebService
public interface MyWebService {
 
	@WebMethod String printMethod(String name);

}