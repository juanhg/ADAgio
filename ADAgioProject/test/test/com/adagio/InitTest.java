package test.com.adagio;

import org.junit.Before;

import com.adagio.duration.Duration;
import com.adagio.instruments.Instrument;
import com.adagio.instruments.LimitedPolyphonicInstrument;
import com.adagio.instruments.MonophonicInstrument;
import com.adagio.instruments.PolyphonicInstrument;
import com.adagio.io.lilypond.LilyPondMusicPieceWriter;
import com.adagio.language.bars.chords.Chord;
import com.adagio.language.bars.chords.intervals.Interval;
import com.adagio.language.instruments.Register;
import com.adagio.language.instruments.Timbre;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.RelativeMusicNote;
import com.adagio.language.musicnotes.notealterations.DoubleFlatAlteration;
import com.adagio.language.musicnotes.notealterations.DoubleSharpAlteration;
import com.adagio.language.musicnotes.notealterations.FlatAlteration;
import com.adagio.language.musicnotes.notealterations.SharpAlteration;
import com.adagio.language.times.Time;

public class InitTest {
	
	//Listener
	public LilyPondMusicPieceWriter listener;
	
	//Times
	public Time time4_4, time2_4, time1_4, time2_1;
	
	// BasicNoteNames
	public BasicNoteName A, B, C, D, E, F, G, S;
	
	// AbsoluteMusicNotes
	public AbsoluteMusicNote A0, A1, A2, A3, A4, A5, A6, A7, A8, A100;
	public AbsoluteMusicNote B0, B1, B2, B3, B4, B5, B6, B7, B8;
	public AbsoluteMusicNote C0, C1, C2, C3, C4, C5, C6, C7, C8;
	public AbsoluteMusicNote D0, D1, D2, D3, D4, D5, D6, D7, D8;
	public AbsoluteMusicNote E0, E1, E2, E3, E4, E5, E6, E7, E8;
	public AbsoluteMusicNote F0, F1, F2, F3, F4, F5, F6, F7, F8;
	public AbsoluteMusicNote G0, G1, G2, G3, G4, G5, G6, G7, G8;
	public AbsoluteMusicNote Am1, Am2, Am5, Am100;
	public AbsoluteMusicNote Bm1, Bm4;
	public AbsoluteMusicNote Cm1, Cm3, Cm5;
	public AbsoluteMusicNote Dm1, Dm2;
	public AbsoluteMusicNote Em1;
	public AbsoluteMusicNote Fm3;
	public AbsoluteMusicNote Gm1, Gm3, Gm5;
	public AbsoluteMusicNote A5Sharp, A2Sharp, A3bb,  A4bb;
	public AbsoluteMusicNote B3Sharp, B4Sharp, B4Flat;
	public AbsoluteMusicNote E3Sharp;
	public AbsoluteMusicNote C1Sharp, C3Sharp, C4DoubleSharp, C4DoubleFlat;
	public AbsoluteMusicNote D5Sharp, D5Flat;
	public AbsoluteMusicNote F0Sharp, F2Sharp;
	public AbsoluteMusicNote G0Sharp, G1Sharp, G3DoubleSharp, G4Sharp, G4Flat;
	public AbsoluteMusicNote S0;
	
	//Relatives Music Notes
	public RelativeMusicNote SRelative;
	public RelativeMusicNote rA;
	
	// Intervals
	public Interval perfect1, perfect4, perfect5, perfect8;
	public Interval diminished2, diminished3, diminished4, diminished5, diminished6, diminished7, diminished8;
	public Interval augmented1, augmented2, augmented3, augmented4, augmented5, augmented6, augmented7, augmented14;
	public Interval doubleAugmented5, doubleAugmented3, doubleAugmented14;
	public Interval doubleDiminished7, doubleDiminished5, doubleDiminished14;
	public Interval minor2, minor3, minor6, minor7;
	public Interval major2, major3, major6, major7;
	
	// Chord
	public Chord silenceChord;
	
