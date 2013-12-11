package pl.edu.agh.pcontology.ontologies;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import pl.edu.agh.pcontology.crawler.EspacenetController;
import pl.edu.agh.pcontology.crawler.EspacenetCrawler;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * Creates a patent ontology using data accessed via Espacenet.
 * 
 * @author kuba
 * @version 1.0
 */
public class EspacenetPatentOnt extends WebPatentOnt {

	private final static String NS = "http://agh.edu.pl/pcontology/";

	/**
	 * {@inheritDoc}
	 */
	public EspacenetPatentOnt(String modelDir, EspacenetController controller)
			throws FileNotFoundException {
		super(modelDir, controller);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createOntology() throws Exception {
		Map<String, String> data = getWebData();
		List<String> dataKeys = EspacenetCrawler.getKeysList();

		for (String key : dataKeys) {
			String literal = data.get(key);
			Resource dataClass = model.getResource(NS + key);
			dataClass.addLiteral(RDF.type, literal);
		}
	}

	/**
	 * Fetches data and stores it in a map.
	 * 
	 * @return map with parsed patent information
	 * @throws Exception
	 */
	private Map<String, String> getWebData() throws Exception {
		controller.startCrawling();
		controller.waitTillFinish();
		return controller.getLocalData();
	}

}
