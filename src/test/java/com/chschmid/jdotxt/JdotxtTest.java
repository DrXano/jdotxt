package com.chschmid.jdotxt;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JdotxtTest {

	@Test
	/*
	 * Method - insertReplaceString
	 */
	public void test1() {
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
	public void test2() {
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
	public void testA() {
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
	public void test3() {
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
	public void test4() {
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
	public void testB() {
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
	public void test5() {
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
	public void test6() {
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
	public void test7() {
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
	public void test8() {
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
	public void test9() {
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
	public void testC() {
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
	public void test10() {
		int offset = 10;
		String original = "qwerty";
		String replace = "man";
		String expected = "qwertyman";
		String actual = Jdotxt.insertReplaceString(original, replace, offset);
		assertEquals(expected, actual);
	}
}
