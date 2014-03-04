package test.com.adagio.language.chords.intervals;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.types.IntegerModel;

import test.com.adagio.InitTest;

import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.AlteredNoteName;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.language.musicnotes.notealterations.DoubleFlatAlteration;
import com.adagio.language.musicnotes.notealterations.DoubleSharpAlteration;
import com.adagio.language.musicnotes.notealterations.FlatAlteration;
import com.adagio.language.musicnotes.notealterations.SharpAlteration;


public class IntervalTest extends InitTest {
	
	AbsoluteMusicNote result, note, expected;
	Alteration alteration;
	BasicNoteName bNoteName;
			
	@Before
	public void setUp() throws Exception {
		super.setUp();

	}
	
	@Test
	public void validIntervalTest() {
		assertEquals(0, perfect1.semitones());
		assertEquals(0, diminished2.semitones());
		assertEquals(1, minor2.semitones());
		assertEquals(1, augmented1.semitones());
		assertEquals(2, major2.semitones());
		assertEquals(2, diminished3.semitones());
		assertEquals(3, minor3.semitones());
		assertEquals(3, augmented2.semitones());
		assertEquals(4, major3.semitones());
		assertEquals(4, diminished4.semitones());
		assertEquals(5, perfect4.semitones());
		assertEquals(5, augmented3.semitones());
		assertEquals(6, diminished5.semitones());
		assertEquals(6, augmented4.semitones());
		assertEquals(7, perfect5.semitones());
		assertEquals(7, diminished6.semitones());
		assertEquals(8, minor6.semitones());
		assertEquals(8, augmented5.semitones());
		assertEquals(9, major6.semitones());
		assertEquals(9, diminished7.semitones());
		assertEquals(10, minor7.semitones());
		assertEquals(10, augmented6.semitones());
		assertEquals(11, major7.semitones());
		assertEquals(11, diminished8.semitones());
		assertEquals(12, perfect8.semitones());
		assertEquals(12, augmented7.semitones());
		assertEquals(9, doubleAugmented5.semitones());
		assertEquals(6, doubleAugmented3.semitones());
		assertEquals(8, doubleDiminished7.semitones());
		assertEquals(5, doubleDiminished5.semitones());
	}
	
