/**
 * Class holding parameters
 */
package main;

import java.util.HashMap;

/**
 * @author Mihai Suteu
 *
 */
public class Config {
	
	private HashMap<String, String> params;
	
	public HashMap<String, String> getParams() {
		return params;
	}
	
	public String getParamValue(String param){
		return params.get(param);
	}


	public Config(){
		params = new HashMap<String, String>();
		//SaveToFile -O
		params.put("SaveToFile", "");
		//TODO other params
		
	}
		
	public void setConfigs(HashMap<String, String> argDict){
		params.putAll(argDict);
	}
	

}
