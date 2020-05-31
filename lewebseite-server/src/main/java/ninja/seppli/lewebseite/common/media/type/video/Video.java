package ninja.seppli.lewebseite.common.media.type.video;

import javax.persistence.Entity;

import ninja.seppli.lewebseite.common.media.Media;

@Entity
public class Video extends Media {

	/**
	 * Constructor
	 */
	public Video() {
		super();
	}

	/**
	 * Constructor
	 * @param id
	 * @param mimeType
	 * @param fileName
	 */
	public Video(Long id, String mimeType, String fileName) {
		super(id, mimeType, fileName);
	}

	/**
	 * Constructor
	 * @param mimeType
	 * @param fileName
	 */
	public Video(String mimeType, String fileName) {
		super(mimeType, fileName);
	}

}
