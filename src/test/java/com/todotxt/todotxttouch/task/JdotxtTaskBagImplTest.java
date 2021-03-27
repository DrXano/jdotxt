package com.todotxt.todotxttouch.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.prefs.Preferences;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chschmid.jdotxt.Jdotxt;
import com.chschmid.jdotxt.gui.JdotxtGUI;
import com.chschmid.jdotxt.util.LanguagesController;

public class JdotxtTaskBagImplTest {

	private ArrayList<Task> savedtodotasks;
	private ArrayList<Task> saveddonetasks;
	private LocalFileTaskRepository savedRepo;
	private JdotxtTaskBagImpl bag;

	@Before
	public void setUp() throws Exception {

		Preferences userPrefs = Preferences.userNodeForPackage(Jdotxt.class);
		JdotxtGUI.lang = new LanguagesController(userPrefs.get("lang", "English"));

		savedRepo = new LocalFileTaskRepository();
		savedtodotasks = savedRepo.load();
		saveddonetasks = savedRepo.loadDoneTasks();
		savedRepo.purge();
		bag = (JdotxtTaskBagImpl) TaskBagFactory.getTaskBag();
		bag.clear();
	}

	@After
	public void tearDown() throws Exception {
		savedRepo.store(savedtodotasks);
		savedRepo.storeDoneTasks(saveddonetasks);
	}

	@Test
	public void testBagAddAsTask() {
		List<Task> expected = new ArrayList<>();
		expected.add(new Task(0, "someTask", new Date()));
		expected.add(new Task(1, "anyTask", new Date()));
		expected.add(new Task(2, "noTask", new Date()));

		bag.addAsTask(expected.get(0).inFileFormat());
		bag.addAsTask(expected.get(1).inFileFormat());
		bag.addAsTask(expected.get(2).inFileFormat());
		List<Task> actual = bag.getTasks();
		assertEquals(expected,actual);
		bag.clear();
	}

