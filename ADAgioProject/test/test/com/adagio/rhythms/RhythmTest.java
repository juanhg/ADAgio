package test.com.adagio.rhythms;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import test.com.adagio.InitTest;

import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class RhythmTest extends InitTest {

	public void setUp() throws Exception {
		super.setUp();
	}
	
	@SuppressWarnings("serial")
	@Test
	public void selectNoteForVoiceTest() {
		
		List<List<AbsoluteMusicNote>> chordsAsAbsoluteNotes  = new ArrayList<List<AbsoluteMusicNote>>();
		
		List<AbsoluteMusicNote> chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(E0);
			add(G0);
			add(B0);
		}};
		
		chordsAsAbsoluteNotes.add(chordAsList);
		
		assertEquals(E0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 0));
		assertEquals(G0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 1));
		assertEquals(B0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 2));
		assertEquals(E0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 3));
		
		chordsAsAbsoluteNotes.clear();
		
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(C0);
			add(E0);
			add(G0);
		}};
		
		chordsAsAbsoluteNotes.add(chordAsList);
		
		assertEquals(C0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 0));
		assertEquals(E0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 1));
		assertEquals(G0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 2));
		assertEquals(C0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 3));
		
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(E0);
			add(G0);
			add(B0);
		}};
		
		chordsAsAbsoluteNotes.add(chordAsList);
		
		assertEquals(C0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 0));
		assertEquals(G0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 1));
		assertEquals(B0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 2));
		assertEquals(E0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 3));
		
		assertEquals(C0, rhythm2.selectNoteforVoice(chordsAsAbsoluteNotes, 0));
		assertEquals(G0, rhythm2.selectNoteforVoice(chordsAsAbsoluteNotes, 1));
		assertEquals(B0, rhythm2.selectNoteforVoice(chordsAsAbsoluteNotes, 2));
		assertEquals(E0, rhythm2.selectNoteforVoice(chordsAsAbsoluteNotes, 3));
		
		
		chordAsList = new ArrayList<AbsoluteMusicNote>(){{
			add(A0);
			add(G0);
			add(F0);
		}};
		
		chordsAsAbsoluteNotes.add(chordAsList);
		
		assertEquals(C0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 0));
		assertEquals(G0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 1));
		assertEquals(B0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 2));
		assertEquals(A0, rhythm1.selectNoteforVoice(chordsAsAbsoluteNotes, 3));
		
	}

}
