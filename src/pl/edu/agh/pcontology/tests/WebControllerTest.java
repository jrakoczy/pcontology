package pl.edu.agh.pcontology.tests;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pl.edu.agh.pcontology.crawler.EspacenetController;
import pl.edu.agh.pcontology.crawler.EspacenetCrawler;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class WebControllerTest {

	public static void main(String[] args) {

		EspacenetController controller = new EspacenetController.Builder("crawled_sites", 1)
				.maxDepth(2).maxPages(1000).politnessDelay(1000)
				.resumableCrawling(false).defineQuery("WO2013154454").build();
		
		
		try {
			controller.startCrawling();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		controller.waitTillFinish();
		Map<String, String> data = controller.getLocalData();
		
		for(Entry<String, String> e : data.entrySet())
			System.out.println(e.getKey() + " " + e.getValue());
	}
}
