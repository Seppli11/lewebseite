package ninja.seppli.lewebseite.common.media.type.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.coobird.thumbnailator.Thumbnails;
import ninja.seppli.lewebseite.common.CommonSettings;
import ninja.seppli.lewebseite.common.exception.MediaEditException;
import ninja.seppli.lewebseite.common.media.MediaService;
import ninja.seppli.lewebseite.common.media.pipe.PipeFunction;
import ninja.seppli.lewebseite.common.media.pipe.PipeStatus;

@Component
public class ImagePipeFunction implements PipeFunction<Image> {

	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * a media service
	 */
	private MediaService mediaService;

	/**
	 * the common settings
	 */
	private CommonSettings commonSettings;

	@Autowired
	public ImagePipeFunction(MediaService mediaService, CommonSettings commonSettings) {
		this.mediaService = mediaService;
		this.commonSettings = commonSettings;
	}

	@Override
	public void edit(Image media, File tempFile, PipeStatus pipeStatus) throws MediaEditException {

		for (int size : commonSettings.getThumbnailSize()) {
			SubImage unsavedSubImage = new SubImage("image/jpg", media.getFileName() + "-" + size + ".jpg", media, size,
					size);
			SubImage subImage = (SubImage) mediaService.save(unsavedSubImage);
			media.getSubImages().add(subImage);
			pipeStatus.setStatus("Create thumbnail \"" + subImage.getFileName() + "\"");
			logger.info("Create thumbnail of the size {} for image \"{}\" (mediaId: {})", size, media.getFileName(),
					media.getId());
			try {
				BufferedImage img = Thumbnails.of(tempFile).size(size, size).outputFormat("jpg").asBufferedImage();
				subImage.setWidth(img.getWidth());
				subImage.setHeight(img.getHeight());
				mediaService.save(subImage);
				ImageIO.write(img, "jpg", mediaService.createFile(subImage));
			} catch (IOException e) {
				throw new MediaEditException("Couldn't convert to size \"" + size + "\"");
			}
		}
		mediaService.save(media);
		pipeStatus.setStatus("Finished processing", true);
	}

}