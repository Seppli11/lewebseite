package ninja.seppli.lewebseite.common.media.type.image;

/**
 * All image size options
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public enum ImageSize {
	/**
	 *a big image
	 */
	BIG(1920),
	/**
	 * a medium sized image
	 */
	MEDIUM(720),
	/**
	 * a small image
	 */
	SMALL(480),
	/**
	 * a very small image
	 */
	VERY_SMALL(240);

	/**
	 * the width
	 */
	private int width;

	/**
	 * Constructor
	 * @param width
	 */
	private ImageSize(int width) {
		this.width = width;
	}

	/**
	 * Returns the size of the larger side (mostly width)
	 * @return the width/larger size
	 */
	public int getWidth() {
		return width;
	}
}
