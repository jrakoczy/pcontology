package pl.edu.agh.pcontology.tests;

import java.io.IOException;

import pl.edu.agh.pcontology.textprocessing.TextPreprocessor;

public class TestMain {

	public static void main(String[] args) {
		String text = "This. A.C.C. is a random sentence.";
		TextPreprocessor preprocessor = new TextPreprocessor("stopwords.txt");
		try {
			System.out.println(preprocessor.removeStopWords(text));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(preprocessor.stemWords(text));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
