package it.cnr.ilc.angelo.clavius.bratAdapter;

import it.cnr.ilc.angelo.clavius.utils.IOUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jdom2.Document;

public class AdapterRun {

	public AdapterRun() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			// gli argomenti sono il File delle annotazioni e il nome del file della tokenizzazione XML presenti entrambi nel contesto di brat
			Document dom = Adapter.adapt(new File(args[0]), args[1]);

			System.err.println(IOUtils.FromXMLtoString(dom));
			
			IOUtils.FromXMLtoFile(dom, "KAF_"+args[1]);


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
