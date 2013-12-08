/**
 * 
 */
package downloadmanager;

import java.io.IOException;
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
	private URLConnection conn;
	private Boolean isConfigured;
	
	public DownloadManager(){
		config = new Configuration();
		isConfigured = false;
	}
	
	/**
	 * Configuring DownloadManager by updating download parameters
	 * @param argDict
	 * @throws ConfigurationException
	 */
	public void configure(HashMap<String, String> argDict) throws ConfigurationException{
		config.configure(argDict);
		try {
			url = new URL(argDict.get("URL"));
			conn = url.openConnection();
		} catch (MalformedURLException e) {
			throw new ConfigurationException("malformed URL");
		} catch (IOException e) {
			throw new ConfigurationException("URL Open Connection error");
		}
		isConfigured = true;
	}
	
	/**
	 * Checks whether resource can be found 
	 * @_url URL of resource
	 * @return
	 */
	public Boolean checkRessourceAvailability(URL _url){
		try{
			URLConnection urlConn = _url.openConnection();
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
		if (checkRessourceAvailability(url)){
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
		//see if spider option
		if (config.getParamValue("Spider").equals("true") ){
			spider();
			return;
		}
	
	}
}
