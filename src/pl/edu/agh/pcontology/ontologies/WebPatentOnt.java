package pl.edu.agh.pcontology.ontologies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import pl.edu.agh.pcontology.crawler.PatentController;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public abstract class WebPatentOnt {
	protected final static String BASE = "RDF/XML";
	
	protected Model model;
	protected PatentController controller;
	protected String modelSrc;
	
	public WebPatentOnt(String modelDest, PatentController controller) throws FileNotFoundException{
		this.model = ModelFactory.createDefaultModel();
		this.modelSrc = modelDest;
		model.read(new FileInputStream(this.modelSrc), BASE);
		this.controller = controller;
	}
	
	public abstract void createOntology() throws Exception;

	/**
	 * @return the model
	 */
	public Model getOntology() {
		return model;
	}
}
