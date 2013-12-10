/**
 * DownloadManager for 1 give configuration
 */
package downloadmanager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import messenger.DownloadMessenger;

/**
 * @author Mihai Suteu
 *
 */
public class DownloadManager {
	private Configuration config;
	private URL url;
	
	/**
	 * Constructs and configures a new Download Manager given the parameters
	 * @param argDict
	 * @throws ConfigurationException
	 */
	public DownloadManager(HashMap<String, String> argDict) throws ConfigurationException{
		System.out.println("configuring Manager for " + argDict.get("URL") + " ...");
		config = new Configuration();
		config.configure(argDict);
		try {
			url = new URL(config.getParamValue("URL"));
		} catch (MalformedURLException e) {
			throw new ConfigurationException("Missing or Malformed URL in DownloadManager Constructor");
		}
		System.out.println("Manager configured!");
	}
	
	/**
	 * Checks whether resource can be found, URL from configuration
	 * @_url URL of resource
	 * @return
	 */
	public Boolean checkRessourceAvailability(){
		try{
			URLConnection urlConn = url.openConnection();
			urlConn.connect();
			urlConn.getInputStream();
		}catch (Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * Spider Option passed in wget call - check availability of file
	 */
	public void spider(){
		System.out.println("checking availability of " + url.toString() + " ...");
		if (checkRessourceAvailability()){
			//file is found
			DownloadMessenger.printFileAvailable();
		}
		else{
			//file is not found
			DownloadMessenger.printFileNotFound();
		}
	}
	
	/**
	 * Run Manager for configured state
	 */
	public void runManager(){
		
		//see if local doc option
		if(config.getParamValue("FileList").equals("true")){
			//TODO get URL from file as Array
			//TODO forkManager() for each URL;
		}
		
		//see if spider option
		if (config.getParamValue("Spider").equals("true")){
			spider();
			return;
		}
			
		//download
		int attempts = 1;
		Boolean downloaded = false;
		int nrTries = Integer.parseInt(config.getParamValue("Tries"));
		do
			try {
				downloadFile();
				downloaded = true;
			} catch (DownloadException e) {
				System.out.println("Attempt " + attempts + "/" + nrTries + " failed: " + e.getMessage());
				System.out.println();
				++attempts;
			}
		while(!downloaded && attempts <= nrTries);
	}
	
	public void downloadFile() throws DownloadException{
		
		System.out.println("preparing download for " + url.toString() + "...");
		System.out.println("file will be saved as " + config.getParamValue("SaveToFile"));
		
		InputStream httpIn = null;
	    OutputStream fileOutput = null;
	    OutputStream bufferedOut = null;
	    
		boolean fileComplete = false;
		byte buffData[] = new byte[1024];
		int nrBuffBytes = 0;
		int nrDownloadedBytes = 0;
		int nrTotalBytes = getFileSize();
		
        //opening streams
        try {
        	System.out.println("opening streams ...");
			httpIn = new BufferedInputStream(url.openStream());
			fileOutput = new FileOutputStream(config.getParamValue("SaveToFile"));
	        bufferedOut = new BufferedOutputStream(fileOutput, 1024);
		} catch (IOException e) {
			throw new DownloadException("Failed Opening Streams");
		}
        
		
	    try {
	        System.out.println("starting download ...");
	        while (!fileComplete) {
	        	nrBuffBytes = httpIn.read(buffData, 0, 1024);
	        	nrDownloadedBytes += nrBuffBytes;
	        	if (nrBuffBytes == -1) {
	        		//read returns -1 when reached eof
	        		fileComplete = true;
	        	} else {
	        		bufferedOut.write(buffData, 0, nrBuffBytes);
	        		DownloadMessenger.printDownloadProgress(nrDownloadedBytes, nrTotalBytes);
	        	}      
	        	
	        }
	      	} catch (Exception e) {
	      		throw new DownloadException("Download Failed");
	      	} finally {
	      		try {
	      			System.out.println("closing streams ...");
	      			bufferedOut.close();
	      			fileOutput.close();
	      			httpIn.close();
	      		} catch (Exception e) {
	      			throw new DownloadException("Closing Streams Failed");
	      		}
	      	}
	    	System.out.println(url.toString() + " sucessfully downloaded!");
	}
	
	/**
	 * Gets File Size of remote file, URL from configuration
	 * @return size of remote file in bytes. -1 if not found
	 */
	public int getFileSize() {
	    HttpURLConnection conn = null;
	    try {
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("HEAD");
	        conn.getInputStream();
	        return conn.getContentLength();
	    } catch (IOException e) {
	        return -1;
	    } finally {
	        conn.disconnect();
	    }
	}
}
