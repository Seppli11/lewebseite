package ninja.seppli.lewebseite.common.exception;

/**
 * A nested exception which contains an other non runtime exception
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class HttpNestedRuntimeException extends RuntimeException {

	/**
	 * Constructor
	 * @param message the message
	 * @param cause the cause
	 */
	public HttpNestedRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * @param cause the cause
	 */
	public HttpNestedRuntimeException(Throwable cause) {
		super(cause);
	}

}
