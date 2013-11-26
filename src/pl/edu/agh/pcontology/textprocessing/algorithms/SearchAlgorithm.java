package pl.edu.agh.pcontology.textprocessing.algorithms;

import java.io.IOException;
import java.util.Map;

public interface SearchAlgorithm {
	public Map<String, Long> searchText(String Text) throws IOException;
}
