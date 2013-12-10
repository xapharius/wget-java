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
		//file containing URLs -i
		params.put("FileList", "false");
		//number of tries to dwnld -t
		params.put("Tries", "1");
	}
		
	public void configure(HashMap<String, String> argDict) throws ConfigurationException{
		updateParams(argDict);
		//see if Tries is positive Integer
		checkSemanticTries();	
		//if URL has a filename, update SaveToFile
		updateSaveToFile(argDict);
	}
	
	public void updateParams(HashMap<String, String> argDict){
		params.putAll(argDict);
	}
	
	public void checkSemanticTries() throws ConfigurationException{
		try{
			if(Integer.parseInt(params.get("Tries")) <= 0){
				throw new NumberFormatException();
			}
		}
		catch (NumberFormatException e){
			throw new ConfigurationException("Tries is not a positive Integer");
		}
	}
	
	public void updateSaveToFile(HashMap<String, String> argDict) throws ConfigurationException{
		//in case it is a file list, it doesn't really matter
		if(params.get("FileList").equals("true")) return;
		URL url;
		try {
			url = new URL(argDict.get("URL"));
		} catch (MalformedURLException e) {
			throw new ConfigurationException("Missing or Malformed URL in Manager Configuration");
		}
		//it is a file
		String fileName = url.getFile();
		if(fileName != "" && fileName.length() > 1){
			//remove "/" from beginning
			params.put("SaveToFile", fileName.substring(1));
		}
	}
	

}
