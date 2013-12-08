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
		Document doc = Jsoup.parse(html);
		Elements desc = doc.select(DESC_CSS_QUERY);
		
		if(desc.size() != 1)
			throw new AmbigousContentException();
		
		return desc.get(0).text();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInventors(String html) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getAbstract(String html) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTitle(String html) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIPC(String html) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getClaim(String html) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getApplicationID(String html) {
		// TODO Auto-generated method stub
		return null;
	}

}
