package pl.edu.agh.pcontology.tests;

import java.io.FileNotFoundException;

import pl.edu.agh.pcontology.crawler.EspacenetController;
import pl.edu.agh.pcontology.ontologies.EspacenetPatentOnt;
import pl.edu.agh.pcontology.ontologies.WebPatentOnt;

import com.hp.hpl.jena.rdf.model.Model;

public class WebControllerTest {

	public static void main(String[] args) {

		EspacenetController controller = new EspacenetController.Builder("crawled_sites", 1)
				.maxDepth(2).maxPages(1000).politnessDelay(1000)
				.resumableCrawling(false).defineQuery("WO2013154454").build();
		
		WebPatentOnt patentOnt = null;	
		try {
			patentOnt = new EspacenetPatentOnt("patent_ont.xml", controller);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			patentOnt.createOntology();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Model mdl = patentOnt.getOntology();
		
		mdl.write(System.out);
		
	}
}
