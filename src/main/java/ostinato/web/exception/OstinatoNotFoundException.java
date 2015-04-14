package ostinato.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Ostinato Not Found")
public class OstinatoNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5801353462914860258L;

}
