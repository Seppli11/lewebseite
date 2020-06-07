package ninja.seppli.lewebseite.common.settings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * All settings about images (expect watermarks)
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class ImageSettings {
	/**
	 * metadata to extract
	 */
	private Set<String> metadataToExtract = new HashSet<>(
			Arrays.asList("Aperture Value", "Make", "Model", "Orientation", "Exposure Time", "Exposure Program",
					"ISO Speed Ratings", "Exif Version", "Date/Time Original", "Exposure Bias Value", "Software"));

	/**
	 * emtpy constructor
	 */
	public ImageSettings() {
	}

	/**
	 * @return the metadataToExtract
	 */
	public Set<String> getMetadataToExtract() {
		return metadataToExtract;
	}

	/**
	 * @param metadataToExtract the metadataToExtract to set
	 */
	public void setMetadataToExtract(Set<String> metadataToExtract) {
		this.metadataToExtract = metadataToExtract;
	}

}
