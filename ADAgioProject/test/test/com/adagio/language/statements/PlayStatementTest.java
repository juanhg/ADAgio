package test.com.adagio.language.statements;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import static org.modelcc.test.ModelAssert.*;

import com.adagio.io.lilypond.LilyPondMusicPieceWriter;
import com.adagio.language.bars.chords.Chord;
import com.adagio.language.bars.chords.ChordIdentifier;
import com.adagio.language.bars.chords.intervals.Interval;
import com.adagio.language.bars.chords.intervals.OptionalInterval;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.language.statements.PlayStatement;

public class PlayStatementTest {

	Model model;
	Parser<PlayStatement> parser;
	LilyPondMusicPieceWriter listener;

	ChordIdentifier M, m, add2,white;
	Interval M3, m3, P1, P5, M2, optM3;
	AbsoluteMusicNote fundamental, bass;
	BasicNoteName bNoteName;
	Chord chord;
	Alteration alteration;
	

	  @SuppressWarnings("unchecked")
	@Before
	  public void setUp() throws Exception {
	    model = JavaModelReader.read(PlayStatement.class);
	    parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	    listener = new LilyPondMusicPieceWriter();
	    
	    M = new ChordIdentifier("M");
		m = new ChordIdentifier("m");
		add2 = new ChordIdentifier("add2");
		white = new ChordIdentifier("");
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
		
		
		// DEFINE CHORD "M" NOTES P1 M3 P5
		// DEFINE CHORD "m" NOTES P1 m3 P5
		// DEFINE CHORD "add2" NOTES P1 M2 (M3) P5
		
		List<Interval> intervals = new ArrayList<Interval>();
		
		intervals.add(P1);
		intervals.add(m3);
		intervals.add(P5);
		listener.getChordsDB().addChord(m, intervals);
		
		intervals = new ArrayList<Interval>();
		intervals.add(P1);
		intervals.add(M3);
		intervals.add(P5);
		listener.getChordsDB().addChord(M, intervals);
		
		intervals = new ArrayList<Interval>();
		intervals.add(P1);
		intervals.add(M3);
		intervals.add(P5);
		listener.getChordsDB().addChord(white, intervals);
		
		intervals = new ArrayList<Interval>();
		intervals.add(P1);
		intervals.add(M2);
		intervals.add(optM3);
		intervals.add(P5);
		listener.getChordsDB().addChord(add2, intervals);
	
	  }
	
	@Test
	public void playSentenceValidtest() {
		assertAmbiguityFree(parser,"PLAY | 3D#m |");
		assertAmbiguityFree(parser,"PLAY | 3Dbm |");
		assertAmbiguityFree(parser,"PLAY | 3D#m# |");
		assertAmbiguityFree(parser,"PLAY | A#M |");
	}
	
	@Test
	public void playSentenceInvalidtest() {
		assertInvalid(parser,"PLAY");
	}
	
	  @Test
	  public void ChordReadTest1() throws Exception {
	    assertAmbiguityFree(parser,"PLAY | G Cm |"); // Nos garantiza que es válido y no hay ambigüedades
	    assertAmbiguityFree(parser, "PLAY | G |");
	    assertAmbiguityFree(parser, "PLAY | Gm |");
	    assertAmbiguityFree(parser, "PLAY | Cm |");
	    assertAmbiguityFree(parser, "PLAY | G C |");
	    assertAmbiguityFree(parser, "PLAY | Gm C |");
	    assertAmbiguityFree(parser, "PLAY | G Cm |");
	    assertAmbiguityFree(parser, "PLAY | G Cm |");
	    assertAmbiguityFree(parser, "PLAY | Bb S |");
	    
	  }
}
