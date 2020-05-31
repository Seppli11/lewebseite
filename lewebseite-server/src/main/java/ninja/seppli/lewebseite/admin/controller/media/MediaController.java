package ninja.seppli.lewebseite.admin.controller.media;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import ninja.seppli.lewebseite.common.media.MediaFactoryManager;
import ninja.seppli.lewebseite.common.media.MediaService;
import ninja.seppli.lewebseite.common.media.pipe.PipeStatus;
import ninja.seppli.lewebseite.common.media.pipe.PipeWorker;

@RestController
@RequestMapping("/admin/api/media")
public class MediaController {
	private MediaService mediaService;
	private MediaFactoryManager mediaFactoryManager;
	private PipeWorker pipeWorker;

	/**
	 * Constructor
	 *
	 * @param mediaService the media service
	 * @param mediaFactoryManager the media factory manager
	 */
	@Autowired
	public MediaController(MediaService mediaService, MediaFactoryManager mediaFactoryManager, PipeWorker pipeWorker) {
		this.mediaService = mediaService;
		this.mediaFactoryManager = mediaFactoryManager;
		this.pipeWorker = pipeWorker;
	}

	@GetMapping("/supportedMimeTypes")
	public String[] getSupportedMimeTypes() {
		return mediaFactoryManager.getSupportedMimeTypes();
	}

	@PostMapping("")
	public void uploadMedia(@RequestParam("files") List<MultipartFile> files)
			throws IOException, InvalidMimeTypeException {
		for (MultipartFile file : files) {
			mediaFactoryManager.getMediaFromMimeType(file);
		}
	}

	@GetMapping("/{id}/status")
	public Object getPipeStatus(@PathVariable("id") long id) throws NotFoundException {
		PipeStatus status = pipeWorker.getPipeStatus(id);
		if(status == null) {
			return new PipeStatus(id, "no status", true);
		}
		return status;
	}

	@GetMapping("/{id}")
	public Object getMedia(@PathVariable("id") long id) throws NotFoundException {
		return toSmallMedia(mediaService.get(id).orElseThrow(() -> new NotFoundException("Media \"" + id + "\" not found")));
	}

	@DeleteMapping("/{id}")
	public void deletetMedia(@PathVariable("id") long id) throws NotFoundException {
		Media media = mediaService.get(id).orElseThrow(() -> new NotFoundException("Media \"" + id + "\" not found"));
		mediaService.delete(media);
	}

	/**
	 * Gets all medias
	 * @param mimeTypeStrings mimeTypes which should be included. If empty, all mimetypes are allowed
	 * @return the medias with the given mimetypes.
	 */
	@GetMapping("")
	public List<Object> getAll(@RequestParam(value = "mimeType", required = false) List<String> mimeTypeStrings) {
		List<Media> mediaList = mediaService.getAll().stream().filter(m -> !m.isHidden()).collect(Collectors.toList());
		if(mimeTypeStrings == null || mimeTypeStrings.isEmpty()) {
			return mediaList.stream().map(this::toSmallMedia).collect(Collectors.toList());
		}
		List<MediaType> mediaTypes = mimeTypeStrings.stream().map(MediaType::valueOf).collect(Collectors.toList());
		return mediaList.stream().filter(media -> {
			MediaType mediaMediaType = MediaType.valueOf(media.getMimeType());
			return mediaTypes.stream().anyMatch(mediaType -> mediaType.includes(mediaMediaType));
		}).map(this::toSmallMedia).collect(Collectors.toList());
	}

	@GetMapping(value = "/{id}/file")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getMediaFile(@PathVariable("id") long id)
			throws NotFoundException, IOException {
		Media media = mediaService.get(id).orElseThrow(() -> new NotFoundException("Media \"" + id + "\" not found"));
		if(!media.isProcessed()) {
			return ResponseEntity.status(203).build();
		}
		File file = mediaService.getFile(media);
		MediaType mediaType = MediaType.valueOf(media.getMimeType());
		return ResponseEntity.ok().contentLength(file.length()).contentType(mediaType)
				.body(new InputStreamResource(mediaService.readFromFile(media)));
	}

	/**
	 * converts to the small media version
	 * @param media the media
	 * @return the converted version
	 */
	private Object toSmallMedia(Media media) {
		MediaType mediaType = MediaType.valueOf(media.getMimeType());
		MediaFactory<?> factory = mediaFactoryManager.findFactory(mediaType).orElse(null);
		if(factory == null) {
			return null;
		}

		return factory.toJsonMedia(media);
	}

}
