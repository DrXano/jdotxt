package com.todotxt.todotxttouch.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class CursorPositionCalculatorTest {

	private int priorCursorPosition;
	private String priorValue;
	private String newValue;
	private int actual;
	private int expected;

	@Before
	public void setUp() {
		priorCursorPosition = -1;
		priorValue = null;
		newValue = null;
		actual = -1;
		expected = -1;
	}

	@Test
	/*
	 * Method - calculate
	 */
	public void testPositivePositionPValueStrNValueStr() {
		priorCursorPosition = 1;
		priorValue = "qwe";
		newValue = "qwerty";
		actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		expected = 4;

		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - calculate
	 */
	public void testPValueStrNValueNull() {
		priorCursorPosition = 1;
		priorValue = "qwe";
		newValue = null;
		actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		expected = 0;

		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - calculate
	 */
	public void testPValueNullNValueStr() {
		priorCursorPosition = 1;
		priorValue = null;
		newValue = "qwerty";
		actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		expected = 6;

		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - calculate
	 * Aberto
	 */
	public void testPValueEmpty() {
		priorCursorPosition = 1;
		priorValue = "";
		newValue = "qwerty";
		actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		expected = 6;

		assertEquals(expected, actual);
	}

	@Test
	/*
	 * Method - calculate
	 */
	public void testNegativePosition() {
		priorCursorPosition = 1;
		priorValue = "qwerty";
		newValue = "qwe";
		actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		expected = 0;

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
