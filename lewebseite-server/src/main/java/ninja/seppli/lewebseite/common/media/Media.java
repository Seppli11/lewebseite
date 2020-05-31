package ninja.seppli.lewebseite.common.media;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * An representation of a media/file
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Media {

	/**
	 * the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * the mimetype
	 */
	private String mimeType;

	/**
	 * the original filename
	 */
	private String fileName;

	/**
	 * if the media is finished processing
	 */
	private boolean processed = false;

	/*
	 * if the media should be hidden when showing all
	 */
	private boolean hidden;

	/**
	 * Empty Constructor
	 */
	public Media() {
	}

	/**
	 * Constructor
	 *
	 * @param mimeType the mimetype
	 * @param fileName the filename
	 */
	public Media(String mimeType, String fileName) {
		this.setMimeType(mimeType);
		this.fileName = fileName;
	}

	/**
	 * Constructor
	 *
	 * @param id       the id
	 * @param mimeType the mimetype
	 * @param fileName the filename
	 */
	public Media(Long id, String mimeType, String fileName) {
		this.id = id;
		this.setMimeType(mimeType);
		this.fileName = fileName;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * @param mimeType the mimeType to set
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the processed
	 */
	public boolean isProcessed() {
		return processed;
	}

	/**
	 * @param processed the processed to set
	 */
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	/**
	 * Sets if the image should be hidden from beeing listed
	 *
	 * @param hidden
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * if the image should be hidden (like with a subimage)
	 *
	 * @return if hidden or not
	 */
	public boolean isHidden() {
		return hidden;
	}
}
