package pl.edu.agh.pcontology.textprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.tartarus.snowball.ext.PorterStemmer;

/**
 * Text preprocessing utils.
 * 
 * @author kuba
 * @version 1.0
 */
public class TextPreprocessor {

	/** Defines a path to a file containing stop words list. */
	private String stopWordsPath;

	/**
	 * TextPreprocessor constuctor.
	 * 
	 * @param stopWordsPath
	 */
	public TextPreprocessor(String stopWordsPath) {
		this.stopWordsPath = stopWordsPath;
	}

	/**
	 * Removes stop words from {@code text}.<br/>
	 * Path to stop words list is declared as a class field.
	 * 
	 * @param text
	 * @return text stripped from stop words
	 * @throws IOException
	 */
	public String removeStopWords(String text) throws IOException {

		List<String> stopWords = loadStopWords(stopWordsPath);

		TokenStream tokenStream = new StandardTokenizer(Version.LUCENE_46,
				new StringReader(text));
		tokenStream = new StopFilter(Version.LUCENE_46, tokenStream,
				StopFilter.makeStopSet(Version.LUCENE_46, stopWords, true)); // true
																				// for
																				// ignore
																				// case
																				// flag
		tokenStream.reset(); // mandatory

		return tokenStreamToString(tokenStream);
	}

	/**
	 * Processes {@code text} using Porter's stemmer to reduce the word to its
	 * base.
	 * 
	 * @param text
	 * @return processed text
	 * @throws IOException
	 */
	public String stemWords(String text) throws IOException {
		//StringBuilder sb = new StringBuilder();

		//PorterStemmer stemmer = new PorterStemmer();
		TokenStream tokenStream = new StandardTokenizer(Version.LUCENE_46,
				new StringReader(text));
		tokenStream = new PorterStemFilter(tokenStream);
		tokenStream.reset(); // mandatory

		return tokenStreamToString(tokenStream);
	}

	/**
	 * Stems a word using Porter's algorithm.
	 * 
	 * @param word
	 * @param stemmer
	 * @return stemmed word
	 */
	private String stemSingleWord(String word, PorterStemmer stemmer) {
		word = word.toLowerCase();
		stemmer.setCurrent(word);

		if (stemmer.stem())
			return stemmer.getCurrent();
		else
			return null;
	}

	/**
	 * Translates {@code TokenStream} into {@code String}.
	 * 
	 * @param tokenStream
	 * @return string
	 * @throws IOException
	 */
	private String tokenStreamToString(TokenStream tokenStream)
			throws IOException {
		StringBuilder sb = new StringBuilder();

		CharTermAttribute token = tokenStream
				.getAttribute(CharTermAttribute.class);

		while (tokenStream.incrementToken()) {
			sb.append(token.toString());
			sb.append(" ");
		}

		tokenStream.close(); // mandatory
		return sb.toString();
	}

	/**
	 * Loads a stop words list from a file.<br/>
	 * <b>Caution:</b> Stop words must be declared in separate lines.
	 * 
	 * @param filename
	 * @return list of stop words
	 * @throws IOException
	 */
	private List<String> loadStopWords(String filename) throws IOException {

		List<String> stopWords = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		try {
			String line;

			while ((line = reader.readLine()) != null) {
				if (!line.isEmpty())
					stopWords.add(line);
			}

		} finally {
			reader.close();
		}

		return stopWords;
	}

	/**
	 * Sets a path to a stop words file.
	 * 
	 * @param stopWordsPath
	 */
	public void setStopWordsPath(String stopWordsPath) {
		this.stopWordsPath = stopWordsPath;
	}

}
