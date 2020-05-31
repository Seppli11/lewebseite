package ninja.seppli.lewebseite.page.rhea.controller;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import ninja.seppli.lewebseite.admin.controller.exception.NotFoundException;
import ninja.seppli.lewebseite.common.article.model.Article;
import ninja.seppli.lewebseite.common.article.service.ArticleService;
import ninja.seppli.lewebseite.common.media.Media;
import ninja.seppli.lewebseite.common.media.MediaService;

/**
 * The main controller for the rhea layout
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Controller
public class RheaController {
	private ArticleService articleService;
	private MediaService mediaService;

	/**
	 * The logger
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Constructor
	 *
	 * @param articleService the article service
	 */
	@Autowired
	public RheaController(ArticleService articleService, MediaService mediaService) {
		this.articleService = articleService;
		this.mediaService = mediaService;
	}

	@GetMapping("/")
	public String indexTemplate(Model model) {
		model.addAttribute("articles", articleService.getAll());
		return "index";
	}

	@GetMapping("/read/{id}")
	public String readArticle(@PathVariable("id") long id, Model model) throws NotFoundException {
		Article article = articleService.get(id)
				.orElseThrow(() -> new NotFoundException("The Article \"" + id + "\" doesn't exist"));
		model.addAttribute("article", article);
		return "readArticle";
	}

	@GetMapping(value = "/media/{id}/file")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getMediaFile(@PathVariable("id") long id)
			throws NotFoundException, IOException {
		Media media = mediaService.get(id).orElseThrow(() -> new NotFoundException("Media \"" + id + "\" not found"));
		File file = mediaService.getFile(media);
		//String mimeTypeStr = Files.probeContentType(Paths.get(media.getFileName()));
		MediaType mediaType = MediaType.valueOf(media.getMimeType());
		return ResponseEntity.ok().contentLength(file.length()).contentType(mediaType)
				.body(new InputStreamResource(mediaService.readFromFile(media)));
	}

}
