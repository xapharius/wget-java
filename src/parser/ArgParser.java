/**
 *  Check validity of Arguments passed to main
 *  Creates a hashmap with arguments
 */
package parser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Mihai Suteu
 *
 */

public class ArgParser {
	
	private HashMap<String, String> params;
	private ArrayList<String> argList;
	
	public ArgParser(){
		params = new HashMap<String, String>();
	}

	/**
	 *  @param args	arguments for wget
	 *  @return True if wget can be run with args, false if not
	 */
	public HashMap<String, String> parseArgs(String[] args){
//		check if no_args or help
		if (trivialCases(args)) return null;
		
//		check for valid arguments and put them to dictionary
		
//		remove arg from List after parsed		
		argList = new ArrayList<String>(Arrays.asList(args));
		
		try{
			//-O, --output-document=FILE
			checkSaveToFile(argList);
			//-S, --spider
			checkSpider(argList);
			//-c
			checkContinue();
			//-limit-rate=RATE
			checkLimitRate();

			//-t, --tries=NUMBER
			checkTries();
			
			//check if Args left
			
			
			//URL
			checkURL(argList);
			
		}
		catch(ParseException e){
			System.out.println(e.getMessage());
			messenger.ParserMessenger.printUsage();
			return null;
		}
			
		return params;
	}
	
	
	public void checkURL(ArrayList<String> argList) throws ParseException{
		if (argList.size() == 0){
			throw new ParseException("expecting URL, got no arguments left");
		}
		if (argList.size() > 1){
			throw new ParseException("expecting URL, got still arguments left");
		}
		try {
			new URL(argList.get(0));
		} catch (MalformedURLException e) {
			throw new ParseException("Malformed URL");
		}
		
		params.put("URL", argList.get(0));
		argList.remove(0);
	}

	private void checkTries() throws ParseException{
		// TODO Auto-generated method stub
		
	}

	private void checkLimitRate() throws ParseException{
		// TODO Auto-generated method stub
		
	}


	private void checkContinue() throws ParseException{
		// TODO Auto-generated method stub
		
	}
	

	public void checkSpider(ArrayList<String> argList) throws ParseException{
		Boolean hasOption;
		hasOption = OptionFinder.hasLongOption(argList, "--spider", "wget: option doesn't require an argument '--spider'");	
		if (hasOption) params.put("Spider", "true");
		else params.put("Spider", "false");
		
	}

	public void checkSaveToFile(ArrayList<String> argList) throws ParseException{
		String strArg;
		strArg = OptionFinder.getArgShortOption(argList, "-O", "wget: option requires an argument -- 'O'");	
		if (strArg != null) params.put("SaveToFile", strArg);
		strArg = OptionFinder.getArgLongOption(argList, "--output-document", "wget: option requires an argument '--output-document=FILENAME'");	
		if (strArg != null) params.put("SaveToFile", strArg);
	}
	
	


	/**
	 *  @param args	arguments for wget
	 *  @return True if args trivial and responded, false if not
	 */
	public Boolean trivialCases(String[] args){
//		no arguments provided
		if (args.length == 0){
			messenger.ParserMessenger.printNoArgs();
			return true;
		}

//		if help arg anywhere
		if (Arrays.asList(args).contains("-h") || Arrays.asList(args).contains("--help")){
			messenger.ParserMessenger.printHelp();
			return true;
		}
		
//		arguments are not trivial
		return false;
	}

	public HashMap<String, String> getParams() {
		return params;
	}
	
	
	
}
