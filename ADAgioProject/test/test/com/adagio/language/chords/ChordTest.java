package test.com.adagio.language.chords;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import test.com.adagio.InitTest;

import com.adagio.language.bars.chords.Chord;

public class ChordTest extends InitTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
	 
	@Test
	public void toAbsoluteTest() {
		Chord result = silenceChord.toAbsoluteChord(C2);
		assertTrue(result.isSilence());
	}

}
