package com.todotxt.todotxttouch.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class UtilJoinTest {
	
	@Test
	public void testJoinColWithThreeElements() {
		List<String> col = new ArrayList<>();
		col.add("one");
		col.add("two");
		col.add("three");
		String expected ="one#two#three";
		String actual = Util.join(col, "#");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testJoinNullCol() {
		List<String> col = null;
		String expected ="";
		String actual = Util.join(col, "#");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testJoinEmptyCol() {
		List<String> col = new ArrayList<>();
		String expected ="";
		String actual = Util.join(col, "#");
		assertEquals(expected, actual);
	}
	
}
