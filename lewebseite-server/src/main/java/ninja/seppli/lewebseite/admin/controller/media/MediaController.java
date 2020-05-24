package ninja.seppli.lewebseite.admin.controller.media;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ninja.seppli.lewebseite.admin.controller.exception.NotFoundException;
import ninja.seppli.lewebseite.common.exception.InvalidMimeTypeException;
import ninja.seppli.lewebseite.common.media.Media;
import ninja.seppli.lewebseite.common.media.MediaFactory;
import ninja.seppli.lewebseite.common.media.MediaService;

@RestController
@RequestMapping("/admin/api/media")
public class MediaController {
	private MediaService mediaService;


	/**
	 * Constructor
	 *
	 * @param mediaService the media service
	 */
	@Autowired
	public MediaController(MediaService mediaService) {
		this.mediaService = mediaService;
	}

	@PostMapping("")
	public void uploadMedia(@RequestParam("files") List<MultipartFile> files) throws IOException, InvalidMimeTypeException {
		for (MultipartFile file : files) {
			Media unsavedMedia = new MediaFactory().getMediaFromMimeType(file);
			Media media = mediaService.save(unsavedMedia);
			mediaService.createFile(media, file.getInputStream());
		}
	}

	@GetMapping("/{id}")
	public Media getMedia(@PathVariable("id") long id) throws NotFoundException {
		return mediaService.get(id).orElseThrow(() -> new NotFoundException("Media \"" + id + "\" not found"));
	}

	@GetMapping("")
	public List<Media> getAll() {
		return mediaService.getAll();
	}

	@GetMapping(value = "/{id}/file")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getMediaFile(@PathVariable("id") long id)
			throws NotFoundException, IOException {
		Media media = mediaService.get(id).orElseThrow(() -> new NotFoundException("Media \"" + id + "\" not found"));
		File file = mediaService.getFile(media);
		MediaType mediaType = MediaType.valueOf(media.getMimeType());
		return ResponseEntity.ok().contentLength(file.length()).contentType(mediaType)
				.body(new InputStreamResource(mediaService.readFromFile(media)));
	}


}
