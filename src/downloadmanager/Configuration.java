/**
 * Class holding parameters
 */
package downloadmanager;

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
		params.put("SaveToFile", "");
		//TODO other params
		
	}
		
	public void configure(HashMap<String, String> argDict){
		params.putAll(argDict);
	}
	

}
