package com.todotxt.todotxttouch.util;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;

public class RelativeDateTest {

	@Test
	/*
	 * method - getRelativeDate
	 */
	public void testIsEqual() {
		Date d = Date.valueOf(LocalDate.of(2020, 1, 1));
		String actual = RelativeDate.getRelativeDate(d);
		String expected = "";
		assertEquals(expected,actual);
	}
	
	@Test
	/*
	 * method - getRelativeDate
	 */
	public void testIsDiferent() {
		Date d = Date.valueOf(LocalDate.now());
		String actual = RelativeDate.getRelativeDate(d);
		String expected = "";
		assertEquals(expected,actual);
	}


}
