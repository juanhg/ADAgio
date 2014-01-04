package test.com.adagio.language.instruments.features;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import test.com.adagio.InitTest;

public class RegisterTest extends InitTest {

	

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@Test
	public void noteToRegisterTest(){
		assertEquals(A2, r1.aNoteToRegister(A2));
		assertEquals(A5Sharp, r1.aNoteToRegister(A5Sharp));
		assertEquals(Cm1, r3.aNoteToRegister(Cm5));
		assertEquals(Cm3, r2.aNoteToRegister(Cm5));
		assertEquals(B2, r2.aNoteToRegister(B3));
		assertEquals(A3bb, r2.aNoteToRegister(A4bb));
		assertEquals(C2, r5.aNoteToRegister(C3));
		assertEquals(A2Sharp, r5.aNoteToRegister(A5Sharp));
	}
	
}
