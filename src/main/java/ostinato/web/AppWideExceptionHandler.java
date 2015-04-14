package ostinato.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ostinato.web.exception.OstinatoNotFoundException;

@ControllerAdvice
public class AppWideExceptionHandler {

	@ExceptionHandler(OstinatoNotFoundException.class)
	public String ostinatoNotFoundHandler(){
		return "error/not_found";
	}
}
