package com.adagio.duration;

import com.adagio.language.figures.Figure;

public class Duration {
	private Figure figure;
	
	public Duration(){
		figure = new Figure();
	}
	
	public Duration(Figure figure){
		this.figure = figure;
	}

	public Figure getFigure() {
		return figure;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}
	
	@Override
	public String toString(){
		return figure.toString();
	}
	
}
