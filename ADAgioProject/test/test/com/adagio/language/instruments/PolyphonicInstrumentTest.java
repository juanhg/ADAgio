package test.com.adagio.language.instruments;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.types.IntegerModel;

import com.adagio.instruments.Instrument;
import com.adagio.instruments.PolyphonicInstrument;
import com.adagio.language.instruments.features.Register;
import com.adagio.language.instruments.features.Timbre;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.AlteredNoteName;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.language.musicnotes.notealterations.DoubleFlatAlteration;
import com.adagio.language.musicnotes.notealterations.SharpAlteration;

public class PolyphonicInstrumentTest {

	AbsoluteMusicNote A5Sharp, A2Sharp, A4bb, A3bb; 
	AbsoluteMusicNote A5,A3, A2, C4,B2, B3,C2, C3, G3, E0, C0, G0; 
	AbsoluteMusicNote Am1, Cm1, Dm1, Am5, Cm5, Gm5, Bm4, Cm3;
	BasicNoteName bNoteName;
	Alteration alteration;
	Register r1, r2, r3, r4, r5, r6;
	Instrument piano;

	@Before
	public void setUp() throws Exception {
		bNoteName = new BasicNoteName("A");
		alteration = new SharpAlteration(true);
		A5Sharp = new AbsoluteMusicNote(new IntegerModel(5), new AlteredNoteName(bNoteName, alteration));

		bNoteName = new BasicNoteName("A");
		alteration = new SharpAlteration(true);
		A2Sharp = new AbsoluteMusicNote(new IntegerModel(2), new AlteredNoteName(bNoteName, alteration));
		
		bNoteName = new BasicNoteName("A");
		alteration = new DoubleFlatAlteration(true);
		A4bb = new AbsoluteMusicNote(new IntegerModel(4), new AlteredNoteName(bNoteName, alteration));

		bNoteName = new BasicNoteName("A");
		alteration = new DoubleFlatAlteration(true);
		A3bb = new AbsoluteMusicNote(new IntegerModel(3), new AlteredNoteName(bNoteName, alteration));

		C0 = new AbsoluteMusicNote(0,"C"); 
		E0 = new AbsoluteMusicNote(0, "E");
		G0 = new AbsoluteMusicNote(0, "G");
		A5 = new AbsoluteMusicNote(5, "A");
		G3 = new AbsoluteMusicNote(3,"G");
		A3 = new AbsoluteMusicNote(3,"A");
		A2 = new AbsoluteMusicNote(2,"A");
		C2 = new AbsoluteMusicNote(2,"C");
		C4 = new AbsoluteMusicNote(4,"C");
		B3 = new AbsoluteMusicNote(3, "B");
		B2 = new AbsoluteMusicNote(2, "B");
		C3 = new AbsoluteMusicNote(3, "C");

		Am1 = new AbsoluteMusicNote(-1,"A");
		Cm1 = new AbsoluteMusicNote(-1,"C");
		Dm1 = new AbsoluteMusicNote(-1,"D");
		Am5 = new AbsoluteMusicNote(-5,"A");
		Cm5 = new AbsoluteMusicNote(-5,"C");
		Gm5 = new AbsoluteMusicNote(-5,"G");
		Bm4 = new AbsoluteMusicNote(-4,"B");
		Cm3 = new AbsoluteMusicNote(-3,"C");
		
		r1 = new Register();
		r2 = new Register(Cm3, A3);
		r3 = new Register(Cm1, C4);
		r4 = new Register(A2, A5Sharp);
		r5 = new Register(A2,B2);
		r6 = new Register(Bm4,C4);
		
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
		assertEquals(expected, result);
	
		
	}

}
