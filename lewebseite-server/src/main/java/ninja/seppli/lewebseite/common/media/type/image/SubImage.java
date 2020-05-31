package ninja.seppli.lewebseite.common.media.type.image;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import ninja.seppli.lewebseite.common.media.Media;

/**
 * A thumbnail of an image
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Entity
public class SubImage extends Media {

	/**
	 * the main Image
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Image mainImage;

	/**
	 * the width
	 */
	private int width;

	/**
	 * the height
	 */
	private int height;

	/**
	 * Constructor
	 */
	public SubImage() {
		super();
	}

	/**
	 * Constructor
	 *
	 * @param mimeType  the mimetype
	 * @param fileName  the filename
	 * @param mainImage the main image
	 * @param width the width
	 * @param height the height
	 */
	public SubImage(String mimeType, String fileName, Image mainImage, int width, int height) {
		super(mimeType, fileName);
		this.mainImage = mainImage;
		this.width = width;
		this.height = height;
		setHidden(true);
	}

	/**
	 * @return the mainImage
	 */
	public Image getMainImage() {
		return mainImage;
	}

	/**
	 * @param mainImage the mainImage to set
	 */
	public void setMainImage(Image mainImage) {
		this.mainImage = mainImage;
	}


	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

}