	@Test
	public void validApplyTest() 
	{
		
		/* CIRCLE OF FIFTHS (RIGHT) */
		
		// P5(-3C) --> -3G
		note = Cm3;
		expected = Gm3;
		result = perfect5.apply(note, C2);
		assertEquals(expected,result);
		
		// P5(-3G) --> -2D
		note = result;
		expected = Dm2;
		result = perfect5.apply(note, C2);
		assertEquals(expected,result);

		// P5(-2D) --> -2A
		note = result;
		expected = Am2;
		result = perfect5.apply(note, C2);
		assertEquals(expected,result);
		
		// P5(-2A) --> -1E
		note = result;
		expected = Em1;
		result = perfect5.apply(note, C2);
		assertEquals(expected,result);
		
		// P5(-1E) --> -1B
		note = result;
		expected = Bm1;
		result = perfect5.apply(note, C2);
		assertEquals(expected,result);	
		
		// P5(-1B) --> 0F#
		note = result;
		expected = F0Sharp;
		result = perfect5.apply(note, C2);
		assertEquals(expected,result);
		
		// P5(0F#) --> 1C#
		note = result;
		expected = C1Sharp;
		result = perfect5.apply(note, C2);
		assertEquals(expected,result);
		
		
		/* CIRCLE OF FIFTHS (LEFT) */
		
		// P4(-3C) --> -3F
		note = Cm3;
		expected = Fm3;
		result = perfect4.apply(note, C2);
		assertEquals(expected,result);
		
		// P5(-3F) --> -3Bb
		note = result;
		bNoteName = new BasicNoteName("B");
		alteration = new FlatAlteration();
		expected = new AbsoluteMusicNote(new IntegerModel(-3), new AlteredNoteName(bNoteName, alteration));
		result = perfect4.apply(note, C2);
		assertEquals(expected,result);

		// P5(-3Bb) --> -2Eb
		note = result;
		bNoteName = new BasicNoteName("E");
		alteration = new FlatAlteration();
		expected = new AbsoluteMusicNote(new IntegerModel(-2), new AlteredNoteName(bNoteName, alteration));
		result = perfect4.apply(note, C2);
		assertEquals(expected,result);
		
		// P5(-2Eb) --> -2Ab
		note = result;
		bNoteName = new BasicNoteName("A");
		alteration = new FlatAlteration();
		expected = new AbsoluteMusicNote(new IntegerModel(-2), new AlteredNoteName(bNoteName, alteration));
		result = perfect4.apply(note, C2);
		assertEquals(expected,result);
		
		// P5(-2Ab) --> -1Db
		note = result;
		bNoteName = new BasicNoteName("D");
		alteration = new FlatAlteration();
		expected = new AbsoluteMusicNote(new IntegerModel(-1), new AlteredNoteName(bNoteName, alteration));
		result = perfect4.apply(note, C2);
		assertEquals(expected,result);

		// P5(-1Db) --> -1Gb
		note = result;
		bNoteName = new BasicNoteName("G");
		alteration = new FlatAlteration();
		expected = new AbsoluteMusicNote(new IntegerModel(-1), new AlteredNoteName(bNoteName, alteration));
		result = perfect4.apply(note, C2);
		assertEquals(expected,result);
		
		// P5(-1Gb) --> 0Cb
		note = result;
		bNoteName = new BasicNoteName("C");
		alteration = new FlatAlteration();
		expected = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		result = perfect4.apply(note, C2);
		assertEquals(expected,result);

		/* OTHERS */
		
		// P1(0C) --> 0C
		expected = new AbsoluteMusicNote(0,"C");
		note = new AbsoluteMusicNote(0,"C");
		result = perfect1.apply(note, C2);
		assertEquals(expected,result);
		
		// M3(0C) --> 0E
		note = new AbsoluteMusicNote(0,"C");
		expected = new AbsoluteMusicNote(0,"E");
		result = major3.apply(note, C2);
		assertEquals(expected,result);
		
		// m3(0C#) --> 0E
		bNoteName = new BasicNoteName("C");
		alteration = new SharpAlteration();
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		expected = new AbsoluteMusicNote(0,"E");
		result = minor3.apply(note, C2);
		assertEquals(expected,result);
		
		// P5(0C#) --> 0G#
		bNoteName = new BasicNoteName("C");
		alteration = new SharpAlteration();
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("G");
		expected =new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		result = perfect5.apply(note, C2);
		assertEquals(expected,result);
		
		// M3(0E#) --> 0G##
		bNoteName = new BasicNoteName("E");
		alteration = new SharpAlteration();
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("G");
		alteration = new DoubleSharpAlteration();
		expected =new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		result = major3.apply(note, C2);
		assertEquals(expected,result);
		
		
		// P5(0A#) --> 1E#
		bNoteName = new BasicNoteName("A");
		alteration = new SharpAlteration();
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("E");
		alteration = new SharpAlteration();
		expected =new AbsoluteMusicNote(new IntegerModel(1), new AlteredNoteName(bNoteName, alteration));
		result = perfect5.apply(note, C2);
		assertEquals(expected,result);
		
		// d5(0B) --> 1F
		note = new AbsoluteMusicNote(0, "B");
		expected =new AbsoluteMusicNote(1,"F");
		result = diminished5.apply(note, C2);
		assertEquals(expected,result);
		
		// d5(0Cb) --> 0Gbb
		bNoteName = new BasicNoteName("C");
		alteration = new FlatAlteration();
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("G");
		alteration = new DoubleFlatAlteration();
		expected =new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		result = diminished5.apply(note, C2);
		assertEquals(expected,result);
		
		// A3(0B#) --> E#
		bNoteName = new BasicNoteName("B");
		alteration = new SharpAlteration();
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("E");
		expected =new AbsoluteMusicNote(new IntegerModel(1), new AlteredNoteName(bNoteName, alteration));
		result = augmented3.apply(note, C2);
		assertEquals(expected,result);
		
		// AA3(0B#) --> E##
		bNoteName = new BasicNoteName("B");
		alteration = new SharpAlteration();
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("E");
		alteration = new DoubleSharpAlteration();
		expected =new AbsoluteMusicNote(new IntegerModel(1), new AlteredNoteName(bNoteName, alteration));
		result = doubleAugmented3.apply(note, C2);
		assertEquals(expected,result);
		
		// A14(0C) --> 2B#
		note = new AbsoluteMusicNote(0,"C");
		bNoteName = new BasicNoteName("B");
		alteration = new SharpAlteration();
		expected = new AbsoluteMusicNote(new IntegerModel(1), new AlteredNoteName(bNoteName, alteration));
		result = augmented14.apply(note, C2);
		assertEquals(expected,result);
		
		// AA14(0C) --> 1B##
		note = new AbsoluteMusicNote(0,"C");
		bNoteName = new BasicNoteName("B");
		alteration = new DoubleSharpAlteration();
		expected = new AbsoluteMusicNote(new IntegerModel(1), new AlteredNoteName(bNoteName, alteration));
		result = doubleAugmented14.apply(note, C2);
		assertEquals(expected,result);
		
		// AA14(0C#) --> 2C##
		bNoteName = new BasicNoteName("C");
		alteration = new SharpAlteration();
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("C");
		alteration = new DoubleSharpAlteration();
		expected = new AbsoluteMusicNote(new IntegerModel(2), new AlteredNoteName(bNoteName, alteration));
		result = doubleAugmented14.apply(note, C2);
		assertEquals(expected,result);
		
		// dd14(4A) --> 6Gb
		note = new AbsoluteMusicNote(4,"A");
		bNoteName = new BasicNoteName("G");
		alteration = new DoubleFlatAlteration();
		expected = new AbsoluteMusicNote(new IntegerModel(6), new AlteredNoteName(bNoteName, alteration));
		result = doubleDiminished14.apply(note, C2);
		assertEquals(expected,result);
		
		// P5(S0) --> S0
		expected = S0.clone();
		result = perfect5.apply(S0, C2);
		assertEquals(expected,result);
		
		// dd14(S0) --> S0
		expected = S0.clone();
		result = doubleDiminished14.apply(S0, C2);
		assertEquals(expected,result);
		
		
	}
	

}
