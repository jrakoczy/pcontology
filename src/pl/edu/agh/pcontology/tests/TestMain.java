package pl.edu.agh.pcontology.tests;

import java.io.FileReader;
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

	public static void main(String[] args) throws IOException {
		KeyWordsAlgorithm algo = new BasicKeyWordsCounter();
		KeyWordsAnalyzer ana = new KeyWordsAnalyzer.Builder(algo).preprocessor(
				new TextPreprocessor("stopwords.txt")).build();

		Set<Map.Entry<String, Long>> set = null;
		try {
			 set = ana.findKeyWords(new FileReader("sample_text.txt"));
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
	
	public static class StaticClass{
	}
	
	public class InstClass{
	}
}
