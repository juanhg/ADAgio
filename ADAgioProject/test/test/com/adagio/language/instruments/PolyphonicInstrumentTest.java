package test.com.adagio.language.instruments;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import test.com.adagio.InitTest;

import com.adagio.instruments.Instrument;
import com.adagio.instruments.PolyphonicInstrument;
import com.adagio.language.instruments.features.Register;
import com.adagio.language.instruments.features.Timbre;
import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class PolyphonicInstrumentTest extends InitTest {

	Instrument piano;

	@Before
	public void setUp() throws Exception {
	
		super.setUp();
	
		Register pianoRegister[]= new Register[1];
		pianoRegister[0] = new Register();
		pianoRegister[0] = r6;
		piano = new PolyphonicInstrument(new Timbre(),pianoRegister);
	}
	
	
	@Test
	public void aNotesToRegisterstest() {
		List<AbsoluteMusicNote> aNotes = new ArrayList<AbsoluteMusicNote>();
		List<AbsoluteMusicNote> expected = new ArrayList<AbsoluteMusicNote>();
		
		aNotes.add(E0);
		aNotes.add(C0);
		aNotes.add(G0);
		
		expected.add(E0);
		expected.add(C0);
		expected.add(G0);
		List<AbsoluteMusicNote> result = piano.aNotesToInstrumentRegister(aNotes);
		//piano(0E,0C,0G) == (0E,0C,0G)
		assertEquals(expected, result);
		
		//2F#m -->  <fis'' a'' cis'''>
		
	
		
	}

}
