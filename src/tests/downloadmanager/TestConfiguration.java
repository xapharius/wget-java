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
	public void TestConfigureDefaultSaveToFile() {
		argDict.put("URL", "http://xapharius.com");
		try {
			config.configure(argDict);
		} catch (ConfigurationException e) {
			fail("Exception thrown");
		}
		assertEquals(config.getParamValue("SaveToFile"), "index.html");
	}
	
	/**
	 * SaveToFile has new name, since file is specified
	 */
	@Test
	public void TestConfigureUpdatedSaveToFile() {
		argDict.put("URL", "http://xapharius.com/beer.jpg");
		try {
			config.configure(argDict);
		} catch (ConfigurationException e) {
			fail("Exception thrown");
		}
		assertEquals(config.getParamValue("SaveToFile"), "beer.jpg");
	}
	
	/**
	 * SaveToFile has default name, since no file is specified although URL ends in slash
	 */
	@Test
	public void TestConfigureDefaultSaveToFile2() {
		argDict.put("URL", "http://xapharius.com/");
		try {
			config.configure(argDict);
		} catch (ConfigurationException e) {
			fail("Exception thrown");
		}
		assertEquals(config.getParamValue("SaveToFile"), "index.html");
	}

}
