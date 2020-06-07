package ninja.seppli.lewebseite.common.media.type.image;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ImageMetadataProperty {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * the directory of the tag
	 */
	private String directory;

	/**
	 * the property name
	 */
	private String name;

	/**
	 * the property value
	 */
	private String value;

	/**
	 * empty Constructor
	 */
	public ImageMetadataProperty() {
	}

	/**
	 * Constructor
	 * @param directory the directory
	 * @param name the name of the property
	 * @param value the value
	 */
	public ImageMetadataProperty(String directory, String name, String value) {
		this.directory = directory;
		this.name = name;
		this.value = value;
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
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the directory
	 */
	public String getDirectory() {
		return directory;
	}

	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ImageMetadataProperty [ id=" + id + ", " + directory + "." + name + " = " + value
				+ "]";
	}


}
