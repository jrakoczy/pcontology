package pl.edu.agh.pcontology.tests;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class OntTest {
	private final static String NS = "http://agh.edu.pl/pcontology/"
	
	public static void main(String[] args){
		/*(Model model = ModelFactory.createDefaultModel();
        
        Resource alice = ResourceFactory.createResource("http://example.org/alice");
        
        Resource bob = ResourceFactory.createResource("http://example.org/bob");
        model.add (alice, RDF.type, FOAF.Person);
        model.add (alice, FOAF.name, "Alice");
        model.add (alice, FOAF.mbox, ResourceFactory.createResource("mailto:alice@example.org"));
        model.add (alice, FOAF.knows, bob);

        model.write(System.out, "TURTLE");*/
		
		Model mdl = ModelFactory.createOntologyModel();
		Resource patent = mdl.createResource(uri)
		
	}

}
