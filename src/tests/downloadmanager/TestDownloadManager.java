package tests.downloadmanager;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import downloadmanager.ConfigurationException;
import downloadmanager.DownloadException;
import downloadmanager.DownloadManager;

public class TestDownloadManager {
	
	private DownloadManager dManager;
	private HashMap<String, String> argDict;

	@Before
	public void setUp() throws Exception {
		argDict = new HashMap<String, String>();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	/**
	 * test for non initialized url
	 */
	@Test
	public void testDownloadManager_missingURL() {
		try {
			dManager = new DownloadManager(argDict);
		} catch (ConfigurationException e) {
			assertEquals(e.getMessage(), "Missing or Malformed URL");
		}
	}

	/**
	 * test for findable website
	 */
	@Test
	public void testCheckRessourceAvailabilityPositiveAddr() {
		argDict.put("URL", "http://xapharius.com");
		try {
			dManager = new DownloadManager(argDict);
		} catch (ConfigurationException e) {
			fail("Manager Configuration Error");
		}
		assertTrue(dManager.checkRessourceAvailability());
	}
	
	/**
	 * test for findable website
	 */
	@Test
	public void testCheckRessourceAvailabilityNegaiveAddr() {
		argDict.put("URL", "http://xapharius123.com");
		try {
			dManager = new DownloadManager(argDict);
		} catch (ConfigurationException e) {
			fail("Manager Configuration Error");
		}
		assertFalse(dManager.checkRessourceAvailability());
	}
	
	/**
	 * test for findable file
	 */
	@Test
	public void testCheckRessourceAvailabilityPositiveFile() {
		argDict.put("URL", "http://xapharius.com/beer.jpg");
		try {
			dManager = new DownloadManager(argDict);
		} catch (ConfigurationException e) {
			fail("Manager Configuration Error");
		}
		assertTrue(dManager.checkRessourceAvailability());
	}
	
	/**
	 * test for non findable file
	 */
	@Test
	public void testCheckRessourceAvailabilityNegativeFile() {
		argDict.put("URL", "http://xapharius.com/beer123.jpg");
		try {
			dManager = new DownloadManager(argDict);
		} catch (ConfigurationException e) {
			fail("Manager Configuration Error");
		}
		assertFalse(dManager.checkRessourceAvailability());
	}
	
	/**
	 * test downloading file, positive 
	 */
	public void testDownloadFile_positive(){
		argDict.put("URL", "http://xapharius.com/beer.jpg");
		try {
			dManager = new DownloadManager(argDict);
			dManager.downloadFile();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	
}
