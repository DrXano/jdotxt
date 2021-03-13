package com.todotxt.todotxttouch.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StringsTest {
	
	private String s;
	private String stringToInsert;
	
	@Before
	public void setUp() {
		s = "qwerty";
		stringToInsert = "abc";
	}

	//----------------------------------------------------------------------------------------------------------
	//------------------------------------------insertPaddedIfNeeded()------------------------------------------
	//----------------------------------------------------------------------------------------------------------

	@Test
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testIfNeededPositiveIndexAndNotExist() {
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
	public void testIfNeededPositiveIndexAndExist() {
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
	public void testIfNeededZeroIndexAndNotExist() {
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
	public void testIfNeededZeroIndexAndExist() {
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
	public void testIfNeededNegativeIndex() {
		String s = "aaabbb";
		String stringToInsert = "ccc";
		int insertAt = -2;
		Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testIfNeededIndexSuperiorToStringSize() {
		String s = "aaabbb";
		String stringToInsert = "ccc";
		int insertAt = 10;
		Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
	}

	@Test
	/*
	 * Method - insertPaddedIfNeeded
	 */
	public void testIfNeededEmptyStringToInsertInto() {
		String s = "";
		String stringToInsert = "ccc";
		int insertAt = 0;
		String expected = "ccc";
		String actual = Strings.insertPaddedIfNeeded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	//----------------------------------------------------------------------------------------------------------
	//------------------------------------------------isBlank()-------------------------------------------------
	//----------------------------------------------------------------------------------------------------------

	@Test
	/*
	 * method - isBlank
	 */
	public void testBlankMethodStringIsNull() {
		String s = null;
		assertTrue(Strings.isBlank(s));
	}

	@Test
	/*
	 * method - isBlank
	 */
	public void testBlankMethodStringIsEmpty() {
		String s = "";
		assertTrue(Strings.isBlank(s));
	}

	@Test
	/*
	 * method - isBlank
	 */
	public void testBlankMethodStringIsBlankCharSeq() {
		String s = "          ";
		assertTrue(Strings.isBlank(s));
	}

	@Test
	/*
	 * method - isBlank
	 */
	public void testBlankMethodStringIsNormal() {
		String s = "qwerty";
		assertFalse(Strings.isBlank(s));
	}

	//----------------------------------------------------------------------------------------------------------
	//------------------------------------------------insertPadded()--------------------------------------------
	//----------------------------------------------------------------------------------------------------------

	@Test
	/*
	 * Method - insertPadded
	 */
	public void testPositiveIndexAndNotExist() {
		String s = "aaabbb";
		String stringToInsert = "ccc";
		int insertAt = 3;
		String expected = "aaa ccc bbb";
		String actual = Strings.insertPadded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - insertPadded
	 */
	public void testPositiveIndexAndExist() {
		String s = "aaa ccc bbb";
		String stringToInsert = "ccc";
		int insertAt = 3;
		String expected = "aaa ccc ccc bbb";
		String actual = Strings.insertPadded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - insertPadded
	 */
	public void testZeroIndexAndNotExist() {
		String s = "aaabbb";
		String stringToInsert = "ccc";
		int insertAt = 0;
		String expected = "ccc aaabbb";
		String actual = Strings.insertPadded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - insertPadded
	 */
	public void testZeroIndexAndExist() {
		String s = "ccc aaabbb";
		String stringToInsert = "ccc";
		int insertAt = 0;
		String expected = "ccc ccc aaabbb";
		String actual = Strings.insertPadded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	/*
	 * Method - insertPadded
	 */
	public void testNegativeIndex() {
		String s = "aaabbb";
		String stringToInsert = "ccc";
		int insertAt = -2;
		Strings.insertPadded(s, insertAt, stringToInsert);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	/*
	 * Method - insertPadded
	 */
	public void testIndexSuperiorToStringSize() {
		String s = "aaabbb";
		String stringToInsert = "ccc";
		int insertAt = 10;
		Strings.insertPadded(s, insertAt, stringToInsert);
	}

	@Test
	/*
	 * Method - insertPadded
	 */
	public void testEmptyStringToInsertInto() {
		String s = "";
		String stringToInsert = "ccc";
		int insertAt = 0;
		String expected = "ccc";
		String actual = Strings.insertPadded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	//----------------------------------------------------------------------------------------------------------
	//--------------------------------------------isEmptyOrNull()-----------------------------------------------
	//----------------------------------------------------------------------------------------------------------

	@Test
	/*
	 * Method - isEmptyOrNull
	 */
	public void testEmptyOrNullMethodStringIsNull() {
		String s = null;
		assertTrue(Strings.isEmptyOrNull(s));
	}
	
	@Test
	/*
	 * Method - isEmptyOrNull
	 */
	public void testEmptyOrNullMethodStringIsEmpty() {
		String s = "";
		assertTrue(Strings.isEmptyOrNull(s));
	}
	
	@Test
	/*
	 * Method - isEmptyOrNull
	 */
	public void testEmptyOrNullMethodStringIsBlankSeq() {
		String s = "       ";
		assertFalse(Strings.isEmptyOrNull(s));
	}
	
	@Test
	/*
	 * Method - isEmptyOrNull
	 */
	public void testEmptyOrNullMethodStringIsNormal() {
		String s = "qwerty";
		assertFalse(Strings.isEmptyOrNull(s));
	}
	
	//-----------------------------------Boundary Value Analysis--------------------------------------
	
	@Test
	/*
	 * Method - insertPadded
	 */
	public void testInsertAtZero() {
		int insertAt = 0;
		String expected = "abc qwerty";
		String actual = Strings.insertPadded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * Method - insertPadded
	 */
	public void testInsertAtSLength() {
		int insertAt = 6;
		String expected = "qwerty abc";
		String actual = Strings.insertPadded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	/*
	 * Method - insertPadded
	 */
	public void testInsertAtNOne() {
		int insertAt = -1;
		Strings.insertPadded(s, insertAt, stringToInsert);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	/*
	 * Method - insertPadded
	 */
	public void testInsertAtSLengthPOne() {
		int insertAt = 7;
		Strings.insertPadded(s, insertAt, stringToInsert);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	/*
	 * Method - insertPadded
	 */
	public void testInsertAtPart1() {
		int insertAt = -5;
		Strings.insertPadded(s, insertAt, stringToInsert);
	}
	
	@Test
	/*
	 * Method - insertPadded
	 */
	public void testInsertAtPart2() {
		int insertAt = 3;
		String expected = "qwe abc rty";
		String actual = Strings.insertPadded(s, insertAt, stringToInsert);
		assertEquals(expected, actual);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	/*
	 * Method - insertPadded
	 */
	public void testInsertAtPart3() {
		int insertAt = 9;
		Strings.insertPadded(s, insertAt, stringToInsert);
	}
}
