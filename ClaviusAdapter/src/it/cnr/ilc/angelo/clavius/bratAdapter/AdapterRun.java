package it.cnr.ilc.angelo.clavius.bratAdapter;

import it.cnr.ilc.angelo.clavius.existConnection.ExistConnect;
import it.cnr.ilc.angelo.clavius.utils.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.jdom2.Document;

public class AdapterRun {
	
	
	private static String ann;
	private static String tok;
	private static String urlTok;
	
	private final static String ExistUrlBase = "http://claviusontheweb.it:8080/exist/rest//db/clavius/documents/";
	private final static String annExt = ".ann";
	private final static String tokExt = "-tok.xml";
	
	private static String context;
	
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
		
		context = args[0]; 
		ann = context.concat(annExt);
		tok = context.concat(tokExt);
		urlTok = ExistUrlBase.concat(args[0]).concat("/").concat(tok);
		
	
		try {

			// gli argomenti sono il File delle annotazioni e il nome del file della tokenizzazione XML presenti entrambi nel contesto di brat
			Document dom = Adapter.adapt(new File(ann), urlTok);

			//System.err.println(IOUtils.FromXMLtoString(dom));
			//IOUtils.FromXMLtoFile(dom, context+"-lexico-semantic.xml");
			
			/*connessione al data base e salvataggio del file XML*/
			ExistConnect connect = new ExistConnect(context);
			connect.connectAndSave(dom);
			
			System.out.println("file " + context+"-lexico-semantic.xml successfully written");


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
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
