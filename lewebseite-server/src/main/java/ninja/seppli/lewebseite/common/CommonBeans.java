package ninja.seppli.lewebseite.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ninja.seppli.lewebseite.common.renderer.Renderer;
import ninja.seppli.lewebseite.common.renderer.markdown.MarkdownRenderer;

/**
 * Contains all beans for the common packages which is used for both admin and user frontend
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
@Configuration
public class CommonBeans {
	/**
	 * the used renderer
	 * @return the renderer
	 */
	@Bean
	Renderer renderer() {
		return new MarkdownRenderer();
	}
}
