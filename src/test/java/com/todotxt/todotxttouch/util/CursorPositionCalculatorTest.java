package com.todotxt.todotxttouch.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CursorPositionCalculatorTest {
	
	private String priorValue;
	private String newValue;
	
	@Before
	public void setUp() {
		priorValue = "qwe";
		newValue = "qwerty";
	}

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
	
	//-----------------------------------Boundary Value Analysis--------------------------------------
	
	@Test
	/*
	 * Method - calculate
	 */
	public void AtestA() {
		int priorCursorPosition = 0;
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 3;

		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * Method - calculate
	 */
	public void BtestB() {
		int priorCursorPosition = 2;
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 5;

		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * Method - calculate
	 */
	public void CtestC() {
		int priorCursorPosition = -1;
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 3;

		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * Method - calculate
	 */
	public void DtestD() {
		int priorCursorPosition = 3;
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 6;

		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * Method - calculate
	 */
	public void EtestE() {
		int priorCursorPosition = -3;
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 3;

		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * Method - calculate
	 */
	public void FtestF() {
		int priorCursorPosition = 1;
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 4;

		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * Method - calculate
	 */
	public void GtestG() {
		int priorCursorPosition = 5;
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 6;

		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * Method - calculate
	 */
	public void HtestH() {
		int priorCursorPosition = 1;
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, null, newValue);
		int expected = 6;

		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * Method - calculate
	 */
	public void ItestI() {
		int priorCursorPosition = 1;
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, null);
		int expected = 0;

		assertEquals(expected, actual);
	}
}
