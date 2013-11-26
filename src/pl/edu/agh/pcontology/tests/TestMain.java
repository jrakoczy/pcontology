package pl.edu.agh.pcontology.tests;

import java.io.IOException;

import pl.agh.edu.pcontology.textprocessing.TextPreprocessor;

public class TestMain {
	
	public static void main(String args[]) throws IOException {
		String text = "This is a random sentence.";
		TextPreprocessor txtProcessor = new TextPreprocessor("stopwords.txt");
		System.out.println(txtProcessor.removeStopWords(text));
		System.out.println(txtProcessor.stemWords(text));
	}

}
