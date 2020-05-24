package ninja.seppli.lewebseite.common.media;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.MediaType;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import ninja.seppli.lewebseite.common.exception.InvalidMimeTypeException;
import ninja.seppli.lewebseite.common.media.type.Image;
import ninja.seppli.lewebseite.common.media.type.Video;

/**
 * A class which creates media classes from a multipart file
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class MediaFactory {

	/**
	 * Tries to convert the given filename into a media
	 *
	 * @param file the file
	 * @return the media object
	 * @throws InvalidMimeTypeException if the mime type is invalid
	 */
	public Media getMediaFromMimeType(MultipartFile file) throws InvalidMimeTypeException {
		String filename = file.getOriginalFilename();
		try {
			String mimeTypeStr = getMimeType(filename);
			MimeType mime = MimeTypeUtils.parseMimeType(mimeTypeStr);
			if (mime.isCompatibleWith(MediaType.valueOf("image/*"))) {
				return new Image(mimeTypeStr, filename);
			} else if(mime.isCompatibleWith(MediaType.valueOf("video/*"))) {
				return new Video(mimeTypeStr, filename);
			}

			throw new InvalidMimeTypeException("The mimeType \"" + mimeTypeStr + "\" isn't supported");
		} catch (IOException e) {
			throw new InvalidMimeTypeException("The mimeType of the file \"" + filename + "\" isn't supported", e);
		}
	}

	public String[] getSupportedMimeTypes() {
		return new String[] { "image/*", "video/*" };
	}

	/**
	 * Finds the mimetype of a file
	 *
	 * @param file the file
	 * @return the mimetype
	 * @throws IOException if something went wrong
	 */
	private String getMimeType(String fileName) throws IOException {
		return Files.probeContentType(Paths.get(fileName));
	}
}
