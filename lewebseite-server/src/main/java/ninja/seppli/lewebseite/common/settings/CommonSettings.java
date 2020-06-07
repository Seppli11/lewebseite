package ninja.seppli.lewebseite.common.settings;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Common settings
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@ConfigurationProperties("lewebseite")
@Component
public class CommonSettings {
	/**
	 * the media path
	 */
	private String mediaPath = "./media";

	/**
	 * the number of threads the pipe worker is allowed to use
	 */
	private int pipeThreads = 2;

	/**
	 * the thumbnail sizes
	 */
	@Value("${image.thumbnailSize:1920,720,480}")
	private List<Integer> thumbnailSize = new ArrayList<>();

	/**
	 * the watermark settings
	 */
	private WatermarkSettings watermark = new WatermarkSettings();

	/**
	 * the image settings
	 */
	private ImageSettings image = new ImageSettings();

	/**
	 * Constructor
	 */
	public CommonSettings() {
	}

	/**
	 * The media path where media files are stored
	 *
	 * @return the path
	 */
	public String getMediaPath() {
		return mediaPath;
	}

	/**
	 * Sets the media path
	 *
	 * @param mediaPath the media path
	 */
	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	/**
	 * @return the pipeThreads
	 */
	public int getPipeThreads() {
		return pipeThreads;
	}

	/**
	 * @param pipeThreads the pipeThreads to set
	 */
	public void setPipeThreads(int pipeThreads) {
		this.pipeThreads = pipeThreads;
	}

	/**
	 * @return the thumbnailSize
	 */
	public List<Integer> getThumbnailSize() {
		return thumbnailSize;
	}

	/**
	 * @param thumbnailSize the thumbnailSize to set
	 */
	public void setThumbnailSize(List<Integer> thumbnailSize) {
		this.thumbnailSize = thumbnailSize;
	}

	/**
	 * Returns the watermark settings
	 * @return the watermark settiogs
	 */
	public WatermarkSettings getWatermark() {
		return watermark;
	}

	/**
	 * the setter for the watermark settings
	 * @param watermark the new settings object
	 */
	public void setWatermark(WatermarkSettings watermark) {
		this.watermark = watermark;
	}

	/**
	 * @return the image
	 */
	public ImageSettings getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(ImageSettings image) {
		this.image = image;
	}

}