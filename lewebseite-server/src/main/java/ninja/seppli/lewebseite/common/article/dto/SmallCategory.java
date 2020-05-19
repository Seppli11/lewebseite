package ninja.seppli.lewebseite.common.article.dto;

import ninja.seppli.lewebseite.common.article.model.Category;
import ninja.seppli.lewebseite.common.service.LazyModelMapper;

/**
 * A smaller version of the {@link Category} without the articles to prevent cyclic dependencies
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class SmallCategory {
	/**
	 * the primary key
	 */
	long id;

	/**
	 * the name of the category
	 */
	String name;


	/**
	 * @return the id
	 */
	public long getId() {
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
	 * the mapper
	 */
	private static LazyModelMapper mapper = new LazyModelMapper();
	/**
	 * Converts a full category to its smaller version
	 * @param category the category
	 * @return the converted smaller category
	 */
	public static SmallCategory fromCategory(Category category) {
		return mapper.getMapper().map(category, SmallCategory.class);
	}
}
