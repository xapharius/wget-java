/**
 * Exception thrown for invalid wget arguments
 */
package downloadmanager;

/**
 * @author Mihai Suteu
 *
 */
public class ConfigurationException extends Exception{
	
	public ConfigurationException(String message){
		super(message);
	}

}
