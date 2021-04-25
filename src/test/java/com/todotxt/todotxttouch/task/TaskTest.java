package com.todotxt.todotxttouch.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.prefs.Preferences;

import org.junit.Before;
import org.junit.Test;

import com.chschmid.jdotxt.Jdotxt;
import com.chschmid.jdotxt.gui.JdotxtGUI;
import com.chschmid.jdotxt.util.LanguagesController;

public class TaskTest {

	@Before
	public void setUp() throws Exception {
		Preferences userPrefs = Preferences.userNodeForPackage(Jdotxt.class);
		JdotxtGUI.lang = new LanguagesController(userPrefs.get("lang", "English"));
	}

	@Test
	public void testTaskUpdate() {
		Task t = new Task();
		t.update("Updated");
		String actual = t.getText();
		String expected = "Updated";
		assertEquals(expected,actual);
	}

	@Test
	public void testTaskConstructorFromTask() {
		Task original = new Task(0, "Task");
		Task newTask = new Task(original);
		assertTrue(newTask.equals(original));
	}

	@Test
	public void testTaskConstructorWithRecOfDays() {
		Task task = new Task(0, "Task rec:7d");
		boolean durationCorrect = task.getDuration() == Calendar.DAY_OF_YEAR;
		boolean amountCorrect = task.getAmount() == 7;
		assertTrue(durationCorrect && amountCorrect);
	}

	@Test
	public void testTaskConstructorWithRecOfWeeks() {
		Task task = new Task(0, "Task rec:3w");
		boolean durationCorrect = task.getDuration() == Calendar.WEEK_OF_YEAR;
		boolean amountCorrect = task.getAmount() == 3;
		assertTrue(durationCorrect && amountCorrect);
	}

	@Test
	public void testTaskConstructorWithRecOfMonths() {
		Task task = new Task(0, "Task rec:12m");
		boolean durationCorrect = task.getDuration() == Calendar.MONTH;
		boolean amountCorrect = task.getAmount() == 12;
		assertTrue(durationCorrect && amountCorrect);
	}

	@Test
	public void testTaskConstructorWithRecOfYears() {
		Task task = new Task(0, "Task rec:100y");
		boolean durationCorrect = task.getDuration() == Calendar.YEAR;
		boolean amountCorrect = task.getAmount() == 100;
		assertTrue(durationCorrect && amountCorrect);
	}

	@Test
	public void testIfTaskIsRec() {
		Task task = new Task(0, "Task rec:7d");
		assertTrue(task.isRec());
	}

	@Test
	public void testIfTaskIsNotRec() {
		Task task = new Task(0, "Task");
		assertFalse(task.isRec());
	}

	@Test
	public void testTaskMarkIncomplete() {
		Task task = new Task(0, "Task");
		task.markComplete(new Date());
		task.markIncomplete();
		assertFalse(task.isCompleted());
	}

	@Test
	public void testTaskInScreenFormat1() {
		Task task = new Task(0, "Task");
		String expected = "Task";
		String actual = task.inScreenFormat();
		assertEquals(expected,actual);
	}

	@Test
	public void testTaskInScreenFormat2() throws ParseException {
		Task task = new Task(0, "Task");
		task.markComplete(getMockDate("2015-01-01"));
		String expected = "x 2015-01-01 Task";
		String actual = task.inScreenFormat();
		assertEquals(expected,actual);
	}

	@Test
	public void testTaskInScreenFormat3() throws ParseException {
		Task task = new Task(0, "Task", getMockDate("2014-01-01"));
		task.markComplete(getMockDate("2015-01-01"));
		String expected = "x 2015-01-01 2014-01-01 Task";
		String actual = task.inScreenFormat();
		assertEquals(expected,actual);
	}

	@Test
	public void testTaskInFileFormatHeaderNoDate1() {
		Task task = new Task(0, "Task");
		String expected = "";
		String actual = task.inFileFormatHeaderNoDate();
		assertEquals(expected,actual);
	}

	@Test
	public void testTaskInFileFormatHeaderNoDate2() throws ParseException {
		Task task = new Task(0, "Task");
		task.markComplete(getMockDate("2015-01-01"));
		String expected = "x 2015-01-01 ";
		String actual = task.inFileFormatHeaderNoDate();
		assertEquals(expected,actual);
	}

	@Test
	public void testTaskInFileFormatHeaderNoDate3() throws ParseException {
		Task task = new Task(0, "Task");
		task.setPriority(Priority.A);
		String expected = "(A) ";
		String actual = task.inFileFormatHeaderNoDate();
		assertEquals(expected,actual);
	}

	@Test
	public void testTaskEqualsWithaNull() {
		Task task = new Task(0, "Task");
		assertFalse(task.equals(null));
	}

	@Test
	public void testTaskEqualsWithAnotherTypeOfObject() {
		Task task = new Task(0, "Task");
		String ImNotATask = "ImNotATask";
		assertFalse(task.equals(ImNotATask));
	}

