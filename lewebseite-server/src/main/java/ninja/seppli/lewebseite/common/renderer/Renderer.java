package ninja.seppli.lewebseite.common.renderer;

/**
 * Renders a text
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public interface Renderer {
	/**
	 * Renders the input text in to the rendered version
	 * @param text the input text
	 * @return the rendered version
	 */
	String render(String text);
}
