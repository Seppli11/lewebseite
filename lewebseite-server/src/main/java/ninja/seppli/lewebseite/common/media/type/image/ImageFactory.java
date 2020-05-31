package ninja.seppli.lewebseite.common.media.type.image;

import java.io.File;

import org.springframework.http.MediaType;

import ninja.seppli.lewebseite.common.exception.InvalidMimeTypeException;
import ninja.seppli.lewebseite.common.media.Media;
import ninja.seppli.lewebseite.common.media.MediaFactory;
import ninja.seppli.lewebseite.common.media.pipe.PipeJob;

public class ImageFactory implements MediaFactory<Image> {

	@Override
	public PipeJob<Image> createMedia(String filename, MediaType mimeType, File tempFile) throws InvalidMimeTypeException {
		return new PipeJob<>(new Image(mimeType.toString(), filename), ImagePipeFunction.class);
	}

	@Override
	public Object toJsonMedia(Media media) {
		if(media instanceof Image) {
			return SmallImage.fromImage((Image) media);
		}
		return null;
	}
}
