package ninja.seppli.lewebseite.common.media.pipe;

import ninja.seppli.lewebseite.common.media.Media;

/**
 * A job for the pipe worker
 * @author Sebastian Zumbrunn
 * @version 1.0
 * @param <T> the type of media
 *
 */
public class PipeJob <T extends Media>{
	/**
	 * the media
	 */
	private T media;
	/**
	 * the pipe functions
	 */
	private PipeFunction<T> pipeFunction = null;

	/**
	 * the class of the pipefunction
	 */
	private Class<? extends PipeFunction<T>> pipeFunctionClass = null;

	/**
	 * Constructor
	 * @param media
	 */
	public PipeJob(T media) {
		this.media = media;
	}

	public PipeJob(T media, PipeFunction<T> fun) {
		this(media);
		this.pipeFunction = fun;
	}

	public PipeJob(T media, Class<? extends PipeFunction<T>> funClass) {
		this(media);
		this.pipeFunctionClass = funClass;
	}

	/**
	 * @return the media
	 */
	public T getMedia() {
		return media;
	}

	/**
	 * @param media the media to set
	 */
	public void setMedia(T media) {
		this.media = media;
	}

	/**
	 * Sets the pipe function for the job
	 * @param fun the function
	 */
	public void setPipeFunction(PipeFunction<T> fun) {
		this.pipeFunction = fun;
	}

	public PipeFunction<T> getPipeFunction() {
		return pipeFunction;
	}

	public void setPipeFunctionClass(Class<PipeFunction<T>> pipeFunctionClass) {
		this.pipeFunctionClass = pipeFunctionClass;
	}

	public Class<? extends PipeFunction<T>> getPipeFunctionClass() {
		return pipeFunctionClass;
	}
}
