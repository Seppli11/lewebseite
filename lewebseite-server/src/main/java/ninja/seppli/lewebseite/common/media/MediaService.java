package ninja.seppli.lewebseite.common.media;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ninja.seppli.lewebseite.admin.controller.exception.NotFoundException;
import ninja.seppli.lewebseite.common.service.AbstractService;
import ninja.seppli.lewebseite.common.settings.CommonSettings;

@Service
public class MediaService extends AbstractService<Media, Long, MediaRepository> {

	private final static int BUFFER_SIZE = 512;

	private CommonSettings commonSettings;

	/**
	 * The media service Constructor
	 *
	 * @param mediaRepo
	 */
	@Autowired
	public MediaService(MediaRepository mediaRepo, CommonSettings commonSettings) {
		super(mediaRepo);
		this.commonSettings = commonSettings;
	}

	/**
	 * Reads the given inputstream to the media's file
	 * @param media the media to write to
	 * @param in the input stream
	 * @throws IOException
	 */
	public void createFileAndWrite(Media media, InputStream in) throws IOException {
		File file = createFile(media);
		FileOutputStream out = new FileOutputStream(file);
		byte[] buffer = new byte[BUFFER_SIZE];
		int readBytes = 0;
		do {
			readBytes = in.read(buffer);
			if(readBytes < 0) {
				break;
			}
			out.write(buffer, 0, readBytes);
		} while(readBytes > 0);
	}

	@Override
	public void delete(Media obj) {
		super.delete(obj);
	}

	/**
	 * creates a new file based on the media's id
	 * @param media the media to write to
	 * @return the created file
	 * @throws IOException
	 */
	public File createFile(Media media) throws IOException {
		File file = getFile(media);
		if(file.exists()) {
			throw new IllegalArgumentException("The file \"" + file.getAbsolutePath() + "\" already exists");
		}
		file.getParentFile().mkdirs();
		if(!file.createNewFile()) {
			throw new IllegalStateException("Couldn't create the file \"" + file.getAbsolutePath() + "\"");
		}

		return file;

	}

	/**
	 * Fetches the media object and reads the media file from the media folder
	 *
	 * @param id the id
	 * @return the input stream
	 * @throws FileNotFoundException
	 * @throws NotFoundException     thrown if the id wasn't found
	 */
	public InputStream readFromFile(long id) throws NotFoundException, FileNotFoundException {
		Media media = this.get(id)
				.orElseThrow(() -> new NotFoundException("The media with the id \"" + id + "\" wasn't found"));
		return readFromFile(media);
	}

	/**
	 * Reads the media file from the media folder
	 *
	 * @param media the media
	 * @return the input stream
	 * @throws FileNotFoundException
	 */
	public InputStream readFromFile(Media media) throws FileNotFoundException {
		Long id = media.getId();
		if (id == null) {
			throw new IllegalArgumentException("Id is null");
		}
		File mediaFile = getFile(media);
		if (!mediaFile.exists()) {
			throw new FileNotFoundException(
					"The media \"" + id + "\" with the name \"" + media.getFileName() + "\" wasn't found");
		}
		return new FileInputStream(mediaFile);
	}

	public File getFile(Media media) {
		if(media.getId() == null) {
			throw new IllegalArgumentException("The Media wasn't saved an doesn't contain an id");
		}
		return new File(commonSettings.getMediaPath() + "/" + media.getId() + ".media");
	}

}
