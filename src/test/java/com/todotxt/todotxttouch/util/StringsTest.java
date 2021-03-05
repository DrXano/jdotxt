package com.todotxt.todotxttouch.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class StringsTest {

	//----------------------------------------------------------------------------------------------------------
	//------------------------------------------insertPaddedIfNeeded()------------------------------------------
	//----------------------------------------------------------------------------------------------------------

	@Test
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testPositiveIndexAndNotExist() {
		String s = "aaabbb";
		String stringToInsert = "ccc";
		int insertAt = 3;
		String expected = "aaa ccc bbb";
		String actual = Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testPositiveIndexAndExist() {
		String s = "aaa ccc bbb";
		String stringToInsert = "ccc";
		int insertAt = 3;
		String expected = "aaa ccc bbb";
		String actual = Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testZeroIndexAndNotExist() {
		String s = "aaabbb";
		String stringToInsert = "ccc";
		int insertAt = 0;
		String expected = "ccc aaabbb";
		String actual = Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testZeroIndexAndExist() {
		String s = "ccc aaabbb";
		String stringToInsert = "ccc";
		int insertAt = 0;
		String expected = "ccc aaabbb";
		String actual = Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testNegativeIndex() {
		String s = "aaabbb";
		String stringToInsert = "ccc";
		int insertAt = -2;
		String actual = Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testIndexSuperiorToStringSize() {
		String s = "aaabbb";
		String stringToInsert = "ccc";
		int insertAt = 10;
		String actual = Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
	}
	
	//----------------------------------------------------------------------------------------------------------
	//------------------------------------------------isBlank()-------------------------------------------------
	//----------------------------------------------------------------------------------------------------------

	@Test
	/*
	 * method - isBlank
	 */
	public void test1() {
		fail("Not yet implemented");
	}
	
	@Test
	/*
	 * method - isBlank
	 */
	public void test2() {
		fail("Not yet implemented");
	}
	
	@Test
	/*
	 * method - isBlank
	 */
	public void test3() {
		fail("Not yet implemented");
	}
	
	@Test
	/*
	 * method - isBlank
	 */
	public void test4() {
		fail("Not yet implemented");
	}
}
