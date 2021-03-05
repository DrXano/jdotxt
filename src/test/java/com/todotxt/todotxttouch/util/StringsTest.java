package com.todotxt.todotxttouch.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StringsTest {

	private String s;
	private String stringToInsert;
	private int insertAt;
	private String actual;
	private String expected;

	@Before
	public void setUp() {
		s = null;
		stringToInsert = null;
		insertAt = -1;
		actual = null;
		expected = null;
	}

	@Test
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testPositiveIndexAndNotExist() {
		s = "aaabbb";
		stringToInsert = "ccc";
		insertAt = 3;
		expected = "aaa ccc bbb";
		actual = Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testPositiveIndexAndExist() {
		s = "aaa ccc bbb";
		stringToInsert = "ccc";
		insertAt = 3;
		expected = "aaa ccc bbb";
		actual = Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testZeroIndexAndNotExist() {
		s = "aaabbb";
		stringToInsert = "ccc";
		insertAt = 0;
		expected = "ccc aaabbb";
		actual = Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testZeroIndexAndExist() {
		s = "ccc aaabbb";
		stringToInsert = "ccc";
		insertAt = 0;
		expected = "ccc aaabbb";
		actual = Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testNegativeIndex() {
		s = "aaabbb";
		stringToInsert = "ccc";
		insertAt = -2;
		actual = Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testIndexSuperiorToStringSize() {
		s = "aaabbb";
		stringToInsert = "ccc";
		insertAt = 10;
		actual = Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
	}
}
