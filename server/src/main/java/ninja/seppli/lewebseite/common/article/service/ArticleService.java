package ninja.seppli.lewebseite.common.article.service;

import org.springframework.beans.factory.annotation.Autowired;

import ninja.seppli.lewebseite.common.article.model.Article;
import ninja.seppli.lewebseite.common.article.repository.ArticleRepository;
import ninja.seppli.lewebseite.common.service.AbstractService;

/**
 * The service for the article repo
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class ArticleService extends AbstractService<Article, Long, ArticleRepository> {

	/**
	 * Constructor
	 * @param repo the repository
	 */
	@Autowired
	public ArticleService(ArticleRepository repo) {
		super(repo);
	}

}
