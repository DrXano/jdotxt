package com.todotxt.todotxttouch.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LocalFileTaskRepositoryTest {

	private ArrayList<Task> savedtodotasks;
	private ArrayList<Task> saveddonetasks;
	private LocalFileTaskRepository repo;
	
	@Before
	public void setUp() throws Exception {
		repo = new LocalFileTaskRepository();
		savedtodotasks = repo.load();
		saveddonetasks = repo.loadDoneTasks();
		repo.purge();
	}

	@After
	public void tearDown() throws Exception {
		repo.store(savedtodotasks);
		repo.storeDoneTasks(saveddonetasks);
	}

	@Test
	public void testStoredTodoTasks() {
		ArrayList<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "task1"));
		tasks.add(new Task(1, "task2"));
		tasks.add(new Task(2, "task3"));
		repo.store(tasks);
		ArrayList<Task> expected = tasks;
		ArrayList<Task> actual = repo.load();
		repo.purge();
		assertEquals(expected,actual);
	}

	@Test
	public void testStoredDoneTasks() {
		ArrayList<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "task1"));
		tasks.add(new Task(1, "task2"));
		tasks.add(new Task(2, "task3"));
		repo.storeDoneTasks(tasks);
		ArrayList<Task> expected = tasks;
		ArrayList<Task> actual = repo.loadDoneTasks();
		repo.purge();
		assertEquals(expected,actual);
	}
	
	@Test
	public void testArchivedTodoTasks() {
		ArrayList<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "task1"));
		tasks.add(new Task(1, "task2"));
		tasks.add(new Task(2, "task3"));
		tasks.get(0).markComplete(new Date());
		tasks.get(1).markIncomplete();
		tasks.get(2).markComplete(new Date());
		repo.archive(tasks);
		ArrayList<Task> loaded = repo.load();
		assertEquals(tasks.get(1).getText(),loaded.get(0).getText());
	}
	
	@Test
	public void testArchivedDoneTasks() {
		ArrayList<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "task1"));
		tasks.add(new Task(1, "task2"));
		tasks.add(new Task(2, "task3"));
		tasks.get(0).markComplete(new Date());
		tasks.get(1).markIncomplete();
		tasks.get(2).markIncomplete();
		repo.archive(tasks);
		ArrayList<Task> loaded = repo.loadDoneTasks();
		assertEquals(tasks.get(0),loaded.get(0));
	}
	
	@Test
	public void testTodoFileModifiedSince() {
		LocalDate ld = LocalDate.of(2020, 1, 1);
		Date d = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
		//Date d = java.sql.Date.valueOf(ld);
		assertTrue(repo.todoFileModifiedSince(d));
	}
	
	@Test
	public void testDoneFileModifiedSince() {
		LocalDate ld = LocalDate.of(2020, 1, 1);
		Date d = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
		assertTrue(repo.doneFileModifiedSince(d));
	}

}
