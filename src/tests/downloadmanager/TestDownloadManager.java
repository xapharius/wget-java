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
	 * test downloading file, negative
	 */
	@Test
	public void testDownloadFile_Negative(){
		argDict.put("URL", "http://xapharius.com/inexistent.txt");
		try {
			dManager = new DownloadManager(argDict);
			dManager.downloadFile();
		} catch (Exception e) {
			e.getMessage();
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * File with 3 urls
	 */
	@Test
	public void testGetURLsFromFile_Positive(){
		argDict.put("URL", "testGetURLsFromFile_Positive");
		try {
			dManager = new DownloadManager(argDict);
		} catch (ConfigurationException e) {
			fail();
		}
		ArrayList<String> urlList = dManager.getURLsFromFile("src/tests/trunk/testURLlist.txt");
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
		argDict.put("URL", "testGetURLsFromFile_UnfindableFile");
		try {
			dManager = new DownloadManager(argDict);
		} catch (ConfigurationException e) {
			fail();
		}
		ArrayList<String> urlList = dManager.getURLsFromFile("someList.txt");
		assertNull(urlList);
	}
	
	/**
	 * Check if forked Manager downloads
	 */
	@Test
	public void testForkDonwloadManager(){
		argDict.put("URL", "FORK TEST");
		argDict.put("SaveToFile", "src/tests/trunk/forkedBeer.jpg");
		try {
			dManager = new DownloadManager(argDict);
			dManager.forkDownloadManager("http://xapharius.com/beer.jpg");
		} catch (ConfigurationException e) {
			fail("Outer Manager Failed");
		}
	}
	
	/**
	 * Check if Option --input-file works
	 */
	@Test
	public void testFileList(){
		argDict.put("URL", "src/tests/trunk/testURLlist.txt");
		argDict.put("SaveToFile", "src/tests/trunk/fromFileList.jpg");
		argDict.put("FileList", "true");
		try {
			dManager = new DownloadManager(argDict);
			dManager.runManager();
		} catch (ConfigurationException e) {
			System.out.println(e.getMessage());
			fail("Outer Manager Failed");
		}
	}
	
	
}
