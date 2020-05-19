package ninja.seppli.lewebseite.admin.controller.article;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ninja.seppli.lewebseite.admin.controller.exception.NotFoundException;
import ninja.seppli.lewebseite.admin.controller.security.UserProvider;
import ninja.seppli.lewebseite.common.article.dto.CreateArticle;
import ninja.seppli.lewebseite.common.article.dto.SmallArticle;
import ninja.seppli.lewebseite.common.article.model.Article;
import ninja.seppli.lewebseite.common.article.service.ArticleService;
import ninja.seppli.lewebseite.common.media.Media;
import ninja.seppli.lewebseite.common.media.MediaService;
import ninja.seppli.lewebseite.common.renderer.Renderer;

@RestController
@RequestMapping("/admin/articles")
public class ArticleController {
	private ArticleService articleService;
	private MediaService mediaService;
	private UserProvider userProvider;
	private Renderer renderer;

	@Autowired
	public ArticleController(ArticleService articleService, MediaService mediaService, UserProvider userProvider, Renderer renderer) {
		this.articleService = articleService;
		this.mediaService = mediaService;
		this.userProvider = userProvider;
		this.renderer = renderer;
	}

	@GetMapping("")
	public SmallArticle[] getAllMapping() {
		return articleService.getAll().stream().map(SmallArticle::fromArticle).toArray(SmallArticle[]::new);
	}

	@GetMapping("/{articleId}")
	public SmallArticle getMapping(@PathVariable("articleId") long articleId) throws NotFoundException {
		return articleService.get(articleId).map(SmallArticle::fromArticle).orElseThrow(() -> new NotFoundException("Article Not Found"));
	}

	@PostMapping("")
	public SmallArticle createArticle(@RequestBody @Valid CreateArticle createArticle, @AuthenticationPrincipal User user) {
		Article article = new Article(createArticle.getTitle(), userProvider.getUser(), null, "");
		return SmallArticle.fromArticle(articleService.save(article));
	}

	@PutMapping("/{id}")
	public void update(@PathVariable("id") long id, @RequestBody @Valid CreateArticle createArticle) throws NotFoundException {
		Article oldArticle = articleService.get(id).orElseThrow(() -> new NotFoundException("Article Not Found"));
		Article newArticle = new Article(id, createArticle.getTitle(), oldArticle.getAuthor(), null, oldArticle.getText());
		articleService.save(newArticle);
	}

	@DeleteMapping("/{articleId}")
	public void deleteArticle(@PathVariable("articleId") long id) throws NotFoundException {
		Article article = articleService.get(id).orElseThrow(() -> new NotFoundException("Article Not Found"));
		articleService.delete(article);
	}

	@GetMapping(value = "/{id}/text")
	@ResponseBody
	public String getArticleText(@PathVariable("id") long id) throws NotFoundException {
		Article article = articleService.get(id).orElseThrow(() -> new NotFoundException("Article Not Found"));
		return article.getText();
	}

	@PutMapping("/{id}/text")
	public void setArticleText(@PathVariable("id") long id, @RequestBody String text) throws NotFoundException {
		Article article = articleService.get(id).orElseThrow(() -> new NotFoundException("Article Not Found"));
		article.setText(text);
		String renderedText = renderer.render(text);
		article.setRenderedText(renderedText);
		articleService.save(article);
	}

	@PutMapping("/{articleId}/setHeaderImage")
	public void setHeaderImage(@PathVariable("articleId") long articleId, @RequestBody List<Long> ids) throws NotFoundException {
		Article article = articleService.get(articleId).orElseThrow(() -> new NotFoundException("Article Not Found"));
		article.getHeaderImages().clear();
		for(long id : ids) {
			Media media = mediaService.get(id).orElseThrow(() -> new NotFoundException("Media Not Found"));
			article.getHeaderImages().add(media);
		}
		articleService.save(article);
	}

}
