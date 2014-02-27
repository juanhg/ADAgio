package com.adagio.language.times;

import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.types.IntegerModel;

import com.adagio.language.figures.Figure;

public class Time implements IModel {
	IntegerModel beats;
	@Prefix("/")
	Figure figure;
	
	public Time(){}
	
	public Time(IntegerModel beats, Figure figure){
		this.beats = beats;
		this.figure = figure.clone();
	}
	
	public Time(int beats, int figureShape){
		this.beats = new IntegerModel(beats);
		this.figure = new Figure(figureShape, 0);
	}
	
	public double duration(){
		return figure.duration()*beats.intValue();
	}
		
	public IntegerModel getBeats() {
		return beats;
	}

	public void setBeats(IntegerModel beats) {
		this.beats = beats;
	}

	public Figure getFigure() {
		return figure;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}
	
	@Override
	public String toString(){
		return this.beats.toString() + "/" + figure.toString();
	}
}
