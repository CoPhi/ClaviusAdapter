package it.cnr.ilc.angelo.clavius.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class IOUtils {

	public IOUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static BufferedReader getReader(File inFile) throws IllegalArgumentException, UnsupportedEncodingException, FileNotFoundException{
		verifyFile(inFile, false); 
		return new BufferedReader(new InputStreamReader( new FileInputStream(inFile), "utf-8"));
	}
	
	public static void verifyFile( File aFile, boolean w ) {

		if (aFile == null) {
			throw new IllegalArgumentException("File should not be null.");
		}

		if (!aFile.exists()) {
			throw new IllegalArgumentException ("File does not exist: " + aFile);
		}
		if (!aFile.isFile()) {
			throw new IllegalArgumentException("Should not be a directory: " + aFile);
		}
		if (w && !aFile.canWrite()) {
			throw new IllegalArgumentException("File cannot be written: " + aFile);
		}

		//System.out.println("verifying file: " + aFile.getAbsolutePath() + "\n");
	}
	
	public static String FromFileToString(String pathFile) 
			throws IllegalArgumentException, UnsupportedEncodingException, FileNotFoundException, IOException {
		StringBuilder stringBuilder = null;
		BufferedReader reader = null;
		String line = null;
		
		reader = getReader(new File(pathFile));
		stringBuilder = new StringBuilder();
		while(null!=(line = reader.readLine())){
			stringBuilder.append(line);
		}
		
		return stringBuilder.toString();
		
	}
	
	public static String FromXMLtoString(Document XMLdoc) throws IOException{
		if(XMLdoc==null)
			throw new IOException("hehehahe XMLdoc invalid or null");
		XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
		return xo.outputString(XMLdoc);
	}
	
	public static void FromXMLtoFile(Document XMLdoc, String file) throws IOException{
		if(XMLdoc==null)
			throw new IOException("hehehahe XMLdoc invalid or null");
		XMLOutputter xo = new XMLOutputter(Format.getPrettyFormat());
		Writer w = new FileWriterWithEncoding(file, Charset.defaultCharset());
		xo.output(XMLdoc, w);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
