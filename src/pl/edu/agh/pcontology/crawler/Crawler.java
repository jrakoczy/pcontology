package pl.edu.agh.pcontology.crawler;

import java.util.regex.Pattern;

import pl.edu.agh.pcontology.crawler.htmlprocessing.AmbiguousContentException;
import pl.edu.agh.pcontology.crawler.htmlprocessing.EspaceHTMLProcessor;
import pl.edu.agh.pcontology.crawler.htmlprocessing.PatentHTMLProcessor;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

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

	private final static String urlFilter = "http://worldwide.espacenet.com/publicationDetails/";
	private final static String localeConstr = "locale=en";

	/**
	 * Sets filters determining whether site should be fetched or not.
	 * 
	 * @param url
	 * @return value of matching statement
	 */
	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL();
		return !FILTERS.matcher(href).matches() && href.startsWith(urlFilter)
				&& href.contains(localeConstr); 

	}

	/**
	 * Processes fetched data.
	 * 
	 * @param visited
	 *            page
	 */
	// TODO: implement logic of this method
	// TODO: exclude non-english info
	@Override
	public void visit(Page page) {

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String html = htmlParseData.getHtml();
			String url = page.getWebURL().getURL();
			PatentHTMLProcessor htmlProc = EspaceHTMLProcessor.getInstance();

			
			try {
				if (url.toString().contains("search")) {
					System.out.println("Inventors: "
							+ htmlProc.getInventors(html));
					System.out
							.println("ID: " + htmlProc.getApplicationID(html));
				}

				if (url.toString().contains("description"))
					System.out
							.println("Desc: " + htmlProc.getDescription(html));

				if (url.toString().contains("claim"))
					System.out.println("Claim: " + htmlProc.getClaim(html));

				if (url.toString().contains("biblio")) {
					System.out.println("Title: " + htmlProc.getTitle(html));
					System.out.println("Abstract: "
							+ htmlProc.getAbstract(html));
					System.out.println("CPC: " + htmlProc.getCPC(html));
					System.out.println("IPC: " + htmlProc.getIPC(html));
				}

			} catch (AmbiguousContentException e) {
				e.printStackTrace();
			}

		}

	}

}
