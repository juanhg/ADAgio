package test.com.adagio.language.instruments;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.adagio.language.musicnotes.AbsoluteMusicNote;

import test.com.adagio.InitTest;

public class LimitedPolyphonicInstrumentTest extends InitTest {
	
	@Before
	public void setUp() throws Exception{
		super.setUp();
	}
	
	@Test
	public void aNotesToRegistersTest() {
		List<AbsoluteMusicNote> aNotes = new ArrayList<AbsoluteMusicNote>();
		List<AbsoluteMusicNote> expected = new ArrayList<AbsoluteMusicNote>();
		List<AbsoluteMusicNote> result;
		
		//acousticGuitar(0C,0E,0G) == (1C,1E,1G)
		aNotes.add(C0);
		aNotes.add(E0);
		aNotes.add(G0);
		
		expected.add(C1);
		expected.add(E1);
		expected.add(G1);
		result = acousticGuitar.apply(aNotes);
		assertEquals(expected, result);
		aNotes.clear();
		expected.clear();
		
		//acousticGuitar(0E,0G#,F0) == (0E,1G#,1F)
		aNotes.add(E0);
		aNotes.add(G0Sharp);
		aNotes.add(F0);
		
		expected.add(E0);
		expected.add(G1Sharp);
		expected.add(F1);
		result = acousticGuitar.apply(aNotes);
		assertEquals(expected, result);
		aNotes.clear();
		expected.clear();
	}

}
