package com.adagio.language.bars.chords.intervals;
import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.Suffix;

import com.adagio.events.MusicEventListener;
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
	
	@Override
	/**
	 * Applies the interval to the note
	 * @param note
	 * @param data
	 * @return And absolute note with the result of apply the interval to the note. Always is 
	 * higher than the fundamental one. The optional flat of the note is set to true
	 */
	public AbsoluteMusicNote apply(MusicNote note, MusicEventListener listener){
		AbsoluteMusicNote result = super.apply(note, listener);
		result.setOptional(true);
		
		return result;
	}
}
