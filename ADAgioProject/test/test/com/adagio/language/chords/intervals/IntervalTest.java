package test.com.adagio.language.chords.intervals;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.types.IntegerModel;

import com.adagio.events.MusicEventListener;
import com.adagio.io.lilypond.LilyPondMusicPieceWriter;
import com.adagio.language.bars.chords.intervals.Interval;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.AlteredNoteName;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.language.musicnotes.notealterations.DoubleFlatAlteration;
import com.adagio.language.musicnotes.notealterations.DoubleSharpAlteration;
import com.adagio.language.musicnotes.notealterations.FlatAlteration;
import com.adagio.language.musicnotes.notealterations.SharpAlteration;


public class IntervalTest {
	
	AbsoluteMusicNote result, note, expected;
	Alteration alteration;
	BasicNoteName bNoteName;
	MusicEventListener listener;
	
	Interval P1, P4, P5, P8;
	Interval d2, d3, d4, d5, d6, d7, d8;
	Interval A1, A2, A3, A4, A5, A6, A7, A14;
	Interval AA5, AA3, AA14;
	Interval dd7, dd5, dd14;
	Interval m2, m3, m6, m7;
	Interval M2, M3, M6, M7;
			
	@Before
	public void setUp() throws Exception {
		
		listener = new LilyPondMusicPieceWriter();
		
		P1 = new Interval("P",1);
		d2 = new Interval("d",2);
		m2 = new Interval("m",2);
		A1 = new Interval("A",1);
		M2 = new Interval("M",2);
		d3 = new Interval("d",3);
		m3 = new Interval("m",3);
		A2 = new Interval("A",2);
		M3 = new Interval("M",3);
		d4 = new Interval("d",4);
		P4 = new Interval("P",4);
		A3 = new Interval("A",3);
		d5 = new Interval("d",5);
		A4 = new Interval("A",4);
		P5 = new Interval("P",5);
		d6 = new Interval("d",6);
		m6 = new Interval("m",6);
		A5 = new Interval("A",5);
		M6 = new Interval("M",6);
		d7 = new Interval("d",7);
		m7 = new Interval("m",7);
		A6 = new Interval("A",6);
		M7 = new Interval("M",7);
		d8 = new Interval("d",8);
		P8 = new Interval("P",8);
		A7 = new Interval("A",7);
		AA5 = new Interval("AA",5);
		AA3 = new Interval("AA",3);
		dd7 = new Interval("dd",7);
		dd5 = new Interval("dd",5);
		A14 = new Interval("A",14);
		AA14 = new Interval("AA",14);
		dd14 = new Interval("dd", 14);
	}
	
	@Test
	public void validIntervalTest() {
		assertEquals(0, P1.semitones());
		assertEquals(0, d2.semitones());
		assertEquals(1, m2.semitones());
		assertEquals(1, A1.semitones());
		assertEquals(2, M2.semitones());
		assertEquals(2, d3.semitones());
		assertEquals(3, m3.semitones());
		assertEquals(3, A2.semitones());
		assertEquals(4, M3.semitones());
		assertEquals(4, d4.semitones());
		assertEquals(5, P4.semitones());
		assertEquals(5, A3.semitones());
		assertEquals(6, d5.semitones());
		assertEquals(6, A4.semitones());
		assertEquals(7, P5.semitones());
		assertEquals(7, d6.semitones());
		assertEquals(8, m6.semitones());
		assertEquals(8, A5.semitones());
		assertEquals(9, M6.semitones());
		assertEquals(9, d7.semitones());
		assertEquals(10, m7.semitones());
		assertEquals(10, A6.semitones());
		assertEquals(11, M7.semitones());
		assertEquals(11, d8.semitones());
		assertEquals(12, P8.semitones());
		assertEquals(12, A7.semitones());
		assertEquals(9, AA5.semitones());
		assertEquals(6, AA3.semitones());
		assertEquals(8, dd7.semitones());
		assertEquals(5, dd5.semitones());
	}
	
