package test.com.adagio.language;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.adagio.language.RunData;


public class RunDataTest {

	 RunData data;
	 
	@Before
	  public void setUp() throws Exception {
	    data = new RunData();
	  }
	
	@Test
	public void noteDistanceTest() {
		
		 assertEquals(-3,data.noteDistance("A", "E"));
		 assertEquals(-2,data.noteDistance("A", "F"));
		 assertEquals(-1,data.noteDistance("A", "G"));
		 assertEquals(0,data.noteDistance("A", "A"));
		 assertEquals(1,data.noteDistance("A", "B"));
		 assertEquals(2,data.noteDistance("A", "C"));
		 assertEquals(3,data.noteDistance("A", "D"));
		 
		 assertEquals(-3,data.noteDistance("B", "F"));
		 assertEquals(-2,data.noteDistance("B", "G"));
		 assertEquals(-1,data.noteDistance("B", "A"));
		 assertEquals(0,data.noteDistance("B", "B"));
		 assertEquals(1,data.noteDistance("B", "C"));
		 assertEquals(2,data.noteDistance("B", "D"));
		 assertEquals(3,data.noteDistance("B", "E"));
		 
		 assertEquals(-3,data.noteDistance("C", "G"));
		 assertEquals(-2,data.noteDistance("C", "A"));
		 assertEquals(-1,data.noteDistance("C", "B"));
		 assertEquals(0,data.noteDistance("C", "C"));
		 assertEquals(1,data.noteDistance("C", "D"));
		 assertEquals(2,data.noteDistance("C", "E"));
		 assertEquals(3,data.noteDistance("C", "F"));
		 
		 assertEquals(-3,data.noteDistance("D", "A"));
		 assertEquals(-2,data.noteDistance("D", "B"));
		 assertEquals(-1,data.noteDistance("D", "C"));
		 assertEquals(0,data.noteDistance("D", "D"));
		 assertEquals(1,data.noteDistance("D", "E"));
		 assertEquals(2,data.noteDistance("D", "F"));
		 assertEquals(3,data.noteDistance("D", "G"));
		 
		 assertEquals(-3,data.noteDistance("E", "B"));
		 assertEquals(-2,data.noteDistance("E", "C"));
		 assertEquals(-1,data.noteDistance("E", "D"));
		 assertEquals(0,data.noteDistance("E", "E"));
		 assertEquals(1,data.noteDistance("E", "F"));
		 assertEquals(2,data.noteDistance("E", "G"));
		 assertEquals(3,data.noteDistance("E", "A"));
		 
		 assertEquals(-3,data.noteDistance("F", "C"));
		 assertEquals(-2,data.noteDistance("F", "D"));
		 assertEquals(-1,data.noteDistance("F", "E"));
		 assertEquals(0,data.noteDistance("F", "F"));
		 assertEquals(1,data.noteDistance("F", "G"));
		 assertEquals(2,data.noteDistance("F", "A"));
		 assertEquals(3,data.noteDistance("F", "B"));
		 
		 assertEquals(-3,data.noteDistance("G", "D"));
		 assertEquals(-2,data.noteDistance("G", "E"));
		 assertEquals(-1,data.noteDistance("G", "F"));
		 assertEquals(0,data.noteDistance("G", "G"));
		 assertEquals(1,data.noteDistance("G", "A"));
		 assertEquals(2,data.noteDistance("G", "B"));
		 assertEquals(3,data.noteDistance("G", "C"));
	}

}
