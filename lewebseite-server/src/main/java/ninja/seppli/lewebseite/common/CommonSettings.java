package ninja.seppli.lewebseite.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Common settings
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@ConfigurationProperties("lewebseite")
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
}