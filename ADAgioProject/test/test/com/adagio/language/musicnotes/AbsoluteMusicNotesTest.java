package test.com.adagio.language.musicnotes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class AbsoluteMusicNotesTest {

	@Test
	public void isHigherTest() {
		assertEquals(true, (new AbsoluteMusicNote(3, "A")).isHigher(new AbsoluteMusicNote(2, "A")));
		assertEquals(false, (new AbsoluteMusicNote(3, "A")).isHigher(new AbsoluteMusicNote(3, "B")));
		assertEquals(false, (new AbsoluteMusicNote(-1, "A")).isHigher(new AbsoluteMusicNote(4, "C")));
		assertEquals(true, (new AbsoluteMusicNote(-1, "D")).isHigher(new AbsoluteMusicNote(-1, "C")));
		assertEquals(false, (new AbsoluteMusicNote(-1, "C")).isHigher(new AbsoluteMusicNote(-1, "G")));
		assertEquals(true, (new AbsoluteMusicNote(3, "A")).isHigher(new AbsoluteMusicNote(3, "C")));
	}

}
