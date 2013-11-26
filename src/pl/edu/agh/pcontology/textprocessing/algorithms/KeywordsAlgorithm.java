package pl.edu.agh.pcontology.textprocessing.algorithms;

import java.io.IOException;
import java.util.Map;

/**
 * A root for all keyword searching algorithms.
 *
 *@author kuba
 */
public interface KeyWordsAlgorithm {
	
	/**
	 * Searches for occurrences of key words.<br/>
	 * Maps a counter to each word.
	 * 
	 * @param text
	 * @return (keyword, occurrence) map
	 * @throws IOException
	 */
	public Map<String, Long> searchOccurrences(String text) throws IOException;
}
