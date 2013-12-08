package pl.edu.agh.pcontology.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class WebController {

	public static void main(String[] args) throws Exception {

		String crawlStorageFolder = "crawled_sites";
		int numberOfCrawlers = 1;

		CrawlConfig config = new CrawlConfig();

		config.setCrawlStorageFolder(crawlStorageFolder);
		config.setPolitenessDelay(1000);
		config.setMaxDepthOfCrawling(2);
		config.setMaxPagesToFetch(1000);
		config.setResumableCrawling(false);

		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher,
				robotstxtServer);
		controller
				.addSeed("http://worldwide.espacenet.com/searchResults?DB=worldwide.espacenet.com&ST=singleline&compact=false&locale=en_EP&query=WO2013154454");
		controller.start(Crawler.class, numberOfCrawlers);

	}
}
