package tests.parser;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import parser.ArgParser;
import parser.ParseException;

public class TestArgParser_CheckSaveToFile {
	
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
	
}
