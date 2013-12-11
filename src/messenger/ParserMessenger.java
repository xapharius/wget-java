/**
 * Class containing all prints to stdout relating parsing
 */
package messenger;

/**
 * @author Mihai Suteu
 *
 */
public class ParserMessenger {
	/**
	 * print help message
	 */
	public static void printHelp(){
		System.out.println("wget Help");
		System.out.println("Usage: wget [OPTION]... [URL]...");
		System.out.println();
		System.out.println("-O,  --output-document=FILE \t\t write documents to FILE.");
		System.out.println("     --input-file \t\t\t download URLs found in local file. Each URL on a different line.");
		System.out.println("-t,  --tries=NUMBER    \t\t\t set number of retries to NUMBER.");
		System.out.println("     --spider \t\t\t\t to see if remote file is available.");
		System.out.println();
		System.out.println("Currently only http protocol is supported.");
	}
	
	/**
	 * wget call without arguments
	 */
	public static void printNoArgs(){
		System.out.println("wget: missing URL");
		printUsage();
	}
	
	/**
	 * print wget syntax
	 */
	public static void printUsage(){
		System.out.println("Usage: wget [OPTION]... [URL]... ");
		System.out.println("Try `wget --help' for more options.");
	}
}
