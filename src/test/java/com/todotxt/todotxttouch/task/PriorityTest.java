package com.todotxt.todotxttouch.task;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PriorityTest {

	@Test
	public void testPriorityRange() {
		List<Priority> expected = new ArrayList<>();
		expected.add(Priority.C);
		expected.add(Priority.D);
		expected.add(Priority.E);
		expected.add(Priority.F);
		expected.add(Priority.G);
		expected.add(Priority.H);
		expected.add(Priority.I);
		expected.add(Priority.J);
		expected.add(Priority.K);
		assertEquals(expected,Priority.range(Priority.C,Priority.K));
	}

	@Test
	public void testPriorityReverseRange() {
		List<Priority> expected = new ArrayList<>();
		expected.add(Priority.K);
		expected.add(Priority.J);
		expected.add(Priority.I);
		expected.add(Priority.H);
		expected.add(Priority.G);
		expected.add(Priority.F);
		expected.add(Priority.E);
		expected.add(Priority.D);
		expected.add(Priority.C);
		assertEquals(expected,Priority.range(Priority.K,Priority.C));
	}

	@Test
	public void testPriorityRangeInCode() {
		List<String> expected = new ArrayList<>();
		expected.add("C");
		expected.add("D");
		expected.add("E");
		expected.add("F");
		expected.add("G");
		expected.add("H");
		expected.add("I");
		expected.add("J");
		expected.add("K");
		assertEquals(expected,Priority.rangeInCode(Priority.C,Priority.K));
	}
	
	@Test
	public void testPriorityInCode() {
		List<String> expected = new ArrayList<>();
		expected.add("C");
		expected.add("E");
		expected.add("D");
		expected.add("Z");
		
		List<Priority> prios = new ArrayList<>();
		prios.add(Priority.C);
		prios.add(Priority.E);
		prios.add(Priority.D);
		prios.add(Priority.Z);
		
		assertEquals(expected,Priority.inCode(prios));
	}
	
	@Test
	public void testPriorityToPriority() {
		List<Priority> expected = new ArrayList<>();
		expected.add(Priority.C);
		expected.add(Priority.E);
		expected.add(Priority.NONE);
		expected.add(Priority.D);
		expected.add(Priority.Z);
		
		List<String> priosStr = new ArrayList<>();
		priosStr.add("C");
		priosStr.add("E");
		priosStr.add(null);
		priosStr.add("D");
		priosStr.add("Z");
		assertEquals(expected,Priority.toPriority(priosStr));
	}
	
	@Test
	public void testInListFormat() {
		String expected = "A";
		String actual = Priority.A.inListFormat();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testInDetailFormat() {
		String expected = "A";
		String actual = Priority.A.inDetailFormat();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testPrioritySmallRangeInCode() {
		List<String> expected = new ArrayList<>();
		expected.add("C");
		assertEquals(expected,Priority.rangeInCode(Priority.C,Priority.C));
	}
	
	@Test
	public void testToPriorityWithUnsualString() {
		Priority actual = Priority.toPriority("Ola");
		Priority expected = Priority.NONE;
		assertEquals(expected,actual);
	}
}
