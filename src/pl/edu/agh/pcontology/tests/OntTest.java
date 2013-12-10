package pl.edu.agh.pcontology.tests;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDFS;

public class OntTest {
	private final static String NS = "http://agh.edu.pl/pcontology/";
	private final static String FILE_NAME = "patent_ont.xml";
	
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
		
		Property invented = mdl.createProperty(NS, "invented");
		Property isIncludedIn = mdl.createProperty(NS, "isIncludedIn");
		
		mdl.add(inventor, invented, patent);
		mdl.add(content, isIncludedIn, patent);
		
		mdl.add(information, RDFS.subClassOf, content);
		mdl.add(metadata, RDFS.subClassOf, content);
		
		mdl.add(abstr, RDFS.subClassOf, information);
		mdl.add(title, RDFS.subClassOf, information);
		mdl.add(description, RDFS.subClassOf, information);
		mdl.add(claim, RDFS.subClassOf, information);
		
		mdl.add(classification, RDFS.subClassOf, metadata);
		mdl.add(appID, RDFS.subClassOf, metadata);
		
		mdl.add(cpc, RDFS.subClassOf, classification);
		mdl.add(ipc, RDFS.subClassOf, classification);
		
		try {
			mdl.write(new FileOutputStream(FILE_NAME), "RDF/XML");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
