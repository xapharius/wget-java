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

	public static void printFileAvailable() {
		System.out.println("Remote file exists.");
	}
	
	public static void printFileNotFound() {
		System.out.println("Remote file does not exist -- broken link!!!");
	}
	
	public static void printDownloadProgress(int nrDownloadedBytes, int nrTotalBytes) {
		System.out.println(nrDownloadedBytes + "/" + nrTotalBytes 
				+ " B - " + nrDownloadedBytes*100/nrTotalBytes + "%");
	}

	public static void printSuccesfulDownload(String string) {
    	System.out.println(string + " sucessfully downloaded!");
    	System.out.println();
	}
	
}
