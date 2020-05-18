package ninja.seppli.lewebseite.common.article.dto;

import javax.validation.constraints.NotNull;

import ninja.seppli.lewebseite.common.article.model.Category;
import ninja.seppli.lewebseite.common.service.LazyModelMapper;

/**
 * A smaller version of the {@link Category} without the articles to prevent cyclic dependencies and without the id
 * It is used to create a new category via the rest api
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class CreateCategory {
	/**
	 * the name of the category
	 */
	@NotNull
	String name;


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
	 * the mapper
	 */
	private static LazyModelMapper mapper = new LazyModelMapper();
	/**
	 * Converts a full category to its smaller version
	 * @param category the category
	 * @return the converted smaller category
	 */
	public static CreateCategory fromCategory(Category category) {
		return mapper.getMapper().map(category, CreateCategory.class);
	}
}
