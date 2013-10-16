package com.adagio.language.musicnotes;
import org.modelcc.*;

import com.adagio.language.RunData;

public abstract class MusicNote implements IModel {
	public abstract String toString(RunData data);
}
