package ninja.seppli.lewebseite.page.rhea.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ninja.seppli.lewebseite.admin.controller.exception.NotFoundException;
import ninja.seppli.lewebseite.common.article.model.Article;
import ninja.seppli.lewebseite.common.article.service.ArticleService;

/**
 * The main controller for the rhea layout
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Controller
public class RheaController {
	private ArticleService articleService;

	/**
	 * The logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());/**

	 * Constructor
	 * @param articleService the article service
	 */
	@Autowired
	public RheaController(ArticleService articleService) {
		this.articleService = articleService;
	}


	@GetMapping("/")
	public String indexTemplate(Model model) {
		model.addAttribute("articles", articleService.getAll());
		return "index";
	}

	@GetMapping("/read/{id}")
	public String readArticle(@PathVariable("id") long id, Model model) throws NotFoundException {
		Article article = articleService.get(id).orElseThrow(() -> new NotFoundException("The Article \"" + id + "\" doesn't exist"));
		model.addAttribute("article", article);
		return "readArticle";
	}

}
