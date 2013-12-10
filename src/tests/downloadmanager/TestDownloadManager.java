package tests.downloadmanager;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import downloadmanager.ConfigurationException;
import downloadmanager.DownloadException;
import downloadmanager.DownloadManager;

public class TestDownloadManager {
	
	private DownloadManager dManager;
	private DownloadManager dManagerBasic;
	private HashMap<String, String> argDict;

	@Before
	public void setUp() throws Exception {
		argDict = new HashMap<String, String>();
		
		HashMap<String, String> basicArgDict = new HashMap<String, String>();
		basicArgDict.put("URL", "something.com");
		dManagerBasic = new DownloadManager(basicArgDict);
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
			assertEquals(e.getMessage(), "No URL specified");
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
	@Test
	public void testDownloadFile_Positive(){
		argDict.put("URL", "http://xapharius.com/beer.jpg");
		argDict.put("SaveToFile", "src/tests/trunk/beer.jpg");
		try {
			dManager = new DownloadManager(argDict);
			dManager.downloadFile();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * File with 3 urls
	 */
	@Test
	public void testGetURLsFromFile_Positive(){
		ArrayList<String> urlList = dManagerBasic.getURLsFromFile("src/tests/trunk/testURLlist.txt");
		assertNotNull(urlList);
		assertEquals(urlList.size(), 3);
		assertEquals(urlList.get(0), "www.xapharius.com");
		assertEquals(urlList.get(1), "xapharius.com/beer.jpg");
		assertEquals(urlList.get(2), "xapharius.com/inexistent.txt");
	}
	
	/**
	 * File Not found
	 */
	@Test
	public void testGetURLsFromFile_UnfindableFile(){
		ArrayList<String> urlList = dManagerBasic.getURLsFromFile("someList.txt");
		assertNull(urlList);
	}
	
	
}
