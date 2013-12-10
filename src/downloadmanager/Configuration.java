/**
 * Class holding parameters
 */
package downloadmanager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * @author Mihai Suteu
 *
 */
public class Configuration {
	
	private HashMap<String, String> params;
	
	public HashMap<String, String> getParams() {
		return params;
	}
	
	public String getParamValue(String param){
		return params.get(param);
	}


	public Configuration(){
		params = new HashMap<String, String>();
		//SaveToFile -O
		params.put("SaveToFile", "index.html");
		//--spider
		params.put("Spider", "false");
		//-i
		params.put("FileList", "false");
		//TODO other params
		

	}
		
	public void configure(HashMap<String, String> argDict) throws ConfigurationException{
		params.putAll(argDict);
		
		//if URL has a filename, update SaveToFile
		//in case it is a file list, it doesnt really matter
		if(params.get("FileList").equals("true")) return;
		URL url;
		try {
			url = new URL(argDict.get("URL"));
		} catch (MalformedURLException e) {
			throw new ConfigurationException("Malformed URL");
		}
		//it is a file
		String fileName = url.getFile();
		if(fileName != "" && fileName.length() > 1){
			//remove "/" from beginning
			params.put("SaveToFile", fileName.substring(1));
		}
	}
	

}
