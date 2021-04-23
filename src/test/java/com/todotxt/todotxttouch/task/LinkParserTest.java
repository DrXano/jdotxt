package com.todotxt.todotxttouch.task;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LinkParserTest {

	@Test
	public void testLinkParserNull() {
		List<URL> actual = LinkParser.getInstance().parse(null);
		List<URL> expected = new ArrayList<URL>();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLinkParserValid() throws MalformedURLException {
		List<URL> actual = LinkParser.getInstance().parse("https://www.youtube.com/");
		List<URL> expected = new ArrayList<URL>();
		URL link = new URL("https://www.youtube.com/");
		expected.add(link);
		assertEquals(expected, actual);
	}
}
