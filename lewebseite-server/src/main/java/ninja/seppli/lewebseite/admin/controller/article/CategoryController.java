package ninja.seppli.lewebseite.admin.controller.article;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ninja.seppli.lewebseite.admin.controller.exception.NotFoundException;
import ninja.seppli.lewebseite.common.article.dto.CreateCategory;
import ninja.seppli.lewebseite.common.article.dto.SmallArticle;
import ninja.seppli.lewebseite.common.article.dto.SmallCategory;
import ninja.seppli.lewebseite.common.article.model.Article;
import ninja.seppli.lewebseite.common.article.model.Category;
import ninja.seppli.lewebseite.common.article.service.ArticleService;
import ninja.seppli.lewebseite.common.article.service.CategoryService;

/**
 * A controller for the category rest endpoints
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/admin/api/categories")
public class CategoryController {
	/**
	 * logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * category service
	 */
	private CategoryService categoryService;
	/**
	 * the article service
	 */
	private ArticleService articleService;

	/**
	 * Constructor
	 *
	 * @param categoryService the category service
	 * @param articleService  the article service
	 */
	@Autowired
	public CategoryController(CategoryService categoryService, ArticleService articleService) {
		this.categoryService = categoryService;
		this.articleService = articleService;
	}

	@GetMapping("")
	public SmallCategory[] getAll() {
		return categoryService.getAll().stream().map(SmallCategory::fromCategory).toArray(SmallCategory[]::new);
	}

	@GetMapping("/{id}")
	public SmallCategory get(@PathVariable("id") long id) throws NotFoundException {
		return categoryService.get(id).map(SmallCategory::fromCategory)
				.orElseThrow(() -> new NotFoundException("Category Not Found"));
	}

	@PostMapping("")
	public SmallCategory create(@RequestBody @Valid CreateCategory cat) {
		Category category = new Category(cat.getName());
		return SmallCategory.fromCategory(categoryService.save(category));
	}

	@PutMapping("/{id}")
	public SmallCategory update(@PathVariable("id") long id, @RequestBody @Valid CreateCategory cat)
			throws NotFoundException {
		get(id);
		Category category = new Category(id, cat.getName());
		return SmallCategory.fromCategory(categoryService.save(category));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") long id) throws NotFoundException {
		Category cat = categoryService.get(id).orElseThrow(() -> new NotFoundException("Category Not Found"));
		categoryService.delete(cat);
	}

	@GetMapping("/{id}/articles")
	public SmallArticle[] getArticlesInCategory(@PathVariable("id") long id) throws NotFoundException {
		Category cat = categoryService.get(id).orElseThrow(() -> new NotFoundException("Category Not Found"));
		return cat.getArticles().stream().map(SmallArticle::fromArticle).toArray(SmallArticle[]::new);
	}

	@PostMapping("/{catId}/articles/{articleId}")
	public void addArticle(@PathVariable("catId") long catId, @PathVariable("articleId") long articleId)
			throws NotFoundException {
		Category cat = categoryService.get(catId).orElseThrow(() -> new NotFoundException("Category Not Found"));
		Article article = articleService.get(articleId).orElseThrow(() -> new NotFoundException("Article Not Found"));
		if (cat.getArticles().contains(article)) {
			logger.warn("Article {} is already part of Category {}", articleId, catId);
			return;
		}
		cat.getArticles().add(article);
		categoryService.save(cat);
	}

	@DeleteMapping("/{catId}/articles/{articleId}")
	public void removeArticle(@PathVariable("catId") long catId, @PathVariable("articleId") long articleId)
			throws NotFoundException {
		Category cat = categoryService.get(catId).orElseThrow(() -> new NotFoundException("Category Not Found"));
		Article article = articleService.get(articleId).orElseThrow(() -> new NotFoundException("Article Not Found"));
		if (!cat.getArticles().contains(article)) {
			logger.warn("Article {} isn't part of Category {}", articleId, catId);
			return;
		}
		cat.getArticles().remove(article);
		categoryService.save(cat);
	}

}
