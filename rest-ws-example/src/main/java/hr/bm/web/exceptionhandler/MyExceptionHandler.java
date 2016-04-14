package hr.bm.web.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import hr.bm.dto.Error;
import hr.bm.error.MyDataNotFoundException;

@ControllerAdvice
public class MyExceptionHandler {

//	@ExceptionHandler(MyDataNotFoundException.class)
//	public ResponseEntity<Error> spittleNotFound(MyDataNotFoundException e) {
//		long id = e.getMyDataId();
//		Error error = new Error(4, "Data [" + id + "] not found");
//		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
//	}

	@ExceptionHandler(MyDataNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody Error spittleNotFound(MyDataNotFoundException e) {
		long id = e.getMyDataId();
		return new Error(4, "Data [" + id + "] not found");
	}

}
