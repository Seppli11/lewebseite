package ninja.seppli.lewebseite.common.article.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ninja.seppli.lewebseite.common.permission.model.User;

/**
 * Represents a article
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Entity
@Table
public class Article {
	/**
	 * the primary key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * the title of the article
	 */
	@Column(nullable = false)
	private String title;

	/**
	 * the author who wrote it
	 */
	@ManyToOne(optional = false)
	private User author;

	/**
	 * The cached file, or null if no cached version exists
	 */
	@Column(nullable = true)
	private String cachedFile;

	/**
	 * The actual text in the formatting language (ie. markdown, latex, ...)
	 */
	@Column(nullable = false, columnDefinition = "text")
	private String text;

	/**
	 * Constructor for hibernate
	 */
	protected Article() {
	}

	/**
	 * Constructor
	 *
	 * @param id         the id
	 * @param author     the author
	 * @param cachedFile the cached file (can be null)
	 * @param text       the text itself
	 */
	public Article(long id, String title, User author, String cachedFile, String text) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.cachedFile = cachedFile;
		this.text = text;
	}

	/**
	 * Constructor
	 *
	 * @param author     the author
	 * @param cachedFile the cached file or null if none exists
	 * @param text       the text itself
	 */
	public Article(String title, User author, String cachedFile, String text) {
		this(-1, title, author, cachedFile, text);
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

	/**
	 * @return the cachedFile
	 */
	public String getCachedFile() {
		return cachedFile;
	}

	/**
	 * @param cachedFile the cachedFile to set
	 */
	public void setCachedFile(String cachedFile) {
		this.cachedFile = cachedFile;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
}
