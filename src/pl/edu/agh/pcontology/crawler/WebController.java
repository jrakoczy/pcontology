package pl.edu.agh.pcontology.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class WebController {

	public static void main(String[] args) throws Exception {

		String crawlStorageFolder = "crawled_sites";
		int numberOfCrawlers = 10;

		CrawlConfig config = new CrawlConfig();

		config.setCrawlStorageFolder(crawlStorageFolder);
		config.setPolitenessDelay(1000);
		config.setMaxDepthOfCrawling(2);
		config.setMaxPagesToFetch(10);
		config.setResumableCrawling(false);

	
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher,
				robotstxtServer);
		controller
				.addSeed("http://worldwide.espacenet.com/searchResults?compact=false&ST=singleline&query=CA2831978&locale=en_EP&DB=worldwide.espacenet.com");
		controller.start(Crawler.class, numberOfCrawlers);
	}
}
