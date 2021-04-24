/**
 * 
 */
package com.todotxt.todotxttouch.task;

import static org.junit.Assert.*;

import org.junit.Test;

import com.todotxt.todotxttouch.task.PriorityTextSplitter.PrioritySplitResult;

public class PriorityTextSplitterTest {

	
	@Test
	public void testPrioritySplitResultNull() {
		PriorityTextSplitter splitter = PriorityTextSplitter.getInstance();
		PriorityTextSplitter.PrioritySplitResult actual = splitter.split(null);
		PrioritySplitResult expected = new PrioritySplitResult(Priority.NONE, "");
		
		boolean res = false;
		if(actual.priority.equals(expected.priority) && actual.text.equals(expected.text)) {
			res = true;
		}
		assertTrue(res);
	}
	
}
