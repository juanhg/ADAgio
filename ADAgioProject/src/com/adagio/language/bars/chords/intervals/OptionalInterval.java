package com.adagio.language.bars.chords.intervals;
import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.Suffix;

import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.MusicNote;

@Prefix("\\(")
@Suffix("\\)")
public class OptionalInterval extends Interval implements IModel {

	public OptionalInterval(String quality, int number) {
		super(quality, number);
	}
	
	public OptionalInterval() {
		super();
	}
	

	/**
	 * Applies the interval to the note
	 * @param note The note to be modified
	 * @param relative The relative note
	 * @return And absolute note with the result of apply the interval to the note. Always is 
	 * higher than the fundamental one. The optional flat of the note is set to true
	 */
	@Override
	public AbsoluteMusicNote apply(MusicNote note, AbsoluteMusicNote relative){
		AbsoluteMusicNote result = super.apply(note, relative);
		result.setOptional(true);
		
		return result;
	}
}
