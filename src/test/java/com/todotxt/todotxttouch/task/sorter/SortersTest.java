package com.todotxt.todotxttouch.task.sorter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.prefs.Preferences;

import org.junit.Before;
import org.junit.Test;

import com.chschmid.jdotxt.Jdotxt;
import com.chschmid.jdotxt.gui.JdotxtGUI;
import com.chschmid.jdotxt.util.LanguagesController;
import com.todotxt.todotxttouch.task.Priority;
import com.todotxt.todotxttouch.task.Task;

public class SortersTest {

	@Before
	public void setUp() throws Exception {
		Preferences userPrefs = Preferences.userNodeForPackage(Jdotxt.class);
		JdotxtGUI.lang = new LanguagesController(userPrefs.get("lang", "English"));
	}

	@Test
	public void testSorterbyIDAscending() {
		Task t1 = new Task(0, "t1", new Date());
		Task t2 = new Task(1, "t2", new Date());
		Task t3 = new Task(2, "t3", new Date());
		Task t4 = new Task(3, "t4", new Date());
		Task t5 = new Task(4, "t5", new Date());
		Task t6 = new Task(5, "t6", new Date());

		//Inseridos por ordem
		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t1);
		expected.add(t2);
		expected.add(t3);
		expected.add(t4);
		expected.add(t5);
		expected.add(t6);

