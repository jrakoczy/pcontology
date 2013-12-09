package pl.edu.agh.pcontology.tests;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

public class OntTest {
	public static void main(String[] args){
		/*(Model model = ModelFactory.createDefaultModel();
        
        Resource alice = ResourceFactory.createResource("http://example.org/alice");
        
        Resource bob = ResourceFactory.createResource("http://example.org/bob");
        model.add (alice, RDF.type, FOAF.Person);
        model.add (alice, FOAF.name, "Alice");
        model.add (alice, FOAF.mbox, ResourceFactory.createResource("mailto:alice@example.org"));
        model.add (alice, FOAF.knows, bob);

        model.write(System.out, "TURTLE");*/
		
		OntModel mdl = ModelFactory.createOntologyModel();
		OntClass patent = mdl.createClass("http://example.org/patent");
		OntClass author = mdl.createClass("http://example.org/author");
		Property has = mdl.createProperty("has");
		mdl.add(patent, FOAF.maker, author);
		mdl.write(System.out, "TURTLE");
		
	}

}
