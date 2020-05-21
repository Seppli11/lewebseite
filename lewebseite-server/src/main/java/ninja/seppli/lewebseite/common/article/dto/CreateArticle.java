package ninja.seppli.lewebseite.common.article.dto;

import javax.validation.constraints.NotNull;

import ninja.seppli.lewebseite.common.article.model.Article;

/**
 * A verson of {@link Article} with only the title
 * 
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class CreateArticle {
	/**
	 * the title of the article
	 */
	@NotNull
	private String title;

	/**
	 * the description
	 */
	private String description;

	public CreateArticle() {
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
