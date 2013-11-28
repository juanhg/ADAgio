package test.com.adagio.io.lilypond;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.types.IntegerModel;

import com.adagio.io.lilypond.LilyPondMusicPieceWriter;
import com.adagio.language.chords.Chord;
import com.adagio.language.chords.ChordIdentifier;
import com.adagio.language.chords.intervals.Interval;
import com.adagio.language.chords.intervals.OptionalInterval;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.AlteredNoteName;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.language.musicnotes.notealterations.DoubleFlatAlteration;
import com.adagio.language.musicnotes.notealterations.FlatAlteration;
import com.adagio.language.musicnotes.notealterations.SharpAlteration;

public class LilyPondMusicPieceWritterTest {
	 
	ChordIdentifier M, m, add2;
	Interval M3, m3, P1, P5, M2, optM3;
	AbsoluteMusicNote fundamental, bass;
	BasicNoteName bNoteName;
	Chord chord;
	Alteration alteration;
	LilyPondMusicPieceWriter listener;
	 
	@Before
	public void setUp() throws Exception {
		
		M = new ChordIdentifier("M");
		m = new ChordIdentifier("m");
		add2 = new ChordIdentifier("add2");
		M3 = new Interval("M",3);
		m3 = new Interval("m",3);
		P1 = new Interval("P",1);
		P5 = new Interval("P",5);
		M2 = new Interval("M",2);
		optM3 = new OptionalInterval("M",3);
		bass = null;
		fundamental = null;
		chord = null;
		bNoteName = null;
		alteration = null;
		listener = new LilyPondMusicPieceWriter();
		
		// DEFINE CHORD "M" NOTES P1 M3 P5
		// DEFINE CHORD "m" NOTES P1 m3 P5
		// DEFINE CHORD "add2" NOTES P1 M2 (M3) P5
		
		List<Interval> intervals = new ArrayList<Interval>();
		
		intervals.add(P1);
		intervals.add(m3);
		intervals.add(P5);
		listener.getChordsDB().put(m, intervals);
		
		intervals = new ArrayList<Interval>();
		intervals.add(P1);
		intervals.add(M3);
		intervals.add(P5);
		listener.getChordsDB().put(M, intervals);
		
		intervals = new ArrayList<Interval>();
		intervals.add(P1);
		intervals.add(M2);
		intervals.add(optM3);
		intervals.add(P5);
		listener.getChordsDB().put(add2, intervals);
	}
	
	@Test
	public void translateChordTest() {
		
		//0CM --> <c e g>
		fundamental = new AbsoluteMusicNote(0, "C");
		chord = new Chord(fundamental,M,bass);
		assertEquals("<c e g>", listener.translateChord(chord));
		
		//0Dm --> <d f a>
		fundamental = new AbsoluteMusicNote(0, "D");
		chord = new Chord(fundamental,m,bass);
		assertEquals("<d f a>", listener.translateChord(chord));
		
		//3E#M --> <eis''' gisis''' bis'''>
		bNoteName = new BasicNoteName("E");
		alteration = new SharpAlteration(true);
		fundamental = new AbsoluteMusicNote(new IntegerModel(3), new AlteredNoteName(bNoteName, alteration));
		chord = new Chord(fundamental,M,bass);
		assertEquals("<eis''' gisis''' bis'''>", listener.translateChord(chord));
		
		//2F#m -->  <fis'' a'' cis'''>
		bNoteName = new BasicNoteName("F");
		alteration = new SharpAlteration(true);
		fundamental = new AbsoluteMusicNote(new IntegerModel(2), new AlteredNoteName(bNoteName, alteration));
		chord = new Chord(fundamental,m,bass);
		assertEquals("<fis'' a'' cis'''>", listener.translateChord(chord));
		
		//2F#m/2A -->  <fis'' cis''' a'>
		bass = new AbsoluteMusicNote(2,"A");
		bNoteName = new BasicNoteName("F");
		alteration = new SharpAlteration(true);
		fundamental = new AbsoluteMusicNote(new IntegerModel(2), new AlteredNoteName(bNoteName, alteration));
		chord = new Chord(fundamental,m,bass);
		assertEquals("<fis'' cis''' a'>", listener.translateChord(chord));
		
		//4GbM/4Cbb -->   <ges'''' bes'''' des''''' ceses''''>
		bNoteName = new BasicNoteName("C");
		alteration = new DoubleFlatAlteration(true);
		bass = new AbsoluteMusicNote(new IntegerModel(4), new AlteredNoteName(bNoteName, alteration));
		bNoteName = new BasicNoteName("G");
		alteration = new FlatAlteration(true);
		fundamental = new AbsoluteMusicNote(new IntegerModel(4), new AlteredNoteName(bNoteName, alteration));
		chord = new Chord(fundamental,M,bass);
		assertEquals("<ges'''' bes'''' des''''' ceses''''>", listener.translateChord(chord));
	}

}
