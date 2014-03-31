package test.com.adagio.rhythms;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import test.com.adagio.InitTest;

import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.rhythm.RhythmComponent;
import com.adagio.structures.Duration;
import com.adagio.structures.Rhythm;

public class RhythmTest extends InitTest {

	List<List<AbsoluteMusicNote>> chordsAsLists1, chordsAsLists2, chordsAsLists3, 
	chordsAsLists4, chordsAsLists5, chordsAsLists6, chordsAsLists7;
	
	public RhythmComponent rComp0, rComp1, rComp2, rComp3, rComp4, rComp5, rComp6, 
	                       rComp7, rComp8, rComp9, rComp10;
	public List<RhythmComponent> listRComp;
	public Rhythm rhythm1, rhythm2, rhythm3, rhythm4;
	
	//[Note1 0 to 0.33] [Note2 0.33 to 0.66] [Note3 0.66 to 1]
	public Rhythm rhythm5;
	
	//[Note1 0 to 0.5] [Note2 0.5 to 1] [Note3 0 to 0.5]
	public Rhythm rhythm6;
	
	//[Note1 0 to 0.5][Note2 0.5 to 0.75][rA 0.5 to 1][Note3 0.5 to 0.75]
	public Rhythm rhythm7;
	
	//[rA 0.5 to 1][Note1 0 to 0.5][Note2 0.5 to 0.75][Note3 0.5 to 0.75]
	public Rhythm rhythm8;
	
	final AbsoluteMusicNote A8 = new AbsoluteMusicNote(0, "A", new Duration(8,0));
	final AbsoluteMusicNote A5_2 = new AbsoluteMusicNote(5, "A", new Duration(2,0));
	final AbsoluteMusicNote A1_2 = new AbsoluteMusicNote(1, "A", new Duration(2,0));
	final AbsoluteMusicNote A1_8 = new AbsoluteMusicNote(1, "A", new Duration(8,0));
	
	final AbsoluteMusicNote E1 = new AbsoluteMusicNote(0, "E", new Duration(1,0));
	final AbsoluteMusicNote E2 = new AbsoluteMusicNote(0, "E", new Duration(2,0));
	final AbsoluteMusicNote E4 = new AbsoluteMusicNote(0, "E", new Duration(4,0));
	final AbsoluteMusicNote E1_4 = new AbsoluteMusicNote(1, "E", new Duration(4,0));
	final AbsoluteMusicNote E1_2 = new AbsoluteMusicNote(1, "E", new Duration(2,0));
	final AbsoluteMusicNote E4l = new AbsoluteMusicNote(0, "E", new Duration(4,0),true);
	final AbsoluteMusicNote E8 = new AbsoluteMusicNote(0, "E", new Duration(8,0));
	final AbsoluteMusicNote E1_8 = new AbsoluteMusicNote(1, "E", new Duration(8,0));
	final AbsoluteMusicNote E16l = new AbsoluteMusicNote(0, "E", new Duration(16,0), true);
	final AbsoluteMusicNote E64 = new AbsoluteMusicNote(0, "E", new Duration(64,0));
	
	final AbsoluteMusicNote S4p = new AbsoluteMusicNote(0, "S", new Duration(4,1));
	final AbsoluteMusicNote S4 = new AbsoluteMusicNote(0, "S", new Duration(4,0));
	final AbsoluteMusicNote S2 = new AbsoluteMusicNote(0, "S", new Duration(2,0));
	final AbsoluteMusicNote S1 = new AbsoluteMusicNote(0, "S", new Duration(1,0));
	final AbsoluteMusicNote S1p = new AbsoluteMusicNote(0, "S", new Duration(1,1));
	final AbsoluteMusicNote S1ppl = new AbsoluteMusicNote(0, "S", new Duration(1,2),true);
	final AbsoluteMusicNote S1l = new AbsoluteMusicNote(0, "S", new Duration(1,0),true);
	
	final AbsoluteMusicNote S2p = new AbsoluteMusicNote(0, "S", new Duration(2,1));
	
