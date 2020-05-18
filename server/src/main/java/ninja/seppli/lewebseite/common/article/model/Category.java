package ninja.seppli.lewebseite.common.article.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
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
	private Long id;

	/**
	 * the name of the category
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * the articles in this category
	 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable
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
		this.id = null;
		this.name = name;
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
	 * @return the articles
	 */
	public List<Article> getArticles() {
		return articles;
	}


}
