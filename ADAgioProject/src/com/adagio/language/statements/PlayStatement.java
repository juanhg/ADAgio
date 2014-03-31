package com.adagio.language.statements;
import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Prefix;
import org.modelcc.Separator;
import org.modelcc.Suffix;

import com.adagio.events.MusicEventListener;
import com.adagio.events.statements.MusicPlayStatementEvent;
import com.adagio.language.bars.Bar;

@Prefix({"(?i)play"})
public class PlayStatement extends Statement implements IModel {

	@Prefix("( |\\n|\\r|\\t)+\\|?")
	@Suffix("\\|?")
	@Separator("\\|")
	@Multiplicity(minimum = 1)
	Bar [] bars;
	
	@Override
	/**
	 * Takes chords, transforms the fundamental note in AbsoluteNote, and stores them
	 * in the attribute "chords". Each absolute-fundamental-note updates the Relative note.
	 */
	public void run(MusicEventListener listener) {
		
		if(bars != null){
			for(int i = 0; i <bars.length; i++){
				listener.musicPlay(new MusicPlayStatementEvent(this, bars[i]));
			}
		}
	}

}
