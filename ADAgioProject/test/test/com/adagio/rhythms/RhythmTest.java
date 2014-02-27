package test.com.adagio.rhythms;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import test.com.adagio.InitTest;

import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class RhythmTest extends InitTest {

	List<List<AbsoluteMusicNote>> chordsAsLists1, chordsAsLists2, chordsAsLists3, chordsAsLists4;
	
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
	}
	
	@Test
	public void selectNoteForVoiceTest() {
				
		assertEquals(E0, rhythm1.selectNoteforVoice(chordsAsLists1, 0));
		assertEquals(G0, rhythm1.selectNoteforVoice(chordsAsLists1, 1));
		assertEquals(B0, rhythm1.selectNoteforVoice(chordsAsLists1, 2));
		assertEquals(E0, rhythm1.selectNoteforVoice(chordsAsLists1, 3));
			
		assertEquals(C0, rhythm1.selectNoteforVoice(chordsAsLists2, 0));
		assertEquals(E0, rhythm1.selectNoteforVoice(chordsAsLists2, 1));
		assertEquals(G0, rhythm1.selectNoteforVoice(chordsAsLists2, 2));
		assertEquals(C0, rhythm1.selectNoteforVoice(chordsAsLists2, 3));
			
		assertEquals(C0, rhythm1.selectNoteforVoice(chordsAsLists3, 0));
		assertEquals(G0, rhythm1.selectNoteforVoice(chordsAsLists3, 1));
		assertEquals(B0, rhythm1.selectNoteforVoice(chordsAsLists3, 2));
		assertEquals(E0, rhythm1.selectNoteforVoice(chordsAsLists3, 3));
		
		assertEquals(C0, rhythm2.selectNoteforVoice(chordsAsLists3, 0));
		assertEquals(G0, rhythm2.selectNoteforVoice(chordsAsLists3, 1));
		assertEquals(B0, rhythm2.selectNoteforVoice(chordsAsLists3, 2));
		assertEquals(E0, rhythm2.selectNoteforVoice(chordsAsLists3, 3));
				
		assertEquals(C0, rhythm1.selectNoteforVoice(chordsAsLists4, 0));
		assertEquals(G0, rhythm1.selectNoteforVoice(chordsAsLists4, 1));
		assertEquals(B0, rhythm1.selectNoteforVoice(chordsAsLists4, 2));
		assertEquals(A0, rhythm1.selectNoteforVoice(chordsAsLists4, 3));
	}
	
	
	@Test
	public void applyRhythmTest() {
		List<List<AbsoluteMusicNote>> result;
		
		//[0E2][0S2, 0G4][0S2, 0B4][0S2., 0E4][0S1]
		result = rhythm1.apply(chordsAsLists1, time4_4);
		
		//[0E4][0S4, 0G8][0S4, 0B8][0S4., 0E8][0S2]
		result = rhythm1.apply(chordsAsLists1, time2_4);
		
		//[0C8][0S8, 0E16][0S8, 0G16][0S8., OC16][0S4]
		result = rhythm1.apply(chordsAsLists2, time1_4);
		
		//[0C2][0S2, 0G4][0S2, 0B4][0S2., 0E4][0S1]
		result = rhythm1.apply(chordsAsLists3, time4_4);
		
		//[0C2][0S4, 0G8][0S4, 0B8][0S4., 0E8][0S2]
		result = rhythm2.apply(chordsAsLists4, time2_4);
	}
	

}
