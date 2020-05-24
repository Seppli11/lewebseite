package ninja.seppli.lewebseite.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * If the mimetype is invalid
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid MimeType")
public class InvalidMimeTypeException extends Exception {

	/**
	 * Constructor
	 * @param message the message
	 * @param cause the cause
	 */
	public InvalidMimeTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * @param message the message
	 */
	public InvalidMimeTypeException(String message) {
		super(message);
	}


}
