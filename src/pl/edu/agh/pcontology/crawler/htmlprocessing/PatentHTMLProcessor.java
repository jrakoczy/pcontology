package pl.edu.agh.pcontology.crawler.htmlprocessing;

/**
 * A generic interface for HTMLs containing patent information.
 * 
 * @author kuba
 * 
 */
public interface PatentHTMLProcessor {
	/**
	 * Extracts a patent description from {@code html}.
	 * 
	 * @param html
	 * @return string with a patent description
	 */
	public String getDescription(String html) throws AmbigousContentException;

	/**
	 * Extracts inventors names from {@code html}.
	 * 
	 * @param html
	 * @return inventors names
	 */
	public String getInventors(String html) throws AmbigousContentException;

	/**
	 * Extracts a patent abstract from {@code html}.
	 * 
	 * @param html
	 * @return string with an abstract
	 */
	public String getAbstract(String html) throws AmbigousContentException;

	/**
	 * Extracts a patent title from {@code html}.
	 * 
	 * @param html
	 * @return string with a patent title
	 */
	public String getTitle(String html) throws AmbigousContentException;

	/**
	 * Extracts an International Patent Classification from {@code html}.
	 * 
	 * @param html
	 * @return string with IPC of a patent
	 */
	public String getIPC(String html) throws AmbigousContentException;

	/**
	 * Extracts an Cooperative Patent Classification from {@code html}.
	 * 
	 * @param html
	 * @return string with IPC of a patent
	 */
	public String getCPC(String html) throws AmbigousContentException;

	/**
	 * Extracts a patent claim from {@code html}.
	 * 
	 * @param html
	 * @return string with patent claim
	 */
	public String getClaim(String html) throws AmbigousContentException;

	/**
	 * Extracts an application ID (patent number) from {@code html}. <br>
	 * Current format of an id: [country code][application number]
	 * 
	 * @param html
	 * @return string an application ID
	 */
	public String getApplicationID(String html) throws AmbigousContentException;
}
