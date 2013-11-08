package test.com.adagio.language.musicnotes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.adagio.language.musicnotes.BasicNoteName;

public class BasicNoteNamesTest {
	
	BasicNoteName A = new BasicNoteName("A");
	BasicNoteName B = new BasicNoteName("B");
	BasicNoteName C = new BasicNoteName("C");
	BasicNoteName D = new BasicNoteName("D");
	BasicNoteName E = new BasicNoteName("E");
	BasicNoteName F = new BasicNoteName("F");
	BasicNoteName G = new BasicNoteName("G");
	
	@Test
	public void shortestDistanceValidTest() {
		
		 assertEquals(-3, A.shortestDistance(E));
		 assertEquals(-2,A.shortestDistance(F));
		 assertEquals(-1,A.shortestDistance(G));
		 assertEquals(0,A.shortestDistance(A));
		 assertEquals(1,A.shortestDistance(B));
		 assertEquals(2,A.shortestDistance(C));
		 assertEquals(3,A.shortestDistance(D));
		 
		 assertEquals(-3,B.shortestDistance(F));
		 assertEquals(-2,B.shortestDistance(G));
		 assertEquals(-1,B.shortestDistance(A));
		 assertEquals(0,B.shortestDistance(B));
		 assertEquals(1,B.shortestDistance(C));
		 assertEquals(2,B.shortestDistance(D));
		 assertEquals(3,B.shortestDistance(E));
		 
	}
	
	@Test
	public void nextValidTest(){
		assertEquals(true,B.equals(A.next()));
		assertEquals(true,A.equals(G.next()));
		assertEquals(true,E.equals(D.next()));
	}
	
	@Test
	public void previousValidTest(){
		assertEquals(true,A.equals(B.previous()));
		assertEquals(true,G.equals(A.previous()));
		assertEquals(true,C.equals(D.previous()));
	}
	
	
	
	
}
