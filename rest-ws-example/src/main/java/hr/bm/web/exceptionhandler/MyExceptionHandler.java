package hr.bm.web.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import hr.bm.dto.Error;
import hr.bm.error.BmRestDataNotFoundException;

@ControllerAdvice
public class MyExceptionHandler {

//	@ExceptionHandler(BmRestDataNotFoundException.class)
//	public ResponseEntity<Error> spittleNotFound(BmRestDataNotFoundException e) {
//		long id = e.getMyDataId();
//		Error error = new Error(4, "Data [" + id + "] not found");
//		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
//	}

	@ExceptionHandler(BmRestDataNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody Error spittleNotFound(BmRestDataNotFoundException e) {
		long id = e.getMyDataId();
		return new Error(4, "Data [" + id + "] not found");
	}

}
