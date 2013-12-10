package pl.edu.agh.pcontology.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class EspacenetCrawler extends WebCrawler {

	// localData keys
	private final static String ID_KEY = "appID";
	private final static String TITLE_KEY = "title";
	private final static String INVENTOR_KEY = "inventor";
	private final static String DESC_KEY = "description";
	private final static String ABSTR_KEY = "abstract";
	private final static String IPC_KEY = "ipc";
	private final static String CPC_KEY = "cpc";
	private final static String CLAIM_KEY = "claim";

	private final static List<String> dataKeys = new ArrayList<String>();

	static {
		dataKeys.add(ID_KEY);
		dataKeys.add(TITLE_KEY);
		dataKeys.add(INVENTOR_KEY);
		dataKeys.add(DESC_KEY);
		dataKeys.add(ABSTR_KEY);
		dataKeys.add(IPC_KEY);
		dataKeys.add(CPC_KEY);
		dataKeys.add(CLAIM_KEY);
	}

	// extension filters
	private final static Pattern FILTERS = Pattern
			.compile(".*(\\.(css|js|bmp|gif|jpe?g"
					+ "|png|tiff?|mid|mp2|mp3|mp4"
					+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	// domain filter
	private final static String URL_FILTER = "http://worldwide.espacenet.com/publicationDetails/";

	// language filter
	private final static String LOCALE_CONSTR = "locale=en";

	/**
	 * Keys are names of patent properties. Values are data retrieved by
	 * crawler.
	 */
	private Map<String, String> localData = new HashMap<String, String>();

	PatentHTMLProcessor htmlProc = EspaceHTMLProcessor.getInstance();

	/**
	 * Sets filters determining whether site should be fetched or not.
	 * 
	 * @param url
	 * @return value of matching statement
	 */
	@Override
	public boolean shouldVisit(WebURL url) {
		String href = url.getURL();
		return !FILTERS.matcher(href).matches() && href.startsWith(URL_FILTER)
				&& href.contains(LOCALE_CONSTR);

	}

	/**
	 * Processes fetched data.
	 * 
	 * @param visited
	 *            page
	 */
	@Override
	public void visit(Page page) {

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String html = htmlParseData.getHtml();
			String url = page.getWebURL().getURL();

			try {
				if (url.toString().contains("search"))
					processSearchPage(html);

				if (url.toString().contains("description"))
					processDescPage(html);

				if (url.toString().contains("claim"))
					processClaimPage(html);

				if (url.toString().contains("biblio"))
					processBiblioPage(html);

			} catch (AmbiguousContentException e) {
				e.printStackTrace();
			}

		}

	}

	// TODO: multiple results
	/**
	 * Sends retrieved data to {@CrawlController}
	 * 
	 * @return map with stored data
	 */
	public Object getMyLocalData() {
		return localData;
	}

	/**
	 * Returns list of patent data keys. Each key describe a component of patent
	 * content (title, id, etc.)
	 * 
	 * @return data keys
	 */
	public static List<String> getKeysList() {
		return dataKeys;
	}

	/**
	 * Processes bibliographic page extracting patent title, abstract, cpc and
	 * ipc. Then stores the data in {@code localData}.
	 * 
	 * @param html
	 * @throws AmbiguousContentException
	 */
	private void processBiblioPage(String html)
			throws AmbiguousContentException {
		String title = htmlProc.getTitle(html);
		localData.put(TITLE_KEY, title);

		String abstr = htmlProc.getAbstract(html);
		localData.put(ABSTR_KEY, abstr);

		String cpc = htmlProc.getCPC(html);
		localData.put(CPC_KEY, cpc);

		String ipc = htmlProc.getIPC(html);
		localData.put(IPC_KEY, ipc);
	}

	/**
	 * Processes search page extracting patent id and inventors. Then stores the
	 * data in {@code localData}.
	 * 
	 * @param html
	 * @throws AmbiguousContentException
	 */
	private void processSearchPage(String html)
			throws AmbiguousContentException {
		String appID = htmlProc.getApplicationID(html);
		localData.put(ID_KEY, appID);

		String inventors = htmlProc.getInventors(html);
		localData.put(INVENTOR_KEY, inventors);
	}

	/**
	 * Processes claim page extracting patent claim. Then stores the data in
	 * {@code localData}.
	 * 
	 * @param html
	 * @throws AmbiguousContentException
	 */
	private void processClaimPage(String html) throws AmbiguousContentException {
		String claim = htmlProc.getClaim(html);
		localData.put(CLAIM_KEY, claim);
	}

	/**
	 * Processes search page extracting patent description. Then stores the data
	 * in {@code localData}.
	 * 
	 * @param html
	 * @throws AmbiguousContentException
	 */
	private void processDescPage(String html) throws AmbiguousContentException {
		String desc = htmlProc.getDescription(html);
		localData.put(DESC_KEY, desc);
	}
}
