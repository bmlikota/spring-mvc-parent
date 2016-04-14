package hr.bm.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Hello world!
 */
@WebService
public interface MyWebService {
 
	@WebMethod String printMethod(@WebParam(name = "text") String name);

}