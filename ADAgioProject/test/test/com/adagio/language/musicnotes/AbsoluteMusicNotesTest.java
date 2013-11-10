package test.com.adagio.language.musicnotes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.types.IntegerModel;

import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.AlteredNoteName;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.language.musicnotes.notealterations.DoubleFlatAlteration;
import com.adagio.language.musicnotes.notealterations.SharpAlteration;

public class AbsoluteMusicNotesTest {
	
	AbsoluteMusicNote A5Sharp, A4bb; 
	AbsoluteMusicNote A5,A3, A2, C4, B3, C3, G3; 
	AbsoluteMusicNote Am1, Cm1, Dm1, Am5, Cm5, Gm5, Bm4, Cm3;
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
		G3 = new AbsoluteMusicNote(3,"G");
		A3 = new AbsoluteMusicNote(3,"A");
		A2 = new AbsoluteMusicNote(2,"A");
		C4 = new AbsoluteMusicNote(4,"C");
		B3 = new AbsoluteMusicNote(3, "B");
		C3 = new AbsoluteMusicNote(3, "C");
		
		Am1 = new AbsoluteMusicNote(-1,"A");
		Cm1 = new AbsoluteMusicNote(-1,"C");
		Dm1 = new AbsoluteMusicNote(-1,"D");
		Am5 = new AbsoluteMusicNote(-5,"A");
		Cm5 = new AbsoluteMusicNote(-5,"C");
		Gm5 = new AbsoluteMusicNote(-5,"G");
		Bm4 = new AbsoluteMusicNote(-4,"B");
		Cm3 = new AbsoluteMusicNote(-3,"C");
		
	  }
	

	@Test
	public void isHigherTest() {
		assertEquals(true, A3.isHigher(A2));
		assertEquals(false, A3.isHigher(B3));
		assertEquals(false, Am1.isHigher(C4));
		assertEquals(true, Dm1.isHigher(new AbsoluteMusicNote(-1, "C")));
		assertEquals(false, Cm1.isHigher(new AbsoluteMusicNote(-1, "G")));
		assertEquals(true, A3.isHigher(C3));
	}
	
	@Test
	public void semitonesTill() {
		//Positive results
		assertEquals(3, A3.semitonesTill(C4));
		assertEquals(24, A3.semitonesTill(A5));
		assertEquals(25, A3.semitonesTill(A5Sharp));
		assertEquals(10, A3.semitonesTill(A4bb));
		
		//Negative results
		assertEquals(-3, C4.semitonesTill(A3));
		assertEquals(-24, A5.semitonesTill(A3));
		assertEquals(-25, A5Sharp.semitonesTill(A3));
		assertEquals(-10, A4bb.semitonesTill(A3));
	}
	
	public void nextTest(){
		assertEquals(true, Cm3.equals(Bm4.next()));
		assertEquals(true, B3.equals(C4.next()));
		assertEquals(true, Am5.equals(Gm5.next()));
		assertEquals(true, A3.equals(G3.next()));
	}
	
	public void previousTest(){
		assertEquals(true, Bm4.equals(Cm3.next()));
		assertEquals(true, C4.equals(B3.next()));
		assertEquals(true, Gm5.equals(Am5.next()));
		assertEquals(true, G3.equals(A3.next()));
	}

}
