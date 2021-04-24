package com.todotxt.todotxttouch.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.prefs.Preferences;

import org.junit.Before;
import org.junit.Test;

import com.chschmid.jdotxt.Jdotxt;
import com.chschmid.jdotxt.gui.JdotxtGUI;
import com.chschmid.jdotxt.util.LanguagesController;

public class RelativeDateTest {


	private Calendar today;

	@Before
	public void setUp() throws Exception {
		Preferences userPrefs = Preferences.userNodeForPackage(Jdotxt.class);
		JdotxtGUI.lang = new LanguagesController(userPrefs.get("lang", "English"));
		today = getCalendarOf("2020-08-12");
	}

	@Test
	public void testRelativeDateYearsAgo() throws ParseException {
		Calendar cal = new GregorianCalendar();
		cal.set(2008, 2, 12);
		cal.set(GregorianCalendar.HOUR_OF_DAY, 0);
		cal.set(GregorianCalendar.MINUTE, 0);
		cal.set(GregorianCalendar.SECOND, 0);
		cal.set(GregorianCalendar.MILLISECOND,0);
		String expected = "2008-02-12";
		assertEquals(expected, RelativeDate.getRelativeDate(today, getCalendarOf("2008-02-12")));
	}

	@Test
	public void testRelativeDateXmonthsAgo() throws ParseException {
		Calendar cal = new GregorianCalendar();
		cal.set(2008, 2, 12);
		cal.set(GregorianCalendar.HOUR_OF_DAY, 0);
		cal.set(GregorianCalendar.MINUTE, 0);
		cal.set(GregorianCalendar.SECOND, 0);
		cal.set(GregorianCalendar.MILLISECOND,0);
		String expected = "6 months ago";
		assertEquals(expected, RelativeDate.getRelativeDate(today, getCalendarOf("2020-02-12")));
	}

	@Test
	public void testRelativeDate1monthAgo() throws ParseException {
		Calendar cal = new GregorianCalendar();
		cal.set(2008, 2, 12);
		cal.set(GregorianCalendar.HOUR_OF_DAY, 0);
		cal.set(GregorianCalendar.MINUTE, 0);
		cal.set(GregorianCalendar.SECOND, 0);
		cal.set(GregorianCalendar.MILLISECOND,0);
		String expected = "1 month ago";
		assertEquals(expected, RelativeDate.getRelativeDate(today, getCalendarOf("2020-07-12")));
	}

	@Test
	public void testRelativeDateXdaysAgo() throws ParseException {
		Calendar cal = new GregorianCalendar();
		cal.set(2008, 2, 12);
		cal.set(GregorianCalendar.HOUR_OF_DAY, 0);
		cal.set(GregorianCalendar.MINUTE, 0);
		cal.set(GregorianCalendar.SECOND, 0);
		cal.set(GregorianCalendar.MILLISECOND,0);
		String expected = "10 days ago";
		assertEquals(expected, RelativeDate.getRelativeDate(today, getCalendarOf("2020-08-2")));
	}

	@Test
	public void testRelativeDate1dayAgo() throws ParseException {
		Calendar cal = new GregorianCalendar();
		cal.set(2008, 2, 12);
		cal.set(GregorianCalendar.HOUR_OF_DAY, 0);
		cal.set(GregorianCalendar.MINUTE, 0);
		cal.set(GregorianCalendar.SECOND, 0);
		cal.set(GregorianCalendar.MILLISECOND,0);
		String expected = "1 day ago";
		assertEquals(expected, RelativeDate.getRelativeDate(today, getCalendarOf("2020-08-11")));
	}
	
	
	private Calendar getCalendarOf(String date) throws ParseException {
		Calendar converted = GregorianCalendar.getInstance();
		converted.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		return converted;
	}
	
	//Mutantes
	
	@Test
	public void testRelativeDateToday() throws ParseException {
		String expected = "today";
		assertEquals(expected, RelativeDate.getRelativeDate(today, getCalendarOf("2020-08-12")));
	}
	
	@Test
	public void testRelativeDate2DaysAgo() throws ParseException {
		String expected = "2 days ago";
		assertEquals(expected, RelativeDate.getRelativeDate(today, getCalendarOf("2020-08-10")));
	}
	
	@Test
	public void testRelativeDate1MonthAgo30Days() throws ParseException {
		String expected = "1 month ago";
		assertEquals(expected, RelativeDate.getRelativeDate(today, getCalendarOf("2020-07-13")));
	}
	
	@Test
	public void testRelativeDate2MonthsAgo60Days() throws ParseException {
		String expected = "2 months ago";
		assertEquals(expected, RelativeDate.getRelativeDate(today, getCalendarOf("2020-06-13")));
	}
	
	@Test
	public void testRelativeDateFuture() throws ParseException {
		String expected = "2021-08-12";
		assertEquals(expected, RelativeDate.getRelativeDate(today, getCalendarOf("2021-08-12")));
	}
	
	@Test
	public void testRelativeDateFarInThePast() throws ParseException {
		String expected = "2019-08-13";
		assertEquals(expected, RelativeDate.getRelativeDate(today, getCalendarOf("2019-08-13")));
	}
	
	@Test
	public void testRelativeDateWithDate() throws ParseException {
		String expected = "1999-08-12";
		Calendar testDay = getCalendarOf("1999-08-12");
		Date date = testDay.getTime();
		assertEquals(expected, RelativeDate.getRelativeDate(date));
	}

}
