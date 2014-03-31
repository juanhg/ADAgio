package com.adagio.language.statements;

import org.modelcc.IModel;
import org.modelcc.Separator;

public class Verse implements IModel {
	@Separator("\\-")
	SubVerse [] subVerses;
	
	@Override
	public String toString(){
		String composition = "";
		for(int i = 0; i < subVerses.length; i++){
			composition += subVerses[i];
			if(i != subVerses.length-1){
				composition += "-";
			}
		}
		return composition;
	}
	
}
