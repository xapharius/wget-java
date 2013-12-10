package tests.parser;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import parser.ArgParser;
import parser.ParseException;

public class TestArgParser_TrivialCases {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private ArgParser parser = new ArgParser();
	private String[] args;
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	    
	}
	
	/*
	 * No Arguments passed
	 */
	@Test
	public void testNoArgs(){
		args = new String[0];
		parser.trivialCases(args);
		assertTrue(outContent.toString().startsWith("wget: missing URL"));
		outContent.reset();
	}
	
	/*
	 * Short and long command for help
	 */
	@Test
	public void testHelp(){
		args = new String[1];
		
		args[0] = "-h";
		parser.trivialCases(args);
		assertTrue(outContent.toString().startsWith("wget Help"));
		outContent.reset();
		
		args[0] = "--help";
		parser.trivialCases(args);
		assertTrue(outContent.toString().startsWith("wget Help"));
	}
	
	/*
	 * Help command not on first position
	 */
	@Test
	public void testHelpAnyPos(){
		String[] args = {"-O", "arg", "-h"};
		parser.trivialCases(args);
		assertTrue(outContent.toString().startsWith("wget Help"));
	}
	
	

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
}
