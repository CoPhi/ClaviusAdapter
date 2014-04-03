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
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

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
		dom = ClaviusUtils.FactoryRootDom("lexico-semantic_analysis");
		//System.err.println(file.getCanonicalFile().getParent()+File.separator+"Letter147_tokens.xml");
		//System.err.println(IOUtils.FromFileToString(file.getCanonicalFile().getParent()+File.separator+"Letter147_tokens.xml"));

		Document tokens = ClaviusUtils.getXML(IOUtils.FromFileToString(file.getCanonicalFile().getParent()+File.separator+"Letter147_tokens.xml"));
		//System.err.println(IOUtils.FromXMLtoString(tokens));
		String[] lines = splitLine(file);
		for (String line : lines) {
			//estrarre le informazioni e inserirle nei nodi corretti XML
			System.err.println("estrarre le informazioni e inserirle nei nodi corretti XML: " + line);
			DataTransfertObject dto = datahandler(line, tokens);
			Element entity = new Element("entity").
					setAttribute("object", dto.getUrnCts()).
					setAttribute("class", dto.getClasseOnt()).
					setAttribute("individual", dto.getInstOnt());
			dom.getRootElement().addContent(entity);
		}


		return dom;
	}

	private DataTransfertObject datahandler(String line, Document tokens) throws IOException {
		// TODO Auto-generated method stub
		if(null==line){throw new IOException("null line in dataTransfetiObject");}
		DataTransfertObject  dto = null;
		String ctsUrn = null;
		String[] tabs = line.split("\\t");
		ctsUrn = extractCtsUrn(tabs[1], tokens);
		
		dto = new DataTransfertObject();
		
		
		dto.setClasseOnt(tabs[1].split("\\s")[0]);
		dto.setInstOnt(tabs[2]);
		dto.setUrnCts(ctsUrn);

		return dto;
	}

	private String extractCtsUrn(String tab, Document tokens) {
		// TODO Auto-generated method stub
		StringBuilder urnCtsBulder = new StringBuilder();
		String start = tab.split("\\s")[1];
		String end = tab.split("\\s")[2];
		
		XPathFactory xpfac = XPathFactory.instance();
//		XPathExpression<Element> xp = xpfac.compile("//tokens/token/[@start='"+start+"']", Filters.element());
//		for(Element ele : xp.evaluate(tokens)){
//			System.err.println(ele.getAttributeValue("uri"));
//		}
		urnCtsBulder.append(start);
		urnCtsBulder.append(end);
		return urnCtsBulder.toString();
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