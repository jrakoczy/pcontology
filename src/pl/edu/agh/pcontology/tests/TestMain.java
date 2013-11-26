package pl.edu.agh.pcontology.tests;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import pl.edu.agh.pcontology.textprocessing.KeyWordsAnalyzer;
import pl.edu.agh.pcontology.textprocessing.TextPreprocessor;
import pl.edu.agh.pcontology.textprocessing.algorithms.BasicKeyWordsCounter;
import pl.edu.agh.pcontology.textprocessing.algorithms.KeyWordsAlgorithm;

public class TestMain {

	public static void main(String[] args) {
		String text = "This. A.C.C. is a random random sentence sentence.";
		KeyWordsAlgorithm algo = new BasicKeyWordsCounter();
		KeyWordsAnalyzer ana = new KeyWordsAnalyzer.Builder(algo).preprocessor(
				new TextPreprocessor("stopwords.txt")).build();

		Set<Map.Entry<String, Long>> set = null;
		try {
			 set = ana.findKeyWords(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<Entry<String, Long>> it = set.iterator();
		while(it.hasNext()){
			Entry<String, Long> e = it.next();
			System.out.println(e.getKey() + " " + e.getValue());
		}
	}
}
