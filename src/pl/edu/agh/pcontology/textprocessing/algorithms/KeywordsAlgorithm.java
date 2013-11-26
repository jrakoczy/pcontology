package pl.edu.agh.pcontology.textprocessing.algorithms;

import java.io.IOException;
import java.util.Map;

public interface KeywordsAlgorithm {
	public Map<String, Long> searchOcurrences(String text) throws IOException;
}
