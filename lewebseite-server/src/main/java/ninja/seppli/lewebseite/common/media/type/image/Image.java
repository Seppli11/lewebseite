package ninja.seppli.lewebseite.common.media.type.image;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

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
	 * All sub images
	 */
	@OneToMany(mappedBy = "mainImage", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SubImage> subImages = new ArrayList<>();

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


	/**
	 * Returns the sub images
	 * @return the subimages
	 */
	public List<SubImage> getSubImages() {
		return subImages;
	}

}
