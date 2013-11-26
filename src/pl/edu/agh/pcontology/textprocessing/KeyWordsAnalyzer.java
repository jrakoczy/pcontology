package pl.edu.agh.pcontology.textprocessing;

import java.io.IOException;
import java.util.Map;
import java.util.SortedSet;

import pl.edu.agh.pcontology.textprocessing.algorithms.KeywordsAlgorithm;
import pl.edu.agh.pcontology.utils.CollectionUtils;

public class KeyWordsAnalyzer{

	private TextPreprocessor preprocessor;
	private KeywordsAlgorithm algorithm;
	private Map<String, Long> keywords;
	
	
	
	public SortedSet<Map.Entry<String, Long>> findKeyWords(String text) throws IOException{
		text = preprocessor.preprocess(text);
		keywords = algorithm.searchOcurrences(text);
		
		return CollectionUtils.sortByValuesAscending(keywords);
	}
	
	public SortedSet<Map.Entry<String, Long>> getKeywordsDescending(){
		return CollectionUtils.sortByValuesDescending(keywords);
	}
	
	public SortedSet<Map.Entry<String, Long>> getKeyWordsAscending(){
		return CollectionUtils.sortByValuesAscending(keywords);
	}
	
	public Map<String, Long> getKeywords(){
		return keywords;
	}
	
	public static class Builder{
		private TextPreprocessor preprocessor;
		private KeywordsAlgorithm algorithm;
		
		public Builder(KeywordsAlgorithm algorithm){
			this.algorithm = algorithm;
		}
		
		public Builder preprocessor(TextPreprocessor preprocessor){
			this.preprocessor = preprocessor;
			return this;
		}
		
		public KeyWordsAnalyzer build(){
			return new KeyWordsAnalyzer(this);
		}
	}
	
	private KeyWordsAnalyzer(Builder builder){
		this.preprocessor = builder.preprocessor;
		this.algorithm = builder.algorithm;
	}

}
