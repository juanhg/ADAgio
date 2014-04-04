package com.adagio.language.bars;

import org.modelcc.IModel;

import com.adagio.language.figures.Figure;
import com.adagio.language.musicnotes.MusicNote;

public class MelodyBarComponent implements IModel {
	MusicNote note;
	Figure figure;
	
	public MelodyBarComponent(){}
	
	public MelodyBarComponent(MusicNote note, Figure figure){
		this.note = note;
		this.figure = figure;
	}
	
	
	public MusicNote getNote() {
		return note;
	}
	public Figure getFigure() {
		return figure;
	}
	
	@Override
	public String toString(){
		return note.toString() + figure.toString();
	}
	
}
