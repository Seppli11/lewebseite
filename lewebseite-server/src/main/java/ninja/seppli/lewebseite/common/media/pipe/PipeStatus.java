package ninja.seppli.lewebseite.common.media.pipe;

/**
 * A data class with status information of a pipe job (with
 * syncronization/thread save)
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class PipeStatus {
	/**
	 * the id
	 */
	private long mediaId;

	/**
	 * the status
	 */
	private String status;

	/**
	 * if the job is done
	 */
	private boolean done;

	/**
	 * if the job had an error
	 */
	private boolean error;

	/**
	 * An object to syncronize the status
	 */
	private Object syncObj = new Object();

	/**
	 * Constructor
	 * @param mediaId the id of the media
	 */
	public PipeStatus(long mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * Constructor
	 * @param mediaId the id of the media
	 * @param status the status text
	 * @param done if it is done
	 */
	public PipeStatus(long mediaId, String status, boolean done) {
		this.mediaId = mediaId;
		this.status = status;
		this.done = done;
	}

	/**
	 * @return the mediaId
	 */
	public long getMediaId() {
		return mediaId;
	}

	/**
	 * Returns the status
	 *
	 * @return the status
	 */
	public String getStatus() {
		synchronized (syncObj) {
			return status;
		}
	}

	/**
	 * Sets the status
	 *
	 * @param status the status
	 */
	public void setStatus(String status) {
		setStatus(status, false);
	}

	/**
	 * Sets the status
	 *
	 * @param status the status
	 * @param done   if the job is done
	 */
	public void setStatus(String status, boolean done) {
		synchronized (syncObj) {
			this.status = status;
			this.done = done;
			this.error = false;
		}
	}

	/**
	 * Sets the pipe status as done with an error.
	 *
	 * @param msg the message (eg. the status)
	 */
	public void setError(String msg) {
		synchronized (syncObj) {
			this.status = msg;
			this.done = true;
			this.error = true;
		}
	}

	/**
	 * @return the done
	 */
	public boolean isDone() {
		synchronized (syncObj) {
			return done;
		}
	}

	/**
	 * @return the error
	 */
	public boolean isError() {
		synchronized (syncObj) {
			return error;
		}
	}
}
