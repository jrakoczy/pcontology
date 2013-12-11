package pl.edu.agh.pcontology.crawler;

import java.util.Map;

/**
 * Class representing a controller of patent crawlers. It defines crawler setup
 * and a patent query.
 * 
 * @author kuba
 * @version 1.0
 */
public abstract class PatentController {

	protected Map<String, String> localData;

	/**
	 * Performs crawling and stores retrieved data.
	 * 
	 * @throws Exception
	 */
	public abstract void startCrawling() throws Exception;

	/**
	 * Waits until all threads have finished and data has been collected.
	 */
	public abstract void waitTillFinish();

	/**
	 * Local data getter.
	 * 
	 * @return map with local data
	 */
	public Map<String, String> getLocalData() {
		return localData;
	}
}
