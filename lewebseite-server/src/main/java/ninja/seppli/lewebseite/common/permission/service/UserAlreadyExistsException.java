package ninja.seppli.lewebseite.common.permission.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * An exception which is thrown when the user already exists
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "User Exists")
public class UserAlreadyExistsException extends Exception {

	/**
	 * Constructor
	 * @param message the message
	 * @param cause the cause
	 */
	public UserAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * @param message the message
	 */
	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
