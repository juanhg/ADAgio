package test.com.adagio.language.instruments;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import test.com.adagio.InitTest;

import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class MonophonicInstrumentTest extends InitTest {
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}


	@Test
	public void aNotesToRegisterstest() {
		List<AbsoluteMusicNote> aNotes = new ArrayList<AbsoluteMusicNote>();
		List<AbsoluteMusicNote> expected = new ArrayList<AbsoluteMusicNote>();
		List<AbsoluteMusicNote> result;

		//maleVoice(0C,0E,0G) == (2C)
		aNotes.add(C0);
		aNotes.add(E0);
		aNotes.add(G0);

		expected.add(C2);
		result = maleVoice.aNotesToInstrumentRegister(aNotes);
		assertEquals(expected, result);
		aNotes.clear();
		expected.clear();

		//femaleVoice(-1C,-1E,-1G) == (3C)
		aNotes.add(Cm1);
		aNotes.add(Em1);
		aNotes.add(Gm1);

		expected.add(C3);
		result = femaleVoice.aNotesToInstrumentRegister(aNotes);
		assertEquals(expected, result);
		aNotes.clear();
		expected.clear();

		//flute(2F#, A2, C3Sharp) == (2F#);
		aNotes.add(F2Sharp);
		aNotes.add(A2);
		aNotes.add(C3Sharp);

		expected.add(F2Sharp);
		result = flute.aNotesToInstrumentRegister(aNotes);
		assertEquals(expected, result);
		aNotes.clear();
		expected.clear();
		
		//flute(-1A,-1E,-1G) == (E0)
		aNotes.add(Am1);
		aNotes.add(Em1);
		aNotes.add(Gm1);
		
		expected.add(E0);
		result = flute.aNotesToInstrumentRegister(aNotes);
		assertEquals(expected, result);
		aNotes.clear();
		expected.clear();
		
		//flute(7A,7C,7D) == (C3)
		aNotes.add(A7);
		aNotes.add(C7);
		aNotes.add(D7);

		expected.add(C3);
		result = flute.aNotesToInstrumentRegister(aNotes);
		assertEquals(expected, result);
		expected.clear();
		aNotes.clear();
		
		//flute(7A,7C,7D) == (C3)
		aNotes.add(G4);

		expected.add(G2);
		result = flute.aNotesToInstrumentRegister(aNotes);
		assertEquals(expected, result);
		
	}

}