		//Inseridos aleatoriamente
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t3);
		actual.add(t4);
		actual.add(t6);
		actual.add(t2);
		actual.add(t1);
		actual.add(t5);
		
		Collections.sort(actual, Sorters.ID.ascending());

		assertEquals(expected,actual);
	}


	@Test
	public void testSorterbyIDDescending() {
		Task t1 = new Task(0, "t1", new Date());
		Task t2 = new Task(1, "t2", new Date());
		Task t3 = new Task(2, "t3", new Date());
		Task t4 = new Task(3, "t4", new Date());
		Task t5 = new Task(4, "t5", new Date());
		Task t6 = new Task(5, "t6", new Date());

		//Inseridos por ordem
		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t6);
		expected.add(t5);
		expected.add(t4);
		expected.add(t3);
		expected.add(t2);
		expected.add(t1);

		//Inseridos aleatoriamente
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t3);
		actual.add(t4);
		actual.add(t6);
		actual.add(t2);
		actual.add(t1);
		actual.add(t5);
		
		Collections.sort(actual, Sorters.ID.descending());

		assertEquals(expected,actual);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSorterbyIDwithNulls() {
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(null);
		actual.add(null);
		
		Collections.sort(actual, Sorters.ID.ascending());
	}
	
	@Test
	public void testSorterbyPriorityAscending() {
		Task t1 = new Task(0, "t1", new Date());
		Task t2 = new Task(1, "t2", new Date());
		Task t3 = new Task(2, "t3", new Date());
		Task t4 = new Task(3, "t4", new Date());
		Task t5 = new Task(4, "t5", new Date());
		Task t6 = new Task(5, "t6", new Date());
		Task t7 = new Task(6, "t7", new Date());
		Task t8 = new Task(7, "t8", new Date());
		
		t1.setPriority(Priority.D);
		t2.setPriority(Priority.A);
		t3.setPriority(Priority.C);
		t4.setPriority(Priority.C);
		t5.setPriority(Priority.F);
		t6.setPriority(Priority.E);
		t7.setPriority(Priority.B);
		t8.setPriority(Priority.H);
		
		t3.markComplete(new Date());
		t6.markComplete(new Date());
		t7.markComplete(new Date());

		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t2);
		expected.add(t4);
		expected.add(t1);
		expected.add(t5);
		expected.add(t8);
		expected.add(t3);
		expected.add(t6);
		expected.add(t7);
		
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		actual.add(t7);
		actual.add(t8);
		
		Collections.sort(actual, Sorters.PRIORITY.ascending());
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSorterbyPriorityDescending() {
		Task t1 = new Task(0, "t1", new Date());
		Task t2 = new Task(1, "t2", new Date());
		Task t3 = new Task(2, "t3", new Date());
		Task t4 = new Task(3, "t4", new Date());
		Task t5 = new Task(4, "t5", new Date());
		Task t6 = new Task(5, "t6", new Date());
		Task t7 = new Task(6, "t7", new Date());
		Task t8 = new Task(7, "t8", new Date());
		
		t1.setPriority(Priority.D);
		t2.setPriority(Priority.A);
		t3.setPriority(Priority.C); //
		t4.setPriority(Priority.C);
		t5.setPriority(Priority.F);
		t6.setPriority(Priority.E); //
		t7.setPriority(Priority.B); //
		t8.setPriority(Priority.H);
		
		t3.markComplete(new Date());
		t6.markComplete(new Date());
		t7.markComplete(new Date());

		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t3);
		expected.add(t6);
		expected.add(t7);
		expected.add(t8);
		expected.add(t5);
		expected.add(t1);
		expected.add(t4);
		expected.add(t2);
		
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		actual.add(t7);
		actual.add(t8);
		
		Collections.sort(actual, Sorters.PRIORITY.descending());
		
		assertEquals(expected,actual);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSorterbyPriorityWithNulls() {
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(null);
		actual.add(null);
		
		Collections.sort(actual, Sorters.PRIORITY.ascending());
	}
	
	@Test
	public void testSorterbyTextAscending() {
		Task t1 = new Task(0, "Fazer feijoes", new Date());
		Task t2 = new Task(1, "Cortar Limoes", new Date());
		Task t3 = new Task(2, "Abanar abanoes", new Date());
		Task t4 = new Task(3, "Melgar o Camoes", new Date());
		Task t5 = new Task(4, "Pressionar o botao vermelho", new Date());
		Task t6 = new Task(5, "Esperar", new Date());
		
		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t3);
		expected.add(t2);
		expected.add(t6);
		expected.add(t1);
		expected.add(t4);
		expected.add(t5);
		
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		
		Collections.sort(actual, Sorters.TEXT.ascending());
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSorterbyTextDescending() {
		Task t1 = new Task(0, "Fazer feijoes", new Date());
		Task t2 = new Task(1, "Cortar Limoes", new Date());
		Task t3 = new Task(2, "Abanar abanoes", new Date());
		Task t4 = new Task(3, "Melgar o Camoes", new Date());
		Task t5 = new Task(4, "Pressionar o botao vermelho", new Date());
		Task t6 = new Task(5, "Esperar", new Date());
		
		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t5);
		expected.add(t4);
		expected.add(t1);
		expected.add(t6);
		expected.add(t2);
		expected.add(t3);
		
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		
		Collections.sort(actual, Sorters.TEXT.descending());
		
		assertEquals(expected,actual);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSorterbyTextWithNulls() {
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(null);
		actual.add(null);
		
		Collections.sort(actual, Sorters.TEXT.ascending());
	}
	
	@Test
	public void testSorterByDateAscending() {
		Task t1 = new Task(0, "t1", getaDate(3));
		Task t2 = new Task(1, "t2");
		Task t3 = new Task(2, "t3", getaDate(1));
		Task t4 = new Task(3, "t4", getaDate(3));
		Task t5 = new Task(4, "t5");
		Task t6 = new Task(5, "t6", getaDate(2));
		
		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t2);
		expected.add(t5);
		expected.add(t3);
		expected.add(t6);
		expected.add(t1);
		expected.add(t4);
		
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		
		Collections.sort(actual, Sorters.DATE.ascending());
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSorterByDateDescending() {
		Task t1 = new Task(0, "t1", getaDate(3));
		Task t2 = new Task(1, "t2");
		Task t3 = new Task(2, "t3", getaDate(1));
		Task t4 = new Task(3, "t4", getaDate(3));
		Task t5 = new Task(4, "t5");
		Task t6 = new Task(5, "t6", getaDate(2));
		
		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t1);
		expected.add(t4);
		expected.add(t6);
		expected.add(t3);
		expected.add(t2);
		expected.add(t5);
		
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		
		Collections.sort(actual, Sorters.DATE.descending());
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSorterByCompletionDate() {
		Task t1 = new Task(0, "t1");
		Task t2 = new Task(1, "t2");
		Task t3 = new Task(2, "t3");
		Task t4 = new Task(3, "t4");
		Task t5 = new Task(4, "t5");
		Task t6 = new Task(5, "t6");
		
		t2.markComplete(getaDate(2));
		t4.markComplete(getaDate(1));		
		
		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t1);
		expected.add(t3);
		expected.add(t5);
		expected.add(t6);
		expected.add(t4);
		expected.add(t2);
		
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		
		Collections.sort(actual, Sorters.COMPLETION_DATE.ascending());
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSorterByThresholdDate() {
		Task t1 = new Task(0, "t1 t:2019-02-01");
		Task t2 = new Task(1, "t2 t:2019-01-01");
		Task t3 = new Task(2, "t3 t:2018-01-01");
		Task t4 = new Task(3, "t4 t:2018-01-04");
		Task t5 = new Task(4, "t5 t:2020-06-01");
		Task t6 = new Task(5, "t6 t:2020-01-01");
		
		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t3);
		expected.add(t4);
		expected.add(t2);
		expected.add(t1);
		expected.add(t6);
		expected.add(t5);
		
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		
		Collections.sort(actual, Sorters.THRESHOLD_DATE.ascending());
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSorterByDueDate() {
		Task t1 = new Task(0, "t1 due:2019-02-01");
		Task t2 = new Task(1, "t2 due:2019-01-01");
		Task t3 = new Task(2, "t3 due:2018-01-01");
		Task t4 = new Task(3, "t4 due:2015-09-04");
		Task t5 = new Task(4, "t5 due:2020-06-01");
		Task t6 = new Task(5, "t6 due:2017-05-01");
		
		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t4);
		expected.add(t6);
		expected.add(t3);
		expected.add(t2);
		expected.add(t1);
		expected.add(t5);
		
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		
		Collections.sort(actual, Sorters.DUE_DATE.ascending());
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void testSorterByCompletionAscending() {
		Task t1 = new Task(0, "t1");
		Task t2 = new Task(1, "t2");
		Task t3 = new Task(2, "t3");
		Task t4 = new Task(3, "t4");
		Task t5 = new Task(4, "t5");
		Task t6 = new Task(5, "t6");
		
		t3.markComplete(new Date());
		t4.markComplete(new Date());
		t6.markComplete(new Date());

		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t1);
		expected.add(t2);
		expected.add(t5);
		expected.add(t3);
		expected.add(t4);
		expected.add(t6);

		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		
		Collections.sort(actual, Sorters.COMPLETION.ascending());

		assertEquals(expected,actual);
	}
	
	@Test
	public void testSorterByCompletionDescending() {
		Task t1 = new Task(0, "t1");
		Task t2 = new Task(1, "t2");
		Task t3 = new Task(2, "t3");
		Task t4 = new Task(3, "t4");
		Task t5 = new Task(4, "t5");
		Task t6 = new Task(5, "t6");
		
		t3.markComplete(new Date());
		t4.markComplete(new Date());
		t6.markComplete(new Date());

		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t3);
		expected.add(t4);
		expected.add(t6);
		expected.add(t1);
		expected.add(t2);
		expected.add(t5);

		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		
		Collections.sort(actual, Sorters.COMPLETION.descending());

		assertEquals(expected,actual);
	}
	
	@Test(expected = NullPointerException.class)
	public void testSorterByCompletionWithNulls() {
		ArrayList<Task> actual = new ArrayList<>();
		actual.add(null);
		actual.add(null);
		
		Collections.sort(actual, Sorters.COMPLETION.ascending());
	}
	
	@Test
	public void testSorterByProjectAscending() {
		Task t1 = new Task(0, "t1 +projB");
		Task t2 = new Task(1, "t2 +projB +projA");
		Task t3 = new Task(2, "t3 +projA");
		Task t4 = new Task(3, "t4 +projM");
		Task t5 = new Task(4, "t5 +projF");
		Task t6 = new Task(5, "t6 +proj");
		
		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t6);
		expected.add(t3);
		expected.add(t1);
		expected.add(t2);
		expected.add(t5);
		expected.add(t4);

		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		
		Collections.sort(actual, Sorters.PROJECTS.ascending());

		assertEquals(expected,actual);
	}
	
	@Test
	public void testSorterByProjectDescending() {
		Task t1 = new Task(0, "t1 +projB");
		Task t2 = new Task(1, "t2 +projB +projA");
		Task t3 = new Task(2, "t3 +projA");
		Task t4 = new Task(3, "t4 +projM");
		Task t5 = new Task(4, "t5 +projF");
		Task t6 = new Task(5, "t6 +proj");
		
		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t4);
		expected.add(t5);
		expected.add(t2);
		expected.add(t1);
		expected.add(t3);
		expected.add(t6);

		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		
		Collections.sort(actual, Sorters.PROJECTS.descending());

		assertEquals(expected,actual);
	}
	
	@Test
	public void testSorterByContextAscending() {
		Task t1 = new Task(0, "t1 @@contA @contB @contC");
		Task t2 = new Task(1, "t2");
		Task t3 = new Task(2, "t3 @contA @contC");
		Task t4 = new Task(3, "t4 @contA @contC");
		Task t5 = new Task(4, "t5");
		Task t6 = new Task(5, "t6 @contA @contB");
		
		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t2);
		expected.add(t5);
		expected.add(t6);
		expected.add(t3);
		expected.add(t4);
		expected.add(t1);

		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		
		Collections.sort(actual, Sorters.CONTEXTS.ascending());

		assertEquals(expected,actual);
	}
	
	@Test
	public void testSorterByContextDescending() {
		Task t1 = new Task(0, "t1 @@contA @contB @contC");
		Task t2 = new Task(1, "t2");
		Task t3 = new Task(2, "t3 @contA @contC");
		Task t4 = new Task(3, "t4 @contA @contC");
		Task t5 = new Task(4, "t5");
		Task t6 = new Task(5, "t6 @contA @contB");
		
		ArrayList<Task> expected = new ArrayList<>();
		expected.add(t1);
		expected.add(t3);
		expected.add(t4);
		expected.add(t6);
		expected.add(t2);
		expected.add(t5);

		ArrayList<Task> actual = new ArrayList<>();
		actual.add(t1);
		actual.add(t2);
		actual.add(t3);
		actual.add(t4);
		actual.add(t5);
		actual.add(t6);
		
		Collections.sort(actual, Sorters.CONTEXTS.descending());

		assertEquals(expected,actual);
	}
	
	@Test
	public void testCompareDatesWithBothNulls() {
		int result = Sorters.compareDates(null, null, true);
		assertTrue(result == 0);
	}
	
	@Test
	public void testCompareDatesWithD1Null() {
		int result = Sorters.compareDates(null, new Date(), true);
		assertTrue(result == 1);
	}
	
	@Test
	public void testCompareDatesWithD2Null() {
		int result = Sorters.compareDates(new Date(), null, true);
		assertTrue(result == -1);
	}
	
	//Mutant 1
	@Test
	public void testSorterbyGetName() {
				
		String x = Sorters.PRIORITY.getName();
		
		assertEquals("Priority",x);
	}
	
	//Mutant 2
	@Test
	public void testSorterByDifLists() {
		
		List<String> p1 = Arrays.asList("a", "b", "c");
        List<String> p2 = Arrays.asList("x", "y", "z");
		
        int expected = -23;
		int actual = Sorters.PROJECTS.compareLists(p1, p2);
		assertEquals(expected,actual);
	}
	
	//Mutant 3
	@Test
	public void testSorterByCompareListsNull() {
		
		List<String> p1 = Arrays.asList(null, null);
        List<String> p2 = Arrays.asList(null, null);
		
        int expected = 0;
		int actual = Sorters.PROJECTS.compareLists(p1, p2);
		assertEquals(expected,actual);
	}
	
	//Mutant 4
	@Test
	public void testSorterByCompareS1NullS2Not() {
		
		List<String> p1 = Arrays.asList(null, null, "c");
        List<String> p2 = Arrays.asList(null, "y", "z");
		
        int expected = -1;
		int actual = Sorters.PROJECTS.compareLists(p1, p2);
		assertEquals(expected,actual);
	}
	
	//Mutant 4
	@Test
	public void testSorterByCompareS2NullS1Not() {
		
		List<String> p1 = Arrays.asList(null, "y", "c");
        List<String> p2 = Arrays.asList(null, null, "z");
		
        int expected = 1;
		int actual = Sorters.PROJECTS.compareLists(p1, p2);
		assertEquals(expected,actual);
	}
	
	private Date getaDate(int days) {
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, days);
		dt = c.getTime();
		return dt;
	}
}

