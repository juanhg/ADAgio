package com.adagio.language.bars;

import org.modelcc.IModel;

import com.adagio.events.MusicEventListener;
import com.adagio.language.bars.chords.Chord;

public abstract class BarItem implements IModel {
	BarItemDuration duration;

	public BarItemDuration getDuration() {
		return duration;
	}

	public void setDuration(BarItemDuration duration) {
		this.duration = duration;
	}
}
