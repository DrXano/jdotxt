package com.todotxt.todotxttouch.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PathTest {
	
	@Test
	public void testFileNameWithPathBlank() {
		String expected = "";
		String actual = Path.fileName("     ");
		assertEquals(expected, actual);
	}
	
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
		String actual = Path.parentPath("     ");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testParentPathWithBackslashOnly() {
		String expected = "";
		String actual = Path.parentPath("/");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testParentPathWithPathNotBlank() {
		String path = "test/code/todo.java";
		String expected = "test/code/";
		String actual = Path.parentPath(path);
		assertEquals(expected, actual);
	}

	@Test
	public void testParentPathWithPathNotBlankWithBackslashInTheEnd() {
		String path = "test/code/todo.java/";
		String expected = "test/code/";
		String actual = Path.parentPath(path);
		assertEquals(expected, actual);
	}

}