	@Test
	public void testTaskEqualsWithCompletionDates1() throws ParseException{
		Task task1 = new Task(0, "Task");
		Task task2 = new Task(0, "Task");
		task1.markComplete(getMockDate("2015-01-01"));
		task2.markComplete(getMockDate("2015-01-01"));
		assertTrue(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithCompletionDates2() throws ParseException{
		Task task1 = new Task(0, "Task");
		Task task2 = new Task(0, "Task");
		task1.markComplete(getMockDate("2016-01-01"));
		task2.markComplete(getMockDate("2015-01-01"));
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithContexts1(){
		Task task1 = new Task(0, "Task @C1");
		Task task2 = new Task(0, "Task @C1");
		assertTrue(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithContexts2(){
		Task task1 = new Task(0, "Task @C1");
		Task task2 = new Task(0, "Task @C2");
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsDeleted(){
		Task task1 = new Task(0, "Task");
		Task task2 = new Task(0, "Task");
		task1.delete();
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualWithLinks1() {
		Task task1 = new Task(0, "Task http://java.sun.com");
		Task task2 = new Task(0, "Task http://java.sun.com");
		assertTrue(task1.equals(task2));
	}

	@Test
	public void testTaskEqualWithLinks2() {
		Task task1 = new Task(0, "Task");
		Task task2 = new Task(0, "Task http://java.sun.com");
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualWithLinks3() {
		Task task1 = new Task(0, "Task http://java.sun.com");
		Task task2 = new Task(0, "Task http://java.sun.pt");
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithMailAddresses1() {
		Task task1 = new Task(0, "Task someone@gmail.com");
		Task task2 = new Task(0, "Task someone@gmail.com");
		assertTrue(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithMailAddresses2() {
		Task task1 = new Task(0, "Task someone@gmail.com");
		Task task2 = new Task(0, "Task somebody@gmail.com");
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithMailAddresses3() {
		Task task1 = new Task(0, "Task");
		Task task2 = new Task(0, "Task somebody@gmail.com");
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithPrependedDate1() throws ParseException {
		Task task1 = new Task(0, "Task", getMockDate("2015-01-01"));
		Task task2 = new Task(0, "Task", getMockDate("2015-01-01"));
		assertTrue(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithPrependedDate2() throws ParseException {
		Task task1 = new Task(0, "Task", getMockDate("2015-01-01"));
		Task task2 = new Task(0, "Task", getMockDate("2015-01-11"));
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithPrependedDate3() throws ParseException {
		Task task1 = new Task(0, "Task");
		Task task2 = new Task(0, "Task", getMockDate("2015-01-11"));
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithPrependedDate4() throws ParseException {
		Task task1 = new Task(0, "Task", getMockDate("2021-01-01"));
		Task task2 = new Task(0, "Task", getMockDate("2021-03-11"));
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithPriority1() {
		Task task1 = new Task(0, "Task");
		Task task2 = new Task(0, "Task");
		task1.setPriority(Priority.A);
		task2.setPriority(Priority.A);
		assertTrue(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithPriority2() {
		Task task1 = new Task(0, "Task");
		Task task2 = new Task(0, "Task");
		task1.setPriority(Priority.A);
		task2.setPriority(Priority.B);
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithProjects1() {
		Task task1 = new Task(0, "Task +P1");
		Task task2 = new Task(0, "Task +P1");
		assertTrue(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithProjects2() {
		Task task1 = new Task(0, "Task +P1");
		Task task2 = new Task(0, "Task +P2");
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithProjects3() {
		Task task1 = new Task(0, "Task");
		Task task2 = new Task(0, "Task +P2");
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithText1() {
		Task task1 = new Task(0, "Task");
		Task task2 = new Task(0, "Task");
		assertTrue(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithText2() {
		Task task1 = new Task(0, "Tusku");
		Task task2 = new Task(0, "Task");
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithText3() {
		Task task1 = new Task();
		Task task2 = new Task(0, "Task");
		assertFalse(task1.equals(task2));
	}

	@Test
	public void testTaskEqualsWithText4() {
		Task task1 = new Task();
		Task task2 = new Task();
		assertTrue(task1.equals(task2));
	}

	@Test
	public void testTaskInitWithFilters1() {
		Task task = new Task(0, "Task");
		ArrayList<Priority> prios = new ArrayList<>();
		prios.add(Priority.A);
		task.initWithFilters(prios, null, null);
		assertEquals(Priority.A,task.getPriority());
	}

	@Test
	public void testTaskInitWithFilters2() {
		Task task = new Task(0, "Task");
		ArrayList<Priority> prios = new ArrayList<>();
		prios.add(Priority.A);
		prios.add(Priority.B);
		task.initWithFilters(prios, null, null);
		assertEquals(Priority.NONE,task.getPriority());
	}

	@Test
	public void testTaskInitWithFilters3() {
		Task task = new Task(0, "Task");
		task.initWithFilters(null, null, null);
		assertEquals(Priority.NONE,task.getPriority());
	}

	@Test
	public void testTaskInitWithFilters4() {
		Task task = new Task(0, "Task");
		ArrayList<String> ctxts = new ArrayList<>();
		ctxts.add("C1");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("C1");
		task.initWithFilters(null, ctxts, null);
		assertEquals(expected,task.getContexts());
	}


	@Test
	public void testTaskInitWithFilters5() {
		Task task = new Task(0, "Task");
		ArrayList<String> ctxts = new ArrayList<>();
		ctxts.add("C1");
		ctxts.add("C2");
		ArrayList<String> expected = new ArrayList<>();
		task.initWithFilters(null, ctxts, null);
		assertEquals(expected,task.getContexts());
	}

	@Test
	public void testTaskInitWithFilters6() {
		Task task = new Task(0, "Task");
		ArrayList<String> pjs = new ArrayList<>();
		pjs.add("P1");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("P1");
		task.initWithFilters(null, null, pjs);
		assertEquals(expected,task.getProjects());
	}

	@Test
	public void testTaskInitWithFilters7() {
		Task task = new Task(0, "Task");
		ArrayList<String> pjs = new ArrayList<>();
		pjs.add("P1");
		pjs.add("P2");
		ArrayList<String> expected = new ArrayList<>();
		task.initWithFilters(null, null, pjs);
		assertEquals(expected,task.getProjects());
	}

	@Test
	public void testTaskHashcode1() {
		Task task1 = new Task();
		Task task2 = new Task();
		assertTrue(task1.equals(task2));
		assertTrue(task1.hashCode() == task2.hashCode());
	}

	@Test
	public void testTaskHashcode2() throws ParseException {
		Task task1 = new Task(0, "Task @C1 +C1 http://java.sun.com someone@gmail.com", getMockDate("2000-01-01"));
		Task task2 = new Task(0, "Task @C1 +C1 http://java.sun.com someone@gmail.com", getMockDate("2000-01-01"));
		task1.setPriority(Priority.A);
		task2.setPriority(Priority.A);
		task1.delete();
		task2.delete();
		assertTrue(task1.hashCode() == task2.hashCode());
	}

	@Test
	public void testTaskHashcode3() throws ParseException {
		Task task1 = new Task(0, "Task @C1 +C1 http://java.sun.com someone@gmail.com", getMockDate("2000-01-01"));
		Task task2 = new Task();
		task1.setPriority(Priority.A);
		task1.delete();
		assertFalse(task1.hashCode() == task2.hashCode());
	}

	@Test
	public void testTaskHashcode4() {
		Task task = new Task(0, null, null);
		int expected = computeTaskHashCode(task);
		int actual = task.hashCode();
		assertEquals(expected,actual);
	}

	@Test
	public void testTaskHashcode5() throws ParseException {
		Task task = new Task(73, "Task @C1 +C1 http://java.sun.com someone@gmail.com", getMockDate("2000-01-01"));
		task.markComplete(getMockDate("2000-05-01"));
		task.setPriority(Priority.A);
		task.delete();
		int expected = computeTaskHashCode(task);
		int actual = task.hashCode();
		assertEquals(expected,actual);
	}

	private int computeTaskHashCode(Task t) {
		final int prime = 31;
		int result = 1;
		result = prime * result + (t.isCompleted() ? 1231 : 1237);
		result = prime * result	+ ((t.getCompletionDate() == null) ? 0 : t.getCompletionDate().hashCode());
		result = prime * result	+ ((t.getContexts() == null) ? 0 : t.getContexts().hashCode());
		result = prime * result + (t.isDeleted() ? 1231 : 1237);
		result = prime * result + (int) (t.getId() ^ (t.getId() >>> 32));
		result = prime * result + ((t.getLinks() == null) ? 0 : t.getLinks().hashCode());
		result = prime * result	+ ((t.getMailAddresses() == null) ? 0 : t.getMailAddresses().hashCode())
				+ ((t.getPhoneNumbers() == null) ? 0 : t.getPhoneNumbers().hashCode());
		result = prime * result	+ ((t.getPrependedDate() == null) ? 0 : t.getPrependedDate().hashCode());
		result = prime * result	+ ((t.getPriority() == null) ? 0 : t.getPriority().hashCode());
		result = prime * result	+ ((t.getProjects() == null) ? 0 : t.getProjects().hashCode());
		result = prime * result	+ ((t.getRelativeAge() == null) ? 0 : t.getRelativeAge().hashCode());
		result = prime * result + ((t.getText() == null) ? 0 : t.getText().hashCode());
		return result;
	}

	private Date getMockDate(String date) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	}
}
