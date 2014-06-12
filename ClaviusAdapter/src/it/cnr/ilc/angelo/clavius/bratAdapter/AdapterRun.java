package it.cnr.ilc.angelo.clavius.bratAdapter;

import it.cnr.ilc.angelo.clavius.utils.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jdom2.Document;

public class AdapterRun {
	
	
	private static String ann;
	private static String tok;
	
	private final static String annExt = ".ann";
	private final static String tokExt = "-tok.xml";
	
	public AdapterRun() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// FIXME: attenzione le polirematiche devono essere token sequenziali, ma non sempre è vero. Esempio: "linea etenim Dinostrati" nella lettera 147
		if(args.length!=1){
			try {
				throw new Exception("Parametri d'ingresso sbagliati");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
				return;
			}
		}
		ann = args[0].concat(annExt);
		tok = args[0].concat(tokExt);
	
		try {

			// gli argomenti sono il File delle annotazioni e il nome del file della tokenizzazione XML presenti entrambi nel contesto di brat
			Document dom = Adapter.adapt(new File(ann), tok);

			//System.err.println(IOUtils.FromXMLtoString(dom));
			
			IOUtils.FromXMLtoFile(dom, "KAF_"+annExt+tok);
			
			System.out.println("file" + "KAF_"+annExt+tok+" successfully written");


		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
