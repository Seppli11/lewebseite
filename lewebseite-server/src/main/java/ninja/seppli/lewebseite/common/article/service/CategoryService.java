package ninja.seppli.lewebseite.common.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ninja.seppli.lewebseite.common.article.model.Category;
import ninja.seppli.lewebseite.common.article.repository.CategoryRepository;
import ninja.seppli.lewebseite.common.service.AbstractService;

/**
 * The service for the category repo
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Service
public class CategoryService extends AbstractService<Category, Long, CategoryRepository> {

	/**
	 * Constructor
	 * @param repo the repository
	 */
	@Autowired
	public CategoryService(CategoryRepository repo) {
		super(repo);
	}

}
