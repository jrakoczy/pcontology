package pl.edu.agh.pcontology.crawler.htmlprocessing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Class containing method useful for processing patent information from
 * espace.net. <br>
 * <b>Caution:</b> Singleton design pattern implementd.
 * 
 * @author kuba
 * 
 */
public final class EspaceHTMLProcessor implements PatentHTMLProcessor {

	private final static String DESC_CSS_QUERY = "#description .printTableText";
	private final static String INV_CSS_QUERY = ".inventorColumn";
	private final static String ABSTRACT_CSS_QUERY = ".printAbstract";
	private final static String TITLE_CSS_QUERY = "h3";
	private final static String IPC_CSS_QUERY = ".containsTable .printTableText i";
	private final static String CPC_CSS_QUERY = ".classTT";
	private final static String CLAIM_CSS_QUERY = "#claims";
	private final static String ID_CSS_QUERY = ".publicationInfoColumn .highlight";

	/**
	 * Shall not instantiate. Use {@code getInstace()} instead.
	 */
	private EspaceHTMLProcessor() {
	}

	/**
	 * Stores a sole instance of EspaceHTMLProcessor.
	 * 
	 * @author kuba
	 * 
	 */
	private static class SingletonHolder {
		private final static EspaceHTMLProcessor instance = new EspaceHTMLProcessor();
	}

	/**
	 * Instantiates a singleton of EspaceHTMLProcessor.
	 * 
	 * @return a sole instance of EspaceHTMLProcessor
	 */
	public static EspaceHTMLProcessor getInstance() {
		return SingletonHolder.instance;
	}

	// search page info

	/**
	 * {@inheritDoc}
	 * 
	 * <b>Caution:</b> {@code} html needs to be a search page (contains 'search'
	 * in url).
	 */
	@Override
	public String getApplicationID(String html) throws AmbiguousContentException {
		Document doc = Jsoup.parse(html);
		Elements content = doc.select(ID_CSS_QUERY);

		if (content.size() != 2)
			throw new AmbiguousContentException();

		String countryCode = content.get(0).text();
		String appNumber = content.get(1).text();

		return countryCode + appNumber;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <b>Caution:</b> {@code} html needs to be a search page (contains 'search'
	 * in url).
	 */
	@Override
	public String getInventors(String html) throws AmbiguousContentException {
		Element inventors = getContentbyQuery(html, INV_CSS_QUERY);
		return inventors.ownText(); // trims headers
	}

	// bibliographic page info

	/**
	 * {@inheritDoc}
	 * 
	 * <b>Caution:</b> {@code} html needs to be a bibliographic page (contains
	 * 'biblio' in url).
	 */
	@Override
	public String getAbstract(String html) throws AmbiguousContentException {
		Element abstr = getContentbyQuery(html, ABSTRACT_CSS_QUERY);
		return abstr.text();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <b>Caution:</b> {@code} html needs to be a bibliographic page (contains
	 * 'biblio' in url).
	 */
	@Override
	public String getTitle(String html) {
		Document doc = Jsoup.parse(html);
		Elements content = doc.select(TITLE_CSS_QUERY);

		// no ambiguity when more than one element matches
		// there's no other way to retrieve just a title

		Element title = content.get(0);
		return title.text();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <b>Caution:</b> {@code} html needs to be a bibliographic page (contains
	 * 'biblio' in url).
	 */
	@Override
	public String getIPC(String html) throws AmbiguousContentException {
		Element ipc = getContentbyQuery(html, IPC_CSS_QUERY);
		return ipc.text();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <b>Caution:</b> {@code} html needs to be a bibliographic page (contains
	 * 'biblio' in url).
	 */
	@Override
	public String getCPC(String html) throws AmbiguousContentException {
		Element cpc = getContentbyQuery(html, CPC_CSS_QUERY);
		return cpc.text();
	}

	// description page info

	/**
	 * {@inheritDoc}
	 * 
	 * <b>Caution:</b> {@code} html needs to be a description page (contains
	 * 'description' in url).
	 */
	@Override
	public String getDescription(String html) throws AmbiguousContentException {
		Element description = getContentbyQuery(html, DESC_CSS_QUERY);
		return description.text();
	}

	// claim page info

	/**
	 * {@inheritDoc}
	 * 
	 * <b>Caution:</b> {@code} html needs to be a patent claim page (contains
	 * 'claims' in url).
	 */
	@Override
	public String getClaim(String html) throws AmbiguousContentException {
		Element claim = getContentbyQuery(html, CLAIM_CSS_QUERY);
		return claim.text();
	}

	// private methods

	/**
	 * Extracts content of an element defined by {@code cssQuery}.
	 * 
	 * @param html
	 * @param cssQuery
	 * @return
	 * @throws AmbiguousContentException
	 */
	private Element getContentbyQuery(String html, String cssQuery)
			throws AmbiguousContentException {
		Document doc = Jsoup.parse(html);
		Elements content = doc.select(cssQuery);

		if (content.size() != 1)
			throw new AmbiguousContentException();

		return content.get(0);
	}

}
