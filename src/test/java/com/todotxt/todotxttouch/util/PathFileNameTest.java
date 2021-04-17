package com.todotxt.todotxttouch.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class PathFileNameTest {

	@Test
	public void testFileNameWithPathNotBlank() {
		String path = "test/code/todo.java";
		String expected = "todo.java";
		String actual = Path.fileName(path);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFileNameWithBackslashInTheEnd() {
		String path = "test/code/todo.java/";
		String expected = "todo.java";
		String actual = Path.fileName(path);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testParentPathWithPathBlank() {
		String expected = "";
		String actual = Path.fileName("   ");
		assertEquals(expected, actual);
	}

}
