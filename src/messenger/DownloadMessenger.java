/**
 *  Class containing all prints to stdout relating downloading
 */
package messenger;

/**
 * @author Mihai Suteu
 *
 */
public class DownloadMessenger {

	public static void printFileAvailable() {
		System.out.println("Remote file exists.");
	}

	public static void printFileNotFound() {
		System.out.println("Remote file does not exist -- broken link!!!");	
	}
	
}