	@Test
	public void testBagUpdate1() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "someTask", new Date()));
		tasks.add(new Task(1, "anyTask", new Date()));
		tasks.add(new Task(2, "noTask", new Date()));

		for(Task t : tasks)
			bag.addAsTask(t.inFileFormat());

		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();

		Task expected = new Task(1, "anyTask", dt);

		bag.update(expected);
		List<Task> updatedTasks = bag.getTasks();

		assertEquals(expected, updatedTasks.get(1));
	}

	@Test
	public void testBagUpdate2() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "someTask", new Date()));
		tasks.add(new Task(1, "anyTask", new Date()));
		tasks.add(new Task(2, "noTask", new Date()));

		for(Task t : tasks)
			bag.addAsTask(t.inFileFormat());

		bag.update(null);

		assertEquals(tasks,bag.getTasks());
	}

	@Test(expected = TaskPersistException.class)
	public void testBagUpdate3() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "someTask", new Date()));
		tasks.add(new Task(1, "anyTask", new Date()));
		tasks.add(new Task(2, "noTask", new Date()));

		for(Task t : tasks)
			bag.addAsTask(t.inFileFormat());

		Task updatedTask = new Task(3, "voidTask", new Date());
		bag.update(updatedTask);
	}

	@Test
	public void testBagDelete1() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "someTask", new Date()));
		tasks.add(new Task(1, "anyTask", new Date()));
		tasks.add(new Task(2, "noTask", new Date()));

		for(Task t : tasks)
			bag.addAsTask(t.inFileFormat());

		Task toRemove = tasks.get(1);
		bag.delete(toRemove);
		tasks.remove(toRemove);

		assertEquals(tasks, bag.getTasks());
	}

	@Test(expected = TaskPersistException.class)
	public void testBagDelete2() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "someTask", new Date()));
		tasks.add(new Task(1, "anyTask", new Date()));
		tasks.add(new Task(2, "noTask", new Date()));

		for(Task t : tasks)
			bag.addAsTask(t.inFileFormat());

		Task toRemove = new Task(3, "voidTask", new Date());
		bag.delete(toRemove);
	}

	@Test
	public void testBagGetPriorities() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "someTask", new Date()));
		tasks.add(new Task(1, "anyTask", new Date()));
		tasks.add(new Task(2, "noTask", new Date()));

		tasks.get(0).setPriority(Priority.A);
		tasks.get(1).setPriority(Priority.D);
		tasks.get(2).setPriority(Priority.E);

		for(Task t : tasks)
			bag.addAsTask(t.inFileFormat());

		ArrayList<Priority> expected = new ArrayList<>();
		expected.add(Priority.A);
		expected.add(Priority.D);
		expected.add(Priority.E);

		assertEquals(expected,bag.getPriorities());
	}

	@Test
	public void testBagGetContexts1() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "someTask @Cont1 @Cont2", new Date()));
		tasks.add(new Task(1, "anyTask @Cont3", new Date()));
		tasks.add(new Task(2, "noTask @Cont4", new Date()));

		for(Task t : tasks)
			bag.addAsTask(t.inFileFormat());

		ArrayList<String> expected = new ArrayList<>();
		expected.add("Cont1");
		expected.add("Cont2");
		expected.add("Cont3");
		expected.add("Cont4");
		assertEquals(expected,bag.getContexts(false));
	}

	@Test
	public void testBagGetContexts2() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "someTask @Cont1 @Cont2", new Date()));
		tasks.add(new Task(1, "anyTask @Cont3", new Date()));
		tasks.add(new Task(2, "noTask @Cont4", new Date()));

		for(Task t : tasks)
			bag.addAsTask(t.inFileFormat());

		ArrayList<String> expected = new ArrayList<>();
		expected.add("-");
		expected.add("Cont1");
		expected.add("Cont2");
		expected.add("Cont3");
		expected.add("Cont4");
		assertEquals(expected,bag.getContexts(true));
	}

	@Test
	public void testBagGetProjects1() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "someTask @Cont1 @Cont2 +Proj1", new Date()));
		tasks.add(new Task(1, "anyTask @Cont3 +Proj2", new Date()));
		tasks.add(new Task(2, "noTask @Cont4 +Proj3 +Proj4", new Date()));

		for(Task t : tasks)
			bag.addAsTask(t.inFileFormat());

		ArrayList<String> expected = new ArrayList<>();
		expected.add("Proj1");
		expected.add("Proj2");
		expected.add("Proj3");
		expected.add("Proj4");
		assertEquals(expected,bag.getProjects(false));
	}

	@Test
	public void testBagGetProjects2() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "someTask @Cont1 @Cont2 +Proj1", new Date()));
		tasks.add(new Task(1, "anyTask @Cont3 +Proj2", new Date()));
		tasks.add(new Task(2, "noTask @Cont4 +Proj3 +Proj4", new Date()));

		for(Task t : tasks)
			bag.addAsTask(t.inFileFormat());

		ArrayList<String> expected = new ArrayList<>();
		expected.add("-");
		expected.add("Proj1");
		expected.add("Proj2");
		expected.add("Proj3");
		expected.add("Proj4");
		assertEquals(expected,bag.getProjects(true));
	}

	@Test
	public void testBagSize() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task(0, "someTask", new Date()));
		tasks.add(new Task(1, "anyTask", new Date()));
		tasks.add(new Task(2, "noTask", new Date()));

		for(Task t : tasks)
			bag.addAsTask(t.inFileFormat());

		assertTrue(bag.size() == 3);
	}

	@Test
	public void testBagStore() {
		List<Task> expected = new ArrayList<>();
		expected.add(new Task(0, "someTask", new Date()));
		expected.add(new Task(1, "anyTask", new Date()));
		expected.add(new Task(2, "noTask", new Date()));

		for(Task t : expected)
			bag.addAsTask(t.inFileFormat());

		bag.store();
		bag.reload();

		List<Task> actual = bag.getTasks();

		assertEquals(expected, actual);
		bag.clear();
	}

	@Test
	public void testBagArchive1() {
		List<Task> expected = new ArrayList<>();
		Task t1 = new Task(0, "someTask", new Date());
		Task t2 = new Task(1, "anyTask", new Date());
		Task t3 = new Task(2, "noTask", new Date());

		t2.markComplete(new Date());

		expected.add(t1);
		expected.add(t2);
		expected.add(t3);

		for(Task t : expected)
			bag.addAsTask(t.inFileFormat());

		bag.archive();
		bag.reload();
		List<Task> actual = bag.getTasks();
		assertFalse(actual.contains(t2));
		bag.clear();
	}

	@Test
	public void testBagArchive2() {
		List<Task> expected = new ArrayList<>();
		Task t1 = new Task(0, "someTask", new Date());
		Task t2 = new Task(1, "anyTask", new Date());
		Task t3 = new Task(2, "noTask", new Date());

		t1.markComplete(new Date());
		t2.markComplete(new Date());

		expected.add(t1);
		expected.add(t2);
		expected.add(t3);

		for(Task t : expected)
			bag.addAsTask(t.inFileFormat());


		bag.archive();
		bag.unarchive(t1);
		bag.reload();
		List<Task> actual = bag.getTasks();
		assertTrue(actual.contains(t1));
		bag.clear();
	}

	@Test
	public void testBagArchive3() {
		List<Task> expected = new ArrayList<>();
		Task t1 = new Task(0, "someTask", new Date());
		Task t2 = new Task(1, "anyTask", new Date());
		Task t3 = new Task(2, "noTask", new Date());

		t1.markComplete(new Date());
		t2.markComplete(new Date());

		expected.add(t1);
		expected.add(t2);
		expected.add(t3);

		for(Task t : expected)
			bag.addAsTask(t.inFileFormat());


		bag.archive();
		bag.unarchive(new Task(-1, "someTask", new Date()));
		bag.reload();
		List<Task> actual = bag.getTasks();
		assertTrue(actual.contains(t1));
		bag.clear();
	}

	@Test
	public void testBagArchive4() {
		List<Task> expected = new ArrayList<>();
		Task t1 = new Task(0, "someTask", new Date());
		Task t2 = new Task(1, "anyTask", new Date());
		Task t3 = new Task(2, "noTask", new Date());

		t2.markComplete(new Date());
		t3.markComplete(new Date());

		expected.add(t1);
		expected.add(t2);
		expected.add(t3);

		for(Task t : expected)
			bag.addAsTask(t.inFileFormat());


		bag.archive();
		bag.unarchive(new Task(5, "noTask", new Date()));
		bag.reload();
		List<Task> actual = bag.getTasks();
		assertTrue(actual.contains(t3));
		bag.clear();
	}

	@Test
	public void testBagFilterByPriority() {
		Task t1 = new Task(0, "t1", new Date());
		Task t2 = new Task(1, "t2", new Date());
		Task t3 = new Task(2, "t3", new Date());
		Task t4 = new Task(3, "t4", new Date());
		Task t5 = new Task(4, "t5", new Date());
		Task t6 = new Task(5, "t6", new Date());

		t1.setPriority(Priority.B);
		t2.setPriority(Priority.A);
		t3.setPriority(Priority.A);
		t4.setPriority(Priority.E);
		t5.setPriority(Priority.C);
		t6.setPriority(Priority.F);

		bag.addAsTask(t1.inFileFormat());
		bag.addAsTask(t2.inFileFormat());
		bag.addAsTask(t3.inFileFormat());
		bag.addAsTask(t4.inFileFormat());
		bag.addAsTask(t5.inFileFormat());
		bag.addAsTask(t6.inFileFormat());

		List<Priority> priorities = new ArrayList<>();
		priorities.add(Priority.A);

		List<Task> expected = new ArrayList<>();
		expected.add(t2);
		expected.add(t3);

		List<Task> actual = bag.getTasks(FilterFactory.generateAndFilter(priorities, new ArrayList<String>(), new ArrayList<String>(), null, false, true, true), null);

		assertEquals(expected,actual);
		bag.clear();
	}

	@Test
	public void testBagFilterByContext() {
		Task t1 = new Task(0, "t1 @Cont1", new Date());
		Task t2 = new Task(1, "t2 @Cont2", new Date());
		Task t3 = new Task(2, "t3 @Cont1", new Date());
		Task t4 = new Task(3, "t4 @Cont1 @Cont2", new Date());
		Task t5 = new Task(4, "t5 @Cont3", new Date());
		Task t6 = new Task(5, "t6 @Cont4", new Date());

		bag.addAsTask(t1.inFileFormat());
		bag.addAsTask(t2.inFileFormat());
		bag.addAsTask(t3.inFileFormat());
		bag.addAsTask(t4.inFileFormat());
		bag.addAsTask(t5.inFileFormat());
		bag.addAsTask(t6.inFileFormat());

		List<String> contexts = new ArrayList<>();
		contexts.add("Cont1");

		List<Task> expected = new ArrayList<>();
		expected.add(t1);
		expected.add(t3);
		expected.add(t4);

		List<Task> actual = bag.getTasks(FilterFactory.generateAndFilter(new ArrayList<Priority>(), contexts, new ArrayList<String>(), null, false, true, true), null);

		assertEquals(expected,actual);
		bag.clear();
	}
	
	@Test
	public void testBagFilterByProject() {
		Task t1 = new Task(0, "t1 +P1", new Date());
		Task t2 = new Task(1, "t2 +P2", new Date());
		Task t3 = new Task(2, "t3 +P3", new Date());
		Task t4 = new Task(3, "t4 +P2 +P3", new Date());
		Task t5 = new Task(4, "t5 +P4", new Date());
		Task t6 = new Task(5, "t6 +P4", new Date());

		bag.addAsTask(t1.inFileFormat());
		bag.addAsTask(t2.inFileFormat());
		bag.addAsTask(t3.inFileFormat());
		bag.addAsTask(t4.inFileFormat());
		bag.addAsTask(t5.inFileFormat());
		bag.addAsTask(t6.inFileFormat());

		List<String> projects = new ArrayList<>();
		projects.add("P2");

		List<Task> expected = new ArrayList<>();
		expected.add(t2);
		expected.add(t4);

		List<Task> actual = bag.getTasks(FilterFactory.generateAndFilter(new ArrayList<Priority>(), new ArrayList<String>(), projects, null, false, true, true), null);

		assertEquals(expected,actual);
		bag.clear();
	}
	
	@Test
	public void testBagFilterByTestWithCaseSensitive() {
		Task t1 = new Task(0, "fazer uma coisa", new Date());
		Task t2 = new Task(1, "faZer outra coisa", new Date());
		Task t3 = new Task(2, "fazer ainda outra coisa", new Date());
		Task t4 = new Task(3, "Mandar um mail", new Date());
		Task t5 = new Task(4, "Almoçar", new Date());
		Task t6 = new Task(5, "Mandar mais um mail", new Date());

		bag.addAsTask(t1.inFileFormat());
		bag.addAsTask(t2.inFileFormat());
		bag.addAsTask(t3.inFileFormat());
		bag.addAsTask(t4.inFileFormat());
		bag.addAsTask(t5.inFileFormat());
		bag.addAsTask(t6.inFileFormat());

		List<Task> expected = new ArrayList<>();
		expected.add(t2);

		String filterText = "faZer";
		boolean caseSensitive = true;
		
		List<Task> actual = bag.getTasks(FilterFactory.generateAndFilter(new ArrayList<Priority>(), new ArrayList<String>(), new ArrayList<String>(), filterText, caseSensitive, true, true), null);

		assertEquals(expected,actual);
		bag.clear();
	}
	
	@Test
	public void testBagFilterByTestWithOutCaseSensitive() {
		Task t1 = new Task(0, "fazer uma coisa", new Date());
		Task t2 = new Task(1, "faZer outra coisa", new Date());
		Task t3 = new Task(2, "fazer ainda outra coisa", new Date());
		Task t4 = new Task(3, "Mandar um mail", new Date());
		Task t5 = new Task(4, "Almoçar", new Date());
		Task t6 = new Task(5, "Mandar mais um mail", new Date());

		bag.addAsTask(t1.inFileFormat());
		bag.addAsTask(t2.inFileFormat());
		bag.addAsTask(t3.inFileFormat());
		bag.addAsTask(t4.inFileFormat());
		bag.addAsTask(t5.inFileFormat());
		bag.addAsTask(t6.inFileFormat());

		List<Task> expected = new ArrayList<>();
		expected.add(t1);
		expected.add(t2);
		expected.add(t3);

		String filterText = "faZer";
		boolean caseSensitive = false;
		
		List<Task> actual = bag.getTasks(FilterFactory.generateAndFilter(new ArrayList<Priority>(), new ArrayList<String>(), new ArrayList<String>(), filterText, caseSensitive, true, true), null);

		assertEquals(expected,actual);
		bag.clear();
	}
	
	@Test
	public void testBagFilterByHidden() {
		Task t1 = new Task(0, "t1", new Date());
		Task t2 = new Task(1, "h:1 t2", new Date());
		Task t3 = new Task(2, "t3", new Date());
		Task t4 = new Task(3, "h:1 t4", new Date());
		Task t5 = new Task(4, "t5", new Date());
		Task t6 = new Task(5, "h:1 t6", new Date());

		bag.addAsTask(t1.inFileFormat());
		bag.addAsTask(t2.inFileFormat());
		bag.addAsTask(t3.inFileFormat());
		bag.addAsTask(t4.inFileFormat());
		bag.addAsTask(t5.inFileFormat());
		bag.addAsTask(t6.inFileFormat());
		

		List<Task> expected = new ArrayList<>();
		expected.add(t1);
		expected.add(t3);
		expected.add(t5);

		List<Task> actual = bag.getTasks(FilterFactory.generateAndFilter(new ArrayList<Priority>(), new ArrayList<String>(), new ArrayList<String>(), null, false, false, true), null);

		assertEquals(expected,actual);
		bag.clear();
	}
	
	@Test
	public void testBagFilterByThreshold() {
		Task t1 = new Task(0, "t1 t:2018-01-01", new Date());
		Task t2 = new Task(1, "t2 t:2018-01-02", new Date());
		Task t3 = new Task(2, "t3 t:2022-01-01", new Date());
		Task t4 = new Task(3, "t4 t:2023-01-01", new Date());
		Task t5 = new Task(4, "t5 t:2018-01-04", new Date());
		Task t6 = new Task(5, "t6", new Date());

		bag.addAsTask(t1.inFileFormat());
		bag.addAsTask(t2.inFileFormat());
		bag.addAsTask(t3.inFileFormat());
		bag.addAsTask(t4.inFileFormat());
		bag.addAsTask(t5.inFileFormat());
		bag.addAsTask(t6.inFileFormat());
		
		List<Task> expected = new ArrayList<>();
		expected.add(t1);
		expected.add(t2);
		expected.add(t5);
		expected.add(t6);

		List<Task> actual = bag.getTasks(FilterFactory.generateAndFilter(new ArrayList<Priority>(), new ArrayList<String>(), new ArrayList<String>(), null, false, true, false), null);

		assertEquals(expected,actual);
		bag.clear();
	}
}
