package ninja.seppli.lewebseite.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import ninja.seppli.lewebseite.common.exception.InvalidMimeTypeException;
import ninja.seppli.lewebseite.common.media.MediaFactoryManager;
import ninja.seppli.lewebseite.common.media.pipe.PipeWorker;
import ninja.seppli.lewebseite.common.media.type.image.ImageFactory;
import ninja.seppli.lewebseite.common.media.type.video.VideoFactory;
import ninja.seppli.lewebseite.common.renderer.Renderer;
import ninja.seppli.lewebseite.common.renderer.markdown.MarkdownRenderer;

/**
 * Contains all beans for the common packages which is used for both admin and
 * user frontend
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Configuration
public class CommonBeans {

	/**
	 * the pip worker
	 */
	private PipeWorker pipeWorker;

	/**
	 * the commonSettings
	 */
	private CommonSettings commonSettings;

	/**
	 * Constructor
	 *
	 * @param pipeWorker the pipeworker
	 * @param commonSettings the common settings
	 */
	@Autowired
	public CommonBeans(PipeWorker pipeWorker, CommonSettings commonSettings) {
		this.pipeWorker = pipeWorker;
		this.commonSettings = commonSettings;
	}

	/**
	 * the used renderer
	 *
	 * @return the renderer
	 */
	@Bean
	Renderer renderer() {
		return new MarkdownRenderer();
	}

	/**
	 * A media factory manager with the supported factories
	 *
	 * @return the media factory manager
	 * @throws InvalidMimeTypeException if an invalid type was used
	 */
	@Bean
	MediaFactoryManager mediaFactoryManager() throws InvalidMimeTypeException {
		MediaFactoryManager manager = new MediaFactoryManager(pipeWorker);
		manager.addFactory(MediaType.valueOf("image/*"), new ImageFactory());
		manager.addFactory(MediaType.valueOf("video/*"), new VideoFactory());
		return manager;
	}

}
