package ninja.seppli.lewebseite.common.media.type.image;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ninja.seppli.lewebseite.common.media.Media;
import ninja.seppli.lewebseite.common.service.LazyModelMapper;

/**
 * a dto for images
 *
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class SmallImage extends Media {
	private List<Long> subImages = new ArrayList<>();

	public SmallImage() {
	}

	/**
	 * @return the subImages
	 */
	public List<Long> getSubImages() {
		return subImages;
	}

	/**
	 * @param subImages the subImages to set
	 */
	public void setSubImages(List<Long> subImages) {
		this.subImages = subImages;
	}

	/**
	 * the mapper
	 */
	private static LazyModelMapper mapper = new LazyModelMapper(map -> {
		map.createTypeMap(Image.class, SmallImage.class)
		.addMappings(typeMapper -> typeMapper.skip(SmallImage::setSubImages));
	});

	/**
	 * Converts a full category to its smaller version
	 *
	 * @param image the image
	 * @return the converted smaller image
	 */
	public static SmallImage fromImage(Image image) {
		SmallImage smallImg = mapper.getMapper().map(image, SmallImage.class);
		smallImg.setSubImages(image.getSubImages().stream().map(SubImage::getId).collect(Collectors.toList()));
		return smallImg;
	}
}
