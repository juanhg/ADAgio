package com.adagio.language.definitions;

import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Prefix;
import org.modelcc.Separator;
import org.modelcc.Suffix;

import com.adagio.events.MusicEventListener;
import com.adagio.events.definitions.RhythmDefinitionEvent;
import com.adagio.language.rhythm.RhythmComponent;
import com.adagio.language.rhythm.RhythmIdentifier;


@Prefix({"(?i)Define", "( |\\n|\\r|\\t)+","(?i)Rhythm"})
public class RhythmDefinition extends Definition implements IModel{
	
	@Prefix({"( |\\n|\\r|\\t)+", "\""})
	@Suffix("\"")
	RhythmIdentifier identifier; 
	
	@Prefix("( |\\n|\\r|\\t)+")
	@Separator("( |\\n|\\r|\\t)+")
	@Multiplicity(minimum = 1)
	RhythmComponent components [];

	@Override
	public void run(MusicEventListener listener) {
		listener.rhythmDefinition(new RhythmDefinitionEvent(this, identifier, components));
	}
}
