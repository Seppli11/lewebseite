package ninja.seppli.lewebseite.common.article.dto;

import java.util.ArrayList;
import java.util.List;

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
	private String shortContent;

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
	private List<Media> headerImages = new ArrayList<>();

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
	public String getShortContent() {
		return shortContent;
	}

	/**
	 * @param shortContent the shortContent to set
	 */
	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
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
	public List<Media> getHeaderImages() {
		return headerImages;
	}

	/**
	 * @param headerImages the headerImages to set
	 */
	public void setHeaderImages(List<Media> headerImages) {
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
		mapperObj.typeMap(Article.class, SmallArticle.class).setPostConverter(context -> {
			Article src = context.getSource();
			if (src == null) {
				return null;
			}
			String text = src.getText();
			if (text != null) {
				String shortText = text.substring(0, Math.min(300, text.length()));
				context.getDestination().setShortContent(shortText);
			}

			return context.getDestination();
		});
	});

	/**
	 * Maps a full article to its small version
	 *
	 * @param article the full article
	 * @return the small version
	 */
	public static SmallArticle fromArticle(Article article) {
		return mapper.getMapper().map(article, SmallArticle.class);
	}

}
