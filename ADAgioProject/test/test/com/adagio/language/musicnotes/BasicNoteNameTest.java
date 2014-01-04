package test.com.adagio.language.musicnotes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import test.com.adagio.InitTest;

public class BasicNoteNameTest extends InitTest {
	
	@Before
	public void setUp() throws Exception{
		super.setUp();
	}

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
		assertEquals(B, A.next());
		assertEquals(A, G.next());
		assertEquals(E, D.next());
	}

	@Test
	public void previousValidTest(){
		assertEquals(A, B.previous());
		assertEquals(G, A.previous());
		assertEquals(C, D.previous());
	}
	
}
