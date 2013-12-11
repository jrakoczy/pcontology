package pl.edu.agh.pcontology.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class FileUtils {

	// you mustn't instantiate this class
	private FileUtils() {

	}

	/**
	 * Loads a file into string.
	 * 
	 * @param filename
	 * @return string
	 * @throws IOException
	 */
	public static String loadFileIntoString(FileReader freader)
			throws IOException {
		BufferedReader breader = new BufferedReader(freader);
		StringBuilder builder = new StringBuilder();

		try {
			String line;
			while ((line = breader.readLine()) != null)
				builder.append(line);

			return builder.toString();
		} finally {
			breader.close();
		}

	}
}
