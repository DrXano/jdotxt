package com.chschmid.jdotxt;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JdotxtTest {

	@Test
	/*
	 * Method - insertReplaceString
	 */
	public void testOffsetZeroG() {
		int offset = 0;
		String original = "qwerty";
		String replace = "man";
		String expected = "manrty";
		String actual = Jdotxt.insertReplaceString(original, replace, offset);
		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * Method - insertReplaceString
	 */
	public void testOffsetZeroL() {
		int offset = 0;
		String original = "qwerty";
		String replace = "abcdefgh";
		String expected = "abcdefgh";
		String actual = Jdotxt.insertReplaceString(original, replace, offset);
		assertEquals(expected, actual);
	}
	

	
	@Test
	/*
	 * Method - insertReplaceString
	 */
	public void testOffsetZeroE() {
		int offset = 0;
		String original = "qwerty";
		String replace = "abcdef";
		String expected = "abcdef";
		String actual = Jdotxt.insertReplaceString(original, replace, offset);
		assertEquals(expected, actual);
	}
	
	
	@Test
	/*
	 * Method - insertReplaceString
	 */
	public void testOffsetLenMOneG() {
		int offset = 5;
		String original = "qwerty";
		String replace = "";
		String expected = "qwerty";
		String actual = Jdotxt.insertReplaceString(original, replace, offset);
		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * Method - insertReplaceString
	 */
	public void testOffsetLenMOneL() {
		int offset = 5;
		String original = "qwerty";
		String replace = "man";
		String expected = "qwertman";
		String actual = Jdotxt.insertReplaceString(original, replace, offset);
		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * Method - insertReplaceString
	 */
	public void testOffsetLenMOneE() {
		int offset = 3;
		String original = "qwerty";
		String replace = "man";
		String expected = "qweman";
		String actual = Jdotxt.insertReplaceString(original, replace, offset);
		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * Method - insertReplaceString
	 */
	public void testOffsetMOne() {
		int offset = -1;
		String original = "qwerty";
		String replace = "man";
		String expected = "manrty";
		String actual = Jdotxt.insertReplaceString(original, replace, offset);
		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * Method - insertReplaceString
	 */
	public void testOffsetLen() {
		int offset = 6;
		String original = "qwerty";
		String replace = "man";
		String expected = "qwertyman";
		String actual = Jdotxt.insertReplaceString(original, replace, offset);
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - insertReplaceString
	 */
	public void testOffsetPart1() {
		int offset = -5;
		String original = "qwerty";
		String replace = "man";
		String expected = "manrty";
		String actual = Jdotxt.insertReplaceString(original, replace, offset);
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - insertReplaceString
	 */
	public void testOffsetPart6L() {
		int offset = 2;
		String original = "qwerty";
		String replace = "man";
		String expected = "qwmany";
		String actual = Jdotxt.insertReplaceString(original, replace, offset);
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - insertReplaceString
	 */
	public void testOffsetPart6E() {
		int offset = 2;
		String original = "qwerty";
		String replace = "mens";
		String expected = "qwmens";
		String actual = Jdotxt.insertReplaceString(original, replace, offset);
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - insertReplaceString
	 */
	public void testOffsetPart7() {
		int offset = 3;
		String original = "qwerty";
		String replace = "mens";
		String expected = "qwemens";
		String actual = Jdotxt.insertReplaceString(original, replace, offset);
		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - insertReplaceString
	 */
	public void testOffsetPart3() {
		int offset = 10;
		String original = "qwerty";
		String replace = "man";
		String expected = "qwertyman";
		String actual = Jdotxt.insertReplaceString(original, replace, offset);
		assertEquals(expected, actual);
	}
}
