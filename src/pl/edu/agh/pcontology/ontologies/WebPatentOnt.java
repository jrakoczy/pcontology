package pl.edu.agh.pcontology.ontologies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import pl.edu.agh.pcontology.crawler.PatentController;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * Class representing ontlogy for a particular patent document.
 * 
 * @author kuba
 * @version 1.0
 */
public abstract class WebPatentOnt {
	protected final static String BASE = "RDF/XML";

	protected Model model;
	protected PatentController controller;
	protected String modelSrc;

	/**
	 * {@code WebPatentOnt} constructor. Creates ontology using {@code modelSrc}
	 * - a xml file.
	 * 
	 * @param modelSrc
	 *            - filepath
	 * @param controller
	 *            - crawlers controller
	 * @throws FileNotFoundException
	 */
	public WebPatentOnt(String modelSrc, PatentController controller)
			throws FileNotFoundException {
		this.model = ModelFactory.createDefaultModel();
		this.modelSrc = modelSrc;
		model.read(new FileInputStream(this.modelSrc), BASE);
		this.controller = controller;
	}

	/**
	 * Creates ontology for a specific patent. A patent query and crawlers setup
	 * is defined by {@code PatentController}.
	 * 
	 * @throws Exception
	 */
	public abstract void createOntology() throws Exception;

	/**
	 * Model (ontology getter)
	 * 
	 * @return the model
	 */
	public Model getOntology() {
		return model;
	}
}
