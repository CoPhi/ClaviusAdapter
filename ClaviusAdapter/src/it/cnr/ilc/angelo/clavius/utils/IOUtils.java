package it.cnr.ilc.angelo.clavius.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class IOUtils {

	public IOUtils() {
		// TODO Auto-generated constructor stub
	}
	
	protected static BufferedReader getReader(File inFile) throws IllegalArgumentException, UnsupportedEncodingException, FileNotFoundException{
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

		System.out.println("verifying file: " + aFile.getAbsolutePath() + "\n");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
