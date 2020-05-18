package ninja.seppli.lewebseite.common.media;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * An representation of a media/file
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Entity
public class Media {

	/**
	 * the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * the original filename
	 */
	private String fileName;

	/**
	 * Empty Constructor
	 */
	public Media() {
	}

	/**
	 * Constructor
	 * @param fileName the filename
	 */
	public Media(String fileName) {
		this.fileName = fileName;
	}



	/**
	 * Constructor
	 * @param id the id
	 * @param fileName the filename
	 */
	public Media(Long id, String fileName) {
		this.id = id;
		this.fileName = fileName;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
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
}
