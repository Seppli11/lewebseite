package ninja.seppli.lewebseite.common.media.type.video;

import java.io.File;

import org.springframework.http.MediaType;

import ninja.seppli.lewebseite.common.exception.InvalidMimeTypeException;
import ninja.seppli.lewebseite.common.media.MediaFactory;
import ninja.seppli.lewebseite.common.media.pipe.PipeJob;

public class VideoFactory implements MediaFactory {

	@Override
	public PipeJob<Video> createMedia(String filename, MediaType mimeType, File file) throws InvalidMimeTypeException {
		return new PipeJob<>(new Video(mimeType.toString(), filename));
	}

}
