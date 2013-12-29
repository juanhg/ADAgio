package com.adagio.language.instruments.features;

import org.modelcc.IModel;
import org.modelcc.Prefix;

import com.adagio.language.musicnotes.AbsoluteMusicNote;

public class Register implements IModel {

	AbsoluteMusicNote lowerNote;
	@Prefix("(?i)to")
	AbsoluteMusicNote higherNote;
	
	
	/**
	 * Checks if an AbsoluteNote belong to the register
	 * @param aNote AbsoluteNote to check
	 * @return True if the AbsoluteNote belong to the register. False in other case.
	 */
	public boolean belong(AbsoluteMusicNote aNote){
		//TODO complete this function
		return true;
	}
	
}
