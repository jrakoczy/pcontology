package pl.edu.agh.pcontology.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.Header;

/**
 * Class representing a web crawler.
 * 
 * @author kuba
 * @version 1.0
 */
public class Crawler extends WebCrawler {

	private final static Pattern FILTERS = Pattern
			.compile(".*(\\.(css|js|bmp|gif|jpe?g"
					+ "|png|tiff?|mid|mp2|mp3|mp4"
					+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	/**
	 * Sets filters determining whether site should be fetched or not.
	 * 
	 * @param url
	 * @return value of matching statement
	 */
	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches()
				&& href.startsWith("http://worldwide.espacenet.com/publicationDetails/"); // patent
																							// information
																							// only
	}

	/**
	 * Processes fetched data.
	 * 
	 * @param visited page 
	 */
	@Override
	public void visit(Page page) {

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			List<WebURL> links = htmlParseData.getOutgoingUrls();

			System.out.println(html);
		}
		System.out.println("------------------------");
	}
}
