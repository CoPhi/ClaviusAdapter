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
import java.util.HashMap;
import java.util.List;
import java.util.Map;



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

	public static Document adapt(File file, String fileName) 
			throws IllegalArgumentException, UnsupportedEncodingException, FileNotFoundException, IOException{
		Adapter bratAdapter = new Adapter();
		return bratAdapter.parse(file, fileName);
	}

	private Document parse(File file, String fileName) 
			throws IllegalArgumentException, UnsupportedEncodingException, FileNotFoundException, IOException{
		Document dom = null;
		dom = ClaviusUtils.FactoryRootDom("lexico-semantic_analysis");
		ClaviusUtils.verifyFile(new File(fileName), false);
		//System.err.println(file.getCanonicalFile().getParent()+File.separator+"Letter147_tokens.xml");
		//System.err.println(IOUtils.FromFileToString(file.getCanonicalFile().getParent()+File.separator+"Letter147_tokens.xml"));

		Document tokens = ClaviusUtils.getXML(IOUtils.FromFileToString(file.getCanonicalFile().getParent()+File.separator+fileName));
		//System.err.println(IOUtils.FromXMLtoString(tokens));
		// TODO cambiare il nome del file in argomento passato al programma
		String[] lines = splitLine(file);
		Map<String, String> references = getReferences(lines);
		for (String line : lines) {
			//estrarre le informazioni di token e inserirle nei nodi corretti XML
			//System.err.println("estrarre le informazioni e inserirle nei nodi corretti XML: " + line);
			if(line.startsWith("T")){
				DataTransfertObject dto = datahandler(line,tokens,references);
				Element entity = new Element("entity").
						setAttribute("object", dto.getUrnCts()).
						setAttribute("class", dto.getClasseOnt()).
						setAttribute("individual", dto.getInstOnt());
				dom.getRootElement().addContent(entity);
			}
		}


		return dom;
	}

	private Map<String, String> getReferences(String[] lines) {
		// TODO Auto-generated method stub
		Map<String, String> ret = new HashMap<String, String>();
		for(String line : lines){
			if(line.startsWith("N")){
				String ref = line.split("\\t")[1];
				String[] rdata = ref.split("\\s");
				String k = rdata[1];
				if(k.startsWith("T")){
					ret.put(k, rdata[2]);
				}
			}
			// FIXME - questa informazione è il contenuto della nota e dovrebbe nella versione finale essere eliminata, perchè sarà presente solo il riferimento al dataset
			else if (line.startsWith("#")){
				String[] ref = line.split("\\t");
				String[] rdata = ref[1].split("\\s");
				String k = rdata[1];
				if(k.startsWith("T")){
					ret.put(k, ref[2]);
				}
			}
		}
		return ret;
	}

	private DataTransfertObject datahandler(String line, Document tokens, Map<String,String> references) throws IOException {
		// TODO Auto-generated method stub
		if(null==line){throw new IOException("null line in dataTransfetiObject");}
		DataTransfertObject  dto = null;
		String ctsUrn = "";
		String insOnt = "";
		String[] tabs = line.split("\\t");
		ctsUrn = extractCtsUrn(tabs[1], tokens);
		
		if(references.containsKey(tabs[0])){
			insOnt = references.get(tabs[0]);
		}
		
		dto = new DataTransfertObject();


		dto.setClasseOnt(tabs[1].split("\\s")[0]);
		dto.setInstOnt(insOnt);
		dto.setUrnCts(ctsUrn);

		return dto;
	}

	private String extractCtsUrn(String tab, Document tokens) {
		// TODO Auto-generated method stub
		StringBuilder urnCtsBulder = new StringBuilder();
		String start = tab.split("\\s")[1];
		String end = tab.split("\\s")[2];

		XPathFactory xpfac = XPathFactory.instance();
		XPathExpression<Element> xp = xpfac.compile("/tokens/token[@start<="+end+" and @end >="+start +"]", Filters.element());
		//System.err.println("/tokens/token[@start<="+end+" and @end >="+start +"]"); // WoW it needs an Engineer!
		for(Element ele : xp.evaluate(tokens)){
			//System.err.println(ele.getAttributeValue("uri"));
			urnCtsBulder.append(ele.getAttributeValue("uri")+"\u0020");
		}
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
