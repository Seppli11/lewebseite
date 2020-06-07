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
import net.coobird.thumbnailator.Thumbnails.Builder;
import ninja.seppli.lewebseite.common.exception.MediaEditException;
import ninja.seppli.lewebseite.common.media.MediaService;
import ninja.seppli.lewebseite.common.media.pipe.PipeFunction;
import ninja.seppli.lewebseite.common.media.pipe.PipeStatus;
import ninja.seppli.lewebseite.common.settings.CommonSettings;
import ninja.seppli.lewebseite.common.settings.WatermarkSettings;

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
	/**
	 * the water mark settings
	 */
	private WatermarkSettings watermarkSettings;


	@Autowired
	public ImagePipeFunction(MediaService mediaService, CommonSettings commonSettings) {
		this.mediaService = mediaService;
		this.commonSettings = commonSettings;
		this.watermarkSettings = commonSettings.getWatermark();

	}

	private BufferedImage generateWatermark(int width, int height) throws IOException {
		if (watermarkSettings.getPathFile() == null || !watermarkSettings.getPathFile().exists()) {
			return null;
		}
		logger.info("Generate watermark \"{}\" at the positin \"{}\" with the opacity \"{}\"", watermarkSettings.getPath(),
				watermarkSettings.getPosition().name(), watermarkSettings.getOpacity());
		Builder<File> watermarkBuilder = Thumbnails.of(watermarkSettings.getPathFile());
		if (watermarkSettings.getWidth() > 0) {
			watermarkBuilder = watermarkBuilder.width((width / 100) * watermarkSettings.getWidth());
		}
		if (watermarkSettings.getHeight() > 0) {
			watermarkBuilder = watermarkBuilder.height((height / 100) * watermarkSettings.getHeight());
		}
		if (watermarkSettings.getHeight() > 0 && watermarkSettings .getWidth() > 0) {
			watermarkBuilder = watermarkBuilder.keepAspectRatio(false);
		}
		return watermarkBuilder.asBufferedImage();
	}

	@Override
	public Image edit(Image media, File tempFile, PipeStatus pipeStatus) throws MediaEditException {

		for (int size : commonSettings.getThumbnailSize()) {
			SubImage unsavedSubImage = new SubImage("image/jpg", media.getFileName() + "-" + size + ".jpg", media, size,
					size);
			media.getSubImages().add(unsavedSubImage);
			media = (Image) mediaService.save(media);
			SubImage subImage = media.getSubImages().get(media.getSubImages().size() - 1);
			pipeStatus.setStatus("Create thumbnail \"" + subImage.getFileName() + "\"");
			logger.info("Create thumbnail of the size {} for image \"{}\" (mediaId: {})", size, media.getFileName(),
					media.getId());
			try {
				Builder<File> builder = Thumbnails.of(tempFile).size(size, size);
				BufferedImage img = builder.outputFormat("jpg").asBufferedImage();

				BufferedImage watermarkImg = generateWatermark(img.getWidth(), img.getHeight());
				if(watermarkImg != null) {
					logger.info("Render watermark to \"{}\" with the opacity \"{}\"", watermarkSettings.getPosition().name(), watermarkSettings.getOpacity());
					img = Thumbnails.of(img).scale(1).watermark(watermarkSettings.getPosition(), watermarkImg, watermarkSettings.getOpacity()).asBufferedImage();
				}
				subImage.setWidth(img.getWidth());
				subImage.setHeight(img.getHeight());
				subImage.setProcessed(true);
				mediaService.save(subImage);
				File thumbnailFile = mediaService.createFile(subImage);
				if(ImageIO.write(img, "jpg", thumbnailFile)) {
					logger.info("Wrote thumbnail to \"{}\"", thumbnailFile.getAbsolutePath());
				} else {
					logger.warn("Couldn't write thumbnail \"{}\"", thumbnailFile.getAbsolutePath());
				}
			} catch (IOException e) {
				throw new MediaEditException("Couldn't convert to size \"" + size + "\"");
			}
		}
		pipeStatus.setStatus("Finished processing", true);
		return media;
	}

}