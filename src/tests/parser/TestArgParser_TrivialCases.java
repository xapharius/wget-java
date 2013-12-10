package tests.parser;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import parser.ArgParser;
import parser.ParseException;

public class TestArgParser_TrivialCases {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private ArgParser parser = new ArgParser();
	private ArrayList<String> argList;
	
	@Before
	public void setUp() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	    argList = new ArrayList<String>();
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	/*
	 * No Arguments passed
	 */
	@Test
	public void testNoArgs(){
		parser.trivialCases(argList);
		assertTrue(outContent.toString().startsWith("wget: missing URL"));
		outContent.reset();
	}
	
	/*
	 * Short and long command for help
	 */
	@Test
	public void testHelp(){
		argList.add("-h");
		parser.trivialCases(argList);
		assertTrue(outContent.toString().startsWith("wget Help"));
		outContent.reset();
		argList.remove(0);
		
		argList.add("--help");
		parser.trivialCases(argList);
		assertTrue(outContent.toString().startsWith("wget Help"));
	}
	
	/*
	 * Help command not on first position
	 */
	@Test
	public void testHelpAnyPos(){
		argList.add("-O");
		argList.add("arg");
		argList.add("-h");
		parser.trivialCases(argList);
		assertTrue(outContent.toString().startsWith("wget Help"));
	}
	
}
