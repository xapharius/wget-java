package tests.parser;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import parser.OptionFinder;
import parser.ParseException;

public class TestOptionFinder {

	private ArrayList<String> argList;
	private String retArg;
	private Boolean hasArg;
	
	@Before
	public void setUp() throws Exception {
		argList = new ArrayList<String>();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Return argument of option in short form, single and right occurrence of arg
	 */
	@Test
	public void testGetArgShortOption_SingleOcc() {
		argList.add("-O");
		argList.add("filename");
		argList.add("url");	
		
		try {
			retArg = OptionFinder.getArgShortOption(argList, "-O", "error-O");
		} catch (ParseException e) {
			fail("Exception thrown!");
		}
		//check if right parameter passed
		assertEquals(retArg, "filename");
		//check if args deleted
		assertEquals(1, argList.size());		
	}
	
	/**
	 * Return argument of option in short form, single and right occurrence of arg
	 */
	@Test
	public void testGetArgShortOption_MultipleOcc(){
		argList.add("-O");
		argList.add("filename");	
		argList.add("-O");
		argList.add("filename2");
		try {
			retArg = OptionFinder.getArgShortOption(argList, "-O", "error-O");
		} catch (ParseException e) {
			fail("Exception thrown!");
		}
		//check if right parameter passed
		assertEquals(retArg, "filename2");
		//check if args deleted
		assertEquals(0, argList.size());
	}
	
	/**
	 * Return argument of option in short form, missing argument
	 */
	@Test
	public void testGetArgShortOption_MissingArg(){
		argList.add("-O");	
		argList.add("-t");
		argList.add("filename2");
		try {
			retArg = OptionFinder.getArgShortOption(argList, "-O", "error-O");
			fail("No exception thrown");
		} catch (ParseException e) {
			assertTrue(e.getMessage().startsWith("error-O"));
		}
	}
	
	/**
	 * Return argument of option in short form, missing last argument
	 */
	@Test
	public void testGetArgShortOption_MissingLastArg(){
		argList.add("-O");	
		try {
			retArg = OptionFinder.getArgShortOption(argList, "-O", "error-O");
			fail("No exception thrown");
		} catch (ParseException e) {
			assertTrue(e.getMessage().startsWith("error-O"));
		}
	}
	
	/**
	 * Return argument of option in long form, single and right occurrence of arg
	 */
	@Test
	public void testGetArgLongOption_SingleOcc() {
		argList.add("--output-document=filename");
		argList.add("url");		
		try {
			retArg = OptionFinder.getArgLongOption(argList, "--output-document", "error-OL");
		} catch (ParseException e) {
			fail("Exception thrown!");
		}
		//check if right parameter passed
		assertEquals(retArg, "filename");
		//check if args deleted
		assertEquals(1, argList.size());		
	}
	
	/**
	 * Return argument of option in long form, multiple and right occurrence of arg
	 */
	@Test
	public void testGetArgLongOption_MultipleOcc() {
		argList.add("--output-document=filename");
		argList.add("url");
		argList.add("--output-document=filename2");	
		try {
			retArg = OptionFinder.getArgLongOption(argList, "--output-document", "error-OL");
		} catch (ParseException e) {
			fail("Exception thrown!");
		}
		//check if right parameter passed
		assertEquals(retArg, "filename2");
		//check if args deleted
		assertEquals(1, argList.size());		
	}
	
	/**
	 * Return argument of option in long form, multiple and right occurrence of arg
	 */
	@Test
	public void testGetArgLongOption_MultipleEquals() {
		argList.add("--output-document==");
		argList.add("url");
		try {
			retArg = OptionFinder.getArgLongOption(argList, "--output-document", "error-OL");
		} catch (ParseException e) {
			fail("Exception thrown!");
		}
		//check if right parameter passed
		assertEquals(retArg, "=");
		//check if args deleted
		assertEquals(1, argList.size());		
	}
	
	/**
	 * Return argument of option in Long form, missing argument
	 */
	@Test
	public void testGetArgLongOption_MissingArg(){
		argList.add("--output-document=");	
		argList.add("filename2");
		try {
			retArg = OptionFinder.getArgLongOption(argList, "--output-document", "error-OL");
			fail("No exception thrown");
		} catch (ParseException e) {
			assertTrue(e.getMessage().equals("error-OL"));
		}
	}
	
	/**
	 * Return argument of option in Long form, missing equals and arg
	 */
	@Test
	public void testGetArgLongOption_MissuseArg(){
		argList.add("--output-document");	
		argList.add("filename2");
		try {
			retArg = OptionFinder.getArgLongOption(argList, "--output-document", "error-OL");
			fail("No exception thrown");
		} catch (ParseException e) {
			assertTrue(e.getMessage().equals("error-OL"));
		}
	}
	
	/**
	 * Test whether Long option is found and removed
	 */
	@Test
	public void testHasLongOptionPositive(){
		argList.add("--spider");	
		argList.add("filename");
		try {
			hasArg = OptionFinder.hasLongOption(argList, "--spider", "error-spider");
		} catch (ParseException e) {
			fail("Exception thrown");
		}
		assertTrue(hasArg);
		assertEquals(1, argList.size());	
	}
	
	/**
	 * Test whether all multiple occurences are found and removed
	 */
	@Test
	public void testHasLongOptionMultipleOccurences(){
		argList.add("--spider");	
		argList.add("filename");
		argList.add("--spider");	
		try {
			hasArg = OptionFinder.hasLongOption(argList, "--spider", "error-spider");
		} catch (ParseException e) {
			fail("Exception thrown");
		}
		assertTrue(hasArg);
		assertEquals(1, argList.size());	
	}
	
	/**
	 * Test whether Long option is not found
	 */
	@Test
	public void testHasLongOptionNegative(){
		argList.add("--something");	
		argList.add("filename");
		try {
			hasArg = OptionFinder.hasLongOption(argList, "--spider", "error-spider");
		} catch (ParseException e) {
			fail("Exception thrown");
		}
		assertFalse(hasArg);
		assertEquals(2, argList.size());	
	}
	
	/**
	 * Test if throws exception
	 */
	@Test
	public void testHasLongOptionException(){
		argList.add("--spider=true");	
		argList.add("filename");
		try {
			hasArg = OptionFinder.hasLongOption(argList, "--spider", "error-spider");
			fail("No exception thrown");
		} catch (ParseException e) {
			assertTrue(e.getMessage().equals("error-spider"));
		}
		assertEquals(2, argList.size());	
	}

}
