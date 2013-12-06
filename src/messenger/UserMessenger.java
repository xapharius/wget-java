/**
 * Class containing all prints to stdout
 */
package messenger;

/**
 * @author Mihai Suteu
 *
 */
public class UserMessenger {
	/**
	 * print help message
	 */
	public static void printHelp(){
		System.out.println("wget Help");
		System.out.println("Usage: wget [OPTION]... [URL]...");
		System.out.println("`-O filname` \t Save to named file");
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
