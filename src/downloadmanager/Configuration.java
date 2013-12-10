/**
 * Class holding parameters
 */
package downloadmanager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import parser.ParseException;

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
		//overwrite default params
		params.putAll(argDict);
		//see if Tries is positive Integer
		checkSemanticTries(params);	
		//if URL has a filename, update SaveToFile
		semanticURL(params);
	}
	
	/**
	 * Check for Semantic Errors of URL, and update associated parameters
	 * @param argDict
	 * @throws ConfigurationException
	 */
	public void semanticURL(HashMap<String, String> argDict) throws ConfigurationException{
		
		//check if uninitialized URL
		if(argDict.get("URL") == null) 
			throw new ConfigurationException("No URL specified");
		
		//if local file then skip semantic URL validation
		if(argDict.get("FileList").equals("true")) return;
		
		//check protocol
		checkProtocol(argDict);
		
		//check URL form
		try {
			new URL(argDict.get("URL"));
		} catch (MalformedURLException e) {
			throw new ConfigurationException("Malformed URL");
		}
		
		//if it is a file <=> implicit -O option
		updateSaveToFile(argDict);
	}
	
	/**
	 * Sees if URL has supported Protocol, if not specified http is assumed
	 * @throws ConfigurationException
	 */
	public void checkProtocol(HashMap<String, String> argDict) throws ConfigurationException{
		String urlStr = argDict.get("URL");
		String[] splits = urlStr.split("://", 2);
		
		if (splits.length == 2){
			if (!splits[0].equals("http")){
				//Protocol is specified and not http
				throw new ConfigurationException("Unsupported Protocol!");
			}
		}
		else{
			//protocol missing - assuming implicit http - put http
			urlStr = "http://" + urlStr;
		}
		argDict.put("URL", urlStr);
	}

	/**
	 * If it is a file, update SaveToFile parameter
	 * @param argDict
	 * @throws ConfigurationException
	 */
	public void updateSaveToFile(HashMap<String, String> argDict) throws ConfigurationException{
		
		//If filename already specified return
		if (!argDict.get("SaveToFile").equals("index.html")) return;
		
		//create url to easily extract filename
		URL url;
		
		try {
			url = new URL(argDict.get("URL"));
		} catch (MalformedURLException e) {
			throw new ConfigurationException("Missing or Malformed URL in updateSaveToFile: " + argDict.get("URL"));
		}
		
		//it is a file
		String fileName = url.getFile();
		if(fileName != "" && fileName.length() > 1){
			//remove "/" from beginning
			argDict.put("SaveToFile", fileName.substring(1));
		}
	}
	
	/**
	 * Check if argument of tries has a semantic meaning
	 * @throws ConfigurationException
	 */
	public void checkSemanticTries(HashMap<String, String> argDict) throws ConfigurationException{
		try{
			if(Integer.parseInt(argDict.get("Tries")) <= 0){
				throw new NumberFormatException();
			}
		}
		
		catch (NumberFormatException e){
			throw new ConfigurationException("Tries is not a positive Integer");
		}
	}

}
