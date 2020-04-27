package ninja.seppli.lewebseite.common.article.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Represents a category
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Entity
@Table
public class Category {
	/**
	 * the primary key
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * the name of the category
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * the articles in this category
	 */
	@ManyToMany
	private List<Article> articles = new ArrayList<>();

	/**
	 * Constructor for hibernate
	 */
	protected Category() {
	}

	/**
	 * Constructor
	 * @param name the name of the category
	 */
	public Category(String name) {
		this(-1, name);
	}

	/**
	 * Constructor
	 * @param id the id
	 * @param name the name of the category
	 */
	public Category(long id, String name) {
		this.id = id;
		this.name = name;
	}


}
