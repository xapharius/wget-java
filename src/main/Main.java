package main;

import java.util.Arrays;
import java.util.HashMap;

import parser.ArgParser;
import downloadmanager.ConfigurationException;
import downloadmanager.DownloadException;
import downloadmanager.DownloadManager;;

/**
 * @author Mihai Suteu
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArgParser parser = new ArgParser();
		HashMap<String, String> params = parser.parseArgs(args);
		if(params == null) return;
		
		try {
			DownloadManager dmanager = new DownloadManager(params);
			dmanager.runManager();
		} catch (ConfigurationException e) {
			System.out.println(e.getMessage());
		}
			
	}

}
