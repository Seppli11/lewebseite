package ninja.seppli.lewebseite.admin.controller.exception;

/**
 * An exception which will be returned to the front end as a 404
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class NotFoundException extends Exception{

	/**
	 * Constructor
	 * @param message The error message
	 * @param cause the cause
	 */
	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param message the error message
	 */
	public NotFoundException(String message) {
		super(message);
	}

}
