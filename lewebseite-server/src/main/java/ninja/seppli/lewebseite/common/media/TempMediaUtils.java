package ninja.seppli.lewebseite.common.media;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A utils class which is used with temp media files
 * @author Sebastian Zumbrunn
 * @version 1.0
 *
 */
public class TempMediaUtils {
	/**
	 * the used buffer size
	 */
	private final static int BUFFER_SIZE = 512;

	/**
	 * Reads the given inputstream to a new temp file, which is returned
	 * @param in the input stream
	 * @return the newly created temp file
	 * @throws IOException
	 */
	public static File writeToTempFile(InputStream in) throws IOException {
		Path path = Files.createTempFile("lewebseite-media-", ".media");
		File file = path.toFile();
		try (FileOutputStream out = new FileOutputStream(file)) {
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
		return file;
	}
}
