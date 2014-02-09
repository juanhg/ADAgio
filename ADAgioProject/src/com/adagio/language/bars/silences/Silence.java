package com.adagio.language.bars.silences;

import org.modelcc.IModel;
import org.modelcc.Pattern;
import org.modelcc.Value;

import com.adagio.events.MusicEventListener;
import com.adagio.language.bars.BarItem;
import com.adagio.language.bars.chords.Chord;

@Pattern(regExp = "_|S|s|r|R")
public class Silence extends BarItem implements IModel {
	@Value
	String value;	
}
