/**
 * 
 */
package downloadmanager;

import java.util.HashMap;

/**
 * @author Mihai Suteu
 *
 */
public class DownloadManager {
	private Configuration config;
	
	public DownloadManager(){
		config = new Configuration();
	}
	
	public void configure(HashMap<String, String> argDict){
		config.configure(argDict);
	}
	
	
}
