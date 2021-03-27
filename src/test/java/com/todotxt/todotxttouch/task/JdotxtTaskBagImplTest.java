package com.todotxt.todotxttouch.task;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JdotxtTaskBagImplTest {

	private ArrayList<Task> savedtodotasks;
	private ArrayList<Task> saveddonetasks;
	private LocalFileTaskRepository repo;
	private JdotxtTaskBagImpl bag;
	
	@Before
	public void setUp() throws Exception {
		repo = new LocalFileTaskRepository();
		savedtodotasks = repo.load();
		saveddonetasks = repo.loadDoneTasks();
		repo.purge();
		bag = new JdotxtTaskBagImpl(repo);
		bag.clear();
	}

	@After
	public void tearDown() throws Exception {
		repo.store(savedtodotasks);
		repo.storeDoneTasks(saveddonetasks);
	}

	@Test
	public void testBagAddAsTask() {
		bag.addAsTask("somaTask");
		bag.clear();
	}

}
