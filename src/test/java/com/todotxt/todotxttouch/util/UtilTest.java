package com.todotxt.todotxttouch.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chschmid.jdotxt.Jdotxt;
import com.todotxt.todotxttouch.TodoException;
import com.todotxt.todotxttouch.task.LocalFileTaskRepository;
import com.todotxt.todotxttouch.task.Task;

public class UtilTest {
	
	final static String DEFAULTDIR = Jdotxt.DEFAULT_DIR;

	private ArrayList<Task> savedtodotasks;
	private ArrayList<Task> saveddonetasks;
	private LocalFileTaskRepository savedRepo;

	@Before
	public void setUp() throws Exception {

		/*
		Preferences userPrefs = Preferences.userNodeForPackage(Jdotxt.class);
		JdotxtGUI.lang = new LanguagesController(userPrefs.get("lang", "English"));
		*/

		savedRepo = new LocalFileTaskRepository();
		savedtodotasks = savedRepo.load();
		saveddonetasks = savedRepo.loadDoneTasks();
		savedRepo.purge();
	}

	@After
	public void tearDown() throws Exception {
		savedRepo.store(savedtodotasks);
		savedRepo.storeDoneTasks(saveddonetasks);
	}

	@Test
	public void testReadStreamNullStream() {
		String actual = Util.readStream(null);
		assertNull(actual);
	}
	
	@Test
	public void testReadStreamNotNullStream() throws IOException {
		File f = new File("temp.txt");
		f.createNewFile();
		populate(f);
		InputStream is = new FileInputStream(f);
		String expected = "onetwothree";
		String actual = Util.readStream(is);
		assertEquals(expected, actual);
		is.close();
		f.delete();
	}
	
	@Test
	public void testReadStreamClosed() throws IOException {
		File f = new File("temp.txt");
		f.createNewFile();
		InputStream is = new FileInputStream(f);
		is.close();
		String actual = Util.readStream(is);
		assertNull(actual);
		f.delete();
	}
	
	@Test
	public void testWriteFile() throws IOException {
		File f = new File("temp.txt");
		File copy = new File("temp2.txt");
		f.createNewFile();
		copy.createNewFile();
		populate(f);
		
		InputStream is = new FileInputStream(f);
		
		Util.writeFile(is, copy);
		
		InputStream copyis = new FileInputStream(copy);
		
		String expected = "onetwothree";
		String actual = Util.readStream(copyis);
		
		assertEquals(expected, actual);
		f.delete();
		copy.delete();
		copyis.close();
	}
	
	@Test(expected = TodoException.class)
	public void testCopyFileNewFileIsNull() throws IOException {
		File origFile = new File("temp.txt");
		File newFile = null;
		Util.copyFile(origFile, newFile, false);
	}
	
	@Test
	public void testCopyFile2() {
		File origFile = new File("temp.txt");
		File newFile = new File("temp/temp2.txt");
		//Util.copyFile(origFile, newFile, false);
	}
	
	@Test
	public void testCopyFile3() {
		
	}
	
	@Test
	public void testCopyJoinNullCollection() {
		String expected ="";
		String actual = Util.join(null, "#");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCopyJoinCollectionNotNull() {
		List<String> col = new ArrayList<>();
		col.add("one");
		col.add("two");
		col.add("three");
		String expected ="one#two#three";
		String actual = Util.join(col, "#");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCopyJoinCollectionEmpty() {
		List<String> col = new ArrayList<>();
		String expected ="";
		String actual = Util.join(col, "#");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSplitBlankString() {
		List<String> expected = new ArrayList<>();
		List<String> actual = Util.split("     ", "#");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSplitNonBlankString() {
		List<String> expected = new ArrayList<>();
		expected.add("one");
		expected.add("two");
		expected.add("three");
		List<String> actual = Util.split("one#two#three", "#");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testPrependString() {
		ArrayList<String> expected = new ArrayList<>();
		expected.add("okone");
		expected.add("oktwo");
		ArrayList<String> actual = new ArrayList<>();
		actual.add("one");
		actual.add("two");
		Util.prependString(actual, "ok");
		assertEquals(expected, actual);
	}

	@Test
	public void testIntegerList2IntArray() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		int[] expected = {(int) 1, (int) 2, (int) 3};
		int[] actual = Util.integerList2IntArray(list);

		assertArrayEquals(expected, actual);
	}
	
	@Test(expected = TodoException.class)
	public void testRenameFile1() throws IOException {
		File origFile = new File(DEFAULTDIR + File.separator + "origFile.txt");
		File newFile = new File(DEFAULTDIR + File.separator + "newFile.txt");
		origFile.createNewFile();
		newFile.createNewFile();
		Util.renameFile(origFile, newFile,false);
		origFile.delete();
		newFile.delete();
	}
	
	@Test
	public void testRenameFile2() throws IOException {
		File origFile = new File(DEFAULTDIR + File.separator + "origFile.txt");
		File newFile = new File(DEFAULTDIR + File.separator + "newFile.txt");
		origFile.createNewFile();
		newFile.createNewFile();
		Util.renameFile(origFile, newFile,true);
		assertTrue(!origFile.exists() && newFile.exists());
		origFile.delete();
		newFile.delete();
	}
	
	@Test
	public void testRenameFile3() throws IOException {
		File origFile = new File(DEFAULTDIR + File.separator + "origFile.txt");
		File newFile = new File(DEFAULTDIR + File.separator + "newFile.txt");
		origFile.createNewFile();
		Util.renameFile(origFile, newFile,true);
		assertTrue(!origFile.exists() && newFile.exists());
		origFile.delete();
		newFile.delete();
	}
	
	@Test(expected = TodoException.class)
	public void testRenameFile4() throws IOException {
		File origFile = new File(DEFAULTDIR + File.separator + "origFile.txt");
		File newFile = new File(DEFAULTDIR + File.separator + "newFile.txt");
		Util.renameFile(origFile, newFile,true);
		origFile.delete();
		newFile.delete();
	}
	
	@Test(expected = TodoException.class)
	public void testcopyFile1() throws IOException {
		File origFile = new File(DEFAULTDIR + File.separator + "origFile.txt");
		File newFile = new File(DEFAULTDIR + File.separator + "newFile.txt");
		origFile.createNewFile();
		newFile.createNewFile();
		Util.copyFile(origFile, newFile, false);
		origFile.delete();
		newFile.delete();
	}
	
	@Test
	public void testcopyFile2() throws IOException {
		File origFile = new File(DEFAULTDIR + File.separator + "origFile.txt");
		File newFile = new File(DEFAULTDIR + File.separator + "newFile.txt");
		origFile.createNewFile();
		newFile.createNewFile();
		populate(origFile);
		Util.copyFile(origFile, newFile, true);
		assertArrayEquals(Files.readAllBytes(newFile.toPath()),Files.readAllBytes(origFile.toPath()));
		origFile.delete();
		newFile.delete();
	}
	
	private void populate(File f) throws IOException {
		FileWriter wr = new FileWriter(f);
		wr.append("one");
		wr.append("two");
		wr.append("three");
		wr.close();
	}

}
