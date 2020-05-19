package ninja.seppli.lewebseite.common.renderer.markdown;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import ninja.seppli.lewebseite.common.renderer.Renderer;

/**
 * the markdown renderer
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class MarkdownRenderer implements Renderer {
	/**
	 * the markdown parser
	 */
	private Parser parser;
	private HtmlRenderer renderer;

	/**
	 * Constructor
	 */
	public MarkdownRenderer() {
		this.parser = Parser.builder().build();
		this.renderer = HtmlRenderer.builder().build();
	}

	@Override
	public String render(String text) {
		Node doc = parser.parse(text);
		return renderer.render(doc);
	}

}
