package pl.edu.agh.pcontology.textprocessing;

import java.io.IOException;
import java.util.Map;
import java.util.SortedSet;

import pl.edu.agh.pcontology.textprocessing.algorithms.KeywordsAlgorithm;
import pl.edu.agh.pcontology.utils.CollectionUtils;

public class KeyWordsAnalyzer {

	private TextPreprocessor preprocessor;
	private KeywordsAlgorithm algorithm;
	private Map<String, Long> keywords;

	/**
	 * Preprocesses text, searches for keywords and counts their occurrences.
	 * 
	 * @param text
	 * @return sorted keywords
	 * @throws IOException
	 */
	public SortedSet<Map.Entry<String, Long>> findKeyWords(String text)
			throws IOException {

		if (preprocessor != null)
			text = preprocessor.preprocess(text);

		keywords = algorithm.searchOccurrences(text);

		return CollectionUtils.sortByValuesAscending(keywords);
	}

	/**
	 * Returns keywords sorted in descending order.
	 * 
	 * @return sorted keywords
	 */
	public SortedSet<Map.Entry<String, Long>> getKeywordsDescending() {
		return CollectionUtils.sortByValuesDescending(keywords);
	}

	/**
	 * Returns keywords sorted in ascending order.
	 * 
	 * @return sorted keywords
	 */
	public SortedSet<Map.Entry<String, Long>> getKeyWordsAscending() {
		return CollectionUtils.sortByValuesAscending(keywords);
	}

	/**
	 * Returns unsorted keywords.
	 * 
	 * @return keywords map
	 */
	public Map<String, Long> getKeywords() {
		return keywords;
	}

	/**
	 * A {@code KeywordsAnalyzer} builder allowing definition of different
	 * preprocessing and analysis algorithms. <br\>
	 * 
	 * <b>Usage:</b><br\>
	 * {@code} KeyWordAnalyzer analyzer = KeywordAnalyzer<br/>
	 * .Builder(new KeywordsAlgorithm(){<br/>
	 * public Map<String, Long> searchOcurrences(String text) throws IOException
	 * {<br
	 * /} <em>*overridden logic goes here*</em><br/>
	 * }})<br/>
	 * .preprocessor(new TextPreprocessor(path))<br/>
	 * .build()
	 */
	public static class Builder {
		private TextPreprocessor preprocessor;
		private KeywordsAlgorithm algorithm;

/**
		 * Builder constructor. {@code KeyWord algorithm is mandatory field.
		 * 
		 * @param algorithm
		 */
		public Builder(KeywordsAlgorithm algorithm) {
			this.algorithm = algorithm;
		}

		/**
		 * Sets preprocessor instance.
		 * 
		 * @param preprocessor
		 * @return builder
		 */
		public Builder preprocessor(TextPreprocessor preprocessor) {
			this.preprocessor = preprocessor;
			return this;
		}

		/**
		 * Builds {@code KeyWordsAnalyzer}.
		 * 
		 * @return
		 */
		public KeyWordsAnalyzer build() {
			return new KeyWordsAnalyzer(this);
		}
	}

	/**
	 * Private constructor. Instantiate using {@code Builder}.
	 * 
	 * @param builder
	 */
	private KeyWordsAnalyzer(Builder builder) {
		this.preprocessor = builder.preprocessor;
		this.algorithm = builder.algorithm;
	}

}
