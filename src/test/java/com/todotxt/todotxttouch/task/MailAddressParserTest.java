package com.todotxt.todotxttouch.task;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MailAddressParserTest {

	@Test
	public void tesMailAddressParserNull() {
		List<String> actual = MailAddressParser.getInstance().parse(null);
		List<String> expected = new ArrayList<>();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testMailAddressParserValid() throws MalformedURLException {
		List<String> actual = MailAddressParser.getInstance().parse("RandomEmailAccount123@gmail.com");
		List<String> expected = new ArrayList<String>();
		String mail = "RandomEmailAccount123@gmail.com";
		expected.add(mail);
		assertEquals(expected, actual);
	}

}
