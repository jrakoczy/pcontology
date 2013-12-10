package pl.edu.agh.pcontology.crawler;

import java.util.Map;

public interface PatentController {
	public void startCrawling() throws Exception;
	public void waitTillFinish();
	public Map<String, String> getLocalData();
}
