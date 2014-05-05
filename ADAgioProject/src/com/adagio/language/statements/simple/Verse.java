package com.adagio.language.statements.simple;

import org.modelcc.IModel;
import org.modelcc.Separator;

public class Verse implements IModel {
	@Separator("\\-")
	SubVerse [] subVerses;
	
	public SubVerse[] getSubVerses() {
		return subVerses;
	}

	@Override
	public String toString(){
		String composition = "";
		for(int i = 0; i < subVerses.length; i++){
			if(subVerses[i] != null){
				composition += subVerses[i];
				if(i != subVerses.length-1){
					composition += "-";
				}
			}
		}
		return composition;
	}

}
