package test.com.adagio.language.chords.intervals;

import static org.junit.Assert.*;
import org.junit.Test;
import com.adagio.language.chords.intervals.Interval;


public class IntervalTest {
	
	@Test
	public void validIntervalTest() {
		assertEquals(0, (new Interval("P",1)).semitones());
		assertEquals(0, (new Interval("d",2)).semitones());
		assertEquals(1, (new Interval("m",2)).semitones());
		assertEquals(1, (new Interval("A",1)).semitones());
		assertEquals(2, (new Interval("M",2)).semitones());
		assertEquals(2, (new Interval("d",3)).semitones());
		assertEquals(3, (new Interval("m",3)).semitones());
		assertEquals(3, (new Interval("A",2)).semitones());
		assertEquals(4, (new Interval("M",3)).semitones());
		assertEquals(4, (new Interval("d",4)).semitones());
		assertEquals(5, (new Interval("P",4)).semitones());
		assertEquals(5, (new Interval("A",3)).semitones());
		assertEquals(6, (new Interval("d",5)).semitones());
		assertEquals(6, (new Interval("A",4)).semitones());
		assertEquals(7, (new Interval("P",5)).semitones());
		assertEquals(7, (new Interval("d",6)).semitones());
		assertEquals(8, (new Interval("m",6)).semitones());
		assertEquals(8, (new Interval("A",5)).semitones());
		assertEquals(9, (new Interval("M",6)).semitones());
		assertEquals(9, (new Interval("d",7)).semitones());
		assertEquals(10, (new Interval("m",7)).semitones());
		assertEquals(10, (new Interval("A",6)).semitones());
		assertEquals(11, (new Interval("M",7)).semitones());
		assertEquals(11, (new Interval("d",8)).semitones());
		assertEquals(12, (new Interval("P",8)).semitones());
		assertEquals(12, (new Interval("A",7)).semitones());
		assertEquals(9, (new Interval("AA",5)).semitones());
		assertEquals(6, (new Interval("AA",3)).semitones());
		assertEquals(8, (new Interval("dd",7)).semitones());
		assertEquals(5, (new Interval("dd",5)).semitones());
	}

}
