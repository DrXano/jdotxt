package com.todotxt.todotxttouch.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.todotxt.todotxttouch.task.Task;

public class TaskIoTest {

	private List<Task> tasks;
	
	@Before
	public void setUp() {
		tasks = new ArrayList<>();
		tasks.add(new Task(1, "task1"));
		tasks.add(new Task(2, "task2"));
		tasks.add(new Task(3, "task3"));
	}
	
	@Test
	public void testWriteToFile() throws IOException {
		File f = new File("temp.txt");
		f.createNewFile();
		TaskIo.writeToFile(tasks, f);
		List<Task> expected = tasks;
		List<Task> actual = TaskIo.loadTasksFromFile(f);
		assertEquals(expected,actual);
	}

}
