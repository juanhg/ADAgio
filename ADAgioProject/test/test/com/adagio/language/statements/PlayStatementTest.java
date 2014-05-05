package test.com.adagio.language.statements;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;
import static org.modelcc.test.ModelAssert.assertInvalid;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import test.com.adagio.InitTest;

import com.adagio.io.lilypond.LilyPondMusicPieceWriter;
import com.adagio.language.bars.chords.Chord;
import com.adagio.language.bars.chords.ChordIdentifier;
import com.adagio.language.bars.chords.intervals.Interval;
import com.adagio.language.bars.chords.intervals.OptionalInterval;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.language.statements.simple.PlayStatement;

public class PlayStatementTest extends InitTest {

	Model model;
	Parser<PlayStatement> parser;
	LilyPondMusicPieceWriter listener;

	ChordIdentifier M, m, add2,white;
	Interval optM3;
	AbsoluteMusicNote fundamental, bass;
	BasicNoteName bNoteName;
	Chord chord;
	Alteration alteration;
	

	  @SuppressWarnings("unchecked")
	@Before
	  public void setUp() throws Exception {
		super.setUp();
	    model = JavaModelReader.read(PlayStatement.class);
	    parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	    listener = new LilyPondMusicPieceWriter();
	    
	    M = new ChordIdentifier("M");
		m = new ChordIdentifier("m");
		add2 = new ChordIdentifier("add2");
		white = new ChordIdentifier("");
		optM3 = new OptionalInterval("M",3);
		bass = null;
		fundamental = null;
		chord = null;
		bNoteName = null;
		alteration = null;
		
		List<Interval> intervals = new ArrayList<Interval>();
		
		intervals.add(perfect1);
		intervals.add(minor3);
		intervals.add(perfect5);
		listener.getChordsDB().addChord(m, intervals);
		
		intervals = new ArrayList<Interval>();
		intervals.add(perfect1);
		intervals.add(major3);
		intervals.add(perfect5);
		listener.getChordsDB().addChord(M, intervals);
		
		intervals = new ArrayList<Interval>();
		intervals.add(perfect1);
		intervals.add(major3);
		intervals.add(perfect5);
		listener.getChordsDB().addChord(white, intervals);
		
		intervals = new ArrayList<Interval>();
		intervals.add(perfect1);
		intervals.add(major2);
		intervals.add(optM3);
		intervals.add(perfect5);
		listener.getChordsDB().addChord(add2, intervals);
	
	  }
	
	@Test
	public void playSentenceValidtest() {
		assertAmbiguityFree(parser,"PLAY | 3D#m |");
		assertAmbiguityFree(parser,"PLAY  3D#m |");
		assertAmbiguityFree(parser,"PLAY | 3D#m ");
		assertAmbiguityFree(parser,"PLAY 3D#m");
		assertAmbiguityFree(parser,"PLAY | 3Dbm |");
		assertAmbiguityFree(parser,"PLAY | 3D#m# |");
		assertAmbiguityFree(parser,"PLAY | A#M |");
		assertAmbiguityFree(parser,"PLAY | A B | C "
				                 + "melody voz  C4 | B2 - 4A4 |");
		assertAmbiguityFree(parser,"PLAY | A B | C "
                					+ "melody voz  C4 | B2 - 4A4 |"
									+ "lyrics voz | hola - amigo | mio");
		assertAmbiguityFree(parser,"PLAY | A B | C "
				+ "melody voz  C4 | B2 - 4A4 |"
				+ "lyrics voz | hola - amigo | mio "
				+ "melody voz3  C4 | B2 - 4A4 |");
		assertAmbiguityFree(parser,"PLAY | A B | C "
				+ "melody voz1  C4 | B2 - 4A4 |"
				+ "melody voz3  C4 | B2 - 4A4 |"
				+ " lyrics voz1 | hola - amigo | mio"
				+ " lyrics voz3 | hola - amigo | mio");
		assertAmbiguityFree(parser,"PLAY | A B | C "
				+ "melody voz  C4 | B2 - 4A4 |"
				+ "lyrics voz | hola - amigo | mio "
				+ "melody voz3  C4 | B2 - 4A4 |"
				+ "melody voz4  C4 | B2 - 4A4 |"
				+ "melody voz5  C4 | B2 - 4A4 |");
		
		assertAmbiguityFree(parser,"play         | Em          | G        | D         | C\n"
			+"melody flute | E2 - E2     | G2 - G2  | D2 - D2   | C2 - C2 |\n"
			+"Lyrics flute |she - fills| my - bed | with - ga | so - line");
		
		assertAmbiguityFree(parser,"play         | Em          | G        | D         | C\n"
				+"melody flute | E2 - E2     | G2 - G2  | D2 - D2   | C2 - C2 |\n"
				+"Lyrics flute |she - fills| my - bed | with - ga | so - line|");
		assertAmbiguityFree(parser,"play         | Em          | G        | D         | C\n"
				+"melody flute |E2 - E2 | G2 - G2  | D2 - D2   | C2 - C2 |\n"
				+"Lyrics          flute |she - fills| my - bed | with - ga | so - line");
		
	}
	
	@Test
	public void playSentenceInvalidtest() {
		assertInvalid(parser,"PLAY | A B | C "
				+ "melody voz1  C4 | B2 - 4A4 |"
				+ "melody voz3  C4 | B2 - 4A4 |"
				+ " lyrics voz1 | hola - amigo | mio"
				+ " lyrics voz2 | hola - amigo | mio");
		
		assertInvalid(parser,"PLAY | A B | C "
				+ "melody voz3  C4 | B2 - 4A4 |"
				+ "melody voz3  C4 | B2 - 4A4 |"
				+ " lyrics voz1 | hola - amigo | mio"
				+ " lyrics voz3 | hola - amigo | mio");
		assertInvalid(parser,"PLAY | A B | C "
				+ "melody voz1  C4 | B2 - 4A4 |"
				+ "melody voz2  C4 | B2 - 4A4 |"
				+ " lyrics voz1 | hola - amigo | mio"
				+ " lyrics voz1 | hola - amigo | mio");
		assertInvalid(parser,"PLAY | A B | C "
				+ "melody voz1  C4 | B2 - 4A4 |"
				+ "melody voz2  C4 | B2 - 4A4 |"
				+ " lyrics voz1 | hola - amigo | mio"
				+ " lyrics voz3 | hola - amigo | mio");
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
