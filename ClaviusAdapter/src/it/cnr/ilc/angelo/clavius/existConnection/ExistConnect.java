/**
 * 
 */
package it.cnr.ilc.angelo.clavius.existConnection;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Node;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

/**
 * @author angelodel80
 *
 */
public class ExistConnect {

	private String context;
	
	protected final String driver = "org.exist.xmldb.DatabaseImpl";
	protected final String collection = 
			"xmldb:exist://claviusontheweb.it:8080/exist/xmlrpc/db/clavius/documents/";
	private Collection root = null;
	

	/**
	 * 
	 */
	public boolean connectAndSave(Document xml) {
		try {

			Class<?> c = Class.forName(driver);
			Database db = (Database)c.newInstance();
			DatabaseManager.registerDatabase(db);
			root = DatabaseManager.getCollection(collection.concat(context),"","");
//			System.err.println(root.getName());
			
//			String[] resources = root.listResources();
//			for(String res: resources){
//				System.out.println(res);
//			}
			
			XMLResource xmlFile = (XMLResource)root.createResource(context+"-lexico-semantic-remoto.xml", "XMLResource");
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlFile.setContent(new XMLOutputter().outputString(xml));
			root.storeResource(xmlFile);
			root.close();
			
			//System.out.println(letterDir.getName());
			
			return true;

		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (XMLDBException ex) {
			ex.printStackTrace();
		} catch (Exception ex){
			ex.printStackTrace(System.err);
		} 
		
		return false;
	}
				
	/**
	 * 
	 */
	public ExistConnect(String context) {
		this.context = context;
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExistConnect test = new ExistConnect("147");
		System.err.println(test.connectAndSave(new Document()));

	}

}
