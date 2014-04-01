/**
 * 
 */
package it.cnr.ilc.angelo.clavius.bratAdapter;

import java.io.File;

import org.jdom2.Document;

/**
 * @author Angelo Del Grosso
 *
 */
public class Adapter {
	/**
	 * 
	 */
	public Adapter() {
		// TODO Auto-generated constructor stub
	}
	
	public static Document adapt(File file){
		Adapter bratAdapter = new Adapter();
		return bratAdapter.parse(file);
	}
	
	 private Document parse(File file){
		Document dom = null;
		String[] lines = splitLine(file);
		for (String line : lines) {
			//estrarre le informazioni e inserirle nei nodi corretti XML
			System.err.println("estrarre le informazioni e inserirle nei nodi corretti XML: " + line);
		}
		
		return dom;
	}

	private String[] splitLine(File file) {
		// TODO Auto-generated method stub
		
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
