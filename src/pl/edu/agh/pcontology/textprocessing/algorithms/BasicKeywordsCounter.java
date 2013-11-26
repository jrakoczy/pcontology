package pl.edu.agh.pcontology.textprocessing.algorithms;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

public class BasicKeywordsCounter implements KeywordsAlgorithm {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Long> searchOccurrences(String text) throws IOException {
		HashMap<String, Long> keywords = new HashMap<String, Long>();
		TokenStream tokenStream = new StandardTokenizer(Version.LUCENE_46,
				new StringReader(text));
		tokenStream.reset(); // mandatory

		CharTermAttribute token = tokenStream
				.getAttribute(CharTermAttribute.class);

		try {
			while (tokenStream.incrementToken()) {
				String term = token.toString();
				long val = 1;

				if (keywords.containsKey(term))
					val = keywords.get(term) + 1;

				keywords.put(term, val);
			}
		} finally {
			tokenStream.close();
		}

		return keywords;
	}

}
