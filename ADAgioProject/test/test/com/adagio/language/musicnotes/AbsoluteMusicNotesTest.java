package test.com.adagio.language.musicnotes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.parser.ParserFactory;
import org.modelcc.types.IntegerModel;

import com.adagio.language.RunData;
import com.adagio.language.definitions.ChordDefinition;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.AlteredNoteName;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.language.musicnotes.notealterations.DoubleFlatAlteration;
import com.adagio.language.musicnotes.notealterations.FlatAlteration;
import com.adagio.language.musicnotes.notealterations.SharpAlteration;

public class AbsoluteMusicNotesTest {
	
	AbsoluteMusicNote A5Sharp, A4bb; 
	AbsoluteMusicNote A5,A3, A2, C4, B3, C3; 
	BasicNoteName bNoteName;
	Alteration alteration;
	
	@Before
	  public void setUp() throws Exception {
		bNoteName = new BasicNoteName("A");
		alteration = new SharpAlteration(true);
		A5Sharp = new AbsoluteMusicNote(new IntegerModel(5), new AlteredNoteName(bNoteName, alteration));
		
		bNoteName = new BasicNoteName("A");
		alteration = new DoubleFlatAlteration(true);
		A4bb = new AbsoluteMusicNote(new IntegerModel(4), new AlteredNoteName(bNoteName, alteration));
		
		
		A5 = new AbsoluteMusicNote(5, "A");
		A3 = new AbsoluteMusicNote(3,"A");
		A2 = new AbsoluteMusicNote(2,"A");
		C4 = new AbsoluteMusicNote(4,"C");
		B3 = new AbsoluteMusicNote(3, "B");
		C3 = new AbsoluteMusicNote(3, "C");
	  }
	

	@Test
	public void isHigherTest() {
		assertEquals(true, A3.isHigher(A2));
		assertEquals(false, A3.isHigher(B3));
		assertEquals(false, (new AbsoluteMusicNote(-1, "A")).isHigher(C4));
		assertEquals(true, (new AbsoluteMusicNote(-1, "D")).isHigher(new AbsoluteMusicNote(-1, "C")));
		assertEquals(false, (new AbsoluteMusicNote(-1, "C")).isHigher(new AbsoluteMusicNote(-1, "G")));
		assertEquals(true, A3.isHigher(C3));
	}
	
	@Test
	public void semites2Notes2Test() {
		assertEquals(3, A3.semitones2Notes(C4));
		assertEquals(24, A3.semitones2Notes(A5));
		assertEquals(25, A3.semitones2Notes(A5Sharp));
		assertEquals(10, A3.semitones2Notes(A4bb));
	}

}
