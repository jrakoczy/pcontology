package pl.edu.agh.pcontology.textprocessing.algorithms;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

public class BasicKeywordsCounter implements SearchAlgorithm {

	@Override
	public Map<String, Long> searchText(String text) throws IOException {
		HashMap<String, Long> keywords = new HashMap<String, Long>();
		TokenStream tokenStream = new StandardTokenizer(Version.LUCENE_46,
				new StringReader(text));

		CharTermAttribute token = tokenStream
				.getAttribute(CharTermAttribute.class);

		while (tokenStream.incrementToken()) {
			String term = token.toString();
			long val = 1;

			if (keywords.containsKey(term))
				val = keywords.get(term);

			keywords.put(term, val);
		}

		return keywords;
	}

}