	@Test
	public void validApplyTest() 
	{
		
		/* CIRCLE OF FIFTHS (RIGHT) */
		
		// P5(-3C) --> -3G
		note = new AbsoluteMusicNote(-3,"C");
		expected = new AbsoluteMusicNote(-3,"G");
		result = P5.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// P5(-3G) --> -2D
		note = result;
		expected = new AbsoluteMusicNote(-2,"D");
		result = P5.Apply(note, listener);
		assertEquals(true, expected.equals(result));

		// P5(-2D) --> -2A
		note = result;
		expected = new AbsoluteMusicNote(-2,"A");
		result = P5.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// P5(-2A) --> -1E
		note = result;
		expected = new AbsoluteMusicNote(-1,"E");
		result = P5.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// P5(-1E) --> -1B
		note = result;
		expected = new AbsoluteMusicNote(-1,"B");
		result = P5.Apply(note, listener);
		assertEquals(true, expected.equals(result));	
		
		// P5(-1B) --> 0F#
		note = result;
		bNoteName = new BasicNoteName("F");
		alteration = new SharpAlteration(true);
		expected = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		result = P5.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// P5(0F#) --> 1C#
		note = result;
		bNoteName = new BasicNoteName("C");
		alteration = new SharpAlteration(true);
		expected = new AbsoluteMusicNote(new IntegerModel(1), new AlteredNoteName(bNoteName, alteration));
		result = P5.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		
		/* CIRCLE OF FIFTHS (LEFT) */
		
		// P4(-3C) --> -3F
		note = new AbsoluteMusicNote(-3,"C");
		expected = new AbsoluteMusicNote(-3,"F");
		result = P4.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// P5(-3F) --> -3Bb
		note = result;
		bNoteName = new BasicNoteName("B");
		alteration = new FlatAlteration(true);
		expected = new AbsoluteMusicNote(new IntegerModel(-3), new AlteredNoteName(bNoteName, alteration));
		result = P4.Apply(note, listener);
		assertEquals(true, expected.equals(result));

		// P5(-3Bb) --> -2Eb
		note = result;
		bNoteName = new BasicNoteName("E");
		alteration = new FlatAlteration(true);
		expected = new AbsoluteMusicNote(new IntegerModel(-2), new AlteredNoteName(bNoteName, alteration));
		result = P4.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// P5(-2Eb) --> -2Ab
		note = result;
		bNoteName = new BasicNoteName("A");
		alteration = new FlatAlteration(true);
		expected = new AbsoluteMusicNote(new IntegerModel(-2), new AlteredNoteName(bNoteName, alteration));
		result = P4.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// P5(-2Ab) --> -1Db
		note = result;
		bNoteName = new BasicNoteName("D");
		alteration = new FlatAlteration(true);
		expected = new AbsoluteMusicNote(new IntegerModel(-1), new AlteredNoteName(bNoteName, alteration));
		result = P4.Apply(note, listener);
		assertEquals(true, expected.equals(result));

		// P5(-1Db) --> -1Gb
		note = result;
		bNoteName = new BasicNoteName("G");
		alteration = new FlatAlteration(true);
		expected = new AbsoluteMusicNote(new IntegerModel(-1), new AlteredNoteName(bNoteName, alteration));
		result = P4.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// P5(-1Gb) --> 0Cb
		note = result;
		bNoteName = new BasicNoteName("C");
		alteration = new FlatAlteration(true);
		expected = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		result = P4.Apply(note, listener);
		assertEquals(true, expected.equals(result));

		/* OTHERS */
		
		// P1(0C) --> 0C
		expected = new AbsoluteMusicNote(0,"C");
		note = new AbsoluteMusicNote(0,"C");
		result = P1.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// M3(0C) --> 0E
		note = new AbsoluteMusicNote(0,"C");
		expected = new AbsoluteMusicNote(0,"E");
		result = M3.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// m3(0C#) --> 0E
		bNoteName = new BasicNoteName("C");
		alteration = new SharpAlteration(true);
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		expected = new AbsoluteMusicNote(0,"E");
		result = m3.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// P5(0C#) --> 0G#
		bNoteName = new BasicNoteName("C");
		alteration = new SharpAlteration(true);
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("G");
		expected =new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		result = P5.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// M3(0E#) --> 0G##
		bNoteName = new BasicNoteName("E");
		alteration = new SharpAlteration(true);
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("G");
		alteration = new DoubleSharpAlteration(true);
		expected =new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		result = M3.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		
		// P5(0A#) --> 1E#
		bNoteName = new BasicNoteName("A");
		alteration = new SharpAlteration(true);
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("E");
		alteration = new SharpAlteration(true);
		expected =new AbsoluteMusicNote(new IntegerModel(1), new AlteredNoteName(bNoteName, alteration));
		result = P5.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// d5(0B) --> 1F
		note = new AbsoluteMusicNote(0, "B");
		expected =new AbsoluteMusicNote(1,"F");
		result = d5.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// d5(0Cb) --> 0Gbb
		bNoteName = new BasicNoteName("C");
		alteration = new FlatAlteration(true);
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("G");
		alteration = new DoubleFlatAlteration(true);
		expected =new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		result = d5.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// A3(0B#) --> E#
		bNoteName = new BasicNoteName("B");
		alteration = new SharpAlteration(true);
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("E");
		expected =new AbsoluteMusicNote(new IntegerModel(1), new AlteredNoteName(bNoteName, alteration));
		result = A3.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// AA3(0B#) --> E##
		bNoteName = new BasicNoteName("B");
		alteration = new SharpAlteration(true);
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("E");
		alteration = new DoubleSharpAlteration(true);
		expected =new AbsoluteMusicNote(new IntegerModel(1), new AlteredNoteName(bNoteName, alteration));
		result = AA3.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// A14(0C) --> 2B#
		note = new AbsoluteMusicNote(0,"C");
		bNoteName = new BasicNoteName("B");
		alteration = new SharpAlteration(true);
		expected = new AbsoluteMusicNote(new IntegerModel(1), new AlteredNoteName(bNoteName, alteration));
		result = A14.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// AA14(0C) --> 1B##
		note = new AbsoluteMusicNote(0,"C");
		bNoteName = new BasicNoteName("B");
		alteration = new DoubleSharpAlteration(true);
		expected = new AbsoluteMusicNote(new IntegerModel(1), new AlteredNoteName(bNoteName, alteration));
		result = AA14.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// AA14(0C#) --> 2C##
		bNoteName = new BasicNoteName("C");
		alteration = new SharpAlteration(true);
		note = new AbsoluteMusicNote(new IntegerModel(0), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("C");
		alteration = new DoubleSharpAlteration(true);
		expected = new AbsoluteMusicNote(new IntegerModel(2), new AlteredNoteName(bNoteName, alteration));
		result = AA14.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
		// dd14(4A) --> 6Gb
		note = new AbsoluteMusicNote(4,"A");
		bNoteName = new BasicNoteName("G");
		alteration = new DoubleFlatAlteration(true);
		expected = new AbsoluteMusicNote(new IntegerModel(6), new AlteredNoteName(bNoteName, alteration));
		result = dd14.Apply(note, listener);
		assertEquals(true, expected.equals(result));
		
	}
	

}
