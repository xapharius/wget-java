/**
 *  Class containing all prints to stdout relating downloading
 */
package messenger;

import java.net.URL;

/**
 * @author Mihai Suteu
 *
 */
public class DownloadMessenger {
	//TODO cleanup unused methods

	public static void printFileAvailable() {
		System.out.println("Remote file exists.");
	}
	
	public static void printFileAvailable(URL _url) {
		System.out.println(_url.toString() + " Remote file exists.");
	}

	public static void printFileNotFound() {
		System.out.println("Remote file does not exist -- broken link!!!");
	}
	
	public static void printFileNotFound(URL _url) {
		if (_url == null){
			System.out.println("Remote file does not exist -- broken link!!!");
		}
		else{
			System.out.println(_url.toString() + " -- broken link!!!");	
		}
	}
	
}
