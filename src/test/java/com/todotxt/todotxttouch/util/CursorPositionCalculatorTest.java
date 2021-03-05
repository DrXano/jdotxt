package com.todotxt.todotxttouch.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class CursorPositionCalculatorTest {

	@Test
	/*
	 * Method - calculate
	 */
	public void testPositivePositionPValueStrNValueStr() {
		int priorCursorPosition = 1;
		String priorValue = "qwe";
		String newValue = "qwerty";
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 4;

		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - calculate
	 */
	public void testPValueStrNValueNull() {
		int priorCursorPosition = 1;
		String priorValue = "qwe";
		String newValue = null;
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 0;

		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - calculate
	 */
	public void testPValueNullNValueStr() {
		int priorCursorPosition = 1;
		String priorValue = null;
		String newValue = "qwerty";
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 6;

		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - calculate
	 * Aberto
	 */
	public void testPValueEmpty() {
		int priorCursorPosition = 1;
		String priorValue = "";
		String newValue = "qwerty";
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 6;

		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - calculate
	 */
	public void testNegativePosition() {
		int priorCursorPosition = 1;
		String priorValue = "qwerty";
		String newValue = "qwe";
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 0;

		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - calculate
	 */
	public void test7() {
		fail("Not yet implemented");
	}

	@Test
	/*
	 * Method - calculate
	 */
	public void test8() {
		fail("Not yet implemented");
	}

	@Test
	/*
	 * Method - calculate
	 */
	public void test9() {
		fail("Not yet implemented");
	}

	@Test
	/*
	 * Method - calculate
	 */
	public void test10() {
		fail("Not yet implemented");
	}

	@Test
	/*
	 * Method - calculate
	 */
	public void test11() {
		fail("Not yet implemented");
	}

}
