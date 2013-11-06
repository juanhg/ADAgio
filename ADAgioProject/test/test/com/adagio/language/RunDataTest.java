package test.com.adagio.language;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.adagio.language.RunData;
import com.adagio.language.musicnotes.BasicNoteName;


public class RunDataTest {

	 RunData data;
	 
	@Before
	  public void setUp() throws Exception {
	    data = new RunData();
	  }
	
	@Test
	public void noteDistanceTest() {
		
		 assertEquals(-3,data.noteDistance(new BasicNoteName("A"), new BasicNoteName("E")));
		 assertEquals(-2,data.noteDistance(new BasicNoteName("A"), new BasicNoteName("F")));
		 assertEquals(-1,data.noteDistance(new BasicNoteName("A"), new BasicNoteName("G")));
		 assertEquals(0,data.noteDistance(new BasicNoteName("A"), new BasicNoteName("A")));
		 assertEquals(1,data.noteDistance(new BasicNoteName("A"), new BasicNoteName("B")));
		 assertEquals(2,data.noteDistance(new BasicNoteName("A"), new BasicNoteName("C")));
		 assertEquals(3,data.noteDistance(new BasicNoteName("A"), new BasicNoteName("D")));
		 
		 assertEquals(-3,data.noteDistance(new BasicNoteName("B"), new BasicNoteName("F")));
		 assertEquals(-2,data.noteDistance(new BasicNoteName("B"), new BasicNoteName("G")));
		 assertEquals(-1,data.noteDistance(new BasicNoteName("B"), new BasicNoteName("A")));
		 assertEquals(0,data.noteDistance(new BasicNoteName("B"), new BasicNoteName("B")));
		 assertEquals(1,data.noteDistance(new BasicNoteName("B"), new BasicNoteName("C")));
		 assertEquals(2,data.noteDistance(new BasicNoteName("B"), new BasicNoteName("D")));
		 assertEquals(3,data.noteDistance(new BasicNoteName("B"), new BasicNoteName("E")));
		 
	}
	
	@Test
	public void semitones2NotesTest() {
		assertEquals(2,data.semitones2Notes(new BasicNoteName("A"), new BasicNoteName("B")));
		assertEquals(1,data.semitones2Notes(new BasicNoteName("B"), new BasicNoteName("C")));
		assertEquals(4,data.semitones2Notes(new BasicNoteName("C"), new BasicNoteName("E")));
		assertEquals(4,data.semitones2Notes(new BasicNoteName("F"), new BasicNoteName("A")));
		assertEquals(7,data.semitones2Notes(new BasicNoteName("F"), new BasicNoteName("C")));
	}

}
