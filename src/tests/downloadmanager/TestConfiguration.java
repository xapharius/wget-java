package tests.downloadmanager;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import downloadmanager.Configuration;
import downloadmanager.ConfigurationException;

public class TestConfiguration {

	private Configuration config;
	private HashMap<String, String> argDict;
	
	@Before
	public void setUp() throws Exception {
		config = new Configuration();
		argDict = new HashMap<String, String>();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * SaveToFile has default name, since file is not specified
	 */
	@Test
	public void TestUpdateSaveToFileKeepDefault() {
		argDict.put("URL", "http://xapharius.com");
		try {
			config.updateParams(argDict);
			config.updateSaveToFile(argDict);
		} catch (ConfigurationException e) {
			fail("Exception thrown");
		}
		assertEquals(config.getParamValue("SaveToFile"), "index.html");
	}
	
	/**
	 * SaveToFile has new name, since file is specified
	 */
	@Test
	public void TestUpdateSaveToFileChange() {
		argDict.put("URL", "http://xapharius.com/beer.jpg");
		config.updateParams(argDict);
		try {
			config.updateSaveToFile(argDict);
		} catch (ConfigurationException e) {
			fail("Exception thrown");
		}
		assertEquals(config.getParamValue("SaveToFile"), "beer.jpg");
	}
	
	/**
	 * SaveToFile has default name, since no file is specified although URL ends in slash
	 */
	@Test
	public void TestUpdateSaveToFileKeepDefault2() {
		argDict.put("URL", "http://xapharius.com/");
		config.updateParams(argDict);
		try {
			config.updateSaveToFile(argDict);
		} catch (ConfigurationException e) {
			fail("Exception thrown");
		}
		assertEquals(config.getParamValue("SaveToFile"), "index.html");
	}
	
	/**
	 * Tries is positive integer
	 */
	@Test
	public void TestCheckSemanticTriesPositive() {
		argDict.put("Tries", "3");
		config.updateParams(argDict);
		try {
			config.checkSemanticTries();
		} catch (ConfigurationException e) {
			fail("Exception thrown");
		}
	}
	
	/**
	 * Tries is not a positive integer
	 */
	@Test
	public void TestCheckSemanticTriesNotInteger() {
		argDict.put("Tries", "3.5");
		config.updateParams(argDict);
		try {
			config.checkSemanticTries();
			fail("Exception not thrown");
		} catch (ConfigurationException e) {
		}
	}
	
	/**
	 * Tries is not a positive integer
	 */
	@Test
	public void TestCheckSemanticTriesNotPositive() {
		argDict.put("Tries", "-1");
		config.updateParams(argDict);
		try {
			config.checkSemanticTries();
			fail("Exception not thrown");
		} catch (ConfigurationException e) {
		}
	}

}
