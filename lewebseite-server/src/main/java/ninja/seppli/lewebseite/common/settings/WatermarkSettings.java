package ninja.seppli.lewebseite.common.settings;

import java.io.File;

import net.coobird.thumbnailator.geometry.Positions;

public class WatermarkSettings {

	/**
	 * the path to the water mark
	 */
	private String path = null;

	/**
	 * the opacity of the watermark
	 */
	private float opacity = 1f;

	/**
	 * the position of the watermark
	 */
	private Positions position = Positions.BOTTOM_RIGHT;

	/**
	 * the watermark width
	 */
	private int width = -1;

	/**
	 * the watermark height
	 */
	private int height = -1;

	/**
	 * Constructor
	 */
	public WatermarkSettings() {
	}


	/**
	 * @return the watermarkImage
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param watermarkImage the watermarkImage to set
	 */
	public void setPath(String watermarkImage) {
		this.path = watermarkImage;
	}

	/**
	 * Returns null or the file to the water mark image. This
	 * function may return a file even if the file itself doesn't exist
	 * @return the file or null
	 */
	public File getPathFile() {
		if(this.path == null) {
			return null;
		}
		return new File(this.path);
	}

	/**
	 * @return the watermarkOpacity
	 */
	public float getOpacity() {
		return opacity;
	}

	/**
	 * @param watermarkOpacity the watermarkOpacity to set
	 */
	public void setOpacity(float watermarkOpacity) {
		this.opacity = watermarkOpacity;
	}

	/**
	 * @return the position
	 */
	public Positions getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Positions position) {
		this.position = position;
	}

	/**
	 * @return the watermarkWidth
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param watermarkWidth the watermarkWidth to set
	 */
	public void setWidth(int watermarkWidth) {
		this.width = watermarkWidth;
	}

	/**
	 * @return the watermarkHeight
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param watermarkHeight the watermarkHeight to set
	 */
	public void setHeight(int watermarkHeight) {
		this.height = watermarkHeight;
	}
}
