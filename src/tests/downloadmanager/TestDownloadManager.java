package tests.downloadmanager;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import downloadmanager.DownloadManager;

public class TestDownloadManager {
	
	private DownloadManager dmanager;

	@Before
	public void setUp() throws Exception {
		dmanager = new DownloadManager();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * test for findable website
	 */
	@Test
	public void testCheckRessourceAvailabilityPositiveAddr() {
		URL url = null;
		try {
			url = new URL("http://xapharius.com");
		} catch (MalformedURLException e) {
			fail("url not initialized correctly");
		}
		assertTrue(dmanager.checkRessourceAvailability(url));
	}
	
	/**
	 * test for findable website
	 */
	@Test
	public void testCheckRessourceAvailabilityNegaiveAddr() {
		URL url = null;
		try {
			url = new URL("http://xapharius123.com");
		} catch (MalformedURLException e) {
			fail("url not initialized correctly");
		}
		assertFalse(dmanager.checkRessourceAvailability(url));
	}
	
	/**
	 * test for findable file
	 */
	@Test
	public void testCheckRessourceAvailabilityPositiveFile() {
		URL url = null;
		try {
			url = new URL("http://xapharius.com/beer.jpg");
		} catch (MalformedURLException e) {
			fail("url not initialized correctly");
		}
		assertTrue(dmanager.checkRessourceAvailability(url));
	}
	
	/**
	 * test for non findable file
	 */
	@Test
	public void testCheckRessourceAvailabilityNegativeFile() {
		URL url = null;
		try {
			url = new URL("http://xapharius.com/beer123.jpg");
		} catch (MalformedURLException e) {
			fail("url not initialized correctly");
		}
		assertFalse(dmanager.checkRessourceAvailability(url));
	}
	
	/**
	 * test for non initialized url
	 */
	@Test
	public void testCheckRessourceAvailabilityUninitialized() {
		URL url = null;
		assertFalse(dmanager.checkRessourceAvailability(url));
	}

}
