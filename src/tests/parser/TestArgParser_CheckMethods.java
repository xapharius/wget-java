package tests.parser;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import parser.ArgParser;
import parser.ParseException;

public class TestArgParser_CheckMethods {
	
	private ArgParser parser;
	private ArrayList<String> argList;
	
	@Before
	public void setUp() throws Exception {
		argList = new ArrayList<String>(); 
		parser = new ArgParser();
	}
	
	
	/**
	 * put both option possibilities, should save the long form's arg
	 */
	@Test
	public void testChekSaveToFile_bothOptions(){
		argList.add("-O");
		argList.add("filename");
		argList.add("--output-document=filename2");
		argList.add("url");	
		try {
			parser.checkSaveToFile(argList);
		} catch (ParseException e) {
			fail("Exception thrown!");
		}
		//check if right parameter passed
		assertEquals(parser.getParams().get("SaveToFile"), "filename2");
		//check if args deleted
		assertEquals(1, argList.size());
	}
	
	/**
	 * put both option possibilities, should save the long form's arg
	 */
	@Test
	public void testChekSaveToFile_bothOptions2(){
		argList.add("-O");
		argList.add("filename");
		argList.add("--output-document=filename2");
		argList.add("-O");
		argList.add("filename3");
		argList.add("-t");
		argList.add("url");	
		try {
			parser.checkSaveToFile(argList);
		} catch (ParseException e) {
			fail("Exception thrown!");
		}
		//check if right parameter passed
		assertEquals(parser.getParams().get("SaveToFile"), "filename2");
		//check if args deleted
		assertEquals(2, argList.size());
	}
	
	@Test
	public void testCheckURL_WellformedURL(){
		argList.add("http://xapharius.com");
		try {
			parser.checkURL(argList);
		} catch (ParseException e) {
			fail("Threw ParseException");
		}
		assertEquals(parser.getParams().get("URL"), "http://xapharius.com");
	}
	
	@Test
	public void testCheckURL_WellformedURLNoProtocol(){
		argList.add("www.xapharius.com");
		try {
			parser.checkURL(argList);
		} catch (ParseException e) {
			fail("Threw ParseException");
		}
		assertEquals(parser.getParams().get("URL"), "http://www.xapharius.com");
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
	public void testCheckURL_UnsupportedProtocol(){
		argList.add("ftp://www.xapharius.com");
		try {
			parser.checkURL(argList);
		} catch (ParseException e) {
			assertTrue(e.getMessage().equals("Unsupported Protocol!"));
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
	public void testCheckSpider_positive(){
		argList.add("http://xapharius.com");
		argList.add("--spider");	
		try {
			parser.checkSpider(argList);
		} catch (ParseException e) {
			fail("Exception thrown");
		}
		assertEquals(parser.getParams().get("Spider"), "true");
	}
	
	@Test
	public void testCheckSpider_negative(){
		argList.add("http://xapharius.com");
		argList.add("-s");	
		try {
			parser.checkSpider(argList);
		} catch (ParseException e) {
			fail("Exception thrown");
		}
		assertEquals(parser.getParams().get("Spider"), "false");
	}
	
	@Test
	public void testCheckSpider_exception(){
		argList.add("http://xapharius.com");
		argList.add("--spider");
		argList.add("--spider=");
		try {
			parser.checkSpider(argList);
			fail("No Exception thrown");
		} catch (ParseException e) {
			assertEquals(e.getMessage(), "wget: option doesn't require an argument '--spider'");
		}
		
	}
	
	@Test
	public void testCheckForLeftovers_positive(){
		argList.add("http://xapharius.com");
		try {
			parser.checkForLeftovers(argList);
		} catch (ParseException e) {
			fail("Exception thrown");
		}
		assertEquals(argList.size(), 1);
	}
	
	@Test
	public void testCheckForLeftovers_tooManyArgs(){
		argList.add("http://xapharius.com");
		argList.add("-t");
		try {
			parser.checkForLeftovers(argList);
			fail("Exception not thrown");
		} catch (ParseException e) {
			assertEquals(e.getMessage(), "Unrecognized arguments");
		}
	}
	
	@Test
	public void testCheckForLeftovers_onlyOptionLeft(){
		argList.add("-t");
		try {
			parser.checkForLeftovers(argList);
			fail("Exception not thrown");
		} catch (ParseException e) {
			assertTrue(e.getMessage().startsWith("Unrecognized option"));
		}
	}
	
}
