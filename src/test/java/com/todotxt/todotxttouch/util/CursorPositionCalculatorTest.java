package com.todotxt.todotxttouch.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
	public void testNegativePositionPValueStrNValueStr() {
		int priorCursorPosition = -1;
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
	public void testZeroPositionPValueStrNValueStr() {
		int priorCursorPosition = 0;
		String priorValue = "qwe";
		String newValue = "qwerty";
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 3;

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
	 */
	public void testPValueNullNValueNull() {
		int priorCursorPosition = 0;
		String priorValue = null;
		String newValue = null;
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 0;
		assertEquals(expected, actual);
	}
	

	@Test
	/*
	 * Method - calculate
	 */
	public void testPValueEmpty() {
		int priorCursorPosition = 0;
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
	public void testNValueEmpty() {
		int priorCursorPosition = 1;
		String priorValue = "qwerty";
		String newValue = "";
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 0;

		assertEquals(expected, actual);
	}
}