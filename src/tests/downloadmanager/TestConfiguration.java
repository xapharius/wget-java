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
		argDict = config.getParams();
	}

	@After
	public void tearDown() throws Exception {
	}


	/**
	 * Protocol is ok, URL should remain unchanged
	 */
	@Test
	public void TestCheckProtocolPositive() {
		argDict.put("URL", "http://xapharius.com");
		try {
			config.checkProtocol(argDict);
		} catch (ConfigurationException e) {
			fail("Exception thrown");
		}
		assertEquals(argDict.get("URL"), "http://xapharius.com");
	}
	
	/**
	 * url is ok but missing http, URL should get http prefix
	 */
	@Test
	public void TestCheckProtocolImplicitHttp() {
		argDict.put("URL", "xapharius.com");
		try {
			config.checkProtocol(argDict);
		} catch (ConfigurationException e) {
			fail("Exception thrown");
		}
		assertEquals(argDict.get("URL"), "http://xapharius.com");
	}
	
	/**
	 * Protocol is unsupported, exception
	 */
	@Test
	public void TestCheckProtocolUnsupported() {
		argDict.put("URL", "ftp://xapharius.com");
		try {
			config.checkProtocol(argDict);
			fail("No Exception thrown");
		} catch (ConfigurationException e) {
			assertEquals(e.getMessage(), "Unsupported Protocol!");
		}
	}
	
	/**
	 * SaveToFile has default name, since file is not specified
	 */
	@Test
	public void TestUpdateSaveToFileKeepDefault() {
		argDict.put("URL", "http://xapharius.com");
		try {
			config.updateSaveToFile(argDict);
		} catch (ConfigurationException e) {
			fail("Exception thrown");
		}
		assertEquals(argDict.get("SaveToFile"), "index.html");
	}
	
	/**
	 * SaveToFile has new name, since file is specified
	 */
	@Test
	public void TestUpdateSaveToFileChange() {
		argDict.put("URL", "http://xapharius.com/beer.jpg");
		try {
			config.updateSaveToFile(argDict);
		} catch (ConfigurationException e) {
			fail("Exception thrown");
		}
		assertEquals(argDict.get("SaveToFile"), "beer.jpg");
	}
	
	/**
	 * SaveToFile has default name, since no file is specified although URL ends in slash
	 */
	@Test
	public void TestUpdateSaveToFileKeepDefault2() {
		argDict.put("URL", "http://xapharius.com/");
		try {
			config.updateSaveToFile(argDict);
		} catch (ConfigurationException e) {
			fail("Exception thrown");
		}
		assertEquals(argDict.get("SaveToFile"), "index.html");
	}
	
	/**
	 * Wellformed URL
	 */
	@Test
	public void TestSemanticURLPositive() {
		argDict.put("URL", "www.xapharius.com");
		try {
			config.semanticURL(argDict);
		} catch (ConfigurationException e) {
			System.out.println(e.getMessage());
			fail("Exception thrown");
		}
		assertEquals(argDict.get("SaveToFile"), "index.html");
	}
	
	/**
	 * Wellformed URL to File
	 */
	@Test
	public void TestSemanticURLPositiveURLToFile() {
		argDict.put("URL", "www.xapharius.com/beer.jpg");
		try {
			config.semanticURL(argDict);
		} catch (ConfigurationException e) {
			System.out.println(e.getMessage());
			fail("Exception thrown");
		}
		assertEquals(argDict.get("SaveToFile"), "beer.jpg");
	}
	
	/**
	 * Wellformed URL to File, SaveToFile Option already used
	 */
	@Test
	public void TestSemanticURLNamedOutputDocument() {
		argDict.put("URL", "www.xapharius.com/beer.jpg");
		argDict.put("SaveToFile", "photo.jpg");
		try {
			config.semanticURL(argDict);
		} catch (ConfigurationException e) {
			System.out.println(e.getMessage());
			fail("Exception thrown");
		}
		assertEquals(argDict.get("SaveToFile"), "photo.jpg");
	}
	
	
	/**
	 * Local File
	 */
	@Test
	public void TestSemanticURLFileList() {
		argDict.put("URL", "localfile/list.txt");
		argDict.put("FileList", "true");
		try {
			config.semanticURL(argDict);
		} catch (ConfigurationException e) {
			System.out.println(e.getMessage());
			fail("Exception thrown");
		}
		assertEquals(argDict.get("URL"), "localfile/list.txt");
	}
	
	/**
	 * Empty URL
	 */
	@Test
	public void TestSemanticURLEmptyURL() {
		try {
			config.semanticURL(argDict);
			fail("No Exception thrown");
		} catch (ConfigurationException e) {
			assertEquals(e.getMessage(), "No URL specified");
		}
	}
	
	/**
	 * Unsupported Protocol
	 */
	@Test
	public void TestSemanticURLUnsupportedProtocol() {
		argDict.put("URL", "ftp://url");
		try {
			config.semanticURL(argDict);
			fail("No Exception thrown");
		} catch (ConfigurationException e) {
			assertEquals(e.getMessage(), "Unsupported Protocol!");
		}
	}
	
	/**
	 * Tries is positive integer
	 */
	@Test
	public void TestCheckSemanticTriesPositive() {
		argDict.put("Tries", "3");
		try {
			config.checkSemanticTries(argDict);
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
		try {
			config.checkSemanticTries(argDict);
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
		try {
			config.checkSemanticTries(argDict);
			fail("Exception not thrown");
		} catch (ConfigurationException e) {
		}
	}

}
