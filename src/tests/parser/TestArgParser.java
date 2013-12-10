package tests.parser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import parser.ArgParser;

public class TestArgParser {

	private ArgParser parser;
	private ArrayList<String> argList;
	private HashMap<String, String> argDict;
	
	@Before
	public void setUp() throws Exception {
		parser = new ArgParser();
		argList = new ArrayList<String>();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * all Arguments, valid syntax
	 */
	@Test
	public void testParseArgs_allArgsPositive() {
		argList.add("-O");
		argList.add("filename");
		argList.add("--spider");
		argList.add("-t");
		argList.add("2");
		argList.add("url");
		argList.add("--input-file");
		argDict = parser.parseArgs(argList);
		assertNotNull(argDict);
		assertEquals(argDict.get("SaveToFile"), "filename");
		assertEquals(argDict.get("Tries"), "2");
		assertEquals(argDict.get("URL"), "url");
		assertEquals(argDict.get("Spider"), "true");
		assertEquals(argDict.get("FileList"), "true");
	}
	
	/**
	 * some Arguments, valid syntax
	 */
	@Test
	public void testParseArgs_someArgsPositive() {
		argList.add("--output-document=filename");
		argList.add("url");
		argList.add("--input-file");
		argDict = parser.parseArgs(argList);
		assertNotNull(argDict);
		assertEquals(argDict.get("SaveToFile"), "filename");
		assertEquals(argDict.get("Tries"), null);
		assertEquals(argDict.get("URL"), "url");
		assertEquals(argDict.get("Spider"), "false");
		assertEquals(argDict.get("FileList"), "true");
	}
	
	/**
	 * some Arguments, missing URL
	 */
	@Test
	public void testParseArgs_missingURL() {
		argList.add("-t");
		argList.add("2");
		argList.add("--output-document=filename");
		argList.add("--input-file");
		argDict = parser.parseArgs(argList);
		assertNull(argDict);
	}
	
	/**
	 * some Arguments, missing Argument
	 */
	@Test
	public void testParseArgs_missingArg() {
		argList.add("--output-document=filename");
		argList.add("-t");
		argList.add("url");
		argList.add("--input-file");
		argDict = parser.parseArgs(argList);
		assertNull(argDict);
	}
	
	/**
	 * some Arguments, unknown Argument
	 */
	@Test
	public void testParseArgs_unknownArg() {
		argList.add("--output-document=filename");
		argList.add("-q");
		argList.add("url");
		argList.add("--input-file");
		argDict = parser.parseArgs(argList);
		assertNull(argDict);
	}

}
