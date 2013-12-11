package pl.edu.agh.pcontology.crawler;

import java.util.List;
import java.util.Map;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * Class representing a controller of Espacenet crawlers.
 * 
 * @author kuba
 * @version 1.0
 */
public class EspacenetController extends PatentController {

	private final static String SEARCH_URL = "http://worldwide.espacenet.com/searchResults?DB=worldwide.espacenet.com&ST=singleline&compact=false&locale=en_EP";
	private final static String QUERY_STR = "&query=";

	private String tempFiles;
	private int numberOfCrawlers;
	private CrawlConfig cfg = new CrawlConfig();
	private CrawlController controller;
	private String query;

	/**
	 * {@inheritDoc}
	 */
	public void startCrawling() throws Exception {

		PageFetcher pageFetcher = new PageFetcher(cfg);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
				pageFetcher);
		controller = new CrawlController(cfg, pageFetcher, robotstxtServer);

		controller.addSeed(SEARCH_URL + query);
		controller.start(EspacenetCrawler.class, numberOfCrawlers);

		List<Object> data = controller.getCrawlersLocalData();
		storeData(data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void waitTillFinish() {
		controller.waitUntilFinish();
	}

	public static String getQueryStr() {
		return QUERY_STR;
	}

	/**
	 * Temp files directory getter.
	 * 
	 * @return the tempFiles
	 */
	public String getTempFiles() {
		return tempFiles;
	}

	/**
	 * Temp files directory setter.
	 * 
	 * @param tempFiles
	 *            the tempFiles to set
	 */
	public void setTempFiles(String tempFiles) {
		this.tempFiles = tempFiles;
	}

	/**
	 * Number of crawlers getter.
	 * 
	 * @return the numberOfCrawlers
	 */
	public int getNumberOfCrawlers() {
		return numberOfCrawlers;
	}

	/**
	 * Number of crawlers setter.
	 * 
	 * @param numberOfCrawlers
	 *            the numberOfCrawlers to set
	 */
	public void setNumberOfCrawlers(int numberOfCrawlers) {
		this.numberOfCrawlers = numberOfCrawlers;
	}

	/**
	 * Query getter.
	 * 
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Query setter.
	 * 
	 * @param query
	 *            the query to set
	 */
	public void setQuery(String query) {
		this.query = QUERY_STR + query;
	}

	/**
	 * An {@code EspacenetController} builder. Allows defining various crawling
	 * parameters.
	 * 
	 */
	public static class Builder {
		private String tempFiles;
		private int numberOfCralwers;
		private CrawlConfig cfg = new CrawlConfig();
		private String query = EspacenetController.QUERY_STR;

		public Builder(String _tempFiles, int _numberOfCrawlers) {
			this.tempFiles = _tempFiles;
			this.cfg.setCrawlStorageFolder(this.tempFiles);
			this.numberOfCralwers = _numberOfCrawlers;
		}

		/**
		 * Sets number of crawlers (threads).
		 * 
		 * @param preprocessor
		 * @return builder
		 */
		public Builder crawlersNumber(int number) {
			this.numberOfCralwers = number;
			return this;
		}

		/**
		 * Sets a path to crawler temp files.
		 * 
		 * @param dir
		 * @return builder
		 */
		public Builder tempFilesDir(String dir) {
			this.tempFiles = dir;
			return this;
		}

		/**
		 * Sets delay (in ms) between queries. <b>Caution: <b>Avoid setting
		 * politeness delay < 200 [ms].
		 * 
		 * @param delay
		 * @return
		 */
		public Builder politnessDelay(int delay) {
			this.cfg.setPolitenessDelay(delay);
			return this;
		}

		/**
		 * Sets max depth of crawling. Default value is -1 (no limits).
		 * 
		 * @param depth
		 * @return builder
		 */
		public Builder maxDepth(int depth) {
			this.cfg.setMaxDepthOfCrawling(depth);
			return this;
		}

		/**
		 * Sets maximum number of pages to crawl. Default value is -1 (no
		 * limits).
		 * 
		 * @param pages
		 * @return
		 */
		public Builder maxPages(int pages) {
			this.cfg.setMaxPagesToFetch(pages);
			return this;
		}

		/**
		 * Determines if a crawler resumes an interrupted attempt.
		 * 
		 * @param flag
		 * @return
		 */
		public Builder resumableCrawling(boolean flag) {
			this.cfg.setResumableCrawling(flag);
			return this;
		}

		public Builder defineQuery(String _query) {
			this.query += _query;
			return this;
		}

		/**
		 * Builds {@code EspaceController}.
		 * 
		 * @return
		 */
		public EspacenetController build() {
			return new EspacenetController(this);
		}

	}

	/**
	 * Instantiate using {@code Builder methods}.
	 * 
	 * @param builder
	 */
	private EspacenetController(Builder builder) {

		this.tempFiles = builder.tempFiles;
		this.numberOfCrawlers = builder.numberOfCralwers;
		this.cfg = builder.cfg;
		this.query = builder.query;
	}

	// private methods

	/**
	 * Stores retrieved data in a map.
	 * 
	 * @param crawlersData
	 */
	// TODO: multiple results
	private void storeData(List<Object> crawlersData) {
		for (Object o : crawlersData) {
			if (o instanceof Map) {
				localData = (Map) o;
			}
		}
	}

}
