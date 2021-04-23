package com.todotxt.todotxttouch.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TreeTest {

	@Test
	public void testIsLoaded1() {
		Tree<Integer> tree = new Tree<>(2);
		assertFalse(tree.isLoaded());
	}
	
	@Test
	public void testIsLoaded2() {
		Tree<Integer> tree = new Tree<>(2);
		tree.setLoaded();
		assertTrue(tree.isLoaded());
	}
	
	@Test
	public void testGetData() {
		Tree<String> tree = new Tree<>("Olá");
		assertEquals("Olá",tree.getData());
	}
	
	@Test
	public void testGetChildrenNull() {
		Tree<String> tree = new Tree<>("Olá");
		assertNull(tree.getChildren());
	}
	
	@Test
	public void testGetParent() {
		Tree<String> parent = new Tree<>("Olá");
		Tree<String> child = parent.addChild("Adeus");
		assertEquals(parent,child.getParent());
	}
	
	@Test
	public void testContainsChild1() {
		Tree<String> parent = new Tree<>("Olá");
		Tree<String> child = new Tree<>(parent, "Adeus");
		parent.addChild(child);
		assertTrue(parent.contains(child));
	}
	
	@Test
	public void testContainsChild2() {
		Tree<String> parent = new Tree<>("Olá");
		Tree<String> child = new Tree<>(parent, "Adeus");
		assertFalse(parent.contains(child));
	}
	
	@Test
	public void testContainsChild3() {
		Tree<String> parent = new Tree<>("Olá");
		Tree<String> child = new Tree<>(parent, "Adeus");
		parent.setLoaded();
		assertFalse(parent.contains(child));
	}
	
	@Test
	public void testContainsData1() {
		Tree<String> parent = new Tree<>("Olá");
		assertFalse(parent.contains("Adeus"));
	}
	
	@Test
	public void testContainsData2() {
		Tree<String> parent = new Tree<>("Olá");
		parent.setLoaded();
		assertFalse(parent.contains("Adeus"));
	}
	
	@Test
	public void testContainsData3() {
		Tree<String> parent = new Tree<>("Olá");
		parent.setLoaded();
		parent.addChild("Ok");
		parent.addChild("sim");
		parent.addChild("nao");
		assertTrue(parent.contains("sim"));
	}
	
	@Test
	public void testGetChild() {
		Tree<String> parent = new Tree<>("Olá");
		Tree<String> expected = new Tree<>("sim");
		parent.addChild("Ok");
		parent.addChild(expected);
		parent.addChild("nao");
		assertEquals(expected,parent.getChild(1));
	}

	//Mutantes
	
	@Test
	public void testContainsData4() {
		Tree<String> parent = new Tree<>("Olá");
		parent.setLoaded();
		parent.addChild("Ok");
		parent.addChild("sim");
		parent.addChild("nao");
		assertFalse(parent.contains("algo"));
	}
}
