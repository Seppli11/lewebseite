package ninja.seppli.lewebseite.common.media.type;

import javax.persistence.Entity;

import ninja.seppli.lewebseite.common.media.Media;

/**
 * An image
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Entity
public class Image extends Media {

	/**
	 * Constructor
	 */
	public Image() {
		super();
	}

	/**
	 * Constructor
	 * @param id
	 * @param mimeType
	 * @param fileName
	 */
	public Image(Long id, String mimeType, String fileName) {
		super(id, mimeType, fileName);
	}

	/**
	 * Constructor
	 * @param mimeType
	 * @param fileName
	 */
	public Image(String mimeType, String fileName) {
		super(mimeType, fileName);
	}

}
