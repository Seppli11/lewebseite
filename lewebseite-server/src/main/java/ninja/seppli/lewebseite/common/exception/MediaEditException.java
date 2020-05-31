package ninja.seppli.lewebseite.common.exception;

/**
 * An exception which can be  thrown if something goes wrong while editing a media
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class MediaEditException extends Exception {

	/**
	 * Constructor
	 * @param message the message
	 * @param cause the cause
	 */
	public MediaEditException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * @param message the message
	 */
	public MediaEditException(String message) {
		super(message);
	}
}
