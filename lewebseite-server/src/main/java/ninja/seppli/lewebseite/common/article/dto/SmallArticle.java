package ninja.seppli.lewebseite.common.article.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.convention.MatchingStrategies;

import ninja.seppli.lewebseite.common.article.model.Article;
import ninja.seppli.lewebseite.common.media.Media;
import ninja.seppli.lewebseite.common.permission.dto.SaveUser;
import ninja.seppli.lewebseite.common.service.LazyModelMapper;

/**
 * A verson of {@link Article} without the content
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class SmallArticle {
	/**
	 * the primary key
	 */
	private long id;

	/**
	 * the title of the article
	 */
	private String title;

	/**
	 * An excerp of the content
	 */
	private String description;

	/**
	 * the author who wrote it
	 */
	private SaveUser author;

	/**
	 * the categories
	 */
	private List<SmallCategory> categories = new ArrayList<>();

	/**
	 * the header images
	 */
	private List<Long> headerImages = new ArrayList<>();

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
	 * @return the shortContent
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the shortContent to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the author
	 */
	public SaveUser getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(SaveUser author) {
		this.author = author;
	}

	/**
	 * @return the categories
	 */
	public List<SmallCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<SmallCategory> categories) {
		this.categories = categories;
	}

	/**
	 * @return the headerImages
	 */
	public List<Long> getHeaderImages() {
		return headerImages;
	}

	/**
	 * @param headerImages the headerImages to set
	 */
	public void setHeaderImages(List<Long> headerImages) {
		this.headerImages = headerImages;
	}

	/**
	 * @return the mapper
	 */
	public static LazyModelMapper getMapper() {
		return mapper;
	}

	/**
	 * @param mapper the mapper to set
	 */
	public static void setMapper(LazyModelMapper mapper) {
		SmallArticle.mapper = mapper;
	}

	/**
	 * the lazy mapper
	 */
	private static LazyModelMapper mapper = new LazyModelMapper(mapperObj -> {
		mapperObj.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		mapperObj.createTypeMap(Article.class, SmallArticle.class)
		.addMappings(mapping -> mapping.skip(SmallArticle::setHeaderImages));
	});

	/**
	 * Maps a full article to its small version
	 *
	 * @param article the full article
	 * @return the small version
	 */
	public static SmallArticle fromArticle(Article article) {
		SmallArticle smallArticle = mapper.getMapper().map(article, SmallArticle.class);
		smallArticle.setHeaderImages(article.getHeaderImages().stream().map(Media::getId).collect(Collectors.toList()));
		return smallArticle;
	}

}
