package hr.bm.web.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppWideExceptionHandlerForNullPointer {

  @ExceptionHandler(NullPointerException.class)
  public String nullPointer() {
    return "error-null";
  }

}