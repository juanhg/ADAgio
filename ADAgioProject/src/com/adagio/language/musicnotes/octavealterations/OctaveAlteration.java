package com.adagio.language.musicnotes.octavealterations;

import java.util.regex.Pattern;
import org.modelcc.IModel;

public abstract class OctaveAlteration implements IModel {
	
	/**
	 * The function calculates the number of octaves that increases or decreases
	 * the alteration
	 * @return An integer value. Positive to increase. Negative to decrease.
	 */
	public abstract int toInt();
}