	// Registers
	public Register r1, r2, r3, r4, r5, r6;
	public Register[] maleVoiceR, femaleVoiceR, realPianoR, acousticGuitarR, fluteR, perfectInstrumentR;
	
	// Instruments
	public Instrument maleVoice, femaleVoice, realPiano, acousticGuitar, flute, polyInstrument, perfectInstrument;
	
	
	@Before
	  public void setUp() throws Exception {
		
		//Listener
		listener = new LilyPondMusicPieceWriter();
		
		//Times
		time4_4 = new Time(4, 4);
		time2_4 = new Time(2, 4);
		time1_4 = new Time(1, 4);
		time2_1 = new Time(2, 1);
		
		//BasicNoteNames
		A = new BasicNoteName("A");
		B = new BasicNoteName("B");
		C = new BasicNoteName("C");
		D = new BasicNoteName("D");
		E = new BasicNoteName("E");
		F = new BasicNoteName("F");
		G = new BasicNoteName("G");
		S = new BasicNoteName("S");
		
		// AbsoluteMusicNotes
		A0 = new AbsoluteMusicNote(0,"A");
		A1 = new AbsoluteMusicNote(1,"A"); 
		A2 = new AbsoluteMusicNote(2,"A"); 
		A3 = new AbsoluteMusicNote(3,"A"); 
		A4 = new AbsoluteMusicNote(4,"A"); 
		A5 = new AbsoluteMusicNote(5,"A"); 
		A6 = new AbsoluteMusicNote(6,"A"); 
		A7 = new AbsoluteMusicNote(7,"A"); 
		A8 = new AbsoluteMusicNote(8,"A"); 
		A100 = new AbsoluteMusicNote(100,"A");
		
		B0 = new AbsoluteMusicNote(0,"B");
		B1 = new AbsoluteMusicNote(1,"B"); 
		B2 = new AbsoluteMusicNote(2,"B"); 
		B3 = new AbsoluteMusicNote(3,"B"); 
		B4 = new AbsoluteMusicNote(4,"B"); 
		B5 = new AbsoluteMusicNote(5,"B"); 
		B6 = new AbsoluteMusicNote(6,"B"); 
		B7 = new AbsoluteMusicNote(7,"B"); 
		B8 = new AbsoluteMusicNote(8,"B"); 
		
		C0 = new AbsoluteMusicNote(0,"C");
		C1 = new AbsoluteMusicNote(1,"C"); 
		C2 = new AbsoluteMusicNote(2,"C"); 
		C3 = new AbsoluteMusicNote(3,"C"); 
		C4 = new AbsoluteMusicNote(4,"C"); 
		C5 = new AbsoluteMusicNote(5,"C"); 
		C6 = new AbsoluteMusicNote(6,"C"); 
		C7 = new AbsoluteMusicNote(7,"C"); 
		C8 = new AbsoluteMusicNote(8,"C"); 
		
		
		D0 = new AbsoluteMusicNote(0,"D");
		D1 = new AbsoluteMusicNote(1,"D"); 
		D2 = new AbsoluteMusicNote(2,"D"); 
		D3 = new AbsoluteMusicNote(3,"D"); 
		D4 = new AbsoluteMusicNote(4,"D"); 
		D5 = new AbsoluteMusicNote(5,"D"); 
		D6 = new AbsoluteMusicNote(6,"D"); 
		D7 = new AbsoluteMusicNote(7,"D"); 
		D8 = new AbsoluteMusicNote(8,"D");
		
		E0 = new AbsoluteMusicNote(0,"E");
		E1 = new AbsoluteMusicNote(1,"E"); 
		E2 = new AbsoluteMusicNote(2,"E"); 
		E3 = new AbsoluteMusicNote(3,"E"); 
		E4 = new AbsoluteMusicNote(4,"E"); 
		E5 = new AbsoluteMusicNote(5,"E"); 
		E6 = new AbsoluteMusicNote(6,"E"); 
		E7 = new AbsoluteMusicNote(7,"E"); 
		E8 = new AbsoluteMusicNote(8,"E");
		
		F0 = new AbsoluteMusicNote(0,"F");
		F1 = new AbsoluteMusicNote(1,"F"); 
		F2 = new AbsoluteMusicNote(2,"F"); 
		F3 = new AbsoluteMusicNote(3,"F"); 
		F4 = new AbsoluteMusicNote(4,"F"); 
		F5 = new AbsoluteMusicNote(5,"F"); 
		F6 = new AbsoluteMusicNote(6,"F"); 
		F7 = new AbsoluteMusicNote(7,"F"); 
		F8 = new AbsoluteMusicNote(8,"F");
		
		
		G0 = new AbsoluteMusicNote(0,"G");
		G1 = new AbsoluteMusicNote(1,"G"); 
		G2 = new AbsoluteMusicNote(2,"G"); 
		G3 = new AbsoluteMusicNote(3,"G"); 
		G4 = new AbsoluteMusicNote(4,"G"); 
		G5 = new AbsoluteMusicNote(5,"G"); 
		G6 = new AbsoluteMusicNote(6,"G"); 
		G7 = new AbsoluteMusicNote(7,"G"); 
		G8 = new AbsoluteMusicNote(8,"G");
		
		Am1 = new AbsoluteMusicNote(-1,"A");
		Am2 = new AbsoluteMusicNote(-2, "A");
		Am5 = new AbsoluteMusicNote(-5,"A");
		Am100 = new AbsoluteMusicNote(-100,"A");
		
		Bm1 = new AbsoluteMusicNote(-1, "B");
		Bm4 = new AbsoluteMusicNote(-4,"B");
		
		Cm1 = new AbsoluteMusicNote(-1,"C");
		Cm3 = new AbsoluteMusicNote(-3,"C");
		Cm5 = new AbsoluteMusicNote(-5,"C");

		
		Dm1 = new AbsoluteMusicNote(-1,"D");
		Dm2 = new AbsoluteMusicNote(-2,"D");
		
		Em1 = new AbsoluteMusicNote(-1, "E");
		
		Fm3 = new AbsoluteMusicNote(-3, "F");
		
		Gm1 = new AbsoluteMusicNote(-1, "G");
		Gm5 = new AbsoluteMusicNote(-5,"G");
		Gm3 = new AbsoluteMusicNote(-3, "G");
		
		A2Sharp = new AbsoluteMusicNote(2, "A", new SharpAlteration());
		A5Sharp = new AbsoluteMusicNote(5, "A", new SharpAlteration());
		A3bb = new AbsoluteMusicNote(3, "A", new DoubleFlatAlteration());
		A4bb = new AbsoluteMusicNote(4, "A", new DoubleFlatAlteration());
		B3Sharp = new AbsoluteMusicNote(3, "B", new SharpAlteration());
		B4Sharp = new AbsoluteMusicNote(4, "B", new SharpAlteration());
		B4Flat = new AbsoluteMusicNote(4, "B", new FlatAlteration());
		E3Sharp = new AbsoluteMusicNote(3, "E", new SharpAlteration());
		C1Sharp = new AbsoluteMusicNote(1, "C", new SharpAlteration());
		C3Sharp = new AbsoluteMusicNote(3, "C", new SharpAlteration());
		C4DoubleSharp  = new AbsoluteMusicNote(4, "C", new DoubleSharpAlteration());
		C4DoubleFlat  = new AbsoluteMusicNote(4, "C", new DoubleFlatAlteration());
		D5Sharp = new AbsoluteMusicNote(5, "D", new SharpAlteration());
		D5Flat = new AbsoluteMusicNote(5, "D", new FlatAlteration());
		F0Sharp = new AbsoluteMusicNote(0,"F", new SharpAlteration());
		F2Sharp = new AbsoluteMusicNote(2, "F", new SharpAlteration());
		G0Sharp = new AbsoluteMusicNote(0, "G", new SharpAlteration());
		G1Sharp = new AbsoluteMusicNote(1, "G", new SharpAlteration());
		G3DoubleSharp = new AbsoluteMusicNote(3, "G", new DoubleSharpAlteration());
		G4Sharp = new AbsoluteMusicNote(4, "G", new SharpAlteration());
		G4Flat = new AbsoluteMusicNote(4, "G", new FlatAlteration());
		
		S0 = AbsoluteMusicNote.genSilence(new Duration());
		
		//RelativeNotes
		SRelative = RelativeMusicNote.genSilence();
		rA = new RelativeMusicNote(A, null);
		
		// Intervals
		perfect1 = new Interval("P",1);
		diminished2 = new Interval("d",2);
		minor2 = new Interval("m",2);
		augmented1 = new Interval("A",1);
		major2 = new Interval("M",2);
		diminished3 = new Interval("d",3);
		minor3 = new Interval("m",3);
		augmented2 = new Interval("A",2);
		major3 = new Interval("M",3);
		diminished4 = new Interval("d",4);
		perfect4 = new Interval("P",4);
		augmented3 = new Interval("A",3);
		diminished5 = new Interval("d",5);
		augmented4 = new Interval("A",4);
		perfect5 = new Interval("P",5);
		diminished6 = new Interval("d",6);
		minor6 = new Interval("m",6);
		augmented5 = new Interval("A",5);
		major6 = new Interval("M",6);
		diminished7 = new Interval("d",7);
		minor7 = new Interval("m",7);
		augmented6 = new Interval("A",6);
		major7 = new Interval("M",7);
		diminished8 = new Interval("d",8);
		perfect8 = new Interval("P",8);
		augmented7 = new Interval("A",7);
		doubleAugmented5 = new Interval("AA",5);
		doubleAugmented3 = new Interval("AA",3);
		doubleDiminished7 = new Interval("dd",7);
		doubleDiminished5 = new Interval("dd",5);
		augmented14 = new Interval("A",14);
		doubleAugmented14 = new Interval("AA",14);
		doubleDiminished14 = new Interval("dd", 14);
		
		//Chords
		silenceChord = Chord.genSilence();
		
		// Registers
		r1 = new Register();
		r2 = new Register(Cm3, A3);
		r3 = new Register(Cm1, C4);
		r4 = new Register(A2, A5Sharp);
		r5 = new Register(A2,B2);
		r6 = new Register(Bm4,C4);
		
		// Instrument Registers
		maleVoiceR = new Register[1];
		maleVoiceR[0] = new Register(C2,C5);
		femaleVoiceR = new Register[1];
		femaleVoiceR[0] = new Register(C3, C6);
		fluteR = new Register[1];
		fluteR[0] = new Register(C0,C3);
		realPianoR = new Register[1];
		realPianoR[0] = new Register(A0,C8);
		acousticGuitarR = new Register[6];
		acousticGuitarR[0] = new Register(E0, G1);
		acousticGuitarR[1] = new Register(A0, C2);
		acousticGuitarR[2] = new Register(D1, F2);
		acousticGuitarR[3] = new Register(G1, A2Sharp);
		acousticGuitarR[4] = new Register(B1, D3);
		acousticGuitarR[5] = new Register(E2, G3);
		
		perfectInstrumentR = new Register[1];
		perfectInstrumentR[0] = new Register(Am100, A100);
		
		//Instruments
		maleVoice = new MonophonicInstrument(new Timbre("voice"), maleVoiceR);
		femaleVoice = new MonophonicInstrument(new Timbre("voice"), femaleVoiceR);
		flute = new MonophonicInstrument(new Timbre("flute"), fluteR);
		polyInstrument = new PolyphonicInstrument(new Timbre(), fluteR);
		realPiano = new PolyphonicInstrument(new Timbre("piano"), realPianoR);
		acousticGuitar = new LimitedPolyphonicInstrument(new Timbre("acousticguitar"), acousticGuitarR);
		perfectInstrument = new PolyphonicInstrument(new Timbre(), perfectInstrumentR);
	  }
}
