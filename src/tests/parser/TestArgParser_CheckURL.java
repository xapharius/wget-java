/**
 * 
 */
package tests.parser;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import parser.ArgParser;
import parser.ParseException;

/**
 * @author Mihai Suteu
 *
 */
public class TestArgParser_CheckURL {
	
	
	private ArgParser parser;
	private ArrayList<String> argList;
	
	@Before
	public void setUp() throws Exception {
		parser = new ArgParser();
		argList = new ArrayList<String>();
	}
	
	@Test
	public void testCheckURL_WellformedURL(){
		argList.add("http://xapharius.com");
		try {
			parser.checkURL(argList);
		} catch (ParseException e) {
			fail("Threw ParseException");
		}
	}
	
	@Test
	public void testCheckURL_MalformedURL(){
		argList.add("www.xapharius.com");
		try {
			parser.checkURL(argList);
		} catch (ParseException e) {
			assertTrue(e.getMessage().equals("Malformed URL"));
		}
	}
	
	@Test
	public void testCheckURL_noArgs(){
		try {
			parser.checkURL(argList);
		} catch (ParseException e) {
			assertTrue(e.getMessage().equals("expecting URL, got no arguments left"));
		}
	}
	
	@Test
	public void testCheckURL_tooManyArgs(){
		argList.add("http://xapharius.com");
		argList.add("also something else");
		try {
			parser.checkURL(argList);
		} catch (ParseException e) {
			assertTrue(e.getMessage().equals("expecting URL, got still arguments left"));
		}
	}
}
