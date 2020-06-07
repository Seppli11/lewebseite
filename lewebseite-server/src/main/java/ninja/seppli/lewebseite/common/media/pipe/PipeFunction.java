package ninja.seppli.lewebseite.common.media.pipe;

import java.io.File;

import ninja.seppli.lewebseite.common.exception.MediaEditException;
import ninja.seppli.lewebseite.common.media.Media;

/**
 * A pipe function which edits media
 * @author Sebastian Zumbrunn
 * @version 1.0
 * @param <T> the type of media
 *
 */
public interface PipeFunction<T extends Media> {

	/**
	 * Edits a media object
	 * @param media the media object
	 * @param tempFile the temp file
	 * @param pipeStatus  the pipe status
	 * @throws MediaEditException if something goes wrong
	 */
	T edit(T media, File tempFile, PipeStatus pipeStatus) throws MediaEditException;

}
