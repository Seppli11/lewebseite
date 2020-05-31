package ninja.seppli.lewebseite.page.rhea.controller;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ninja.seppli.lewebseite.common.media.Media;
import ninja.seppli.lewebseite.common.media.type.image.Image;

@Component(value = "mediaCarousel")
public class MediaCarouselMethods {
	public String generateSrcSet(Image img) {
		String str = img.getSubImages().stream().map(subImg -> generateUrl(subImg) + " " + subImg.getWidth() + "w")
				.collect(Collectors.joining(","));
		System.out.println("subimages: " + str);
		return str;
	}

	public String generateUrl(Media img) {
		return "/media/" + img.getId() + "/file";
	}
}
