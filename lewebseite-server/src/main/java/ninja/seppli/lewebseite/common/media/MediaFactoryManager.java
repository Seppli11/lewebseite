package ninja.seppli.lewebseite.common.media;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import ninja.seppli.lewebseite.common.exception.InvalidMimeTypeException;
import ninja.seppli.lewebseite.common.media.pipe.PipeJob;
import ninja.seppli.lewebseite.common.media.pipe.PipeWorker;

/**
 * A class which creates media classes from a multipart file
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class MediaFactoryManager {
	/**
	 * a map with all factories
	 */
	private Map<MediaType, MediaFactory<?>> factoryMap = new HashMap<>();

	/**
	 * the pipe worker
	 */
	private PipeWorker pipeWorker;

	public MediaFactoryManager(PipeWorker pipeWorker) {
		this.pipeWorker = pipeWorker;
	}

	/**
	 * Tries to convert the given filename into a media
	 *
	 * @param multipartFile the file
	 * @return the media object
	 * @throws InvalidMimeTypeException if the mime type is invalid
	 */
	public Media getMediaFromMimeType(MultipartFile multipartFile) throws InvalidMimeTypeException {
		String filename = multipartFile.getOriginalFilename();
		File file = null;
		try {
			file = TempMediaUtils.writeToTempFile(multipartFile.getInputStream());
		} catch (IOException e1) {
			throw new InvalidMimeTypeException("Couldn't write tempfile", e1);
		}
		try {
			String mimeTypeStr = getMimeType(filename);
			MediaType mime = MediaType.valueOf(mimeTypeStr);
			MediaFactory<?> factory = findFactory(mime).orElseThrow(() -> new InvalidMimeTypeException("The mimeType \"" + mimeTypeStr + "\" isn't supported"));
			PipeJob<?> job = factory.createMedia(filename, mime, file);
			pipeWorker.acceptJob(job, file);
			return job.getMedia();
		} catch (IOException e) {
			throw new InvalidMimeTypeException("The mimeType of the file \"" + filename + "\" isn't supported", e);
		}
	}

	/**
	 * Returns all supported mimeTypes
	 * @return the mimetypes
	 */
	public String[] getSupportedMimeTypes() {
		return factoryMap.keySet().stream().map(MediaType::toString).toArray(String[]::new);
	}

	/**
	 * Adds a factory
	 * @param mediaType the media type which is supported by the factory
	 * @param factory the factory
	 * @throws InvalidMimeTypeException if an other factory already exists, which supports the given mediaType or would be superseeded by this
	 */
	public void addFactory(MediaType mediaType, MediaFactory<?> factory) throws InvalidMimeTypeException {
		if(findFactory(mediaType).isPresent()) {
			throw new InvalidMimeTypeException("The mediaType \"" + mediaType + "\" is already supported");
		}
		factoryMap.put(mediaType, factory);
	}

	/**
	 * If this manager contains a factory for the given media type
	 * @param mediaType the media type
	 * @return if it is supported
	 */
	public boolean isSupported(MediaType mediaType) {
		return factoryMap.keySet().stream().anyMatch(media -> mediaType.isCompatibleWith(mediaType));
	}

	/**
	 * Finds a factory with the given media type
	 * @param fileMediaType the media type to look for (uses include, so image/* will match image/jpg)
	 * @return the optional of a media factory
	 */
	public Optional<MediaFactory<?>> findFactory(MediaType fileMediaType) {
		return factoryMap.entrySet().stream().filter(entry -> fileMediaType.isCompatibleWith(entry.getKey())).findAny().map(Entry::getValue);
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
