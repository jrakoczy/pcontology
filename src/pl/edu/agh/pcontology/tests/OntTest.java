package pl.edu.agh.pcontology.tests;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

public class OntTest {
	private final static String NS = "http://agh.edu.pl/pcontology/";
	
	public static void main(String[] args){
		
		Model mdl = ModelFactory.createDefaultModel();
		Resource patent = mdl.createResource(NS + "patent");
		Resource inventor = mdl.createResource(NS + "inventor");
		Resource content = mdl.createResource(NS + "content");
		
		Resource information = mdl.createResource(NS + "information");
		Resource metadata = mdl.createResource(NS + "metadata");
		Resource abstr = mdl.createResource(NS + "abstract");
		Resource title = mdl.createResource(NS + "title");
		Resource description = mdl.createResource(NS + "description");
		Resource claim = mdl.createResource(NS + "claim");
		
		Resource appID = mdl.createResource(NS + "appID");
		Resource classification = mdl.createResource(NS + "classification");
		Resource cpc = mdl.createResource(NS + "cpc");
		Resource ipc = mdl.createResource(NS + "ipc");
		
		Property contains = mdl.createProperty(NS + "contains");
		
		mdl.add(patent, FOAF.maker, inventor);
		mdl.add(patent, contains, content);
		
		mdl.add(content, contains, information);
		mdl.add(content, contains, metadata);
		
		mdl.add(information, contains, abstr);
		mdl.add(information, contains, title);
		mdl.add(information, contains, description);
		mdl.add(information, contains, claim);
		
		mdl.add(metadata, contains, classification);
		mdl.add(metadata, contains, appID);
		
		mdl.add(classification, contains, cpc);
		mdl.add(classification, contains, ipc);
		
		mdl.write(System.out, "TURTLE");
	}

}
