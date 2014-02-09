package com.adagio.language.bars;

import com.adagio.language.figures.Figure;

public class BarItemDuration {
	private Figure figure;
	
	public BarItemDuration(){
		figure = new Figure();
	}
	
	public BarItemDuration(Figure figure){
		this.figure = figure;
	}

	public Figure getFigure() {
		return figure;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}
	
}
