package ninja.seppli.lewebseite.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Common settings
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
	 * Constructor
	 */
	public CommonSettings() {
	}

	/**
	 * The media path where media files are stored
	 * @return the path
	 */
	public String getMediaPath() {
		return mediaPath;
	}

	/**
	 * Sets the media path
	 * @param mediaPath the media path
	 */
	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}
}