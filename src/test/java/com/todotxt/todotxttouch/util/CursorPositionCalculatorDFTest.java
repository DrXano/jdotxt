package com.todotxt.todotxttouch.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CursorPositionCalculatorDFTest {

	@Test
	public void testCalculateWithNewValueNull() {
		int priorCursorPosition = 1;
		String priorValue = "qwe";
		String newValue = null;
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 0;

		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateWithPriorValueNull() {
		int priorCursorPosition = 1;
		String priorValue = null;
		String newValue = "qwerty";
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 6;

		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateWithPosRedefiniton() {
		int priorCursorPosition = 0;
		String priorValue = "qwerty";
		String newValue = "qwe";
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 0;

		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateWithPosWithinNewValueLimit() {
		int priorCursorPosition = 1;
		String priorValue = "qwe";
		String newValue = "qwerty";
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 4;

		assertEquals(expected, actual);
	}

	@Test
	public void testCalculateWithPosOverNewValueLimit() {
		int priorCursorPosition = 5;
		String priorValue = "qwe";
		String newValue = "qwerty";
		int actual = CursorPositionCalculator.calculate(priorCursorPosition, priorValue, newValue);
		int expected = 6;

		assertEquals(expected, actual);
	}

}
