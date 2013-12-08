/**
 * Easy Finding of Arguments from ArrayList
 */
package parser;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Mihai Suteu
 *
 */
public class OptionFinder {

	/**
	 * 
	 * @param argList
	 * @param option Option in short form e.g "-O"
	 * @param parseErrorMessage
	 * @return Argument of Option
	 * @throws ParseException
	 */
	public static String getArgShortOption(ArrayList<String> argList, String option, String parseErrorMessage) throws ParseException{
		String retArg = null;
		while(argList.contains(option)){
			int index = argList.indexOf(option);
			//doesnt have an argument (or is last)
			if(index + 1 >= argList.size() || argList.get(index+1).startsWith("-")){
				throw new ParseException(parseErrorMessage);
			}
			//update dictionary
			retArg= argList.get(index + 1);
			//remove from list
			argList.remove(index);
			argList.remove(index);
		}
		return retArg;
	}
	
	/**
	 * 
	 *@param argList
	 * @param option Option in short form e.g "--output-document"
	 * @param parseErrorMessage
	 * @return Argument of Option
	 * @throws ParseException
	 */
	public static String getArgLongOption(ArrayList<String> argList, String option, String parseErrorMessage) throws ParseException{
		String retArg = null;
		//see if no Equal Sign or Args are missing 
		if (argList.contains(option) || argList.contains(option + "=")){
			throw new ParseException(parseErrorMessage);
		}
		Iterator<String> iter = argList.iterator();
		//iterate and split since option and parameter are in one token
		while (iter.hasNext()) {
		   String str = iter.next();
		   if(str.startsWith(option + "=")){
			   String[] splits = str.split("=",2);
			   retArg = splits[1];
			   iter.remove();
		   }
		}
		return retArg;
	}

	/**
	 * Sees whether option appears in argument List
	 * @param argList
	 * @param option
	 * @param parseErrorMessage
	 * @return True or False
	 * @throws ParseException
	 */
	public static Boolean hasLongOption(ArrayList<String> argList, String option, String parseErrorMessage) throws ParseException{
		Iterator<String> iter = argList.iterator();
		//iterate and split since option and parameter are in one token
		while (iter.hasNext()) {
		   String str = iter.next();
		   if(str.startsWith(option + "=")){
			   throw new ParseException(parseErrorMessage);
		   }
		}
		if(argList.contains(option)){
			while(argList.contains(option)) argList.remove(option);
			return true;
		}
		return false;
	}
	
}
