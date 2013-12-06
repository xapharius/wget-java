/**
 * Exception thrown for invalid wget arguments
 */
package parser;

/**
 * @author Mihai Suteu
 *
 */
public class ParseException extends Exception{
	
	public ParseException(String message){
		super(message);
	}

}
