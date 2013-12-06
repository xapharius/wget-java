package tests.parser;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import parser.ArgParser;
import parser.ParseException;

public class TestArgParser_CheckSaveToFile {
	
	private ArgParser parser;
	
	@Before
	public void setUp() throws Exception {
		parser = new ArgParser();
	}
	
	
	/*
	 * Save-to-File Argument detector, single occurrence and the right syntax
	 */
	@Test
	public void testChekSaveToFile_ShortSingleOcc(){
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("-O");
		argList.add("filename");
		argList.add("url");	
		try {
			parser.checkSaveToFile(argList);
		} catch (ParseException e) {
			fail("Exception thrown!");
		}
		//check if right parameter passed
		assertEquals(parser.getParams().get("SaveToFile"), "filename");
		//check if args deleted
		assertEquals(1, argList.size());
	}
	
	/*
	 * Save-to-File Argument detector, multiple occurrence and the right syntax
	 */
	@Test
	public void testChekSaveToFile_ShortMultipleOcc(){
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("-O");
		argList.add("filename");	
		argList.add("-O");
		argList.add("filename2");
		try {
			parser.checkSaveToFile(argList);
		} catch (ParseException e) {
			fail("Exception thrown!");
		}
		//check if right parameter passed
		assertEquals(parser.getParams().get("SaveToFile"), "filename2");
		//check if args deleted
		assertEquals(0, argList.size());
	}
	
	/*
	 * Save-to-File Argument detector, missing Argument
	 */
	@Test
	public void testChekSaveToFile_ShortOmittedArg(){
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("-O");	
		argList.add("-t");
		argList.add("filename2");
		try {
			parser.checkSaveToFile(argList);
		} catch (ParseException e) {
			assertTrue(e.getMessage().startsWith("wget: option requires an argument"));
		}
	}
	
	/*
	 * Save-to-File Argument detector, missing Argument because last
	 */
	@Test
	public void testChekSaveToFile_ShortLastArg(){
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("-O");	
		try {
			parser.checkSaveToFile(argList);
		} catch (ParseException e) {
			assertTrue(e.getMessage().startsWith("wget: option requires an argument"));
		}
	}
	
	/*
	 * Save-to-File Argument detector, single occurrence and the right syntax
	 */
	@Test
	public void testChekSaveToFile_LongSingleOcc(){
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("--output-document=filename");
		argList.add("url");
		try {
			parser.checkSaveToFile(argList);
		} catch (ParseException e) {
			fail("Exception thrown!");
		}
		//check if right parameter passed
		assertEquals(parser.getParams().get("SaveToFile"), "filename");
		//check if args deleted
		assertEquals(1, argList.size());
	}
	
	/*
	 * Save-to-File Argument detector, multiple occurrence and the right syntax
	 */
	@Test
	public void testChekSaveToFile_LongMultipleOcc(){
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("--output-document=filename");
		argList.add("url");
		argList.add("--output-document=filename2=");
		try {
			parser.checkSaveToFile(argList);
		} catch (ParseException e) {
			fail("Exception thrown!");
		}
		//check if right parameter passed
		assertEquals(parser.getParams().get("SaveToFile"), "filename2=");
		//check if args deleted
		assertEquals(1, argList.size());
	}
	
	/*
	 * Save-to-File Argument detector, missing argument
	 */
	@Test
	public void testChekSaveToFile_LongOmittedArg(){
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("--output-document=");
		argList.add("url");
		try {
			parser.checkSaveToFile(argList);
		} catch (ParseException e) {
			assertTrue(e.getMessage().startsWith("wget: option requires an argument"));
		}
	}
	
	/*
	 * Save-to-File Argument detector, missing argument
	 */
	@Test
	public void testChekSaveToFile_LongMisuse(){
		ArrayList<String> argList = new ArrayList<String>();
		argList.add("--output-document");
		argList.add("url");
		try {
			parser.checkSaveToFile(argList);
		} catch (ParseException e) {
			assertTrue(e.getMessage().startsWith("wget: option requires an argument"));
		}
	}
}
