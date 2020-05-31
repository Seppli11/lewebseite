package ninja.seppli.lewebseite.common.media;

import java.io.File;

import org.springframework.http.MediaType;

import ninja.seppli.lewebseite.common.exception.InvalidMimeTypeException;
import ninja.seppli.lewebseite.common.media.pipe.PipeJob;

/**
 * A factory for a media
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public interface MediaFactory<T extends Media> {
	/**
	 * Creates the media
	 * @param filename the filename
	 * @param mimeType the mimetype
	 * @param multipartFile the recieved multipart file
	 * @return the created media
	 * @throws InvalidMimeTypeException if something went wrong
	 */
	PipeJob<T> createMedia(String filename, MediaType mimeType, File tempFile) throws InvalidMimeTypeException;

	default Object toJsonMedia(Media media) {
		return media;
	}

}
