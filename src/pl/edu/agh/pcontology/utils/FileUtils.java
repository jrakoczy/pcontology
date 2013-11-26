package pl.edu.agh.pcontology.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class FileUtils {

	/**
	 * Loads a file into string.
	 * 
	 * @param filename
	 * @return string
	 * @throws IOException
	 */
	public static String loadFileIntoString(String filename) throws IOException {
		BufferedReader breader = new BufferedReader(new FileReader(filename));
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
