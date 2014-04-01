/**
 * 
 */
package it.cnr.ilc.angelo.clavius.bratAdapter;

import it.cnr.ilc.angelo.clavius.utils.ClaviusUtils;
import it.cnr.ilc.angelo.clavius.utils.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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

	public static Document adapt(File file) 
			throws IllegalArgumentException, UnsupportedEncodingException, FileNotFoundException, IOException{
		Adapter bratAdapter = new Adapter();
		return bratAdapter.parse(file);
	}

	private Document parse(File file) 
			throws IllegalArgumentException, UnsupportedEncodingException, FileNotFoundException, IOException{
		Document dom = null;
		//System.err.println(file.getCanonicalFile().getParent()+File.separator+"Letter147_tokens.xml");
		//System.err.println(IOUtils.FromFileToString(file.getCanonicalFile().getParent()+File.separator+"Letter147_tokens.xml"));

		Document tokens = ClaviusUtils.getXML(IOUtils.FromFileToString(file.getCanonicalFile().getParent()+File.separator+"Letter147_tokens.xml"));
		//System.err.println(IOUtils.FromXMLtoString(tokens));
		String[] lines = splitLine(file);
		for (String line : lines) {
			//estrarre le informazioni e inserirle nei nodi corretti XML
			System.err.println("estrarre le informazioni e inserirle nei nodi corretti XML: " + line);
		}
		

		return dom;
	}

	private String[] splitLine(File file) 
			throws IllegalArgumentException, UnsupportedEncodingException, FileNotFoundException, IOException {

		BufferedReader reader = null;
		List<String> lines = null;
		String line = null;
		

		reader = IOUtils.getReader(file);
		lines = new ArrayList<String>();

		while(null!=(line = reader.readLine())){
			lines.add(line);
		}


		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines.toArray(new String[0]);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