	final AbsoluteMusicNote G2 = new AbsoluteMusicNote(0, "G", new Duration(2,0));
	final AbsoluteMusicNote G2opt = new AbsoluteMusicNote(0, "G", new Duration(2,0), false, true);
	final AbsoluteMusicNote G4 = new AbsoluteMusicNote(0, "G", new Duration(4,0));
	final AbsoluteMusicNote G8 = new AbsoluteMusicNote(0, "G", new Duration(8,0));
	
	final AbsoluteMusicNote B8 = new AbsoluteMusicNote(0, "B", new Duration(8,0));
	final AbsoluteMusicNote B4 = new AbsoluteMusicNote(0, "B", new Duration(4,0));
	final AbsoluteMusicNote B2 = new AbsoluteMusicNote(0, "B", new Duration(2,0));
	final AbsoluteMusicNote B2opt = new AbsoluteMusicNote(0, "B", new Duration(2,0), false, true);
	
	final AbsoluteMusicNote C4 = new AbsoluteMusicNote(0, "C", new Duration(4,0));
	final AbsoluteMusicNote C2 = new AbsoluteMusicNote(0, "C", new Duration(2,0));
	
	
	@SuppressWarnings("serial")
	public void setUp() throws Exception {
		super.setUp();
		
		chordsAsLists1  = new ArrayList<List<AbsoluteMusicNote>>();
		List<AbsoluteMusicNote> chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(E0);
			add(G0);
			add(B0);
		}};
		chordsAsLists1.add(chordAsList);
		
		chordsAsLists2  = new ArrayList<List<AbsoluteMusicNote>>();
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(C0);
			add(E0);
			add(G0);
		}};
		chordsAsLists2.add(chordAsList);
		
		chordsAsLists3  = new ArrayList<List<AbsoluteMusicNote>>();
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(C0);
			add(E0);
			add(G0);
		}};
		chordsAsLists3.add(chordAsList);
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(E0);
			add(G0);
			add(B0);
		}};
		chordsAsLists3.add(chordAsList);
		
		chordsAsLists4  = new ArrayList<List<AbsoluteMusicNote>>();
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(C0);
			add(E0);
			add(G0);
		}};
		chordsAsLists4.add(chordAsList);
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(E0);
			add(G0);
			add(B0);
		}};
		chordsAsLists4.add(chordAsList);
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(A0);
			add(G0);
			add(F0);
		}};
		chordsAsLists4.add(chordAsList);
		
		chordsAsLists5  = new ArrayList<List<AbsoluteMusicNote>>();
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(C0);
			add(E0);
			add(G0);
		}};
		chordsAsLists5.add(chordAsList);
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(G0);
			add(B0);
			add(D0);
		}};
		chordsAsLists5.add(chordAsList);
		
		chordsAsLists6  = new ArrayList<List<AbsoluteMusicNote>>();
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(C0);
			add(E0);
			add(G2opt);
		}};
		chordsAsLists6.add(chordAsList);
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(E0);
			add(G0);
			add(B2opt);
		}};
		chordsAsLists6.add(chordAsList);
		
		chordsAsLists7  = new ArrayList<List<AbsoluteMusicNote>>();
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(C5);
			add(E5);
			add(G5);
		}};
		chordsAsLists7.add(chordAsList);
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(E5);
			add(G5);
			add(B5);
		}};
		chordsAsLists7.add(chordAsList);
		
		rComp0 = new RhythmComponent(1, 0, 1);
		rComp1 = new RhythmComponent(1, 0, 0.50);
		rComp2 = new RhythmComponent(2, 0.50, 0.75);
		rComp3 = new RhythmComponent(3, 0.50, 0.75);
		rComp4 = new RhythmComponent(4, 0.75, 1);
		rComp5 = new RhythmComponent(2, 0.5, 1);
		rComp6 = new RhythmComponent(A5, 0.5, 1);
		rComp7 = new RhythmComponent(1, 0, 0.33);
		rComp8 = new RhythmComponent(2, 0.33, 0.66);
		rComp9 = new RhythmComponent(3, 0.66, 1);
		rComp10 = new RhythmComponent(rA, 0, 1);
				
		listRComp = new ArrayList<RhythmComponent>(){{
			add(rComp1);
			add(rComp2);
			add(rComp3);
			add(rComp4);
		}};
		
		rhythm1 = new Rhythm(listRComp);
		
		listRComp = new ArrayList<RhythmComponent>(){{
			add(rComp0);
			add(rComp2);
			add(rComp3);
			add(rComp4);
		}};
		
		rhythm2 = new Rhythm(listRComp);
		
		listRComp = new ArrayList<RhythmComponent>(){{
			add(rComp1);
			add(rComp5);
		}};
	
		rhythm3 = new Rhythm(listRComp);
		
		listRComp = new ArrayList<RhythmComponent>(){{
			add(rComp1);
			add(rComp2);
			add(rComp6);
			add(rComp3);
		}};
	
		rhythm4 = new Rhythm(listRComp);
		
		//Rhythm5 --> [Note1 0 to 0.33] [Note2 0.33 to 0.66] [Note3 0.66 to 1]
				
		listRComp = new ArrayList<RhythmComponent>(){{
			add(rComp7);
			add(rComp8);
			add(rComp9);
		}};
		
		rhythm5 = new Rhythm(listRComp);
		
		//Rhythm6 --> [Note1 0 to 0.5] [Note2 0.5 to 1] [Note3 0 to 0.5]
		
		listRComp = new ArrayList<RhythmComponent>(){{
			add(rComp1);
			add(rComp5);
			add(rComp1);
		}};
		
		rhythm6 = new Rhythm(listRComp);
		
		// Rhythm7 --> [Note1 0 to 0.5][Note2 0.5 to 0.75][rA 0.5 to 1][Note3 0.5 to 0.75]
		
		listRComp = new ArrayList<RhythmComponent>(){{
			add(rComp1);
			add(rComp2);
			add(rComp10);
			add(rComp3);
		}};
		
		rhythm7 = new Rhythm(listRComp);
		
		// Rhythm8 --> [rA 0.5 to 1][Note1 0 to 0.5][Note2 0.5 to 0.75][Note3 0.5 to 0.75]
		
		listRComp = new ArrayList<RhythmComponent>(){{
			add(rComp10);
			add(rComp1);
			add(rComp2);
			add(rComp3);
		}};
		
		rhythm8 = new Rhythm(listRComp);
	}
	
	
	@Test
	public void genLigaturedNotesTest() {
		List<AbsoluteMusicNote> result;
		
		result = Rhythm.genLigaturedNotes(A1, 8);
		result = Rhythm.genLigaturedNotes(A1, 16);
		result = Rhythm.genLigaturedNotes(A1, 3.3);
	}
	
		
	@Test
	public void assignNotesTest() {
		Map<Integer, List<AbsoluteMusicNote>> result;
		
		//[0C, 0E, 0G] --> Note1 (0C, 0G) Note2 (0E)
		result = rhythm3.assignNotes(chordsAsLists2.get(0), realPiano);
		
		//[0E, 0G, 0B] --> Note1 (0E, 0B) Note2 (0G)
		result = rhythm3.assignNotes(chordsAsLists1.get(0), realPiano);
		
		//[0C, 0E, 0G] --> Note1 (0C) Note2 (0E) Note3 (0G) Note4 (OC)
		result = rhythm1.assignNotes(chordsAsLists2.get(0), perfectInstrument);
	}
	
	@SuppressWarnings("serial")
	@Test
	public void applyRhythmTest() {
		List<List<AbsoluteMusicNote>> result; 
		List<List<AbsoluteMusicNote>> expected = new ArrayList<List<AbsoluteMusicNote>>();
		List<AbsoluteMusicNote> voice;
				
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(E2);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S2);
			add(G4);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S2);
			add(B4);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S2p);
			add(E1_4);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S1);
		}};
		expected.add(voice);
		
		//[0E2][0S2, 0G4][0S2, 0B4][0S2., 1E4][0S1]
		result = rhythm1.apply(chordsAsLists1, realPiano, time4_4, C2);
		assertEquals(expected, result);
		expected.clear();
		
		
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(C4);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S4);
			add(G8);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S4);
			add(B8);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S4p);
			add(E1_8);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S2);
		}};
		expected.add(voice);
				

		//[0C4][0S4, 0G8][0S4, 0B8][0S4., 1E8][0S2]
		result = rhythm1.apply(chordsAsLists3, realPiano, time2_4, C2);
		assertEquals(expected, result );
		expected.clear();
		
		
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(C2);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S4);
			add(G8);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S4);
			add(B8);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S4p);
			add(A1_8);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S2);
		}};
		expected.add(voice);
		
		//[0C2][0S4, 0G8][0S4, 0B8][0S4., 1A8][0S2]
		result = rhythm2.apply(chordsAsLists4, realPiano, time2_4, C2);
		assertEquals(expected, result);
		expected.clear();
		
		
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(C2);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(G2);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S2);
			add(B2);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S1);
		}};
		expected.add(voice);

		//[0C2][0G2][0S2, 0B2][0S1]
		result = rhythm3.apply(chordsAsLists5, realPiano, time4_4, C2);
		assertEquals(expected, result);
		expected.clear();
		
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(E1);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S1);
			add(G2);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S1);
			add(B2);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S1p);
			add(E1_2);
		}};
		expected.add(voice);
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S1ppl);
			add(S4);
		}};
		expected.add(voice);
		
		//[0E1][0S1, 0G2][0S1, 0B2][0S1., 1E2][0S1~, 0S1]
		result = rhythm1.apply(chordsAsLists1, realPiano, time2_1, C2);
		assertEquals(expected, result);
		expected.clear();

		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(E4l);
			add(E16l);
			add(E64);
		}};
		
		//[[0E4~, 0E16~, 0E64], [0S4~, 0S16~, 0S64, 0G4~, 0G16~, 0G64], [0S2~, 0S8~, 0S32, 0B4~, 0B16~, 0B64..], [0S1]]
		result = rhythm5.apply(chordsAsLists1, realPiano, time4_4, C2);
		assertEquals(voice, result.get(0));
		voice.clear();
		
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(S2);
			add(A5_2);
		}};
		
		//[0C2][0S2, 0E4][0S2, 5A2][0S2, 0G4][S1]
		result = rhythm4.apply(chordsAsLists2, realPiano, time4_4, C2);
		assertEquals(voice, result.get(2));
		voice.clear();
		
		//[[0C2], [0G2], [0S2, 0G2], [0C2], [0G2], [0S1]]
		result = rhythm6.apply(chordsAsLists3, realPiano, time4_4, C2);
		assertEquals(result.get(0), result.get(3));
		assertEquals(6,result.size());
		
		//Checking behavior with Optional-notes (Must delete it)
		result = rhythm6.apply(chordsAsLists6, realPiano, time4_4, C2);
		assertEquals(result.get(0), result.get(2));
		assertEquals(4,result.size());
		
		
		//Checking behavior with Optional-notes (Must use it)
		result = rhythm1.apply(chordsAsLists6, realPiano, time4_4, C2);
		assertEquals(5,result.size());
		
		//Checking behavior with relativeComponents
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(A5_2);
		}};
		
		//[[5C4], [0S4, 5G8], [5A2], [0S4, 5B8], [0S2]]
		result = rhythm7.apply(chordsAsLists7, realPiano, time2_4, C2);
		assertEquals(voice,result.get(2));
		voice.clear();
		
		voice = new ArrayList<AbsoluteMusicNote>(){{
			add(A1_2);
		}};
		
		//[[5C4], [0S4, 5G8], [5A2], [0S4, 5B8], [0S2]]
		result = rhythm8.apply(chordsAsLists7, realPiano, time2_4, super.C2);
		assertEquals(voice,result.get(0));
		voice.clear();
		
		//[0E4][0S4, 0G8][0S4, 0B8][0S4., 0E8][0S2]
		result = rhythm1.apply(chordsAsLists1, realPiano, time2_4, C2);
		
		//[0C8][0S8, 0E16][0S8, 0G16][0S8., OC16][0S4]
		result = rhythm1.apply(chordsAsLists2, realPiano, time1_4, C2);
	}
}
