package pl.edu.agh.pcontology.crawler.htmlprocessing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Class containing method useful for processing patent information from espace.net. <br>
 * <b>Caution:</b> Singleton design pattern implementd.
 * 
 * @author kuba
 *
 */
public final class EspaceHTMLProcessor implements PatentHTMLProcessor{
	
	private final static String DESC_CSS_QUERY = "#description .printTableText";
	private final static String TABLE_ROW = "tr";
	private final static String TABLE_CELL = "td";
	private final static String INV_SPECIFIC_STR = "Inventor(s)";
	private final static String ABSTRACT_CSS_QUERY = ".printAbstract";
	private final static String TITLE_CSS_QUERY = "h3";
	private final static String IPC_CSS_QUERY = ".containsTable .printTableText i";
	private final static String CPC_CSS_QUERY = ".classTT";
	private final static String CLAIM_CSS_QUERY = "#claims";
	private final static String ID_CSS_QUERY = ".publicationInfoColumn .highlight";
	
	/**
	 * Shall not instantiate. Use {@code getInstace()} instead.
	 */
	private EspaceHTMLProcessor() {}
	
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
	
	/**
	 * {@inheritDoc}
	 * @throws AmbigousContentException 
	 */
	@Override
	public String getDescription(String html) throws AmbigousContentException {
		return getContentbyQuery(html, DESC_CSS_QUERY);
	}
	
	/**
	 * {@inheritDoc}
	 * @throws AmbigousContentException 
	 */
	@Override
	public String getInventors(String html) throws AmbigousContentException {
		Document doc = Jsoup.parse(html);
		Elements rows = doc.select(TABLE_ROW);
		
		for(Element e : rows){
			if(e.text().contains(INV_SPECIFIC_STR)){
				Elements invCell = e.select(TABLE_CELL);
				
				if(rows.size() > 1)
					throw new AmbigousContentException();
				
				return invCell.get(0).text();
			}
		}
		
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @throws AmbigousContentException 
	 */
	@Override
	public String getAbstract(String html) throws AmbigousContentException {
		return getContentbyQuery(html, ABSTRACT_CSS_QUERY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTitle(String html) {
		Document doc = Jsoup.parse(html);
		Elements content = doc.select(TITLE_CSS_QUERY);
		
		// no ambiguity when more than one element matches 
		//there's no other way to retrieve just a title
		
		Element title = content.get(0);
		return title.text();
	}

	/**
	 * {@inheritDoc}
	 * @throws AmbigousContentException 
	 */
	@Override
	public String getIPC(String html) throws AmbigousContentException {
		return getContentbyQuery(html, IPC_CSS_QUERY);
	}

	/**
	 * {@inheritDoc}
	 * @throws AmbigousContentException 
	 */
	@Override
	public String getCPC(String html) throws AmbigousContentException {
		return getContentbyQuery(html, CPC_CSS_QUERY);
	}
	
	/**
	 * {@inheritDoc}
	 * @throws AmbigousContentException 
	 */
	@Override
	public String getClaim(String html) throws AmbigousContentException {
		return getContentbyQuery(html, CLAIM_CSS_QUERY);
	}

	/**
	 * {@inheritDoc}
	 * @throws AmbigousContentException 
	 */
	@Override
	public String getApplicationID(String html) throws AmbigousContentException {
		Document doc = Jsoup.parse(html);
		Elements content = doc.select(ID_CSS_QUERY);
		
		if(content.size() != 2)
			throw new AmbigousContentException();
		
		String countryCode = content.get(0).text();
		String appNumber = content.get(1).text();
		
		return countryCode + appNumber;
	}
	
	/**
	 * Extracts content of an element defined by {@code cssQuery}.
	 * 
	 * @param html
	 * @param cssQuery
	 * @return
	 * @throws AmbigousContentException
	 */
	private String getContentbyQuery(String html, String cssQuery) throws AmbigousContentException{
		Document doc = Jsoup.parse(html);
		Elements content = doc.select(cssQuery);
		
		if(content.size() != 1)
			throw new AmbigousContentException();
		
		return content.get(0).text();
	}

}
