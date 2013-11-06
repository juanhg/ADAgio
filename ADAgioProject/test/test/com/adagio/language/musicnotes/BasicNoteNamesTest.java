package test.com.adagio.language.musicnotes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.adagio.language.musicnotes.BasicNoteName;

public class BasicNoteNamesTest {
	
	@Test
	public void shortestDistanceTest() {
		
		 assertEquals(-3, (new BasicNoteName("A")).shortestDistance(new BasicNoteName("E")));
		 assertEquals(-2,(new BasicNoteName("A")).shortestDistance(new BasicNoteName("F")));
		 assertEquals(-1,(new BasicNoteName("A")).shortestDistance(new BasicNoteName("G")));
		 assertEquals(0,(new BasicNoteName("A")).shortestDistance(new BasicNoteName("A")));
		 assertEquals(1,(new BasicNoteName("A")).shortestDistance(new BasicNoteName("B")));
		 assertEquals(2,(new BasicNoteName("A")).shortestDistance(new BasicNoteName("C")));
		 assertEquals(3,(new BasicNoteName("A")).shortestDistance(new BasicNoteName("D")));
		 
		 assertEquals(-3,(new BasicNoteName("B")).shortestDistance(new BasicNoteName("F")));
		 assertEquals(-2,(new BasicNoteName("B")).shortestDistance(new BasicNoteName("G")));
		 assertEquals(-1,(new BasicNoteName("B")).shortestDistance(new BasicNoteName("A")));
		 assertEquals(0,(new BasicNoteName("B")).shortestDistance(new BasicNoteName("B")));
		 assertEquals(1,(new BasicNoteName("B")).shortestDistance(new BasicNoteName("C")));
		 assertEquals(2,(new BasicNoteName("B")).shortestDistance(new BasicNoteName("D")));
		 assertEquals(3,(new BasicNoteName("B")).shortestDistance(new BasicNoteName("E")));
		 
	}

}
